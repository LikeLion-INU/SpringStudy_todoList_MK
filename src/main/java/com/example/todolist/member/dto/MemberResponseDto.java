package com.example.todolist.member.dto;

import com.example.todolist.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class MemberResponseDto {
    private String memberId;
    private String memberPw;
    private String memberName;

    public static class MemberJoinDto extends MemberResponseDto{

        public MemberJoinDto(Member m) {
            super(m);
        }
    }


    @Data
    public static class MemberLoginDto {
        private String memberName;

        public MemberLoginDto(Member m) {
            this.memberName = m.getMemberName();
        }
    }

    @Getter
    public static class MemberFindDto extends MemberResponseDto{
        private final Long id;

        public MemberFindDto(Member m) {
            super(m);
            this.id = m.getId();
        }
    }

    @Data
    public static class MemberUpdateDto{
        private String memberPw;
        private String memberName;

        public MemberUpdateDto(Member m){
            this.memberPw = m.getMemberPw();
            this.memberName = m.getMemberName();
        }
    }

    public MemberResponseDto(Member m){
        this.memberId = m.getMemberId();
        this.memberPw = m.getMemberPw();
        this.memberName = m.getMemberName();
    }
}
