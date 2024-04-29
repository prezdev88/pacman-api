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

yay:
- get upgrade packages info native (yay -Qun)
- get upgrade packages info foreign (yay -Qum)

# Architecture


API --> PackageService --> PacmanImpl --> CommandService
                      |--> YayImpl

