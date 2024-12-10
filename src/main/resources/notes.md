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
0 Repositorio               : extra
1 Nombre                    : xorg-font-util
2 Versión                   : 1.4.1-1
3 Descripción               : X.Org font utilities
4 Arquitectura              : x86_64
5 URL                       : https://xorg.freedesktop.org/
6 Licencias                 : custom
7 Grupos                    : xorg-fonts  xorg
8 Provee                    : font-util
9 Depende de                : glibc
10 Dependencias opcionales  : Nada
11 En conflicto con         : font-util
12 Remplaza a               : font-util
13 Tamaño de la descarga    : 33,46 KiB
14 Tamaño de la instalación : 228,23 KiB
15 Encargado                : Andreas Radke <andyrtr@archlinux.org>
16 Fecha de creación        : vie 08 sep 2023 06:38:47
17 Validado por             : Suma MD5  Suma SHA-256  Firma
```

# Hacer (See: PackageService)
La idea es que existan dos implementaciones de PackageService:
- NativePackageServiceImpl
- ForeignPackageServiceImpl

#  ForeignPackageServiceImpl
## Get foreign installed package
pacman -Qmei

## Get foreign installed package (lite)
pacman -Qme

## Get upgrade foreign packages
yay -Qum

## Get foreign package info by package name
pacman -Qmei visual-studio-code-bin

## Get installed packages log apps by month and year
grep '] installed' /var/log/pacman.log | grep '\[2024-12'

## Get removed packages log apps by month and year
grep '] removed' /var/log/pacman.log | grep '\[2024-12'

## Get package history log by package name
grep '\[ALPM\]' /var/log/pacman.log | grep 'discord'
