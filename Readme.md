# Testing with curl
curl http://localhost:8080/calculate/USD/50000/BTC

# Swagger UI
You can use swagger-ui to see and test api: http://localhost:8080/swagger-ui.html


# Build and Test
mvn clean package

# Run with docker
docker build --build-arg CI_PROJECT_NAME=trader --tag trader:0.1 .

docker run -d --rm -p 8081:8080 --name trader trader:0.1

# About the code

## Models
Trader models kept in com.sgveteris.trader.model package. Enums are used for currency types and they seperated as CoinCurrencyEnum and PriceCurrencyEnum. It helped for validation process but actually String would have been better for flexibility in real life.


## CalculationController
CalculationController receives requests and validates.

After all it calls Calculation Service and returns response.

## Validations
CoinCurrencyEnumValidator is used for CoinCurrencyEnum and it returns appropriate response like below:

> Requested coin currency is not available. Available currencies are: BTC

Same would have been done for PriceCurrencyEnum but it left as is. It gives error during String enum conversation in controller.

## CalculationService

calculate method is developed. BlockChainTickerCacheManager is used in order to cache it's response for 10 seconds.

## BlockChainClient

Because ticker method returns results in key value pairs. BlockChainClient extends HashMap<String, TickerModel>. It was the easiest way to parse response and start using it.


## Tests

In order to call api CalculationClient is written.
Two tests are written. One is for correct response and the other one expects exception because requested coinCurrency is sent as IOT. 
