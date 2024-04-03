package com.example.todolist.todo.service;

import com.example.todolist.todo.dto.TodoRequestDto;
import com.example.todolist.todo.dto.TodoResponseDto;

public interface TodoService {
    /*
    투두 추가
     */
    TodoResponseDto add(TodoRequestDto.TodoAddDto todoAddDto);
    /*
    투두 전체 조회
     */
    TodoResponseDto.TodoListDto findAll();

    String delete(Long id);
    TodoResponseDto update(TodoRequestDto.TodoUpdateDto todoUpdateDto, Long id);

}
