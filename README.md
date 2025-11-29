# Care Plus Life Balance API

## Integrantes
| Nome | RM |
|------|-----|
| Gabriel Mediotti Marques | 552632 |
| Jó Sales | 552679 |
| Miguel Garcez de Carvalho | 553768 |
| Vinicius Souza e Silva | 552781 |

## Sobre o Projeto
Este projeto é uma proposta para o desafio "Care Plus", visando expandir os serviços de saúde digital. A solução "Life Balance" é uma plataforma de rastreamento de hábitos saudáveis (hidratação, sono, passos) que promove o bem-estar e a prevenção através de gamificação e monitoramento, sem entrar em diagnósticos clínicos.

## Arquitetura
O projeto segue uma Arquitetura Orientada a Serviços (SOA) implementada através de uma aplicação Spring Boot modularizada.

- **Camada de Apresentação (Controller)**: Exposição de APIs RESTful.
- **Camada de Serviço (Service)**: Lógica de negócios e regras de validação.
- **Camada de Dados (Repository)**: Acesso ao banco de dados Oracle via JPA/Hibernate.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3** (Web, Data JPA, Validation)
- **Oracle Database** (Persistência)
- **Flyway** (Migração de Banco de Dados)
- **Maven** (Gerenciamento de Dependências)

## Configuração do Banco de Dados (Oracle)
O projeto está configurado para conectar a um banco de dados Oracle. As configurações estão em `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl
spring.datasource.username=careplus_user
spring.datasource.password=careplus_password
```

### Migrações
O Flyway é utilizado para gerenciar o schema do banco de dados. Os scripts SQL estão localizados em `src/main/resources/db/migration`.

## Como Executar
1. Certifique-se de ter o Java 17 e Maven instalados.
2. Configure o banco de dados Oracle conforme acima.
3. Execute o comando:
   ```bash
   mvn spring-boot:run
   ```

## Documentação da API

### Usuários

#### Criar Usuário
`POST /api/users`
```json
{
  "name": "João Silva",
  "email": "joao@email.com"
}
```

#### Listar Usuários
`GET /api/users`

#### Buscar Usuário por ID
`GET /api/users/{id}`

### Hábitos

#### Criar Hábito para Usuário
`POST /api/habits/user/{userId}`
```json
{
  "type": "WATER",
  "targetValue": 2000
}
```

#### Listar Hábitos de um Usuário
`GET /api/habits/user/{userId}`

#### Atualizar Progresso do Hábito
`PATCH /api/habits/{habitId}/progress?value=250`

## Segurança e Validação
A API implementa validação de entrada para garantir a integridade dos dados e prevenir injeções.
- Nomes e Emails são obrigatórios.
- Valores de meta devem ser positivos.
- Tratamento global de exceções retorna mensagens de erro claras (JSON).