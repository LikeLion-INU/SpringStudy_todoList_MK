package com.example.todolist.member;

import com.example.todolist.member.dto.MemberRequestDto;
import com.example.todolist.member.dto.MemberResponseDto;
import com.example.todolist.member.service.MemberServiceImpl;
import com.example.todolist.todo.service.TodoServiceImpl;
import com.example.todolist.todo.dto.TodoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
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
    public String MemberJoin(@ModelAttribute MemberRequestDto.MemberJoinDto member, Model model){
        System.out.println(member.getMemberId());
        MemberResponseDto.MemberJoinDto m = memberService.join(member);
        if (m != null) {
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "이미 존재하는 아이디입니다.");
            return "join";
        }
    }

    /*
    로그인
    - 로그인 성공 시 이름 띄우기
    - 로그인 실패 시 에러 띄우기
     */
    @PostMapping("/login")
    public String MemberLogin(@ModelAttribute MemberRequestDto.MemberLoginDto member, Model model){
         MemberResponseDto.MemberLoginDto name = memberService.login(member);
         if (name.getMemberName() != null) {
             TodoResponseDto.TodoListDto todos = todoServiceImpl.findAll();
             model.addAttribute("memberName", name.getMemberName());
             model.addAttribute("todo", todos.getTodos());
         }
        return "redirect:/todo/list";
    }

    /*
    프로필 확인
    - 확인이 안될 경우 세션 만료로 로그인 다시 실행
     */
    @GetMapping("/profile")
    public String MemberProfile(Model model){
        MemberResponseDto.MemberFindDto m = memberService.profile();
        if (m!=null){
            model.addAttribute("member", m);
        }
        return "profile";
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
    public String ProfileUpdate(@ModelAttribute MemberRequestDto.MemberUpdateDto member, @PathVariable("id") Long id){
        memberService.update(member,id);
        return "redirect:/member/profile";
    }

    /*
    회원 탈퇴
    - 회원 탈퇴를 누르면 확인 메세지 한번 더
     */
    @DeleteMapping("/delete/{id}")
    public String ProfileDelete(@PathVariable("id") Long id){
        log.info("회원 삭제");
        memberService.delete(id);
        return "redirect:/";
    }






}
