mvn clean install -DskipTests=true
mvn package
java -jar target/dependency/webapp-runner.jar target/*.war
