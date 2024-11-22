# Usa la imagen base
FROM dvmarques/openjdk-17-jdk-alpine-with-timezone:latest AS builder

# Establece el directorio de trabajo
WORKDIR /app/msvc-cursos

COPY ./pom.xml /app
COPY ./msvc-cursos/.mvn ./.mvn
COPY ./msvc-cursos/mvnw .
COPY ./msvc-cursos/pom.xml .


# Construye el proyecto
RUN ./mvnw clean package -Dmaven.test.skip=true -Dspring-boot.repackage.skip -Dmaven.main.skip

# Copia el código fuente
COPY ./msvc-cursos/src ./src 

RUN ./mvnw clean package -Dmaven.test.skip=true


FROM dvmarques/openjdk-17-jdk-alpine-with-timezone:latest 

WORKDIR /app

RUN mkdir ./logs

COPY --from=builder /app/msvc-cursos/target/msvc-cursos-0.0.1-SNAPSHOT.jar .


EXPOSE 8002

CMD [ "java","-jar","msvc-cursos-0.0.1-SNAPSHOT.jar" ]

