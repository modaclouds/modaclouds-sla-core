REM Copy slacore project to $1 directory parameter
REM The slacore project is located in %ORIGIN%. Change the variable 
REM according to your location

SET ORIGIN=C:\AtosSLA\sourceCode\trunk
SET DESTINATION=%1
SET CURRENTDIR=%CD%

IF not exist "%DESTINATION%" (mkdir "%DESTINATION%")
CD %ORIGIN%

copy configuration.properties.sample %DESTINATION%
copy pom.xml %DESTINATION%
copy README.md %DESTINATION%
IF not exist "%DESTINATION%\docs" (mkdir "%DESTINATION%\docs")
xcopy /E /Q /Y "%ORIGIN%\docs" "%DESTINATION%\docs"
IF not exist "%DESTINATION%\samples" (mkdir "%DESTINATION%\samples")
xcopy /E /Q /Y  "%ORIGIN%\samples" "%DESTINATION%\samples"
IF not exist "%DESTINATION%\bin" (mkdir "%DESTINATION%\bin")
xcopy /E /Q /Y  "%ORIGIN%\bin" "%DESTINATION%\bin"

IF not exist "%DESTINATION%\sla-management\src" (mkdir "%DESTINATION%\sla-management\src")
xcopy /E /Q /Y  "%ORIGIN%\sla-management\src" "%DESTINATION%\sla-management\src"
copy "%ORIGIN%\sla-management\pom.xml" "%DESTINATION%\sla-management\" 

IF not exist "%DESTINATION%\sla-personalization\src" (mkdir "%DESTINATION%\sla-personalization\src")
IF not exist "%DESTINATION%\sla-personalization\pom.xml" (copy "%ORIGIN%\sla-personalization\pom.xml" "%DESTINATION%\sla-personalization\")
IF not exist "%DESTINATION%\sla-personalization\src\main\resources" (mkdir "%DESTINATION%\sla-personalization\src\main\resources\")
IF not exist "%DESTINATION%\sla-personalization\src\main\resources\security-context.xml" (copy "%ORIGIN%\sla-personalization\src\main\resources\security-context.xml" "%DESTINATION%\sla-personalization\src\main\resources\")

IF not exist "%DESTINATION%\sla-repository\src" (mkdir "%DESTINATION%\sla-repository\src")
xcopy /E /Q /Y  "%ORIGIN%\sla-repository\src" "%DESTINATION%\sla-repository\src"
copy "%ORIGIN%\sla-repository\pom.xml" "%DESTINATION%\sla-repository\" 

IF not exist "%DESTINATION%\sla-serializers\src" (mkdir "%DESTINATION%\sla-serializers\src")
xcopy /E /Q /Y  "%ORIGIN%\sla-serializers\src" "%DESTINATION%\sla-serializers\src"
copy "%ORIGIN%\sla-serializers\pom.xml" "%DESTINATION%\sla-serializers\" 

IF not exist "%DESTINATION%\sla-service\src" (mkdir "%DESTINATION%\sla-service\src")
xcopy /E /Q /Y  "%ORIGIN%\sla-service\src" "%DESTINATION%\sla-service\src"
copy "%ORIGIN%\sla-service\pom.xml" "%DESTINATION%\sla-service\" 


CD %CURRENTDIR%