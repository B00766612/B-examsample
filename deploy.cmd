
 @echo off

:: ----------------------
:: B00766612 MyAzure Deployment Script
:: Version:4
:: ----------------------

del D:\home\site\wwwroot\webapps\*.war
cd D:\home\site\repository\B00766612examsample\target\
::copy D:\home\site\repository\B00766612examsample\target\*.war D:\home\site\wwwroot\webapps\*.war
::copy D:\home\site\repository\B00766612examsample\target\*.war D:\home\site\wwwroot\webapps\*.war
::%DEPLOYMENT_TARGET%\webapps\*.war
::rename *.war ROOT.war
copy *.war D:\home\site\wwwroot\webapps\*.war
cd D:\home\site\wwwroot\webapps\
rename *.war ROOT.war
:: SET DEPLOYMENT_TARGET=%ARTIFACTS%\wwwroot\webapps
