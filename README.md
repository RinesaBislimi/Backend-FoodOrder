# Përmbledhja e Projektit

## Konfigurimi i ORM-së
Projekti ynë shfrytëzon Java Spring Data JPA për menaxhim efikas të bazës së të dhënave. Me konfigurimet e vendosura për portin e serverit 5454 dhe `ddl-auto=update` të Spring JPA, sigurohet përditësim automatik i strukturës së bazës së të dhënave. Lidhja me MySQL ndodh përmes një URL standard `jdbc:mysql://${MYSQL_HOST:localhost}:3306/foodDB`, duke përdorur kredenciale të sigurta për emrin e përdoruesit dhe password.

## Konfigurimi i Flyway me MySQL

Kemi krijuar një dosje db/migration brenda src/main/resources.Pastaj brenda saj një skriptë SQL të quajtur v1__create_tables.sql. Kjo skriptë e përmbanë skemën e tabelave për bazën e të dhënave.

Pastaj e kemi shtuar Flyway në  Maven, dhe tek pom.xml kemi shtuar keto rreshta të kodit
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
Dhe tek file i application.properties i kemi shtuar keto rreshta të kodit:
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.flyway.baseline-on-migrate=true
spring.jpa.show-sql=true

Me këto konfigurime,kemi përcaktuar Flyway për të menaxhuar migrimet e bazës së të dhënave në MySQL dhe kemi  specifikuar migrimet e parapraka në skriptën v1__create_tables.sql.


## Testimi i funksionalitetit të ORM-së dhe migrimet në databazë duke përdorur Postman

![Screenshot 2024-04-12 224232](https://github.com/RinesaBislimi/Backend-FoodOrder/assets/118773246/1555c873-f0e0-4410-8126-e072c36b894f)

