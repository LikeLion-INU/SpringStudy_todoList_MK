package com.example.todolist.todo.dto;

import com.example.todolist.member.Member;
import com.example.todolist.todo.domain.Todo;
import com.example.todolist.todo.domain.TodoState;
import lombok.Data;

import java.util.List;

@Data
public class TodoResponseDto {
    private Long id;
    private String todoText;
    private TodoState isComplete;

    public TodoResponseDto(Todo todo){
        this.id = todo.getId();
        this.todoText = todo.getTodoText();
        this.isComplete = todo.getIsComplete();
    }

    /*
    반환 처리 모두 이렇게
     */
    @Data
    public static class TodoListDto{
        private Member member;
        private List<TodoResponseDto> todos;

        public TodoListDto(Member m, List<TodoResponseDto> todos){
            this.member = m;
            this.todos = todos;
        }

    }


}
