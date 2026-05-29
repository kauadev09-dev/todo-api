package com.example.todo_api.service;

import com.example.todo_api.dto.TaskRequestDTO;
import com.example.todo_api.dto.TaskResponseDTO;
import com.example.todo_api.exception.TaskNotFoundException;
import com.example.todo_api.model.Task;
import com.example.todo_api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    private Task toEntity(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());
        return task;
    }

    private TaskResponseDTO toDTO(Task task) {
        return new TaskResponseDTO(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.isCompleted(),
            task.getCreatedAt()
        );
    }

    public Page<TaskResponseDTO> findAll(Pageable pageable) {
        Page<Task> tasks = repository.findAll(pageable);
        return tasks.map(task -> toDTO(task));
    }

    public TaskResponseDTO findById(Long id) {
        return toDTO(repository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id)));
    }

    public TaskResponseDTO create(TaskRequestDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public TaskResponseDTO update(Long id, TaskRequestDTO dto) {
        Task existing = repository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setCompleted(dto.isCompleted());
        return toDTO(repository.save(existing));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}