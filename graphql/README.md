# Test GraphQL Query

```graphql  
GRAPHQL http://172.20.144.1:9090/graphql

query {
  user(id: "1") {
    id
    name
    email
    orders {
      id
      product
      quantity
    }
  }
}

# Test via curl

curl -X POST http://localhost:9090/graphql -H "Content-Type: application/json" \
  -d '{
    "query": "query { user(id: \"1\") { id name email orders { id product quantity } } }"
  }'
