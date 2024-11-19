FROM dvmarques/openjdk-17-jdk-alpine-with-timezone

WORKDIR /app/msvc-usuarios

# Copiar solo los archivos necesarios para la construcción
COPY ./msvc-usuarios/pom.xml .
COPY ./msvc-usuarios/.mvn ./.mvn
COPY ./msvc-usuarios/mvnw .

# Asegúrate de que mvnw tenga permisos de ejecución
RUN chmod +x mvnw

# Construir el proyecto
RUN ./mvnw clean package -Dmaven.test.skip=true -Dspring-boot.repackage.skip

# Copiar el código fuente
COPY ./msvc-usuarios/src ./src 

# Construir el artefacto final
RUN ./mvnw clean package -DskipTests

EXPOSE 8001

ENTRYPOINT [ "java", "-jar", "target/msv-usuarios-0.0.1-SNAPSHOT.jar" ]