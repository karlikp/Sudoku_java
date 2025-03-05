# ✏️ Web Sudoku Game
A Sudoku game with the ability to register players and save their moves to a database. 

## Table of contents:
1. [Requirements](#1-requirements)
2. [Usage](#3-usage)
3. [Gallery](#4-gallery)

## 1. Requirements:
- Maven (https://maven.apache.org/download.cgi),
- Tomcat (https://tomcat.apache.org/download-10.cgi),
- Payara server.

Download Payara Server

## 2. Usage:
Clone repository:
```bash
git clone https://github.com/karlikp/Sudoku_java.git
```

Go to repository folder:
```bash
cd path/to/Sudoku_java
```

Build the project:
```bash
mvn clean package
```
execute sudoku:
```bash
java -jar target/Sudoku_Web-1.0.war
```
copy the `.war` file to the `webapps/` directory:
```bash
cp target/nazwa-aplikacji.war /ścieżka/do/tomcata/webapps/
```

Launch Tomcat:
```bash
cd /ścieżka/do/tomcata/bin
./catalina.sh run
```

## 3. Gallery:
