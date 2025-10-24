# PharmacyApp

Веб-приложение для управления аптекой, созданное с использованием Java Servlets, JSP, JSTL и PostgreSQL.

## Запуск проекта
1. Установите зависимости

* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

* [Apache Maven](https://maven.apache.org/download.cgi)

* [Apache Tomcat 10](https://tomcat.apache.org/download-10.cgi)

* [PostgreSQL](https://sbp.enterprisedb.com/getfile.jsp?fileid=1259789)

2. Настройка базы данных

Создайте базу данных со следующими данными:

    URL = "jdbc:postgresql://localhost:5432/pharmacy_app"
    USER = "postgres"
    PASSWORD = "local"
}



3. Деплой в Tomcat

Готовый WAR-файл находится в папке:

[/war/pharmacyApp.war](war/pharmacyApp.war)


Скопируйте его в папку webapps, находящуюся в Apache Tomcat

Пример, куда копировать WAR: C:\apache-tomcat-10.1\webapps\


Запустите Tomcat:

Введите в консоль (укажите свой путь, по которому расположен файл запуска Tomcat): C:\apache-tomcat-10.x\bin\startup.bat

Приложение размещено по адресу:

http://localhost:8080/pharmacyApp/

## Технологии

* Java 17

* Servlet API (Jakarta EE 10)

* JSP + JSTL

* PostgreSQL + JDBC

* Maven

* Tomcat 10