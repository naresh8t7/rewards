<h1>
  <br>Charter Take home test
</h1>
  

- [About The Project](#about-the-project)
- [problem-statement](#problem-statement)
- [How To Build](#how-to-build)
- [Assumptions](#assumptions)
- [How To Use](#how-to-use)


## About the project
Project includes source code for rewards app built by user <a href="https://github.com/naresh8t7">Naresh</a>


## Problem statement
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. 
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
dollar spent between $50 and $100 in each transaction.
(e.g., a $120 purchase = 2x$20 + 1x$50 = 90 points).
 
Given a record of every transaction during a three-month period, calculate the reward points earned for
each customer per month and total.
- Solve using Spring Boot
- Create a RESTful endpoint
- Make up a data set to best demonstrate your solution
- Check solution into GitHub


## How to build
```sh
git clone git@github.com:naresh8t7/rewards.git
mvn clean install
mvn spring-boot run
```

## Assumptions
- H2 in memory database with minimum DB fields required.
- Pagination of results not in scope, rest end points provides all results.
- Round amount in points calculation.


## How to use
### All end points
- Fetch all customers or by id

```
http://localhost:8080/api/customers
http://localhost:8080/api/customers/1000
```

- Fetch all transactions or by customer

```
http://localhost:8080/api/customers/transactions
http://localhost:8080/api/customers/1000/transactions
```

- Fetch rewards for a customer
	- roll_up parameter: either by by_day or by_month.
	- window parameter: duration of aggregation

```
http://localhost:8080/api/customers/1000/rewards (defaults to by_month, with window as 3)
http://localhost:8080/api/customers/1000/rewards?rollUp=by_day&window=2
http://localhost:8080/api/customers/1000/rewards?roll_up=by_day&window=3
http://localhost:8080/api/customers/1000/rewards?roll_up=by_month&window=3
```
- Swagger API Docs

```
http://localhost:8080/swagger-ui/index.html
```

- Actuator/Prometheus endpoints

```
http://localhost:8080/actuator
http://localhost:8080/actuator/health
http://localhost:8080/actuator/prometheus
http://localhost:8080/swagger-ui/index.html
```

