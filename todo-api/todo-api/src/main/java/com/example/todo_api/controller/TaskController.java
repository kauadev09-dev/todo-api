package com.example.todo_api.controller;

import com.example.todo_api.dto.TaskRequestDTO;
import com.example.todo_api.dto.TaskResponseDTO;
import com.example.todo_api.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
public Page<TaskResponseDTO> getAll(Pageable pageable) {
    return service.findAll(pageable);
}

    @GetMapping("/{id}")
    public TaskResponseDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO create(@Valid @RequestBody TaskRequestDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO update(@PathVariable Long id,
                                  @Valid @RequestBody TaskRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}