
 @echo off

:: ----------------------
:: MyAzure Deployment Script
:: Version: 1.
:: ----------------------


copy D:\home\site\repository\B00766612examsample\target %DEPLOYMENT_TARGET%\webapps\
rename *.war ROOT.war
:: SET DEPLOYMENT_TARGET=%ARTIFACTS%\wwwroot\webapps
