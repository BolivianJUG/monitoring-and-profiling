# Monitoring and Profiling

This project uses [Quarkus](https://quarkus.io/) with Java 11

## Running the application in dev mode

```shell script
./mvnw compile quarkus:dev
```

## Building the docker container
```shell
./mvnw package
docker build -f src/main/docker/Dockerfile.jvm -t bolivianjug/monitoring-and-profiling .
```

## Run the application using docker

```shell
docker-compose up -d monitoring-and-profiling
```

Test that everything is running:
```shell
curl -X GET http://localhost:8080/hello/world
```

## Run prometheus
```shell
docker-compose up -d prometheus
```

Now, go to http://localhost:9090 then click on Status>Targets, and finally check that you have the following entries with the label "UP":

* my-app (1/1 up)
* prometheus  (1/1 up)

## Run grafana

```shell
docker-compose up -d grafana
```

To check that grafana is running go to http://localhost:3000.
The default user/pwd is admin/admin

## References
* https://quarkus.io/
* https://quarkus.io/guides/micrometer
  https://www.facebook.com/BolivianJUG/
* https://www.youtube.com/channel/UC3G0BODuA9PYbIEvDigi0iA
