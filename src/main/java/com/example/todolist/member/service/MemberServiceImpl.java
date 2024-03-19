package com.example.todolist.member.service;

import com.example.todolist.erros.errorcode.MemberErrorCode;
import com.example.todolist.erros.exception.MemberException;
import com.example.todolist.member.Member;
import com.example.todolist.member.dto.MemberRequestDto;
import com.example.todolist.member.MemberRepository;
import com.example.todolist.member.dto.MemberResponseDto;
import com.example.todolist.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    /*
    회원가입
    1. 중복 아이디가 있을 경우 에러처리
    2. 중복 아이디가 없을 경우 객체 생성 및 레포지토리 저장
     */
    @Override
    public MemberResponseDto.MemberJoinDto join(MemberRequestDto.MemberJoinDto member) {
        if (memberRepository.findByMemberId(member.getMemberId()).isPresent()) {
            log.error("아이디 중복 에러");
            throw new MemberException(MemberErrorCode.MEMBER_VALIDATION);
        }
        else {
            Member m = member.toEntity(member.getMemberId(), member.getMemberPw(), member.getMemberName());
            memberRepository.save(m);
            return new MemberResponseDto.MemberJoinDto(m);
            //member 객체 생성 후 저장
        }
    }

    /*
    로그인
    1. 아이디 존재 여부 확인
    2. 아아디, 비밀번호의 일치 여부 확인
    3. 오류가 없을 경우 로그인 성공 -> 세션에 아이디 정보 저장
     */
    @Override
    public MemberResponseDto.MemberLoginDto login(MemberRequestDto.MemberLoginDto member) {
        Optional<Member> m = memberRepository.findByMemberId(member.getMemberId());
        if (m.isPresent()){
            if (!m.get().getMemberPw().equals(member.getMemberPw())){
                log.error("아이디와 비밀번호가 일치하지 않습니다.");
            } else{
                httpSession.setAttribute("member", m.get().getId());
                return new MemberResponseDto.MemberLoginDto(m.get());
            }
        } else {
            log.error("해당되는 아이디가 없습니다.");
        }
        return null;
    }

    /*
    프로필 확인
     */

    @Override
    public MemberResponseDto.MemberFindDto profile() {
        Long id = (Long) httpSession.getAttribute("member");
        if (id != null){
            Optional<Member> m = memberRepository.findById(id);
            if (m.isPresent())
                return new MemberResponseDto.MemberFindDto(m.get());
        }
        return null;
    }

    /*
    프로필 업데이트
     */

    @Override
    @Transactional
    public MemberResponseDto.MemberUpdateDto update(MemberRequestDto.MemberUpdateDto member, Long id) {
        Optional<Member> m = memberRepository.findById(id);
        if (m.isPresent()){
            m.get().setMemberPw(member.getMemberPw());
            m.get().setMemberName(member.getMemberName());
            memberRepository.save(m.get());
            return new MemberResponseDto.MemberUpdateDto(m.get());
        }
        return null;
    }

    /*
    회원 삭제
     */

    @Override
    @Transactional
    public void delete(Long id) {
        httpSession.removeAttribute("member");
        memberRepository.deleteById(id);
    }
}
