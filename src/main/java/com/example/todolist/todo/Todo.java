package com.example.todolist.todo;

import com.example.todolist.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String todoText; //할 일

    private boolean isComplete; //완료했는지

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Todo(String todoText) {
        this.todoText = todoText;
    }

}
