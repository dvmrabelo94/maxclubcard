# Projeto Spring Boot com MySQL, Kafka e Docker

Este é um projeto de exemplo utilizando Spring Boot, Gradle, MySQL, Kafka e Docker. Este README fornecerá instruções sobre como configurar e rodar a aplicação em um ambiente Docker.

## Pré-requisitos

- Docker e Docker Compose instalados em sua máquina.
- Java 21 instalado.
- Gradle instalado.

## Executando a Aplicação

Siga os passos abaixo para executar a aplicação:

1. **Clone o repositório**:

    ```sh
    git clone <URL_DO_SEU_REPOSITORIO>
    cd <NOME_DO_REPOSITORIO>
    ```

2. **Construa e suba os contêineres com Docker Compose**:

    ```sh
    docker-compose up --build
    ```

   Este comando irá construir as imagens Docker definidas no `Dockerfile` e iniciar os serviços definidos no `docker-compose.yml`.

3. **Acessar a aplicação**:

   Após os contêineres serem iniciados, você poderá acessar a aplicação no seguinte endereço:

    ```
    http://localhost:8080
    ```

## Parando a Aplicação

Para parar a aplicação e remover os contêineres, execute:

```sh
docker-compose down