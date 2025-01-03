# Usa la imagen base
FROM dvmarques/openjdk-17-jdk-alpine-with-timezone:latest AS builder

# Establece el directorio de trabajo
WORKDIR /app/mscv-gateway

COPY ./pom.xml /app
COPY ./mscv-gateway/.mvn ./.mvn
COPY ./mscv-gateway/mvnw .
COPY ./mscv-gateway/pom.xml .


# Construye el proyecto
RUN ./mvnw clean package -Dmaven.test.skip=true -Dspring-boot.repackage.skip -Dmaven.main.skip

# Copia el código fuente
COPY ./mscv-gateway/src ./src 

RUN ./mvnw clean package -Dmaven.test.skip=true


FROM dvmarques/openjdk-17-jdk-alpine-with-timezone:latest 

WORKDIR /app

RUN mkdir ./logs

COPY --from=builder /app/mscv-gateway/target/mscv-gateway-0.0.1-SNAPSHOT.jar .

    
EXPOSE 8090

CMD [ "java","-jar","mscv-gateway-0.0.1-SNAPSHOT.jar" ]

