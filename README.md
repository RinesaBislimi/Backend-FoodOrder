# Përmbledhja e Projektit

# Konfigurimi i ORM-së
Projekti ynë shfrytëzon Java Spring Data JPA për menaxhim efikas të bazës së të dhënave. Me konfigurimet e vendosura për portin e serverit 5454 dhe `ddl-auto=update` të Spring JPA, sigurohet përditësim automatik i strukturës së bazës së të dhënave. Lidhja me MySQL ndodh përmes një URL standard `jdbc:mysql://${MYSQL_HOST:localhost}:3306/foodDB`, duke përdorur kredenciale të sigurta për emrin e përdoruesit dhe password.
