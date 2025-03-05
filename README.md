<!-- CABEÇALHO -->
<div id="readme-top" align="center">
    <h1>
      DevStage 
    </h1>
    <p>
        <a href="#%EF%B8%8F-sobre-o-projeto">Sobre o Projeto</a> •
        <a href="#-endpoints">Endpoints</a> •
        <a href="#%EF%B8%8F-tecnologias">Tecnologias</a> •
        <a href="#-autor">Autor</a>
    </p>
    <p align="center">
        <img alt="Capa do projeto DevStage" src=".github/cover.png" width=800/>
    </p>
</div>

<!-- SOBRE O PROJETO -->

## 🖥️ Sobre o Projeto

> Projeto desenvolvido durante a 19ª edição da Next Level Week da [Rocketseat](https://www.rocketseat.com.br/), ocorrida entre 17 e 20 de fevereiro de 2025.

Esse projeto consiste em uma aplicação back-end de um sistema de inscrição e indicação de eventos.

O objetivo do desenvolvimento desse projeto foi praticar a linguagem de programação Java e seus fundamentos juntamente com o framework Spring Boot.

Através dessa aplicação, um usuário pode se inscrever em um evento, gerar um link de indicação para um evento, ver o ranking de indicações e ver a quantidade de inscritos que se isncreveram com seu link.

<!-- ENDPOINTS -->

## 💡 Endpoints

| Método | Endpoint                                 | Responsabilidade                                         |
| ------ | ---------------------------------------- | -------------------------------------------------------- |
| POST   | /events                                  | Cria um evento                                           |
| GET    | /events                                  | Lista todos os eventos                                   |
| GET    | /events/:prettyName                      | Busca por um evento específico                           |
| POST   | /subcription/:prettyName                 | Inscreve usuário em um evento                            |
| POST   | /subcription/:prettyName/:userId         | Inscreve usuário em um evento através de indicação       |
| GET    | /subcription/:prettyName/ranking         | Obtém o ranking de indicações de um evento               |
| GET    | /subcription/:prettyName/ranking/:userId | Obtém o ranking de um usuário em indicações de um evento |

<!-- TECNOLOGIAS -->

## 🛠️ Tecnologias

Para o desenvolvimento desse projeto, as seguintes ferramentas foram utilizadas:

- **[Java](https://www.java.com/pt-BR/)**
- **[Spring Boot](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org/)**
- **[Docker](https://www.docker.com/)**
- **[MySQL](https://www.mysql.com/)**

## 👨‍💻 Autor

<img style="border-radius: 15%;" src="https://gitlab.com/uploads/-/system/user/avatar/8603970/avatar.png?width=400" width=70 alt="author-profile-picture"/>

Marcos Kenji Kuribayashi

[![Linkedin Badge](https://img.shields.io/badge/-LinkedIn-blue?style=flat&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/marcos-kuribayashi/) [![Gmail Badge](https://img.shields.io/badge/-marcosken13@gmail.com-c14438?style=flat&logo=Gmail&logoColor=white)](mailto:marcosken13@gmail.com)

---

Desenvolvido por Marcos Kenji Kuribayashi 😉
