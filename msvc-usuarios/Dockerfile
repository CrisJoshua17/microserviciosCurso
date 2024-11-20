# Usa la imagen base
FROM dvmarques/openjdk-17-jdk-alpine-with-timezone:latest

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

EXPOSE 8001

ENTRYPOINT [ "java","-jar","./target/msvc-usuarios-0.0.1-SNAPSHOT.jar" ]

