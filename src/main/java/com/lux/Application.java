package com.lux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lux")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


//{
//    "aboutInfo": "Описание Ивана",
//    "city": "Москва",
//    "course": 3,
//    "dateOfBirth": "1990-01-01",
//    "educationStatus": "студент",
//    "email": "ivan@example.com",
//    "faculty": "Физический",
//    "firstName": "Иван",
//    "lastName": "Иванович",
//    "middleName": "Иванов",
//    "phoneNumber": "+79991234567",
//    "specialty": "Физика",
//    "telegramId": "@ivanov_ivan",
//    "university": "МГУ",
//    "username": "ivanov_ivan"
//}

//{
//        "id": 4,
//        "login": "ivanov_ivan",
//        "password": "oFG0wxYmURcy"
//        }