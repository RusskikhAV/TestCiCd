FROM openjdk:17
COPY . .
COPY /scripts/runner.sh /scripts/runner.sh

RUN ["chmod", "+x", "/scripts/runner.sh"]

ENTRYPOINT ["/scripts/runner.sh"]