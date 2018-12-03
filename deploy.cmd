
 @echo off

:: ----------------------
:: MyAzure Deployment Script
:: Version: 1.
:: ----------------------

cd D:\home\site\repository\B00766612examsample\target\
::copy D:\home\site\repository\B00766612examsample\target\*.war %DEPLOYMENT_TARGET%\webapps\*.war
rename *.war ROOT.war
copy *.war %DEPLOYMENT_TARGET%\webapps\*.war
:: SET DEPLOYMENT_TARGET=%ARTIFACTS%\wwwroot\webapps
