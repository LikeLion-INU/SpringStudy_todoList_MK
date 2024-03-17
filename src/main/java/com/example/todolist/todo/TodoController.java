package com.example.todolist.todo;

import com.example.todolist.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/")
    public String index(Model model){
        List<Todo> todo = todoService.findAll();
        model.addAttribute("todos", todo);
        return "todolist";
    }

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam String todoText){
        todoService.save(todoText);
        return "redirect:/";
    }

    @DeleteMapping("/deleteTodo/{id}")
    public String deleteTodo(@PathVariable("id") Long id){
        todoService.delete(id);
        return "redirect:/";
    }


}
