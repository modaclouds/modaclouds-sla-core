#!/usr/bin/env bash
if [ "$0" != "bin/restoreDatabase.sh" ]; then
	echo "Must be executed from project root"
	exit 1
fi
PWD=$(grep "db.password" configuration.properties | sed -e 's/.*= *\(.*\) *$/\1/')
mysql -p$PWD -u atossla atossla < sla-repository/src/main/resources/sql/atossla.sql
