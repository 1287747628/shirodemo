#! /bin/bash

nohup java -jar -Dspring.config.location=/opt/fonsview/NE/demo/application.yml /opt/fonsview/NE/demo/shirodemo-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &

echo 'success'