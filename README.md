# Дипломная работа "Автоматизация тестирования банковского сервиса для покупким тура"

# Описание:  
Сервис предоставляет возможность покупки тура по карте за свои деньги и по карте в кредит. Необходимо протестировать корректную работу всех функций сервиса.  

# Подготовка:

## Необходимо установить следующее окружение:  
1. Git.
2. Git Hub.
3. IntelliJ Idea.
4. Docker Desktop.
5. DBeaver.

## Шаги воспроизведения:  
1. Запустить Docker Desktop.
2. Открыть проект в IntelliJ Idea.
3. В терминале выполнить команду для подключения Docker docker-compose up --build
4. В терминале выполнить команду для запуска учебного приложения:  
   Для MySQL java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar aqa-shop.jar  
   Для PostgresQL java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar aqa-shop.jar
5. В терминале выполнить команды для запуска тестов:  
   Для MySQL: ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
   Для PostgreSQL: ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
6. В терминале открыть ссылку для создания отчета Gradle.

 
