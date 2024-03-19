package com.example.todolist.todo.domain;

import com.example.todolist.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String todoText; //할 일

    @Enumerated(EnumType.STRING)
    @Column(name = "is_complete")
    private TodoState isComplete = TodoState.NOT_FINISH; //완료했는지

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;


    @Builder
    public Todo(String todoText, TodoState isComplete, Member member) {
        this.todoText = todoText;
        this.isComplete = isComplete;
        this.member = member;
    }

}
