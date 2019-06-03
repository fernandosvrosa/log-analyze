# log-analyze 

Log analyze é uma api para analise de log do jogo [Quake](https://quake.bethesda.net/pt/)  onde é incluido log de N jogos isso resulta em uma analise agrupada por log 

## Configurações 
 Como controle de dependência foi usando o projeto [Maven](https://maven.apache.org/)
 
 Versão do [java](https://openjdk.java.net/projects/jdk/11/) usada foi a versão openJDK 11 
 
 Também foi usando [Docker](https://www.docker.com/) para subir tanto o projeto quando o bando de dados Mongo.
 
 ## Como executar o projeto 
 Para executar o projeto 
 
 1 -  Builde o projeto 
 ```bash
 $ mvn clean package
 ```
 
 2 - Inicie o app juntamente com o banco utilizando o `docker-compose` 
 ```bash
 $ docker-compose up --build -d 
 ```
 
 ## Usando a analise e a consulta 
 colocar o arquivo de log na pasta /opt/log da maquina, pois esta compartilhado com o docker do projeto, usar o endpoint POST  como no exemplo abaixo
 ######POST-> /analyse
 body 
 `` {"name": "test.log"}``
 
 usar o endpoint com o id retornado no post par aconsultar
######GET-> /analyse/{id}


 
 


