# Sistema de Controle de Locadora de Fitas VHS

Este é um projeto web desenvolvido em **Java com Spring Boot** para gerenciar fitas VHS.  
Permite cadastrar, visualizar, editar e excluir fitas e categorias, além de ter login e cadastro de usuários.

---

## Processo de desenvolvimento

O projeto foi feito seguindo a ideia de **camadas**:
- `Controller`: recebe as requisições do site.
- `Service`: faz a lógica.
- `Repository`: acessa o banco de dados.

O **Spring Boot** ajudou a organizar tudo, usando recursos como:
- **Inversão de Controle (IoC)**: para ligar as partes do sistema.
- **Injeção de Dependência (DI)**: para deixar o código mais simples de manter.
- **Spring Security**: para segurança.

A interface foi feita usando:
- **Thymeleaf**: para gerar páginas HTML dinâmicas no servidor.
- **Tailwind CSS**: para deixar o visual moderno e responsivo.

Para guardar os dados, usamos:
- **Spring Data JPA** + **Hibernate**: para trabalhar com banco de dados usando Java.
---

## Tecnologias utilizadas

- **Java 17**
- **Spring Boot**
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-thymeleaf`
  - `spring-boot-starter-validation`
  - `spring-boot-starter-security`
  - `spring-boot-devtools`
- **Hibernate** (ORM)
- **Lombok** (gera getters e setters automaticamente)
- **Tailwind CSS**
- **Maven** (gerenciador de dependências)

---

## Funcionalidades implementadas

✅ **Fitas VHS**
- Listar fitas cadastradas.
- Adicionar nova fita.
- Editar dados de uma fita.
- Excluir fita (com confirmação).
- Cada fita tem um status: DISPONÍVEL, EMPRESTADA ou INDISPONÍVEL.
- Possibilidade de cadastrar categorias e associar fitas a elas.

✅ **Categorias**
- Listar categorias existentes.
- Cadastrar nova categoria.
- Editar categoria.
- Excluir categoria (não permite excluir se houver fitas associadas).

✅ **Usuários**
- Cadastro de novos usuários.
- Login.
- Logout.

---

## Resultados obtidos

O sistema permite organizar de forma simples e prática uma coleção de fitas VHS.  
A estrutura em camadas e o uso do Spring Boot tornam o código organizado e fácil de manter.  
A interface ficou moderna, responsiva e de fácil uso.

---

## ⬇️ Como baixar e instalar

### ✅ Pré-requisitos
- Java 17 ou superior instalado
- Maven instalado
- Git instalado

### 📦 Passos

1. Clone o projeto:
```bash
git clone <https://github.com/andrieli-lopes-web-iii/controle-de-locadora-vhs>
cd controle-de-locadora-vhs