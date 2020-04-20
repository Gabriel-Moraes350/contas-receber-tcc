# Contas a receber

TCC sobre contas a receber, projeto desenvolvido em Kotlin/Java com front-end HTML/CSS/JS.
Tecnologias utilizadas: 
 - [Spring Boot](https://blog.algaworks.com/spring-boot/)
 - [Docker](https://www.docker.com/)
 - [Liquibase](https://www.baeldung.com/liquibase-refactor-schema-of-java-app)
 - [Lombok](https://projectlombok.org/)
 
O uso de Kotlin ou Java fica a critério de cada um =D

## Criação do banco de dados
O projeto fornece uma maneira simples para inciar uma nova instância do banco postgres, caso já possua uma maneira para criar o banco, pode ignorar esse passo.

Dentro da pasta ```src/main/resources/db/``` execute o comando

```bash
docker-compose up -d
```

## Instalação

Usando [maven](https://maven.apache.org/install.html) como package manager para gerenciar as dependências do projeto, na raiz rode o comando

```bash
mvn compile
```

Também é possível rodar usando o maven wrapper no projeto ( sem instalação do maven)

No linux: (No windows troque o `./mvnw` para `mvnw.exe`)
```bash
./mvnw compile
```

## Rodando o projeto
É necessário ter o banco de dados configurados, e caso esteja usando outra configuração, diferente do passo anterior, alterar o arquivo ```src/main/resources/application.yml``` 


```yml
 datasource:
    password: SUA_SENHA
    url: SUA_CONEXAO_BANCO
    username: SEU_USUARIO
```
Em seguida rodar o seguinte comando maven: 
```bash
mvn spring-boot:run
```
Ou executar como java application a classe `Application.kt`

Na primeira vez que rodar o projeto deverá criar as tabelas da base da dados. 
Pois o Liquibase é iniciado toda vez que se roda o projeto. 
Para alterar modelo do banco de dados, criar um novo arquivo na pasta `src/main/resource/db/changelog`seguindo a sintaxe adequada:

```sql
--liquibase formatted sql

--changeset SEU_NOME:NUMERO_ARQUIVO
...SQL

--rollback DROP ...
```
Para mais detalhes, [Liquibase](https://www.liquibase.org/documentation/sql_format.html)

## Contributing
Trabalharemos com a branch `master` sendo feitos pull requests para ela, e com a aprovação do time é feito o `merge`.
No Pull request, seria legal, por uma descrição dos arquivos modificados, alguma possível dúvida/explicação e o link do trello de atividade.

Se possível, também para evitar um possível estresse em debugs, poderiamos criar testes para o back-end

Template para painel admin do frontend: [Concept](https://github.com/puikinsh/concept). Acessar para ter exemplos e documentações


Explicação sobre a ideia dos diretorios do projeto: 
```<pre>
├── src
│   ├── main
│   │   ├── kotlin
│   │   │   └── com
│   │   │       └── techeasy
│   │   │           └── contas
│   │   │               └── receber
│   │   │                   ├── application -- meio de campo aplicação e dominio
│   │   │                   ├── config -- arquivos configuração projeto
│   │   │                   ├── domain -- regras de negocio ( separar cada entidade em pastas)
│   │   │                   │   ├── clientes -- (entidade clientes, possui models, services, factory, etc)
│   │   │                   │   │   └── model
│   │   │                   │   └── usuarios -- (entidade usuarios .... )
│   │   │                   │       └── model
│   │   │                   └── infra -- arquivos relacionados a acesso apps de terceiros ( bd, email, filas, etc)
│   │   │                       └── repositories
│   │   └── resources
│   │       ├── db
│   │       │   └── changelog  -- scripts banco de dados
│   │       ├── static
│   │       │   ├── admin -- template admin (não mexer)
│   │       │   ├── css -- css do projeto
│   │       │   ├── images -- imagens do projeto
│   │       │   └── js -- js do projeto
│   │       └── templates
│   │           ├── clientes -- pasta para cada responsabilidade
│   │           ├── _fragments -- fragmentos de tela (header, navbar,...)
│   │           └── _structure -- arquivos base
│   └── test -- tests da nossa aplicação
```

