# secret-gift-exchange

To run:
```sh
./gradlew clean build bootRun
```

In order to quick reload after code changes, in a second terminal:
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
curl -X PUT localhost:8080/members/3 -d '{"name":"David"}' -H 'Content-Type:application/json'
curl localhost:8080/members
curl -X DELETE localhost:8080/members/4
curl localhost:8080/members

# Test roster generation
curl -X POST localhost:8080/generate_roster/2019
curl localhost:8080/gift_exchange/2019
curl -X POST localhost:8080/generate_roster/2020
curl localhost:8080/gift_exchange/2020
curl -X POST localhost:8080/generate_roster/2021
curl localhost:8080/gift_exchange/2021
# Note: behavior is undefined if members are added / removed between years.
```

Manually run integration tests:
```sh
./gradlew clean build test -i
```

## Notes

I did not have time to write very thorough tests. Because of this, I opted for a small number of integration tests instead of a small number of unit tests since integration tests should provide a wider coverage of the code and should test for the app's behavior more from a user's point of view.

The constraint "A family member cannot be given a gift from the same person more than once every 3 years" cannot be satisified if the total number of members is less than 4. In these cases the constraint is ignored.

The behavior undefined if members are added / removed between years as I did not have time to consider a solution for this case.
