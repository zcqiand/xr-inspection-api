package com.nanrong.inspection.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nanrong.inspection.dto.todo.TodoCreateDto;
import com.nanrong.inspection.dto.todo.TodoResponseDto;
import com.nanrong.inspection.dto.todo.TodoUpdateDto;
import com.nanrong.inspection.model.Todo;
import com.nanrong.inspection.service.TodoService;
import java.time.LocalDateTime;
import java.util.List;

/**
 * REST 控制器，用于处理待办事项的 API 请求。 定义了处理 GET、POST、PUT、DELETE 操作的端点。
 */
@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    /**
     * 构造函数，通过依赖注入获取 TodoService 实例。
     * 
     * @param todoService 待办事项服务
     */
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * 检索所有待办事项。 GET /todos
     * 
     * @return 包含所有待办事项的列表
     */
    @GetMapping
    public List<TodoResponseDto> getAllTodos() {
        return todoService.findAll();
    }

    /**
     * 通过 ID 检索特定的待办事项。 GET /todos/{id}
     * 
     * @param id 待办事项的唯一标识符
     * @return 包含待办事项的 ResponseEntity，如果未找到则返回 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long id) {
        return todoService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 创建新的待办事项。 POST /todos
     * 
     * @param todo 要创建的待办事项对象，从请求体中映射
     * @return 包含新创建待办事项的 ResponseEntity，状态码为 201 Created
     */
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(
            @Valid @RequestBody TodoCreateDto todoCreateDto) {
        Todo todo = new Todo();
        todo.setDescription(todoCreateDto.getDescription());
        todo.setCompleted(todoCreateDto.getIsCompleted());
        todo.setCreationDate(LocalDateTime.now());
        Todo savedTodo = todoService.save(todo);
        return new ResponseEntity<>(todoService.convertToDto(savedTodo), HttpStatus.CREATED);
    }

    /**
     * 更新现有的待办事项。 PUT /todos/{id}
     * 
     * @param id 待办事项的唯一标识符
     * @param todoDetails 包含更新信息的待办事项对象，从请求体中映射
     * @return 包含更新后待办事项的 ResponseEntity，如果未找到则返回 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id,
            @Valid @RequestBody TodoUpdateDto todoUpdateDto) {
        return todoService.findById(id).map(existingTodoDto -> {
            Todo existingTodo = todoService.convertToEntity(existingTodoDto);
            existingTodo.setDescription(todoUpdateDto.getDescription());
            existingTodo.setCompleted(todoUpdateDto.getIsCompleted());
            Todo updatedTodo = todoService.save(existingTodo);
            return ResponseEntity.ok(todoService.convertToDto(updatedTodo));
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * 删除待办事项。 DELETE /todos/{id}
     * 
     * @param id 要删除的待办事项的唯一标识符
     * @return 无内容的 ResponseEntity，状态码为 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (todoService.findById(id).isPresent()) {
            todoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
