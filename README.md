# Përmbledhja e Projektit - "FoodOrder"

## Konfigurimi i ORM-së
Projekti ynë shfrytëzon Java Spring Data JPA për menaxhim efikas të bazës së të dhënave. Me konfigurimet e vendosura për portin e serverit 5454 dhe `ddl-auto=update` të Spring JPA, sigurohet përditësim automatik i strukturës së bazës së të dhënave. Lidhja me MySQL ndodh përmes një URL standard `jdbc:mysql://${MYSQL_HOST:localhost}:3306/foodDB`, duke përdorur kredenciale të sigurta për emrin e përdoruesit dhe password.

## Konfigurimet për Flyway me MySQL

Kemi krijuar një folder db/migration brenda src/main/resources.Pastaj brenda saj një skriptë SQL të quajtur v1__create_tables.sql. Kjo skriptë e përmbanë skemën e tabelave për bazën e të dhënave.

Pastaj e kemi shtuar Flyway në  Maven, dhe tek pom.xml kemi shtuar keto rreshta të kodit
```
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```
Dhe tek file i application.properties i kemi shtuar keto rreshta të kodit:
```
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.flyway.baseline-on-migrate=true
spring.jpa.show-sql=true
```

Me këto konfigurime,kemi përcaktuar Flyway për të menaxhuar migrimet e bazës së të dhënave në MySQL dhe kemi  specifikuar migrimet e parapara në skriptën v1__create_tables.sql.


## Testimi i funksionalitetit të ORM-së dhe migrimet në databazë duke përdorur Postman

```
1. Për testim, kemi përdorur "Postman" për të eksaminuar funksionalitetin e "User" në projektin tonë
2. Kemi kryer një seri teste në nivele të ndryshme për të siguruar që çdo veprim dhe kërkesë përmbush specifikat dhe ofron rezultate të përshtatshme.
3. Kemi përdorur operacioni "GET" si CRUD operacion për të kryer testimin e nevojshëm për "User".
4. Përmes "Postman", kemi mundur të testojmë komunikimin mes aplikacionit tonë dhe klientëve të tij në një mënyrë të dokumentuar dhe të automatizuar.
5. Hapi përfundimtar ka përfshirë identifikimin dhe zgjidhjen e çdo problemi të mundshëm në një mënyrë efikase dhe efektive duke përdorur rezultatet nga testet e kryera në "Postman".
```
![Screenshot 2024-05-13 164443](https://github.com/RinesaBislimi/Backend-FoodOrder/assets/118773246/a08cce67-845c-4ac0-ac0c-cdf3e514d901)



