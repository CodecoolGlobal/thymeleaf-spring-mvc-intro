curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"id":"10","name":"Tomasz","address":"Kraków"}' \
  http://localhost:8080/api/v1/customers


curl -X DELETE http://localhost:8080/api/v1/customers/2