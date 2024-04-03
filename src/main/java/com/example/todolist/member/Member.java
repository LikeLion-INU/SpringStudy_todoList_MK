package com.example.todolist.member;

import com.example.todolist.todo.domain.Todo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String memberId;
    @NonNull
    private String memberPw;
    private String memberName;


    /*
    todo와 member 관계를 양방향으로 맺어줌
     */
    public void addTodo(Todo todo){
        todo.setMember(this);
    }

    @Builder
    public Member(Long id, @NonNull String memberId, @NonNull String memberPw, String memberName) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
    }

    public void toUpdate(String memberPw, String memberName) {
        this.memberPw = memberPw;
        this.memberName = memberName;
    }
}
