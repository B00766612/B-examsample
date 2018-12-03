
 @echo off

:: ----------------------
:: MyAzure Deployment Script
:: Version: 1.
:: ----------------------

del D:\home\site\wwwroot\webapps\*.war
::cd D:\home\site\repository\B00766612examsample\target\
::copy D:\home\site\repository\B00766612examsample\target\*.war D:\home\site\wwwroot\webapps\*.war
::copy D:\home\site\repository\B00766612examsample\target\*.war D:\home\site\wwwroot\webapps\*.war
::%DEPLOYMENT_TARGET%\webapps\*.war
::rename *.war ROOT.war
copy D:\home\site\repository\B00766612examsample\target\*.war D:\home\site\wwwroot\webapps\*.war
rename D:\home\site\wwwroot\webapps\*.war D:\home\site\wwwroot\webapps\ROOT.war
:: SET DEPLOYMENT_TARGET=%ARTIFACTS%\wwwroot\webapps
