@echo off

cd %~dp0
if not exist bin md bin
echo Main-Class: au.edu.griffith.ict.Main > %TEMP%\manifest.txt

javac src\au\edu\griffith\ict\*.java -d bin\
cd bin
if exist coes-main.jar del coes-main.jar
jar cvfm coes-main.jar %TEMP%\manifest.txt *
cd ..