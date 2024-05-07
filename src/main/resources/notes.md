# Commands

# Native
pacman -Qni

# Foreign
pacman -Qmi

# Native (installed packages)
pacman -Qnei

# Foreign (installed packages)
pacman -Qmei

# Upgrades info
pacman -Qu

# Get info by package name
pacman -Qi libxft

# Upgrades native (yay)
yay -Qun

# Upgrades Foreign (yay)
yay -Qum

# Upgrade native and foreign
yay -Qu

# Uses cases
pacman:
- get installed packages (native) (pacman -Qnei)
- get installed packages (foreign) (pacman -Qmei)
- get upgrade packages info (pacman -Qu)
- get package info by name (pacman -Qi $name)

yay:
- get upgrade packages info native (yay -Qun)
- get upgrade packages info foreign (yay -Qum)

# Architecture


API --> PackageService --> PacmanImpl --> CommandService
                      |--> YayImpl

# Model: Package example
```
Nombre                    : xterm
Versión                   : 390-1
Descripción               : X Terminal Emulator
Arquitectura              : x86_64
URL                       : https://invisible-island.net/xterm/
Licencias                 : custom
Grupos                    : Nada
Provee                    : Nada
Depende de                : libxft  libxaw  ncurses  luit  xbitmaps  libutempter  libxkbfile
Dependencias opcionales   : Nada
Exigido por               : Nada
Opcional para             : graphviz  xorg-xinit
En conflicto con          : Nada
Remplaza a                : Nada
Tamaño de la instalación  : 1014,71 KiB
Encargado                 : T.J. Townsend <blakkheim@archlinux.org>
Fecha de creación         : mar 20 feb 2024 12:31:31
Fecha de instalación      : jue 22 feb 2024 14:48:46
Motivo de la instalación  : Instalado explícitamente
Guion de instalación      : No
Validado por              : Firma
```


# Grupos
pacman -Sg

# Paquetes de un grupo
pacman -Sg ${groupName}

# Info del paquete de un grupo
pacman -Si ${packageName}

# Model: Info del paquete de un grupo
```
Repositorio               : extra
Nombre                    : xorg-font-util
Versión                   : 1.4.1-1
Descripción               : X.Org font utilities
Arquitectura              : x86_64
URL                       : https://xorg.freedesktop.org/
Licencias                 : custom
Grupos                    : xorg-fonts  xorg
Provee                    : font-util
Depende de                : glibc
Dependencias opcionales   : Nada
En conflicto con          : font-util
Remplaza a                : font-util
Tamaño de la descarga     : 33,46 KiB
Tamaño de la instalación  : 228,23 KiB
Encargado                 : Andreas Radke <andyrtr@archlinux.org>
Fecha de creación         : vie 08 sep 2023 06:38:47
Validado por              : Suma MD5  Suma SHA-256  Firma
```