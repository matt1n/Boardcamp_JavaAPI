
# Boardcamp API

Com o boardcamp você pode gerenciar uma locadora de jogos de mesa :game_die:




## Funcionalidades

- Criar um usuário
- Verificar usuário por id
- Criar um jogo
- Listar todos os jogos
- Criar um aluguel relacionando o usuário, jogo e quantidade de tempo alugado
- Listar todos alugueis
- Fechar aluguel


## Variáveis de Ambiente

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no seu .env

`DB_URL`

`DB_USERNAME`

`DB_PASSWORD`


## Documentação da API

### Retorna todos os jogos

```http
  GET /games
```

### Salva um jogo novo

```http
  POST /games
```

#### Body da requisição: 

| Body   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name`      | `String` | **Obrigatório**. Nome do jogo |
| `image`      | `String` | **Obrigatório**. Link da imagem do jogo |
| `stockTotal`      | `Long` | **Obrigatório**. Quantidade em estoque |
| `pricePerDay`      | `Long` | **Obrigatório**. Preço por dia alugado |

### Retorna cliente por id

```http
  GET /customers/{id}
```

#### Parâmetro da requisição: 

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. Id do cliente |

### Salva um novo cliente

```http
  POST /customers
```

#### Body da requisição: 

| Body   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name`      | `String` | **Obrigatório**. Nome do cliente |
| `cpf`      | `String` | **Obrigatório**. Cpf do cliente|

### Retorna todos os aluguéis

```http
  GET /rentals
```

### Salva um novo aluguel

```http
  POST /rentals
```
#### Body da requisição: 

| Body   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `customerId`      | `Long` | **Obrigatório**. Id do cliente |
| `gameId`      | `Long` | **Obrigatório**. Id do jogo|
| `daysRented`      | `Long` | **Obrigatório**. Tempo de aluguel|

### Fecha um aluguel existente

```http
  PUT /rentals/{id}/return
```

#### Parâmetro da requisição: 

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. Id do aluguel |

## Tecnologias

![Static Badge](https://img.shields.io/badge/Spring%20-%20Boot%20-%20boot?link=https%3A%2F%2Fspring.io%2Fprojects%2Fspring-boot)
![Static Badge](https://img.shields.io/badge/Maven%20-%20%23af2052?link=https%3A%2F%2Fmaven.apache.org%2F)
![Static Badge](https://img.shields.io/badge/jUnit%20-%20%2325a162?link=https%3A%2F%2Fjunit.org%2Fjunit5%2F)






