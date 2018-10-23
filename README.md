Api de integração para manipulação de boletos bancarios
=========================================================
Aplicação Web Service - api java desenvolvida para pode efetuar as operações de manipulação de boletos, criação, leitura, alteração, cancelamento.

###1. Technologias utilizadas
* Java 8
* Maven 3.3+
* Spring Boot 
* H2
* Swagger

###2. Arquitetura
Esse projeto foi desenvolvido com o padrão Rest para expor os serviços que realizam operações sobre boletos bancarios, a utilização do padrão Repository para a persistencia das informações.
Foi utilizado o SGDB H2 de modo embedado, em memoria, na aplicação e por isso não é necessario a instalação e configuração de algum banco de dados para pode executar esse projeto para teste local.
Para a documentação da api utulizou-se o framework Swagger integrado com o Spring. 

###3. Para executar este projeto local
```shell
$ git clone https://github.com/joaomarri/boleto-conta-azul
$ mvn spring-boot:run
```
Acesse a documentação da api: ```http://localhost:8080/swagger-ui.html```

Acessar a base de dados H2: ```http://localhost:8080/h2```
Utilizar jdbc url: ```dbc:h2:mem:testdb```

Para consultar os dados no banco: ```select * from bankslips```

###4. Para importar esse projeto no Eclipse IDE
1. Importe para o Eclipse via opção: Import **Existing mavem projects**.
3. Selecione o projeto que deseja importar e clique em finish.

