@echo off
if not exist "bin" mkdir bin

echo Compiling...
javac -d bin -cp "lib/*;src/main/java" src/main/java/com/kagami/atm/*.java src/main/java/com/kagami/atm/ui/*.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation Failed!
    pause
    exit /b
)

echo Running ATM App...
java -cp "bin;lib/*" com.kagami.atm.ui.atmframe
pause
