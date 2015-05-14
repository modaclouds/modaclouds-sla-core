#
# Usage: $0 [port]
#   port: listening port; defaults to 8080
#
PORT=${1:-8080}
java -jar sla-service/target/dependency/jetty-runner.jar --port $PORT --path /sla-service sla-service/target/sla-service.war
