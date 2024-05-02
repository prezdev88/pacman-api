# PacMan API

## Endpoints

### Get installed packages (explicit)
```commandline
curl -i -X GET "http://localhost:8080/api/v1/pacman/packages/installed/explicit"
```

### Get installed packages (explicit). Lite version (name and version)
```commandline
curl -i -X GET "http://localhost:8080/api/v1/pacman/packages/installed/explicit/lite"
```

### Get packages to upgrade
```commandline
curl -i -X GET "http://localhost:8080/api/v1/pacman/packages/upgrade?password=$rootPassword"
```

#### Response when root password is wrong
```
HTTP/1.1 404
{"message": "Wrong root password"}
```

#### Response when no package to upgrade
```
HTTP/1.1 204
```

### Get installed package by name
```commandline
curl -i -X GET "http://localhost:8080/api/v1/pacman/packages/vlc"
```

#### Response when package not found
```
HTTP/1.1 404
{"message":"Package 'vlcs' not found"}
```