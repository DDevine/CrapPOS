#!/bin/bash

[ -d bin ] || mkdir bin
javac src/au/edu/griffith/ict/*.java -d bin/

echo 'Main-Class: au.edu.griffith.ict.Main' > manifest.txt

cd bin
[ -f coes-main.jar ] && rm -f coes-main.jar
jar cvfm coes-main.jar ../manifest.txt *
cd ..
rm manifest.txt

