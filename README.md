# blog-api

API REST de blog em Spring Boot (usuários e posts), com PostgreSQL.

## Requisitos

- Java 17
- Maven Wrapper (`./mvnw`)
- Docker e Docker Compose (para o banco local)

## Configuração de ambiente

1. Copie o arquivo de exemplo:

```bash
cp .env.example .env
```

2. Preencha as variáveis no `.env` (sem aspas):

| Variável | Descrição |
|---|---|
| `DB_HOST` | Host do PostgreSQL (`localhost` no desenvolvimento) |
| `DB_PORT` | Porta publicada no host (mapeada no `docker-compose.yml`) |
| `DATABASE` | Nome do banco |
| `DB_USERNAME` | Usuário do banco |
| `DB_PASSWORD` | Senha do banco |
| `SPRING_JPA_DDL_AUTO` | Estratégia do Hibernate (`update` no desenvolvimento; em produção use `validate` ou `none`) |

O arquivo `.env` não é versionado. Ele é lido pelo Docker Compose e, de forma opcional, pela aplicação (`spring.config.import`).

## Banco de dados local

Com o `.env` preenchido:

```bash
docker compose up -d
```

## Executar a API

```bash
./mvnw spring-boot:run
```

## Build

```bash
./mvnw compile
```

## Testes

```bash
./mvnw test
```

Os testes de contexto (`@SpringBootTest`) esperam o PostgreSQL acessível com as mesmas variáveis de ambiente.

## Segurança

- Não commite `.env`, senhas, tokens ou chaves.
- Use credenciais diferentes em desenvolvimento e produção.
- Em produção, defina as variáveis no ambiente do servidor ou do orquestrador — não em arquivos commitados.
