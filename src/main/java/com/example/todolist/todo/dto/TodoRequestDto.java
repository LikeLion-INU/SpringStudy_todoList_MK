package com.example.todolist.todo.dto;

import com.example.todolist.member.Member;
import com.example.todolist.todo.domain.Todo;
import com.example.todolist.todo.domain.TodoState;
import lombok.Data;

@Data
public class TodoRequestDto {
    private Long id;

    private String todoText; //할 일

    private TodoState isComplete; //완료했는지

    @Data
    public static class TodoAddDto{
        private String todoText;
        private Member member;

        public Todo toEntity(String todoText, Member member){
            return Todo.builder()
                    .todoText(todoText)
                    .member(member)
                    .build();
        }
    }

    public static class TodoUpdateDto extends TodoRequestDto{
    }

}
