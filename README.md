
# SWAPI Planet API

Quick REST API made with Spring Boot, Java 11 and Postgresql to manipulate Star Wars planets.


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`POSTGRES_DB_USER`

`POSTGRES_DB_PASSWORD`

`POSTGRES_DB_NAME`


## Run Locally

You must have a Postgresql database with the following settings
```bash
  port: 5432
  database name: your-name-of-choice
```
Clone the project

```bash
  git clone https://github.com/zam0k/star-wars-planet-api.git
```

Go to the project directory

```bash
  cd star-wars-planet-api
```

Go to main/resources and create a applicatio-secrets.yml file, then insert the following fields

```bash
  spring:
  datasource:
    username: *your-username-of-choice*
    password: *your-password-of-choice*
```

Come back to project main directory and start the server

```bash
  mvn spring-boot:run
```


## Running With Docker
Clone the project

```bash
  git clone https://github.com/zam0k/star-wars-planet-api.git
```

Go to the project directory

```bash
  cd simplified-psp
```

Insert the following command

```bash
  docker-composer up -d --build
```

## Running Tests

First you must have docker running in your machine

To run tests, run the following command

```bash
  mvn test
```


## API Reference

#### Create a new planet

```http
  POST /api/planets/v1/
```

Example of payload

```
  {
    "name": "tatooine",
    "climate": "arid",
    "terrain": "desert"
  }
```

#### Get all planets or all planets matching name parameter

```http
  GET /api/planets/v1?name=namehere
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | **Optional**. Name of a planet |

Example of json expected
```
[
    {
        "id": 1,
        "name": "tatooine",
        "climate": "arid",
        "terrain": "desert",
        "numberOfApparitions": 5
    }
]
```

#### Fetch a planet by its id

```http
  GET /api/planets/v1/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

Example of json expected
```
  {
      "id": 1,
      "name": "tatooine",
      "climate": "arid",
      "terrain": "desert",
      "numberOfApparitions": 5
  }

```

#### Remove an existing planet

```http
  DELETE /api/planets/v1/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of planet about to be deleted |


## Tech Stack

- Java 11
- Spring MVC
- Spring Data JPA
- Hibernate
- Postgresql
- Flyway
- Junit5
- Mockito
- Docker

