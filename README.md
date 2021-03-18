## CRUD JAVA

**This project is an example of a primitive Cache-Aside strategy (Lazy Loading) using Redis, PostgreSQL, Gson, JPA and JDK 15.0.2**

**Docker containers needed to run this Application:**

 1. PostgresSQL
 2. PgAdmin4 
 3. Redis

**Commands to run docker for this Application:**

 1. Create postgres-network:
    
    docker network create -d bridge --subnet 10.0.0.0/18 postgres-network  

 2. Create redis-nwtwork:

    docker network create redis-network

 3. Check the networks:
    
    docker network inspect postgres-network
    
 4. Download and run PostgresSQL docker container:
    
    docker pull postgres
    
    docker run --name postgres --network postgres-network -h 10.0.0.3 -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres
    
 5. Download and run PgAdmin4 docker container:
    
    docker pull dpage/pgadmin4
    
    docker run --name pgadmin4 --network postgres-network -h 10.0.0.2 -p 15432:80 -e PGADMIN_DEFAULT_EMAIL=your_email@email.com -e PGADMIN_DEFAULT_PASSWORD=password -d dpage/pgadmin4

 6. Download and run Redis docker container:  
 
    docker pull redis

    docker run --network redis-network --name redis-server -d redis
