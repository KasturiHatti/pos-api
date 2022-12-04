# pos-api

APIs:

https://sample-api.anymind.com/calcualate
Sample Request:

{
    "price": "100.00",
    "price_modifier": 0.95,
    "payment_method": "MASTERCARD",
    "datetime": "2022-09-01T00:00:00Z"
} 
Sample Response:

{
    "final_price": "95.00",
    "points": 5
}
Error Handling in https://sample-api.anymind.com/payment/rates : 
1. price is > 0
2. price_modifier > 0
3. price_modifier within range for each payment method
3. payment_method is within supported methods

Sample Error Response:
{
    "error": "Price must be greater than 0"
}

https://sample-api.anymind.com/payment/sales
Sample Request:
{

    "startDateTime":"2022-09-01T00:00:00Z",
    "endDateTime":"2022-09-01T23:59:59Z"

}
    
Sample Response:

{

"sales": [
            {
                "datetime":"2022-09-01T00:00:00Z",
                "sales" "1000.00",
                "points": 10
            },
            {
                "datetime":"2022-09-01T01:00:00Z",
                "sales" "2000.00",
                "points": 20
            },
            {
                "datetime":"2022-09-02T00:00:00Z",
                "sales" "5000.00",
                "points": 75
            },
            {
                "datetime":"2022-09-01T23:00:00Z",
                "sales" "7000.00",
                "points": 30
            }
        ]
}


Error Handling in https://sample-api.anymind.com/payment/sales: 
1. end_date is at least 1 hr greater than start_date
2. both dates are not future dates, we can't make sales in the future

Sample Error Response:
{
    "error": "End Date must be at least 1 hour grater than start date"
}

TECH Stack used -
Java 11
Rest 
Mysql
API client - Postman
Deployment - Docker
Test framework - Junit
Maven
Spring Boot 3
Spring JPA
lombok

Instructions to Run the application:

1. Git clone https://github.com/KasturiHatti/pos-api.git
2. 



