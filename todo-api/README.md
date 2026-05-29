# Todo API — REST com Spring Boot

API REST completa para gerenciamento de tarefas, construída com Java e Spring Boot. Projeto ideal para aprender os fundamentos de desenvolvimento backend e para apresentar em entrevistas técnicas.

---

## Tecnologias

- Java 17+
- Spring Boot 3
- Spring Data JPA
- H2 Database (banco em memória)
- Lombok
- Maven

---

## O que o projeto faz

- Criar, listar, buscar, atualizar e deletar tarefas (CRUD completo)
- Retornar respostas paginadas
- Validar dados de entrada
- Tratar erros com mensagens organizadas em JSON

---

## Estrutura de pastas

```
src/main/java/com/example/todo_api/
├── TodoApiApplication.java       ← entry point
├── controller/
│   └── TaskController.java       ← endpoints HTTP
├── service/
│   └── TaskService.java          ← regras de negócio
├── repository/
│   └── TaskRepository.java       ← acesso ao banco
├── model/
│   └── Task.java                 ← entidade do banco
├── dto/
│   ├── TaskRequestDTO.java       ← o que o cliente manda
│   └── TaskResponseDTO.java      ← o que a API devolve
└── exception/
    ├── TaskNotFoundException.java
    └── GlobalExceptionHandler.java
```

---

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /api/tasks | Lista todas as tarefas (paginado) |
| GET | /api/tasks/{id} | Busca uma tarefa pelo ID |
| POST | /api/tasks | Cria uma nova tarefa |
| PUT | /api/tasks/{id} | Atualiza uma tarefa |
| DELETE | /api/tasks/{id} | Remove uma tarefa |

---

## Como rodar

### Pré-requisitos

- Java 17 ou superior instalado
- Maven (o projeto já inclui o `mvnw`)

### Rodando pelo terminal

```bash
.\mvnw.cmd spring-boot:run
```

### Rodando pelo IntelliJ IDEA

1. Abre o arquivo `TodoApiApplication.java`
2. Clica no triângulo verde ▶ na linha do `main`
3. Seleciona **Run 'TodoApiApplication'**

A aplicação sobe em `http://localhost:8080`

---

## Console do banco H2

Com a aplicação rodando, acesse no navegador:

```
http://localhost:8080/h2-console
```

Configurações de conexão:
- JDBC URL: `jdbc:h2:mem:tododb`
- User: `sa`
- Password: (deixa vazio)

---

## Exemplos de uso

### Criar uma tarefa

**POST** `http://localhost:8080/api/tasks`

```json
{
  "title": "Estudar Spring Boot",
  "description": "Fazer o projeto To-Do API"
}
```

Resposta (`201 Created`):

```json
{
  "id": 1,
  "title": "Estudar Spring Boot",
  "description": "Fazer o projeto To-Do API",
  "completed": false,
  "createdAt": "2026-05-28T03:16:11"
}
```

### Listar tarefas com paginação

**GET** `http://localhost:8080/api/tasks?page=0&size=10&sort=createdAt,desc`

```json
{
  "content": [...],
  "totalElements": 1,
  "totalPages": 1,
  "number": 0,
  "size": 10
}
```

### Marcar como concluída

**PUT** `http://localhost:8080/api/tasks/1`

```json
{
  "title": "Estudar Spring Boot",
  "completed": true
}
```

### Deletar

**DELETE** `http://localhost:8080/api/tasks/1`

Resposta: `204 No Content`

### Buscar ID inexistente

**GET** `http://localhost:8080/api/tasks/999`

```json
{
  "erro": "Tarefa não encontrada: 999"
}
```

---

## Conceitos aprendidos

### Arquitetura em camadas

```
Cliente → Controller → Service → Repository → Banco
```

- **Controller** — recebe a requisição HTTP e devolve a resposta
- **Service** — contém as regras de negócio
- **Repository** — faz as consultas no banco via JPA

### DTOs (Data Transfer Objects)

Separa o que o banco conhece (`Task`) do que o cliente vê (`TaskResponseDTO`). Isso protege campos internos e dá controle total sobre a resposta da API.

### Tratamento de erros global

`@ControllerAdvice` captura exceções de todos os controllers e devolve mensagens de erro organizadas em JSON, sem expor stack traces.

### Paginação

O Spring Data JPA injeta `Pageable` automaticamente via parâmetros de URL (`?page=0&size=10`), sem precisar escrever SQL manual.

---

## Próximos passos sugeridos

- Trocar H2 por PostgreSQL para persistência real
- Adicionar autenticação com Spring Security e JWT
- Escrever testes automatizados com JUnit e Mockito
- Fazer deploy na nuvem via Railway ou Render

---

## Como recriar esse projeto do zero

1. Acesse [start.spring.io](https://start.spring.io)
2. Configure: Maven, Java 17+, Group `com.example`, Artifact `todo-api`
3. Adicione as dependências: Spring Web, Spring Data JPA, H2 Database, Lombok, Validation
4. Clique em **Generate** e extraia o zip
5. Crie as pastas `model`, `repository`, `service`, `controller`, `dto`, `exception`
6. Implemente as classes nessa ordem: `Task` → `TaskRepository` → `TaskService` → `TaskController`
7. Configure o `application.properties` com as propriedades do H2
8. Adicione os DTOs e o `GlobalExceptionHandler`
9. Adicione paginação trocando `List` por `Page<T>` e adicionando `Pageable` como parâmetro
