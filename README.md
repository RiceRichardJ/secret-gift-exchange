# secret-gift-exchange

To run:
```sh
./gradlew clean build bootRun
```

In a second terminal, in order to quick reload after code changes:
```sh
./gradlew build -x test
```

Sample curl commands:
```sh
# Test member CRUD operations
curl localhost:8080/members
curl localhost:8080/members/0
curl -X POST localhost:8080/members -d '{"name":"Alice"}' -H 'Content-Type:application/json'
curl -X POST localhost:8080/members -d '{"name":"Bob"}' -H 'Content-Type:application/json'
curl -X POST localhost:8080/members -d '{"name":"Charlie"}' -H 'Content-Type:application/json'
curl -X POST localhost:8080/members -d '{"name":"Dave"}' -H 'Content-Type:application/json'
curl -X POST localhost:8080/members -d '{"name":"Eric"}' -H 'Content-Type:application/json'
curl localhost:8080/members
curl localhost:8080/members/0
curl localhost:8080/members/1
curl -X PUT localhost:8080/members/3 -d '{"name":"David"}'
curl localhost:8080/members
curl -X DELETE localhost:8080/members/4
curl localhost:8080/members

# Test roster generation
curl -X POST localhost:8080/generate_roster/2019
curl localhost:8080/gift_exchange/2019
curl -X POST localhost:8080/generate_roster/2019
curl localhost:8080/gift_exchange/2019
curl -X POST localhost:8080/generate_roster/2019
curl localhost:8080/gift_exchange/2019
# Note: behavior is undefined if members are added / removed between years.
```

Run integration tests:
```sh
./gradlew clean build test -i
```
