#!/bin/bash

name=$1
if [[ -n "$name" ]]; then
	cd FSMStateMachine 
	mvn -q clean package install -DskipTests
	cd ..
	echo 'Compiling FSM ...'
	mvn -q clean package install -DskipTests

	echo 'Executing FSM ...'
    mvn -q exec:java -Dexec.mainClass=$1
else
    echo '[Usage] ./run.sh [main class name]'
fi