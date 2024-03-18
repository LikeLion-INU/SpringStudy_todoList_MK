package com.example.todolist.todo;

import com.example.todolist.member.Service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final MemberServiceImpl memberService;

    @GetMapping("/todolist")
    public String index(Model model){
        List<Todo> todo = todoService.findAll();
        model.addAttribute("todos", todo);
        model.addAttribute("memberName", todoService.findMember().getMemberName());
        return "todolist";
    }

    @PostMapping("/addTodo")
    public String addTodo(@ModelAttribute String todoText){
        todoService.save(todoText);
        return "redirect:/todolist";
    }

    @DeleteMapping("/deleteTodo/{id}")
    public String deleteTodo(@PathVariable("id") Long id){
        todoService.delete(id);
        return "redirect:/todolist";
    }


}
