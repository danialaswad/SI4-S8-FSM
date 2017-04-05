#!/bin/bash

name=$1
if [[ -n "$name" ]]; then
	echo 'Compiling the Generator ...'
	cd FSMGenerator/
	mvn -q clean install -DskipTests

	echo 'Executing the Generator ...'
	mvn -q exec:exec -DargumentA=../resources/$1 
else
    echo '[Usage] ./generate.sh [scxml file name]'
fi