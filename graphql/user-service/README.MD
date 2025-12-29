# User Service API Documentation

http://localhost:8000/swagger-ui.html
http://localhost:8080/v3/api-docs
http://localhost:8080/v3/api-docs.yaml


curl -X GET http://localhost:9090/graphql -H "Content-Type: application/json" \
-d '{
"query": "query user($id: ID!) { user(id: $id) { id name email orders { id product quantity } } }",
"variables": {
"id": "1"
}
}'




