package com.example.todolist.member;

import com.example.todolist.member.dto.MemberRequestDto;
import com.example.todolist.member.dto.MemberResponseDto;
import com.example.todolist.member.service.MemberServiceImpl;
import com.example.todolist.todo.service.TodoServiceImpl;
import com.example.todolist.todo.dto.TodoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberService;
    private final MemberRepository memberRepository;
    private final TodoServiceImpl todoServiceImpl;

    /*
    회원가입
    - 회원가입 설정 후 아이디가 존재하는 오류라면 다시 회원가입 페이지로 돌아가기 (에러 메세지 띄우기)
    - 회원가입 성공 시 Home 화면으로 이동
     */
    @PostMapping("/join")
    public ResponseEntity<?> MemberJoin(@RequestBody MemberRequestDto.MemberJoinDto member, Model model){
        System.out.println(member.getMemberId());
        return ResponseEntity.ok()
                .body(memberService.join(member));
    }

    /*
    로그인
    - 로그인 성공 시 이름 띄우기
    - 로그인 실패 시 에러 띄우기
     */
    @PostMapping("/login")
    public ResponseEntity<?> MemberLogin(@RequestBody MemberRequestDto.MemberLoginDto member, Model model){
         return ResponseEntity.ok()
                 .body(memberService.login(member));
    }

    /*
    프로필 확인
    - 확인이 안될 경우 세션 만료로 로그인 다시 실행
     */
    @GetMapping("/profile")
    public ResponseEntity<?> MemberProfile(){
        return ResponseEntity.ok()
                .body(memberService.profile());
    }

    /*
    프로필 수정 화면으로 넘어가기
    - 회원을 찾을 수 없다면 세션 만료로 로그인으로 넘어감
     */
    @GetMapping("/edit/{id}")
    public String ProfileEdit(@PathVariable("id") Long id, Model model){
        Optional<Member> m = memberRepository.findById(id);
        m.ifPresent(member -> model.addAttribute("member", member));
        return "updateProfile";
    }

    /*
    프로필 업데이트
    - 수정사항 업데이트
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> ProfileUpdate(@RequestBody MemberRequestDto.MemberUpdateDto member, @PathVariable("id") Long id){
        return ResponseEntity.ok()
                .body(memberService.update(member,id));
    }

    /*
    회원 탈퇴
    - 회원 탈퇴를 누르면 확인 메세지 한번 더
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> ProfileDelete(@PathVariable("id") Long id){
        log.info("회원 삭제");
        return ResponseEntity.ok()
                .body(memberService.delete(id));
    }






}
