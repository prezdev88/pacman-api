# PacMan API

## Endpoints

### Get installed packages (explicit)
```commandline
curl -X GET "http://localhost:8080/api/v1/pacman/packages/installed/explicit"
```

### Get installed packages (explicit). Lite version (name and version)
```commandline
curl -X GET "http://localhost:8080/api/v1/pacman/packages/installed/explicit/lite"
```

### Get packages to upgrade
```commandline
curl -X GET "http://localhost:8080/api/v1/pacman/packages/upgrade?password=$rootPassword"
```

### Get installed package by name
```commandline
curl -X GET "http://localhost:8080/api/v1/pacman/packages/vlc"
```