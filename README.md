# Livros e seus autores

API REST para autores e livros utilizando MongoDB e Spring Boot.

Documentação disponibilizada através de [Swagger](http://localhost:8080/doc-bookstore.html).

API desenvolvida para assimilação de conhecimento do MongoDB.

## Como rodar

O projeto utiliza Docker para o Mongo e uma interface web para acessá-lo, mongo express.

Para começar, no diretório root crie um arquivo .env. Neste, cole as chaves abaixo. 
Os valores que estão após o igual (=) podem ser alterados conforme desejar. 
Este arquivo será utilizado tanto no arquivo compose quanto no application.properties. Logo, mesmo que não deseje utilizar o Docker, será necessário criar o arquivo. 

    # MongoDB
    MONGO_INITDB_ROOT_USERNAME=usuario_desejado
    MONGO_INITDB_ROOT_PASSWORD=senha

    # MongoDB Express
    ME_CONFIG_BASICAUTH_USERNAME=usuario_desejado
    ME_CONFIG_BASICAUTH_PASSWORD=senha

Considerado que o Docker está instalado, rode o comando abaixo no diretório raiz do projeto.

    docker compose up

A interface web para o MongoDB pode ser acessada em [na porta 27018](http://localhost:27018/).