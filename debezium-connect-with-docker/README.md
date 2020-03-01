# Debezium Connect Docker Demo

Execute docker-compose yaml with 

```sh
docker-compose up
```

Then run these commands in order.

PostgreSQL; 

```sql
create sequence seq_persons start 1 increment 1;
create table "persons" (id int8 not null, email varchar(255) not null, firstname varchar(255) not null, lastname varchar(255) not null, primary key (id));
alter table if exists "persons" add constraint UK_1x5aosta48fbss4d5b3kuu0rd unique (email);
ALTER TABLE persons REPLICA IDENTITY FULL;
INSERT INTO persons (id, firstname, lastname, email) VALUES (1,'Ali','Veli','ali.veli@mail.com');
```


```sh
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" 127.0.0.1:8083/connectors/ -d '
{ "name": "debezium-test-connector", 
"config": { 
        "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
        "tasks.max": "1",
        "group.id": "test",
        "database.hostname": "127.0.0.1",
        "database.port": "5432",
        "database.user": "user",
        "database.password": "password",
        "database.dbname": "debezium",
        "database.server.name": "debezium",
        "database.whitelist": "public",
        "heartbeat.interval.ms": "1000",
        "table.whitelist": "public.persons",
        "database.history.kafka.bootstrap.servers": "127.0.0.1:9092",
        "key.converter": "org.apache.kafka.connect.json.JsonConverter",
        "key.converter.schemas.enable": "false",
        "value.converter": "org.apache.kafka.connect.json.JsonConverter",
        "value.converter.schemas.enable": "false",
        "plugin.name": "pgoutput"
	} 
}'
#If 127.0.0.1 doesn't work, try your static ip.
```

Kafka;

```sh
# Make sure you are in the Kafka directory before running this command.

# For list all topics
bin/kafka-topics.sh --list --bootstrap-server localhost:9092

# For consume specific topic
bin/kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --from-beginning --topic debezium.public.persons | jq
```