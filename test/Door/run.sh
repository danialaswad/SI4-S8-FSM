#!/bin/bash

mvn -q clean install -DskipTests

echo 'Executing FSM ...'
mvn -q exec:java -Dexec.mainClass=WorkSpaceMyDoor
