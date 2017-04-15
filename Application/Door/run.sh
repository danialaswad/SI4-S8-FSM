#!/bin/bash
cd ../../FSMStateMachine
mvn -q clean package install -DskipTests

cd ../Application/Door
echo 'Executing FSM ...'
mvn -q clean package
mvn -q exec:java -Dexec.mainClass=WorkSpaceMyDoor

