#!/bin/bash
cd "${0%/*}"

# Compile the Java files
cd src
javac -d ../out *.java

# Run the Java class
cd ../out
java Main
