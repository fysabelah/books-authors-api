# Livros e seus autores

API REST para autores e livros utilizando PostgreSQL e Spring Boot.

Documentação disponibilizada através de [Swagger](http://localhost:8080/doc-bookstore.html).

## Como rodar

O projeto pode ser executado localmente e através de Docker.

Para execução, no diretório root crie um arquivo .env. Neste, cole as chaves abaixo. 
Os valores que estão após o igual (=) podem ser alterados conforme desejar. 
Este arquivo será utilizado tanto no arquivo compose quanto no application.properties.  

    PROFILE=dev

    # PostgreSQL
    DATABASE_USERNAME=usuario_postgre
    DATABASE_PASSWORD=senha_postgre
    
    # PgAdmin
    PGADMIN_DEFAULT_EMAIL=email_pgadmin
    PGADMIN_DEFAULT_PASSWORD=senha_pgadmin

Se o profile for igual a _dev_ está sendo executado localmente. Quando igual a _prod_, executando no Docker. 

Para criação dos containers, considerado que o Docker está instalado, rode o comando abaixo no diretório raiz do projeto.

    docker compose up

Também será criado um container para o PgAdmin, sendo necessáio configurar o [servidor](http://localhost:8081/).