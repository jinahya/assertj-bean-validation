@echo off
SET jakarta="assertj-bean-validation-jakarta\src\main\java\com\github\jinahya\assertj\validation\jakarta"
SET javax="assertj-bean-validation-javax\src\main\java\com\github\jinahya\assertj\validation\javax"
REM start cmd.exe /c "%userprofile%\bin\idea.cmd diff assertj-bean-validation-jakarta\src\main\java\com\github\jinahya\assertj\validation\jakarta assertj-bean-validation-javax\src\main\java\com\github\jinahya\assertj\validation\javax"
start cmd.exe /c "%userprofile%\bin\idea.cmd diff %jakarta% %javax%"
