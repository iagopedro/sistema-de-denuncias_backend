# Sistema de Denúncias Backend

Este projeto é uma API REST desenvolvida em Spring Boot para gerenciar denúncias, com autenticação via JWT. Ele utiliza o banco de dados H2 em memória para facilitar o desenvolvimento e conta com documentação da API via Swagger.

## Funcionalidades

- **Autenticação JWT:** Endpoints protegidos que exigem um token válido para acesso.
- **Cadastro e Listagem de Usuários:** Permite a criação e consulta de usuários.
- **Seed de Dados:** Utiliza `CommandLineRunner` para inserir automaticamente usuários no banco de dados se nenhum usuário existir.
- **H2 Console:** Interface web para visualização do banco de dados (acessível via `/h2-console`).
- **Swagger UI:** Documentação interativa da API (acessível via `/swagger-ui/`).

## Pré-requisitos

- Java 17 (ou superior)
- Maven

## Como Executar

1. **Clone o repositório:**

   ````bash
   git clone <URL_DO_REPOSITORIO>
   ````

2. **Navegue até o diretório do projeto:**

   ````bash
   cd sistema-de-denuncias-backend
   ````

3. **Compile o projeto e execute os testes:**

   ````bash
   mvn clean install
   ````

4. **Inicie a aplicação:**

   ````bash
   mvn spring-boot:run
   ````

   *Ou execute a classe principal `ApiPdswApplication.java` diretamente pela sua IDE.*

## Configurações Importantes

- **Banco de Dados H2**
  - **URL:** `jdbc:h2:mem:testdb`
  - **Console:** Acesse [http://localhost:8080/h2-console](http://localhost:8080/h2-console) no seu navegador.
  - *Observação:* Se o H2 Console não abrir em um iframe, verifique a configuração de headers em `SecurityConfig.java`.

- **Endpoints Relacionados à Autenticação e Usuários**
  - **Login:** `POST http://localhost:8080/login`
    - **Payload Exemplo:**
      ````json
      {
        "email": "alice.silva@example.com",
        "password": "senha123"
      }
      ````
  - **Cadastro de Usuário:** `POST http://localhost:8080/users`
  - **Listagem de Usuários:** `GET http://localhost:8080/users`

- **Documentação da API (Swagger)**
  - Acesse [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/) para visualizar a documentação interativa dos endpoints.

## Seed de Dados

No arranque, se a tabela de usuários estiver vazia, o `CommandLineRunner` insere automaticamente os seguintes registros:

- Alice Silva
- Bruno Costa
- Carla Souza
- Daniel Pereira
- Elaine Rocha
- Fernando Lima
- Gabriela Alves
- Henrique Martins
- Isabela Dias
- Julio Fernandes

## Tecnologias Utilizadas

- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA
- **Banco de Dados:** H2 (em memória)
- **Autenticação:** JWT (JSON Web Tokens)
- **Documentação da API:** Swagger

## Problemas Conhecidos

- **H2 Console e X-Frame-Options:**  
  Se o navegador reclamar que o H2 Console não pode ser exibido em um frame (erro de `X-Frame-Options`), verifique em `SecurityConfig.java` se a opção para desabilitar os headers de frame já está configurada:
  
  ````java
  .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
  ````
  
- **Endpoint /login 404:**  
  Certifique-se de que o endpoint `/login` está implementado e mapeado corretamente, retornando uma resposta JSON válida.

## Contribuições

Contribuições são bem-vindas! Caso deseje participar, por favor, abra uma issue ou envie um pull request.

## Licença

Este projeto está licenciado sob a MIT License.
