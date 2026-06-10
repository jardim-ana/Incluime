# Imagem base com Java 21 da Amazon
FROM amazoncorretto:21

# Diretório da aplicação dentro do container
WORKDIR /app

# Copia o JAR gerado pelo Maven (shade/fat jar)
COPY target/etl-apachepoi-1.0-SNAPSHOT.jar app.jar

# Variáveis opcionais (boa prática)
ENV JAVA_OPTS=""

# Comando de execução
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
