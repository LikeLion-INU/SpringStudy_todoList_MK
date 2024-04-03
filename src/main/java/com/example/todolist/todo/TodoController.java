package com.example.todolist.todo;

import com.example.todolist.member.service.MemberServiceImpl;
import com.example.todolist.todo.dto.TodoRequestDto;
import com.example.todolist.todo.dto.TodoResponseDto;
import com.example.todolist.todo.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoServiceImpl todoServiceImpl;
    private final MemberServiceImpl memberService;

    /*
    투두리스트 화면
    - 항상 유저 이름과 같이 반환하기
     */
    @GetMapping("/list")
    public ResponseEntity<?> index(){
        return ResponseEntity.ok()
                .body(todoServiceImpl.findAll());
    }

    /*
    투두 추가
     */
    @PostMapping("/add")
    public ResponseEntity<?> addTodo(@RequestBody TodoRequestDto.TodoAddDto todo){
        return ResponseEntity.ok()
                .body(todoServiceImpl.add(todo));
    }

    /*
    투두 수정
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTodo(@RequestBody TodoRequestDto.TodoUpdateDto todo,
        @PathVariable("id") Long id){
        return ResponseEntity.ok()
                .body(todoServiceImpl.update(todo, id));
    }

    /*
    투두 삭제
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id){
        return ResponseEntity.ok()
                .body(todoServiceImpl.delete(id));
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<?> completeTodo(@PathVariable("id") Long id){
        return ResponseEntity.ok()
                .body(todoServiceImpl.complete(id));
    }

}
