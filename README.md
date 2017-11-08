## klov.

[![Join the chat at https://gitter.im/anshooarora/klov](https://badges.gitter.im/anshooarora/klov.svg)](https://gitter.im/anshooarora/klov?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Demo:  [klov.herokuapp.com](http://klov.herokuapp.com)

Reporting server for Extent API. * klov replaces ExtentX.

### Download

Download latest copy from [extentreports.com](http://extentreports.com/community/)


### Klov Installation

1. Install MongoDB (skip if you have this already)
2. Install Redis-Server (skip if you do not plan to use Redis, see section "Using Klov Without Redis")
3. Run Klov:

```java
java -jar klov-0.0.1.jar
```

#### Using Klov without Redis

To use Klov without Redis, simply uncomment this line in `application.properties`:

```
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.session.SessionAutoConfiguration
```

#### MongoDB Settings

You can configure your MongoDB environment settings from `application.properties`:

```java
# data.mongodb
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=klov
```

#### Redis settings

You can configure your Redis server settings from `application.properties`:

```java
# redis, session
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.session.SessionAutoConfiguration
spring.session.store-type=redis
server.session.timeout=-1
spring.redis.host=localhost
spring.redis.port=6379
```

#### Default admin

```
user:  root
password:  password
```
