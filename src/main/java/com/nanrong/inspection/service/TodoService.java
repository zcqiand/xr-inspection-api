package com.nanrong.inspection.service;

import org.springframework.stereotype.Service;
import com.nanrong.inspection.dto.todo.TodoResponseDto;
import com.nanrong.inspection.model.Todo;
import com.nanrong.inspection.repository.TodoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponseDto> findAll() {
        return todoRepository.findAll().stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<TodoResponseDto> findById(Long id) {
        return todoRepository.findById(id).map(this::convertToDto);
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public TodoResponseDto convertToDto(Todo todo) {
        return new TodoResponseDto(todo.getId(), todo.getDescription(), todo.isCompleted(),
                todo.getCreationDate());
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo convertToEntity(TodoResponseDto todoDto) {
        Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        todo.setCreationDate(todoDto.getCreationDate());
        return todo;
    }
}
