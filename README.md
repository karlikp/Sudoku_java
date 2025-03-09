# ✏️ Web Sudoku Game
A Sudoku game with the ability to register players and save their moves to a database. 

## Table of contents:
1. [Requirements](#1-requirements)
2. [Instalation](#2-instalation)
3. [Usage](#3-usage)
4. [Gallery](#4-gallery)

## 1. Requirements:
- Apache Maven,
- Apache Derby (include repository),
- Payara server.

## 2. Instalation:
- ### 2.1 Download Apache Maven:
  🔗 https://maven.apache.org/download.cgi

  Download the "Binary zip archive" (e.g., apache-maven-3.9.6-bin.zip).

- ### 2.2 Extract the ZIP file:

  Extract it to a directory, for example:
  
  `C:\apache-maven`

- ### 2.3 Add Maven to system environment variables:

  Click Start → Type "Environment Variables" → Edit system environment variables.
  In the System Variables section, find Path, click Edit, and add a new entry:

  `C:\apache-maven\bin`

  Click OK and close all windows.

- ### 2.4 Verify the installation:
  Open Command Prompt (cmd) and type:
  `mvn -version`
 
  You should see something like this:
  ```bash
  Apache Maven 3.x.x
  Maven home: C:\apache-maven
  Java version: 17.0.x
  ```

- ### 2.5 Download Payara Server on Windows:
  🔗 https://www.payara.fish/downloads/
  Download the Payara Server Full version.

- ### 2.6 Extract the ZIP file:
  Extract it to a directory, for example
  `C:\payara_server`

- ### 2.7 Configure Environment Variables (Optional)

    Open Control Panel → System → Advanced System Settings → Environment Variables.
    Under System Variables, find Path, click Edit, and add: `C:\path\to\your\payara\bin`
    Click OK to save changes.
  
- ### 2.8 Clone the Sudoku_java repository:
  ```bash
  git clone https://github.com/karlikp/Sudoku_java.git
  ```
  
## 3. Usage:

- ### 3.1 Starting the Apache Derby DB Server

Navigate to the Derby directory:
```bash
cd /path/to/Sudoku_java/derby_db
```

Start the DB server:
```bash
./startNetworkServer
```
- ### 3.2 Starting the Payara Application Server

Navigate to the Payara server directory:
```bash
cd /path/to/your/payara_server/bin
```

Start the server:
```bash
./asadmin start-domain
```

- ### 3.3 Build the Sudoku Application

Go to app_source folder :
```bash
cd path/to/Sudoku_java/app_source
```

Build the project:
```bash
mvn clean package
```

- ### 3.4 Deploying the Sudoku Application

Navigate to the app_source directory:
```bash
cd path/to/Sudoku_java/app_source/target
```
You should saw `Sudoku_Web-1.0.war`.

Deploy the application to Payara:
```bash
/path/to/payara_server/bin/asadmin deploy Sudoku_Web-1.0.war
```

Check deployment status:
```bash
/path/to/your/payara_server/bin/asadmin list-applications
```

If you see "Sudoku_Web-1.0", the application has been successfully deployed.

Open the application in a browser:
```bash
http://localhost:8080/Sudoku_Web-1.0
```

## 4. Gallery:

<div align="center">
  <img src="https://github.com/user-attachments/assets/8f186cdf-4320-435a-b803-002c3acca3e1" alt="menu">
  <p>Fig. 1. Start page</p>
</div>
<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/ed8788c8-a1f0-4731-8cc2-7868a6afb227" alt="menu">
  <p>Fig. 2. Gameplay</p>
</div>
<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/5f1306ce-237f-4918-a668-e120d22f3895" alt="menu">
  <p>Fig. 3. Example of information about unauthorized movement</p>
</div>
<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/cae10c2f-8054-4c02-8ccf-ff9048ad4c82" alt="menu">
  <p>Fig. 4. Movements history of current game</p>
</div>
<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/b4081b1c-2b4b-4768-87d6-ce0446dbdd9d" alt="menu">
  <p>Fig. 5. Game Database</p>
</div>
<br>

