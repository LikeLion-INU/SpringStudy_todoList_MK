package com.example.todolist.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

    @Builder
    public Member(Long id, @NonNull String memberId, @NonNull String memberPw, String memberName) {
        this.id = id;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
    }
}
