#!/bin/sh

echo building and starting application

dir=$(pwd)

echo "cd $dir/agreementsoap;mvn clean install;cd application-soap-app;mvn spring-boot:run;rm ../soap.sh" >> soap.sh
chmod 700 soap.sh
open -a terminal soap.sh
echo "Waiting for soap-service to start - waiting 1 min"
sleep 60
echo "cd $dir/agreement;mvn clean install;cd application-app;mvn spring-boot:run;rm ../rest.sh" >> rest.sh
chmod 700 rest.sh
open -a terminal rest.sh
