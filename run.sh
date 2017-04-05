#!/bin/bash

name=$1
if [[ -n "$name" ]]; then
	echo 'Compiling FSM ...'
	cd FSMCompiler/
	mvn -q clean install -DskipTests

	echo 'Executing FSM ...'
    mvn -q exec:java -Dexec.mainClass="finite.state.machine."$1
else
    echo '[Usage] ./run.sh [main class name]'
fi