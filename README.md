Тестовое приложение "Библиотека" (REST API)
---------------------------------------------

Сущности Customer, Address, Book. CRUD-операции, пагинация, сортировка.

БД H2 развертывается автоматически через Flyway. Консоль базы http://localhost:8080/library-app/h2-console

Доступен Swagger-UI (http://localhost:8080/library-app/swagger-ui/index.html)


Последняя версия - настроена сборка проекта в war архив и автоматический деплой на внешний Tomcat (по файловому пути);
Путь устанавливается в pom.xml
<br/>
```html
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-war-plugin</artifactId>
	<configuration>
		<failOnMissingWebXml>false</failOnMissingWebXml>
		<outputDirectory>f:\Java\apache-tomcat-9.0.45\webapps\</outputDirectory>
	</configuration>
</plugin>
```

Версия для стандартного Spring Boot Jar:
https://github.com/introby/library/tree/db2af82c0dbd2073dc93b56010cf439ff280553e


Стек:
1. Java 8+
2. Embedded DB (H2)
2. Spring (Boot, Web, Data JPA)
3. Swagger UI
4. Flyway
5. Maven
6. Git
7. Lombok
