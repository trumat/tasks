#!/usr/bin/env bash

fail() {
  echo "There were errors"
}

stop_tomcat()
{
  $CATALINA_HOME/bin/catalina.sh stop
}

if ./crud.sh then
    firefox http://localhost:8080/crud/v1/tasks/showTasks
else
    stop_tomcat
    fail
fi