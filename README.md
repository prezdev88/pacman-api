# PacMan API

## Swagger UI
http://localhost:8080/swagger-ui/index.html

<details>
  <summary>Native packages endpoints</summary>

### Get installed packages (explicit)
```bash
curl -i -X GET "http://localhost:8080/api/v1/native/packages/installed/explicit"
```

### Get installed packages (explicit). Lite version (name and version)
```bash
curl -i -X GET "http://localhost:8080/api/v1/native/packages/installed/explicit/lite"
```

### Get packages to upgrade
```bash
curl -i -X GET "http://localhost:8080/api/v1/native/packages/upgrade?password=$rootPassword"
```

#### Response when root password is wrong
```
HTTP/1.1 400
{"message": "Wrong root password"}
```

#### Response when no package to upgrade
```
HTTP/1.1 204
```

### Get installed package by name
```bash
curl -i -X GET "http://localhost:8080/api/v1/native/packages/vlc"
```

#### Response when package not found
```
HTTP/1.1 404
{"message":"Package 'vlcs' not found"}
```

### Get upgraded packages
```bash
curl -i -X GET "http://localhost:8080/api/v1/native/packages/upgraded?year=$year&month=$month"
```

#### Response when no upgraded packages
```
HTTP/1.1 204
```
</details>


<details>
  <summary>Foreign packages endpoints</summary>

### Get installed packages (explicit)
```bash
curl -i -X GET "http://localhost:8080/api/v1/foreign/packages/installed/explicit"
```

### Get installed packages (explicit). Lite version (name and version)
```bash
curl -i -X GET "http://localhost:8080/api/v1/foreign/packages/installed/explicit/lite"
```

### Get packages to upgrade
```bash
curl -i -X GET "http://localhost:8080/api/v1/foreign/packages/upgrade?password=$rootPassword"
```

#### Response when root password is wrong
```
HTTP/1.1 400
{"message": "Wrong root password"}
```

#### Response when no package to upgrade
```
HTTP/1.1 204
```

### Get installed package by name
```commandline
curl -i -X GET "http://localhost:8080/api/v1/foreign/packages/google-chrome"
```

#### Response when package not found
```
HTTP/1.1 404
{"message":"Package 'vlcs' not found"}
```
</details>

<details>
  <summary>Group packages endpoints</summary>

### Get groups name
```bash
curl -i -X GET "http://localhost:8080/api/v1/native/groups"
```

### Get package names by group name
```bash
curl -i -X GET "http://localhost:8080/api/v1/native/groups/{name}"
```

### Get package by package group name
```bash
curl -i -X GET "http://localhost:8080/api/v1/native/groups/package/{name}"
```
</details>

<details>
  <summary>Packages</summary>

### Get package history by name
```bash
curl -i -X GET "http://localhost:8080/api/v1/packages/$name/history"
```

</details>

<details>
  <summary>Compile and run with JDK 21 GraalVM</summary>

## Install JDK 21 GraalVM
```bash
yay -S jdk21-graalvm-bin
```

## Compile 
```bash
export JAVA_HOME=/usr/lib/jvm/java-21-graalvm
./mvnw native:compile -Pnative -DskipTests
```

## Run
```bash
./target/pacman-api 
```
</details>