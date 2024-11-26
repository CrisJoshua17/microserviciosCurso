# Usa la imagen base
FROM dvmarques/openjdk-17-jdk-alpine-with-timezone:latest AS builder

# Establece el directorio de trabajo
WORKDIR /app/msvc-usuarios

COPY ./pom.xml /app
COPY ./msvc-usuarios/.mvn ./.mvn
COPY ./msvc-usuarios/mvnw .
COPY ./msvc-usuarios/pom.xml .


# Construye el proyecto
RUN ./mvnw clean package -Dmaven.test.skip=true -Dspring-boot.repackage.skip -Dmaven.main.skip

# Copia el código fuente
COPY ./msvc-usuarios/src ./src 

RUN ./mvnw clean package -Dmaven.test.skip=true


FROM dvmarques/openjdk-17-jdk-alpine-with-timezone:latest 

WORKDIR /app

RUN mkdir ./logs

COPY --from=builder /app/msvc-usuarios/target/msvc-usuarios-0.0.1-SNAPSHOT.jar .

ENV PORT 8001

EXPOSE $PORT

CMD [ "java","-jar","msvc-usuarios-0.0.1-SNAPSHOT.jar" ]

