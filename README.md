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
    git clone https://github.com/dvmrabelo94/maxclubcard.git
    cd maxclubcard
    ```

2. **Construa e suba os contêineres com Docker Compose**:
   Obs.: Build o projeto antes.
    ```sh
    docker-compose up --build
    ```

   Este comando irá construir as imagens Docker definidas no `Dockerfile` e iniciar os serviços definidos no `docker-compose.yml`.

3. **Acessar a aplicação**:

   Após os contêineres serem iniciados, você poderá acessar a aplicação no seguinte endereço:

    ```
    http://localhost:8080/swagger-ui/index.html
    ```

   Lá você encontrará os seguintes endpoints:   

   Responsável por cadastrar os clientes.
    ```
    http://localhost:8080/v1/clients
    ```
   Responsável por cadastrar os cartões de crédito e débito.
    ```
    http://localhost:8080/v1/cards
    ```
   Responsável por cadastrar as transações e retornar os números da sorte.
    ```
    http://localhost:8080/v1/transactions
    ``` 
   Responsável por sortear os números da sorte.
4. Também temos um cronjob que faz o sorteio uma vez por semana, esse endpoint serve só para teste, pois tem a mesma lógica que o cronjob.
    ```
    http://localhost:8080/v1/drawnumber
    ```

   O kafka-ui ficará disponível no seguinte endereço:
   Tanto o crojob, quanto o endpoint para sortear, enviará uma mensagem para um tópico kafka, para ajudar a visualizar, disponibilizei o kafka-ui, nesse endereço.
    ```
    http://localhost:9090
    ```

## Parando a Aplicação

Para parar a aplicação e remover os contêineres, execute:

```sh
docker-compose down
```

## Roadmap de Melhorias

Adicionar validadores para campos como CPF, e-mail, etc.

Aumentar a cobertura de testes.

Adicionar API (CRUD) para as bandeiras de cartões. Podendo adicionar mais ou desabilitar alguma.

Adicionar Endpoint para consulta dos números da sorte.

Melhorar a documentação.