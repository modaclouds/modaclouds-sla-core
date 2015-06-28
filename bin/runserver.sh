#
# Usage: $0 [port]
#   port: listening port; defaults to 9040
#
PORT=${1:-9040}
echo "MODACLOUDS_MONITORING_MANAGER_URL=$MODACLOUDS_MONITORING_MANAGER_URL"
echo "MODACLOUDS_SLACORE_URL=$MODACLOUDS_SLACORE_URL"
java -jar sla-service/target/dependency/jetty-runner.jar --port $PORT --path / sla-service/target/sla-service.war
