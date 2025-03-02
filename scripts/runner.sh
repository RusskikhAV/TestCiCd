#!/bin/bash
CMD="java -jar app.jar"
$CMD &
SERVICE_PID=$!
mvn test
wait "$SERVICE_PID"