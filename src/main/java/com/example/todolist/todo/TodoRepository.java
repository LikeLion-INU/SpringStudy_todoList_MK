package com.example.todolist.todo;

import com.example.todolist.todo.domain.Todo;
import com.example.todolist.todo.dto.TodoResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByMemberId(Long id);
}
