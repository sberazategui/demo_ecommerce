# Ecommerce Demo Application

Este proyecto es una aplicación de demostración para un sistema de comercio electrónico desarrollado con Spring Boot. Incluye funcionalidades para consultar los precios de productos basados en identificadores de producto, cadena y fechas específicas.

## Características

- Consultar precios de productos
- Consultar tarifas aplicables
- Manejo de fechas de aplicación

## Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.2.5**
- **Spring MVC**
- **Spring Data JPA**
- **H2 Database** (para pruebas)
- **JUnit 5**
- **Mockito**
- **Lombok**
- **Maven**

## Requisitos Previos

- Java 21 o superior
- Maven 3.6 o superior

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/sabrinaberazategui/ecommerce-demo.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd ecommerce-demo
    ```

3. Compila el proyecto:
    ```sh
    mvn clean install
    ```

## Ejecución

Para ejecutar la aplicación localmente, usa el siguiente comando:
```sh
mvn spring-boot:run
