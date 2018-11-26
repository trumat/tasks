#!/usr/bin/env bash

stop_tomcat()
{
  $CATALINA_HOME/bin/catalina.sh stop
}

fail() {
  echo "There were errors"
}

if ./runcrud.sh; then
    firefox http://localhost:8080/crud/v1/task/getTasks
else
    stop_tomcat
    fail
fi
