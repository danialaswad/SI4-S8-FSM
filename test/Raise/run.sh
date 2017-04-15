#!/bin/bash


echo 'Compiling FSM ...'
mvn -q clean install -DskipTests

echo 'Executing FSM ...'
mvn -q exec:java -Dexec.mainClass=WorkSpaceMyRaise
