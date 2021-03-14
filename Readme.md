# Testing with curl
curl http://localhost:8080/calculate/USD/50000/BTC

# Swagger UI
You can use swagger-ui to see and test api: http://localhost:8080/swagger-ui.html


# Build and Test
mvn clean package

# Run with docker
docker build --build-arg CI_PROJECT_NAME=trader --tag trader:0.1 .

docker run -d --rm -p 8081:8080 --name trader trader:0.1