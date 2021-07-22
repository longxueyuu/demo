
# docker
docker run --name mongo -p 27017:27017 -d mongo:3.4

# mongo
mongo --host localhost --port 27017

## command
mongo --host ${host_name} --port ${port}  -u ${user_name} -p ${password} ${db_name}
db.${db_name}.help()

# replication set
```
127.0.0.1       mongodb-primary
127.0.0.1       mongodb-secondary
127.0.0.1       mongodb-secondary2
```
