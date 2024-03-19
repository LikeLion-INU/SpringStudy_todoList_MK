package com.example.todolist.todo;

import com.example.todolist.member.service.MemberServiceImpl;
import com.example.todolist.todo.dto.TodoRequestDto;
import com.example.todolist.todo.dto.TodoResponseDto;
import com.example.todolist.todo.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoServiceImpl todoServiceImpl;
    private final MemberServiceImpl memberService;

    /*
    투두리스트 화면
    - 항상 유저 이름과 같이 반환하기
     */
    @GetMapping("/list")
    public String index(Model model){
        TodoResponseDto.TodoListDto todo = todoServiceImpl.findAll();
        model.addAttribute("todos", todo.getTodos());
        model.addAttribute("memberName", todo.getMember().getMemberName());
        return "todolist";
    }

    /*
    투두 추가
     */
    @PostMapping("/add")
    public String addTodo(@ModelAttribute TodoRequestDto.TodoAddDto todo){
        todoServiceImpl.add(todo);
        return "redirect:/todo/list";
    }

    /*
    투두 수정
     */
    @PutMapping("/update/{id}")
    public String updateTodo(@ModelAttribute TodoRequestDto.TodoUpdateDto todo){
        todoServiceImpl.update(todo);
        return "redirect:/todo/list";
    }

    /*
    투두 삭제
     */
    @DeleteMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") Long id){
        todoServiceImpl.delete(id);
        return "redirect:/todo/list";
    }

}
