#!/usr/bin/env bash

OUTPUT=../target/service-run-flexiant.bash

cp ../bin/service-run.bash $OUTPUT

if [ -z "$PACKAGE_JSON" ]; then
	echo "PACKAGE_JSON env var not set. You must to package.json path"
	exit 1
fi

function get_var() {
	awk -F'"' "/$1/ {print \$4;}" "$PACKAGE_JSON"
}

function sed_var() {
	local var="environment:$1"
	local subst="@{definitions:$var}"
	local value=$(get_var $var)

	sed -i -e"s|$subst|$value|" "$OUTPUT"
}

IP=127.0.0.1
VERSION=0.7.0.13

sed -i -e's|^.*getent.*$|if test -n ""; then|' "$OUTPUT"
sed_var TOWER4CLOUDS_MANAGER_ENDPOINT_IP
sed_var TOWER4CLOUDS_MANAGER_ENDPOINT_PORT
sed_var SLACORE_ENDPOINT_IP
sed_var SLACORE_PUBLIC_ENDPOINT_IP
sed_var SLACORE_ENDPOINT_PORT
sed_var MYSQL_ENDPOINT_IP
sed_var MYSQL_ENDPOINT_PORT
sed_var MYSQL_DATABASE
sed_var MYSQL_USERNAME
sed_var MYSQL_PASSWORD
sed -i -e"s|@{definitions:environment:JAVA_HOME}|$JAVA_HOME|" "$OUTPUT"
sed -i -e"s|'@{definitions:environment:PATH}'|\"\$PATH\"|" "$OUTPUT"
sed -i -e"s|@{definitions:environment:TMPDIR}|/tmp/modaclouds/modaclouds-services-sla-core-$VERSION|" "$OUTPUT"
sed -i -e"s|@{definitions:environment:BIN}|/opt/modaclouds-services-sla-core-$VERSION/bin|" "$OUTPUT"
sed -i -e"s|@{definitions:environment:LIB}|/opt/modaclouds-services-sla-core-$VERSION/lib|" "$OUTPUT"


#sed -i -e's#^_JETTY_HOME.*$#_JETTY_HOME=$(cygpath -w "${_TMPDIR}/tmp")#' "$OUTPUT"

echo "Output file is in $OUTPUT"
