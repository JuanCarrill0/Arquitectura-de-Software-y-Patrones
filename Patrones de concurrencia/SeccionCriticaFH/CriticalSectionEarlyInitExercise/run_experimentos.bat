@echo off
echo Compilando clases Java...
javac ClientManager.java MiscUtil.java FileLogger.java FileUtil.java Logger.java

echo Ejecutando 100 experimentos...
for /L %%i in (0,1,99) do (
    echo Ejecutando experimento %%i
    java ClientManager %%i
)

echo Todos los experimentos han sido completados.
pause
