#!/bin/bash
# Use -gt 1 to consume two arguments per pass in the loop (e.g. each
# argument has a corresponding value to go with it).
# Use -gt 0 to consume one or more arguments per pass in the loop (e.g.
# some arguments don't have a corresponding value to go with it such
# as in the --default example).
# note: if this is set to -gt 0 the /etc/hosts part is not recognized ( may be a bug )
while [[ $# -gt 1 ]]
do
key="$1"

case $key in
    -f|--file)
    FILE="$2"
    shift # past argument
    ;;
    -n|--name)
    NAME="$2"
    shift # past argument
    ;;
    -w|--workspace)
    WORKSPACE="$2"
    shift # past argument
    ;;
    *)
            # unknown option
    ;;
esac
shift # past argument or value
done

echo 'Compiling the Generator ...'
cd FSMStateMachine
mvn -q clean install -DskipTests

if [ -n "${FILE}" ] && [ -n "${NAME}" ] && [ -n "${WORKSPACE}" ]; then
    mvn -q exec:exec -DargumentA=-f -DargumentB=../"${FILE}" -DargumentC=-n -DargumentD=${NAME} -DargumentE=-w -DargumentF=${WORKSPACE}

elif [ -n "${FILE}" ] && [ -n "${NAME}" ]; then
    mvn -q exec:exec -DargumentA=-f -DargumentB=../"${FILE}" -DargumentC=-n -DargumentD=${NAME} 


elif [ -n "${FILE}" ] &&  [ -n "${WORKSPACE}" ]; then
    mvn -q exec:exec -DargumentA=-f -DargumentB=../"${FILE}" -DargumentC=-w -DargumentD=${WORKSPACE}

elif [[  -n "${FILE}"  ]]; then
    mvn -q exec:exec -DargumentA=-f -DargumentB=../"${FILE}" 

else
    echo '[Usage] ./generate.sh -f [scxml file name]'
fi


if [[ -n $1 ]]; then
    echo "Last line of file specified as non-opt/last argument:"
    tail -1 $1
fi




