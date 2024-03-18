package com.example.todolist.todo;

import com.example.todolist.member.Member;
import com.example.todolist.member.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final HttpSession httpSession;

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;
    public void save(String todoText) {
        Todo todo = Todo.builder().todoText(todoText).build();
        todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        Long id = (Long) httpSession.getAttribute("member");
        return todoRepository.findAllByMemberId(id);
    }

    public Member findMember(){
        Long id = (Long) httpSession.getAttribute("member");
        Optional<Member> m = memberRepository.findById(id);
        return m.orElse(null);
    }


    @Transactional
    public void delete(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(todo1 -> todoRepository.deleteById(todo1.getId()));
    }
}
