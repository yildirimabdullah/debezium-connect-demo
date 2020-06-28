# Debezium Connect Demo

If you want to install Debezium on your local, check the debezium-connect-with-docker directory.

If you want to create the kubernetes pipeline of Debezium, check the debezium-connect-with-k8s directory.


# Debezium Demo

With this demo you can create Zookeeper, Kafka, PostgreSQL and Debezium (Kafka Connect) instance on your Docker. Then with the spring boot application you can create a table in your postgresql container and you can do CRUD operations.


## Requirements

- Docker
- Java 11

## Usage

First, go to the docker directory and create all the containers needed with the ```docker-compose up``` command. Some script files will be added to the cantainers that will be created. With these scripts, it is possible to list Kafka topics and consume to the topic named "debezium.public.persons". 

Once all containers have been successfully created, build the application. While the application is being built, a table named persons will be created on PostgreSQL with liquibase.

After these steps, a connector must be created for the persons table. The following command will be used to create the connector.

```bash
curl --location --request POST '127.0.0.1:8083/connectors/' \
--header 'Content-Type: application/json' \
--data-raw '{ 
"name": "debezium-test-connector", 
"config": { 
	"connector.class": "io.debezium.connector.postgresql.PostgresConnector",
	"database.hostname": "{local-ip}", 
	"database.port": "5432", 
	"database.user": "user", 
	"database.password": "password", 
	"database.dbname" : "debezium",
	"database.server.name": "debezium", 
	"database.whitelist": "public", 
	"table.whitelist": "public.persons",
	"heartbeat.interval.ms": "5000",
	"slot.name": "debezium",
	"key.converter": "org.apache.kafka.connect.json.JsonConverter",
	"key.converter.schemas.enable": "false",
	"value.converter": "org.apache.kafka.connect.json.JsonConverter",
	"value.converter.schemas.enable": "false",
	"plugin.name":"pgoutput"
	} 
}'
```

Finally, with the ```docker exec -u 0 -it kafka /bin/bash``` command, you can enter the Kafka container and list its topics and consume the created topic.

For list all topics and consume created topic, scripts that we create before can be used. 

- Install JQ (Json beautifier for terminal): ```./install-jq.sh``` 
- List all topics: ```./list-topic.sh```
- Consume created topic: ```./consume-persons.sh```

For more details, you can review the posts in my medium account.
[medium/abdullahyildirim](https://medium.com/@abdullahyildirim)