# Controle Financeiro Api

Api para controle dos gastos e ganhos
recebido, onde é possível lançar o montante recebido/gasto e consultar um
balanço do seu saldo por período.

## Como rodar a aplicação local?
A duas maneiras, podendo usar o banco de dados H2 em memoria ou PostgreSQL.
1- Rodando com PostgreSQL:
COnfigure VM options com o profile dev
-Dspring.profiles.active=dev
Tenha o docker instalado na sua maquina e na raiz do repositorio rodar o comando:
```bash
docker-compose up
```
2- Utilizando o H2

Para utilizar o banco em memória h2 é só rodar a aplicação com o profile default, não sendo necessário configurar VM options.

Obs: verificar se tem as seguintes linhas comentadas, caso sim, remover o comentario "#".
```
#spring.datasource.url=jdbc:h2:mem:testdb
#spring.datasource.driverClassName=org.h2.Driver
#spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

#spring.datasource.username=sa
#spring.datasource.password=
#spring.jpa.show-sql=true
#spring.jpa.properties.hibernate.format_sql=true
#spring.h2.console.enabled=true

management.server.port=8082
management.endpoints.web.base-path=/management

management.endpoints.web.exposure.include=*

```

## ENDPOINTS

Swagger: 

Aplicação conta com endpoint de Swagger, para  realização de testes:
http://localhost:8080/swagger-ui.html#/
Obs: para serem acessadas as rodas necessitam do header com valor
"aXRhw7o=." 

Actuator: 

Endpoint para consutar a saúde da aplição.
http://localhost:8082/management
