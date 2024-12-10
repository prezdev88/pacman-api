#!/bin/bash

# yay -S jdk21-graalvm-bin
export JAVA_HOME=/usr/lib/jvm/java-21-graalvm
./mvnw native:compile -Pnative -DskipTests
./target/pacman-api
