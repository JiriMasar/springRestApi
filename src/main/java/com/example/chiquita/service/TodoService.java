package com.example.chiquita.service;

import com.example.chiquita.entities.TodoEntity;
import com.example.chiquita.repositories.TodoRepository;
import com.example.chiquita.requests.CreateTodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    private final UserService userService;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserService userService) {
        this.todoRepository = todoRepository;
        this.userService = userService;
    }

    public TodoEntity create(CreateTodoRequest createTodoRequest, UUID userId) throws Exception {
        var user = userService.getById(userId);
        var todo = new TodoEntity();
        todo.setLabel(createTodoRequest.label());
        todo.setUser(user);

        return todoRepository.save(todo);
    }
}
