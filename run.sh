#!/bin/bash

name=$1
if [[ -n "$name" ]]; then
	echo 'Compiling FSM ...'
	mvn -q clean install -DskipTests

	echo 'Executing FSM ...'
    mvn -q exec:java -Dexec.mainClass=$1
else
    echo '[Usage] ./run.sh [main class name]'
fi