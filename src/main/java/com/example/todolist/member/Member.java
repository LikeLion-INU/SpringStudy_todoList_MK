package com.example.todolist.member;

import com.example.todolist.todo.domain.Todo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

    @ToString.Exclude
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    //fetch - LAZY 요청하는 순간 가져오기 - EAGER 미리 읽어오기
    private List<Todo> todoList = new ArrayList<Todo>();


    /*
    todo와 member 관계를 양방향으로 맺어줌
     */
    public void addTodo(Todo todo){
        todoList.add(todo);
        todo.setMember(this);
    }

    @Builder
    public Member(Long id, @NonNull String memberId, @NonNull String memberPw, String memberName) {
        this.id = id;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
    }
}
