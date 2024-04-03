package com.example.todolist.member.dto;

import com.example.todolist.member.Member;
import lombok.Data;

@Data
public class MemberRequestDto {

    private String memberId;
    private String memberPw;
    private String memberName;

    public Member toEntity(String memberId, String memberPw, String memberName){
        return Member.builder()
                .memberId(memberId)
                .memberPw(memberPw)
                .memberName(memberName)
                .build();
    }

    public static class MemberJoinDto extends MemberRequestDto{

    }

    @Data
    public static class MemberLoginDto{
        private String memberId;
        private String memberPw;
    }

    public static class MemberFindDto extends MemberRequestDto{

    }

    @Data
    public static class MemberUpdateDto{
        private String memberPw;
        private String memberName;
    }

}
