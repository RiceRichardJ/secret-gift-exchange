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
curl localhost:8080/members
curl localhost:8080/members/0
curl -X POST localhost:8080/members -d '{"name":"Bob the Builder"}' -H 'Content-Type:application/json'
curl -X POST localhost:8080/members -d '{"name":"Great Sphinx"}' -H 'Content-Type:application/json'
curl localhost:8080/members
curl localhost:8080/members/0
curl localhost:8080/members/1
curl -X PUT localhost:8080/members/1 -d '{"name":"Big Bro"}'
curl localhost:8080/members
curl -X DELETE localhost:8080/members/0
```

Run integration tests:
```sh
./gradlew clean build test -i
```
