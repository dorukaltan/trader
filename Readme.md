# 

# Build and Test
mvn clean package

# Run with docker
docker build --build-arg CI_PROJECT_NAME=trader . --tag trader:0.1
docker run -d --rm -p 8081:8080 --name trader trader:0.1