# MODAClouds SLA Core #

##Description##

The SLA Core is an implementation of an SLA module, compliant with WS-Agreement.

It supports:

* one-shot negotiation
* agreement enforcement
* REST API
* external monitoring platform: the MODAClouds SLA Core uses Tower 4Clouds. To use another monitoring platform, read the "Using another monitor service" section.

The related projects to SLA Core are:

* [SLA Mediator][4]: A Creator 4Clouds plugin to generate agreements.
* [SLA Dashboard][5]: A dashboard to check the status of the enforced agreements.
* [Tower 4Clouds][9]: The MODAClouds Monitor platform, responsible of suplying the violations of QoS to the SLA Core.
* [Models@Runtime][10]: The MODAClouds deployer, responsible of starting the enforcement of an application when this has been deployed.

Read the [documentation][1]

##Technical description##

For the internal workings of the SLA Core, read the [Developer Guide][2]

Below are described the interaction of the SLA Core with the other components of MODAClouds.

### Models@Runtime ###

Both the endpoint and the agreement id are included as part of the CloudML deployment model and, this way, are provided to the Models@Runtime engine. In particular, they are modelled using the concept of properties attached to the deployment model. Once the deployment is completed, the engine exploits these information to perform the appropriate REST call to enforce the
SLA agreement.

```
"properties" : [
  {
    "eClass" : "net.cloudml.core:Property",
    "name" : "sla_url",
    "value" : <URL>
  },
  {
    "eClass" : "net.cloudml.core:Property",
    "name" : "agreement_id",
    "value" : <UUID>
  }
],
```

### Tower 4Clouds ###

The MODAClouds SLA Core uses Tower 4Clouds as an external monitoring platform. The SLA Core takes advantage of the advanced metrics evaluation provided by Tower 4Clouds, and executes an [on-demand enforcement][11] on each QoS violation detected by Tower 4Clouds.

You have to take into account that the value of the metric parameter in the outputMetric action should be in the constraint of the guarantee term in the form `metric NOT_EXISTS`. For example, given the following monitoring rule:

```
  <mo:monitoringRule id="uuid" startEnabled="true" timeStep="60" timeWindow="60">
    <mo:collectedMetric metricName="ResponseTime"/>
    <mo:monitoredTargets>
      <mo:monitoredTarget class="Method" type="type/>
    </mo:monitoredTargets>
    <mo:metricAggregation aggregateFunction="Average"/>
    <mo:condition>METRIC &gt; 2500.0</mo:condition>
    <mo:actions>
      <mo:action name="outputMetric">
        <mo:parameter name="metric">method1_violated</mo:parameter>
        <mo:parameter name="value"/>
        <mo:parameter name="resourceId"/>
      </mo:action>
    </mo:actions>
  </mo:monitoringRule>
```

the associated guarantee term should be:

```
<wsag:GuaranteeTerm wsag:Name="gtname">
  <wsag:ServiceScope wsag:ServiceName="service">scope</wsag:ServiceScope>
  <wsag:ServiceLevelObjective>
    <wsag:KPITarget>
      <wsag:KPIName>ResponseTime</wsag:KPIName>
      <wsag:CustomServiceLevel>{"constraint": "method1_violated NOT_EXISTS"}</wsag:CustomServiceLevel>
    </wsag:KPITarget>
  </wsag:ServiceLevelObjective>
</wsag:GuaranteeTerm>
```

## Using another monitor service ##

If you need to use another monitor service, you will have to implement some classes to adapt the SLA Core to the type of service: 

* if the monitor pushes the metrics to the SLA Core, you will have to implement a metrics receiver. You have an example of metric receiver in `sla-service/src/main/java/eu/atos/sla/service/rest/MetricsReceiverRest.java`.
* if the SLA Core needs to pull the metrics from the monitor, you will have to implement a class that implements the interface `eu.atos.sla.monitoring.IMetricsRetrieverV2`. You have an dummy example of metric retriever in `sla-enforcement/src/main/java/eu/atos/sla/monitoring/simple/DummyMetricsRetriever.java`. Your retriever should need to do 
real work, e.g., retrieve the metrics from the monitor using the REST interface, and translate the received data into the internal objects used in the SLA Core.

For more details, read the [enforcement section][11] of the developer guide.

##How to deploy##

Read the [Installation Guide][3]

Alternatively, you can install a MODAClouds runtime platform with SLA Core and the needed Tower 4Clouds using the [Energizer 4Clouds integrated platform][6]

## Expected environment vars ##

The expected environment vars to run the module are below.

* `MODACLOUDS_MONITORING_MANAGER_URL` (default: `http://localhost:8170`)
* `MODACLOUDS_SLACORE_URL` (default: `http://localhost:9040`)
* `MODACLOUDS_MYSQL_URL` (A jdbc connection string in the format `jdbc:mysql://${IP}:${PORT}/${DB}`) (default: `jdbc:mysql://localhost:3306/atossla`)
* `MODACLOUDS_MYSQL_USERNAME` (default: `atossla`)
* `MODACLOUDS_MYSQL_PASSWORD` (default: `_atossla_`)

## Change List ##

v0.4.2:

* Fixed wrong callback url for T4C 

v0.4.1:

* Removed public endpoints in wrapper script

v0.4.0:

* T4C support

v0.3.0:

* Handling of master/slaves agreements
* Several modifications to be mOS-guidelines compliant
* Generation of mOS package


##License##

Licensed under the [Apache License, Version 2.0][8]

[1]: docs/TOC.md
[2]: docs/developer-guide.md
[3]: docs/installation-guide.md
[4]: https://github.com/modaclouds/modaclouds-sla-mediator
[5]: https://github.com/modaclouds/modaclouds-sla-dashboard
[6]: https://github.com/modaclouds/modaclouds-integrated-platform#runtime-platform
[8]: http://www.apache.org/licenses/LICENSE-2.0
[9]: http://deib-polimi.github.io/tower4clouds/docs/
[10]: https://github.com/SINTEF-9012/cloudml/wiki
[11]: https://github.com/modaclouds/modaclouds-sla-core/blob/master/docs/developer-guide.md#enforcement
