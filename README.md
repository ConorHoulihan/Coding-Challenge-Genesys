# Coding-Challenge-Genesys
Genesys_Project is the most recent version, it implements testng and the page object model

DockerSelTest is the closest I came to completing this challenge.
In order to run it you need to enter the following in console:
docker pull selenium/standalone-chrome:87.0-20210106
Once that has completed you need to enter:
docker run -d -p 4444:4444 -v /dev/shm:/dev/shm selenium/standalone-chrome:87.0-20210106
Project can then be run through a java ide, apolgies for not getting in running fully through docker.
