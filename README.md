# Bank Service

This project is a banking system built using **Spring Boot**, **Maven**, and **PostgreSQL**, with **Flyway** for database migrations and **dotenv** for environment variable management.
The admin fee of 7500 will be charged when making a withdrawal. You can change the fee by changing **fee** variable in **WithdrawService.java**

Maybe it will show error like this:

```
***************************
APPLICATION FAILED TO START
***************************

Description:

Web server failed to start. Port 8081 was already in use.

Action:

Identify and stop the process that's listening on port 8081 or configure this application to listen on another port.
```

But don't worry, the application still running. And just try to hit the endpoint.

***OR***

Try to use server.port = 0, it will randomly use available port.
You can check which port is used by checking the log which usually like this.

```
Tomcat started on port 3214 (http) with context path '/'
```
and you can use it by hit something like:

```
http://serverip:3214/api/users/xxxxx
```

---

## Environment Configuration

To configure the application, create a `.env` file in the root directory with the following example format:

```ini
DATABASE_URL=jdbc:postgresql://localhost:5432/database
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=postgres
PORT=8080
SERVER=127.0.0.1
```

---

## Endpoint List

Here are the endpoint list:

ip:port/api/users/balance-inquiry
```
ip:port/api/users/balance-inquiry?username=user&accountNo=accountNumber
```

ip:port/api/transaction/withdraw
```
{
    "username": "betha",
    "accountNumber": "2024031100001",
    "trxAmount": 10000
}
```

Created by **Bethavianus**
