#! /bin/bash

nohup java -jar -Dspring.config.location=/opt/fonsview/NE/demo/application.yml /opt/fonsview/NE/demo/shirodemo-0.0.1-SNAPSHOT.jar > /opt/fonsview/NE/demo/log/shirodemo.log 2>&1 &

echo 'success'