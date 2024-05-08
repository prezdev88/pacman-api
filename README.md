# PacMan API

<details>
  <summary>Native packages endpoints</summary>

### Get installed packages (explicit)
```commandline
curl -i -X GET "http://localhost:8080/api/v1/native/packages/installed/explicit"
```

### Get installed packages (explicit). Lite version (name and version)
```commandline
curl -i -X GET "http://localhost:8080/api/v1/native/packages/installed/explicit/lite"
```

### Get packages to upgrade
```commandline
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
```commandline
curl -i -X GET "http://localhost:8080/api/v1/native/packages/vlc"
```

#### Response when package not found
```
HTTP/1.1 404
{"message":"Package 'vlcs' not found"}
```
</details>


<details>
  <summary>Foreign packages endpoints</summary>

### Get installed packages (explicit)
```commandline
curl -i -X GET "http://localhost:8080/api/v1/foreign/packages/installed/explicit"
```

### Get installed packages (explicit). Lite version (name and version)
```commandline
curl -i -X GET "http://localhost:8080/api/v1/foreign/packages/installed/explicit/lite"
```

### Get packages to upgrade
```commandline
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
```commandline
curl -i -X GET "http://localhost:8080/api/v1/native/groups"
```

### Get package names by group name
```commandline
curl -i -X GET "http://localhost:8080/api/v1/native/groups/{name}"
```

### Get package by package group name
```commandline
curl -i -X GET "http://localhost:8080/api/v1/native/groups/package/{name}"
```
</details>
