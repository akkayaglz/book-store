# Book Store Application

Simple Book Store Application Demonstration.
You can find 4 service and their responsibilities in here. 
And also, contains all service inside of the security module as a library.
Security library provide them in order to check authorization mechanism.

# 1. Customer Service

Responsible for customer operations such as;

* Creating the new user.
* Checking customer existence.
* Fetching all orders of the customer.

# 2. Order Service

Responsible for ordering operations such as;

* Placing new order.
* Fetching existing orders by given date interval.
* Fetching specific order by orderId.

# 3. Stock Service

Responsible for stocking operations such as;

* Creating Stock in order to new order.
* Updating Stock in order to new order.
* Checking Stock availability for new order.

# 4. Statistic Service

Responsible for  statistic operations such as;

* Fetching orders information given by date information.

### Service Design

![deepLinks](src\main\resources\service-design.jpg)

### User Scenario
##### 1. Register with username and password - POST(http://localhost:9090/security/v1/register) (add body and token)
##### 2. Login with username and password and get Token - POST(http://localhost:9090/security/v1/login) (add body and token)
##### 3. Create new customer - POST(http://localhost:9090/customer/v1/new-customer) (add body and token)
##### 4. Create new book - POST(http://localhost:9094/stock/v1/new-book) (add body and token)
##### 5. Created new order with generated customerId - POST(http://localhost:9092/order/v1/new-order) (add body and token)
##### 6. Check created order with generated orderId. GET(http://localhost:9092/order/v1/id?orderId=${orderId}) (add token.)
##### 7. Update book PUT(http://localhost:9094/stock/v1/update-book) (add body and token)
##### 8. Get all orders given by date rage - POST(http://localhost:9092/order/v1/list/date-range) (add body and token)
##### 9. Get all orders with customerId - GET(http://localhost:9090/customer/v1/list-orders?customerId=${customerId}) (add token)
##### 10.Calculate statistics given by month and year - POST(http://localhost:9096/statistics/v1/monthly-order) (add body and token)

### API Documentation

You can find api yaml files under the documentation folder.
  * documentation/book-service.yaml
  * documentation/customer-service.yaml
  * documentation/order-service.yaml
  * documentation/statistic-service.yaml

### Postman Collection

You can find the collection under the documentation folder name as **book-store.postman_collection.json**

### Install Application

* mvn clean install
* docker-compose up -d --build