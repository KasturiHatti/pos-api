# pos-api
# Project details:

POS integrated e-commerce platform which offers a point system and has a vast collection of payment methods integrated.
Some payment methods require a commission fee from the payment provider thus you do not want to provide too much discount on a product if the customer selects that payment method. At the same time, you would want to control the points given per purchase based on the payment methods to minimize loss. The following is the list of possible payment methods and their rates.

# APIs:

# http://${hostname}:${port}/calculateSalesPrice
This is the API where we calculate the final price of each payment method based on the supported price modifier

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
Error Handling in http://${hostname}:${port}/calculateSalesPrice :
1. price is > 0
2. price_modifier > 0
3. price_modifier within range for each payment method
3. payment_method is within supported methods

Sample Error Response:
{
"error": "Price must be greater than 0"
}

# http://${hostname}:${port}/sales
This API will provide the sales between two dates divided with 1 hour time hour interval.

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


Error Handling in http://${hostname}:${port}/sales:
1. end_date is at least 1 hr greater than start_date
2. both dates are not future dates, we can't make sales in the future

Sample Error Response:
{
"error": "End Date must be at least 1 hour grater than start date"
}

# TECH Stack used -
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

# Instructions to Run the application:

1. Install mysql
2. Install maven
3. Install Postman
4. Run .sql file dbqueries.sql to create db table
5. git clone https://github.com/KasturiHatti/pos-api.git
6. Maven build
7. Run PosApiApplication.java
8. Call the API http://${hostname}:${port}/calculateSalesPrice using postman - pass the input parameters in the request body
   {

   "price": "100.00",
   "price_modifier": 0.95,
   "payment_method": "CASH_ON_DELIVERY",
   "datetime": "2022-12-05T05:10:00Z"
   }
9. Call the API http://${hostname}:${port}/sales, pass - pass the input parameters as query parameters


# Improvements :

1. We can scale the application by creating more Instances  on multiple servers on different data centers
    1. Keep two instances of the application on two different servers on one data center
    2. Keep two more instances of the application on two different servers on one more data centerThis way, if one server is down on one data center, we can redirect the requests to other server. If one data center is down, then we can redirect the requests to other data centers.This will make the system highly available and redundant.
2. Accept  split payment - Currently system is accepting single payment, if customer selects VISA, then he need to pay whole payment by Visa card. But few customers can choose to pay half payment by cash and other half by card, then we need to be able to accept this. We can do so by
    1. Changing request body
    2. DB changes
    3. Payment Method configuration changes
3. http://${hostname}:${port}/sales - This API BE changes to return the sales within a date range with 1 hour time interval can be done in SQL. Currently handled in Java code.
4. Payment Methods can be made more configurable by putting in properties file. Some code is already available to make it configurable in the source code. 
    
Note about the account :

Initially I used my personal machine to work on this project but the configurations were not working well hence I could not run the project. Then I changed to a different machine where I have valid jetbrains account where I could successfuly run the project. Because of this reason you are seeing two contributors. Hope that is understandable. 
