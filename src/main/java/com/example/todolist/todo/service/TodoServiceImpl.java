package com.example.todolist.todo.service;

import com.example.todolist.erros.errorcode.ErrorCode;
import com.example.todolist.erros.errorcode.MemberErrorCode;
import com.example.todolist.erros.errorcode.TodoErrorCode;
import com.example.todolist.erros.exception.MemberException;
import com.example.todolist.erros.exception.TodoException;
import com.example.todolist.member.Member;
import com.example.todolist.member.MemberRepository;
import com.example.todolist.todo.TodoRepository;
import com.example.todolist.todo.domain.Todo;
import com.example.todolist.todo.domain.TodoState;
import com.example.todolist.todo.dto.TodoRequestDto;
import com.example.todolist.todo.dto.TodoResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService{
    private final HttpSession httpSession;

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    /*
    사용자 객체 반환 함수
     */
    public Member findMember(){
        log.info("접속 회원 조회");
        Long id = (Long) httpSession.getAttribute("member");
        Optional<Member> m = memberRepository.findById(id);
        return m.orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }


    /*
    투두 추가
     */
    @Override
    public void add(TodoRequestDto.TodoAddDto todoAddDto) {
        Member m = findMember(); //현재 사용자 찾기
        Member member = memberRepository.findById(m.getId())
                .orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)); //사용자 객체 찾기

        Todo todo = Todo.builder()
                .todoText(todoAddDto.getTodoText())
                .isComplete(TodoState.NOT_FINISH)
                .member(member)
                .build();
        //투두 내용과 사용자 정보 저장
        todoRepository.save(todo);
        m.addTodo(todo);
        log.info("투두 저장 완료");

    }

    /*
    모든 투두 반환
     */
    @Override
    public TodoResponseDto.TodoListDto findAll() {
        Member m = findMember();
        List<Todo> todos = todoRepository.findAllByMemberId(m.getId());
        List<TodoResponseDto> todoResponseDtos = new ArrayList<>();
        for (Todo t : todos){
            todoResponseDtos.add(new TodoResponseDto(t));
        }
        log.info("투두리스트 반환");
        return new TodoResponseDto.TodoListDto(m,todoResponseDtos);
    }

    /*
    투두 삭제
     */
    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(todo1 -> todoRepository.deleteById(todo1.getId()));
        log.info("투두 삭제 완료");
    }

    /*
    투두 수정
     */
    @Override
    @Transactional
    public void update(TodoRequestDto.TodoUpdateDto todoUpdateDto) {
        Todo todo = todoRepository.findById(todoUpdateDto.getId())
                .orElseThrow(() -> new TodoException(TodoErrorCode.TODO_NOT_FOUND));

        todo.setTodoText(todoUpdateDto.getTodoText());
        todo.setIsComplete(todoUpdateDto.getIsComplete());

        todoRepository.save(todo);
        log.info("투두 업데이트 완료");
    }

    @Transactional
    public void complete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoException(TodoErrorCode.TODO_NOT_FOUND));
        if (todo.getIsComplete().equals(TodoState.FINISH)){
            todo.setIsComplete(TodoState.NOT_FINISH);
        } else {
            todo.setIsComplete(TodoState.FINISH);
        }
        log.info("투두 완료도 설정");
    }
}
