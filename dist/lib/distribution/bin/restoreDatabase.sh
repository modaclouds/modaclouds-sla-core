#!/usr/bin/env bash
#
# Copyright 2015 Atos
# Contact: Atos <roman.sosa@atos.net>
#
#    Licensed under the Apache License, Version 2.0 (the "License");
#    you may not use this file except in compliance with the License.
#    You may obtain a copy of the License at
#
#        http://www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.
#

DIR=$(cd "$(dirname "$0")" && pwd)

SCHEMA="$DIR/../share/schema.sql"

MODACLOUDS_MYSQL_IP=${MODACLOUDS_MYSQL_IP:-127.0.0.1}
MODACLOUDS_MYSQL_PORT=${MODACLOUDS_MYSQL_PORT:-3306}
MODACLOUDS_MYSQL_DB=${MODACLOUDS_MYSQL_DB:-atossla}
MODACLOUDS_MYSQL_USERNAME=${MODACLOUDS_MYSQL_USERNAME:-atossla}
MODACLOUDS_MYSQL_PASSWORD=${MODACLOUDS_MYSQL_PASSWORD:-_atossla_}

echo "Cleaning database: DB='$MODACLOUDS_MYSQL_DB' USER='$MODACLOUDS_MYSQL_USERNAME'"
mysql --host="${MODACLOUDS_MYSQL_IP}" --port="${MODACLOUDS_MYSQL_PORT}" \
	-p"${MODACLOUDS_MYSQL_PASSWORD}" -u "${MODACLOUDS_MYSQL_USERNAME}" "${MODACLOUDS_MYSQL_DB}" < "$SCHEMA"

