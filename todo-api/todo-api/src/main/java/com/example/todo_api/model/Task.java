package com.example.todo_api.model;

// Importa a classe para trabalhar com data e hora
import java.time.LocalDateTime;

// Importa anotação do Hibernate para gerar automaticamente
// a data de criação do registro
import org.hibernate.annotations.CreationTimestamp;

// Importações do JPA (Java Persistence API)
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Importa validação para impedir campo vazio
import jakarta.validation.constraints.NotBlank;

// Importações do Lombok
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// Indica que esta classe representa uma tabela no banco de dados
@Entity

// Lombok:
// Gera automaticamente:
// getters
// setters
// toString()
// equals()
// hashCode()
@Data

// Lombok:
// Gera construtor vazio
@NoArgsConstructor

// Lombok:
// Gera construtor com TODOS os atributos
@AllArgsConstructor

// Classe que representa uma tarefa
public class Task {

    // Define este campo como chave primária da tabela
    @Id

    // Faz o banco gerar o ID automaticamente
    // Exemplo: 1, 2, 3, 4...
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Validação:
    // O título não pode ser vazio ou nulo
    // Caso esteja vazio, retorna a mensagem abaixo
    @NotBlank(message = "Título é obrigatório")
    private String title;


    // Descrição da tarefa
    private String description;


    // Define se a tarefa foi concluída
    // Toda tarefa começa como false
    private boolean completed = false;


    // Gera automaticamente a data e hora
    // quando a tarefa for criada
    @CreationTimestamp
    private LocalDateTime createdAt;

}