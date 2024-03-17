package com.example.todolist.todo;

import com.example.todolist.exception.ErrorCode;
import com.example.todolist.todo.exception.TodoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    public void save(String todoText) {
        Todo todo = Todo.builder().todoText(todoText).build();
        todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }


    @Transactional
    public void delete(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new TodoException(ErrorCode.TODO_NOT_FOUND));
        todoRepository.delete(todo);
    }
}
