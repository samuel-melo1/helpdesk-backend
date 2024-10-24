# HelpDesk - Backend

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

Esse é um projeto construído em  **Java com Spring Boot, Spring Data Jpa, Spring Security e JWT, H2 Database.** 

Projeto de um sistema de Central de Chamados. Sendo resposável por gerenciar os chamados entre os Técnicos e os Clientes.Sendo possível realizar as operaçãos de criar, deletar, atualizar e listar os Chamados, os Clientes e os Técnicos.
## Table of Contents

- [Instalação](#instalação)
- [Usabilidade](#usabilidade)
- [API Endpoints](#api-endpoints)
- [Contribuição](#contribuição)

## Instalação

1. Clone o repositório:

```bash
git clone https://github.com/samuel-melo1/helpdesk-backend
```

## Usabilidade
 
1. Inicie a aplicação com o Maven
2. A API estará disponível em http://localhost:8080
3. O H2 Database estará disponível em: http://localhost:8080/h2-console

## API Endpoints
A API possui os seguintes endpoints:

```markdown

POST /chamados - Realizar a criação de um chamado.

GET /chamados/{id} - Buscar um chamado cadastrado na base de dados. 

GET /chamados - Buscar todos os chamados cadastrados na base de dados. 

PUT /chamados/{id} - Atualizar um chamado cadastrado na base de dados.
 
POST /clientes - Realizar a criação de um Cliente.

GET /clientes - Buscar todos os clientes cadastrados na base de dados

GET /clientes/{id} - Buscar um cliente cadastrado na base de dados. 

PUT /clientes/{id} - Atualizar um cliente cadastrado na base de dados.

DELETE /clientes/{id} - Deletar um cliente cadastrado na base de dados.

POST /tecnicos - Realizar a criação de um Técnico.

GET /tecnicos - Buscar todos os técnicos cadastrados na base de dados

GET /tecnicos/{id} - Buscar um técnico cadastrado na base de dados. 

PUT /tecnicos/{id} - Atualizar um técnico cadastrado na base de dados.

DELETE /tecnicos/{id} - Deletar um técnico cadastrado na base de dados.

```

## Contribuição

Sugestões e/ou contribuições são bem-vindas! Se você encontrar qualquer questão ou tenha sugestões de melhorias, por favor, abra uma solicitação pull para o repositório. 


Ao contribuir para este projeto, siga o estilo de código existente, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), e envie suas alterações em uma ramificação separada.
