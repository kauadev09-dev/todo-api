package com.example.todo_api.repository;

import com.example.todo_api.dto.TaskResponseDTO;
import com.example.todo_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.todo_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Importa a anotação Repository do Spring
import org.springframework.stereotype.Repository;

// Importa o JpaRepository do Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Importa a interface List
import java.util.List;


// Indica que esta interface é um repositório
// responsável por acessar o banco de dados
@Repository

// Interface responsável pelas operações do banco
// relacionadas à entidade Task
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCompleted(boolean completed);

    List<Task> findByTitleContaining(String keyword);
}