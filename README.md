# MODAClouds SLA Core #

##Description##

The SLA Core is an implementation of an SLA module, compliant with WS-Agreement.

It supports:

* one-shot negotiation
* agreement enforcement
* REST API

Read the [documentation][1]

##Technical description##

Read the [Developer Guide][2]

##How to deploy##

Read the [Installation Guide][3]

## Expected environment vars ##

* MODACLOUDS_MONITORING_MANAGER_URL
* MODACLOUDS_SLACORE_URL
* MODACLOUDS_MYSQL_URL (= jdbc:mysql://${MODACLOUDS_MYSQL_IP}:${MODACLOUDS_MYSQL_PORT}/${MODACLOUDS_MYSQL_DB})
* MODACLOUDS_MYSQL_USERNAME
* MODACLOUDS_MYSQL_PASSWORD

## Change List ##

v0.3.0:

* Handling of master/slaves agreements
* Several modifications to be mOS-guidelines compliant
* Generation of mOS package


##License##

Licensed under the [Apache License, Version 2.0][8]

[1]: docs/TOC.md
[2]: docs/developer-guide.md
[3]: docs/installation-guide.md
[8]: http://www.apache.org/licenses/LICENSE-2.0
