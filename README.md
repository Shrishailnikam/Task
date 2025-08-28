cURL

curl --location 'http://localhost:8080/calculate-age' \
--header 'Content-Type: application/json' \
--data '{
    "birthday": "1990-05-10"
}'

Response
{
  "age": "35 years, 3 months, 18 days, 14 hours"
}

