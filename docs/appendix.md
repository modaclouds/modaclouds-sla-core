#Appendix REST API examples#

##Providers<a name="providers"></a>##

###Create a provider###

Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/appendix/provider01.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/providers

	POST /sla-service/providers HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 117
	<?xml version="1.0" encoding="UTF-8"?><provider>    <uuid>provid
	er01</uuid>    <name>provider01name</name></provider>

	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/providers/provider01
	Content-Type: application/xml
	Content-Length: 254
	Date: Thu, 06 Nov 2014 16:33:12 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><message 
	code="201" message="The provider has been stored successfully in
	 the SLA Repository Database. It has location http://localhost:8
	080/sla-service/providers/provider01" elementId="provider01"/>

---

Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/appendix/provider02.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/providers

	POST /sla-service/providers HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 117
	<?xml version="1.0" encoding="UTF-8"?><provider>    <uuid>provid
	er02</uuid>    <name>provider02name</name></provider>

	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/providers/provider02
	Content-Type: application/xml
	Content-Length: 254
	Date: Thu, 06 Nov 2014 16:33:12 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><message 
	code="201" message="The provider has been stored successfully in
	 the SLA Repository Database. It has location http://localhost:8
	080/sla-service/providers/provider02" elementId="provider02"/>

---

Content type: application/json

	$ /usr/bin/curl -u user:password -d@samples/appendix/provider03.json -X POST -H Content-type:application/json -H Accept:application/json http://localhost:8080/sla-service/providers

	POST /sla-service/providers HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/json
	Accept:application/json
	Content-Length: 45
	{"uuid":"provider03","name":"provider03name"}

	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/providers/provider03
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Thu, 06 Nov 2014 16:33:12 GMT
	c2
	{"code":201,"message":"The provider has been stored successfully
	 in the SLA Repository Database. It has location http://localhos
	t:8080/sla-service/providers/provider03","elementId":"provider03
	"}

---

Provider exists.
Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/appendix/provider02.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/providers

	POST /sla-service/providers HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 117
	<?xml version="1.0" encoding="UTF-8"?><provider>    <uuid>provid
	er02</uuid>    <name>provider02name</name></provider>

	HTTP/1.1 409 Conflict
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 181
	Date: Thu, 06 Nov 2014 16:33:13 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><error co
	de="409" message="Provider with id:provider02 or name:provider02
	name already exists in the SLA Repository Database"/>

###Get a provider###

Accept: application/xml

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/xml http://localhost:8080/sla-service/providers/provider02?

	GET /sla-service/providers/provider02? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 126
	Date: Thu, 06 Nov 2014 16:33:13 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><provider
	><uuid>provider02</uuid><name>provider02name</name></provider>

---

Accept: application/json

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/json http://localhost:8080/sla-service/providers/provider02?

	GET /sla-service/providers/provider02? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/json

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Thu, 06 Nov 2014 16:33:13 GMT
	2d
	{"uuid":"provider02","name":"provider02name"}

---

Provider not exists.
Accept: application/xml

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/xml http://localhost:8080/sla-service/providers/notexists?

	GET /sla-service/providers/notexists? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 404 Not Found
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 156
	Date: Thu, 06 Nov 2014 16:33:14 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><error co
	de="404" message="There is no provider with uuid notexists in th
	e SLA Repository Database"/>

###Get all the providers###

Accept: application/xml

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/xml http://localhost:8080/sla-service/providers?

	GET /sla-service/providers? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 291
	Date: Thu, 06 Nov 2014 16:33:14 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><provider
	s><provider><uuid>provider01</uuid><name>provider01name</name></
	provider><provider><uuid>provider02</uuid><name>provider02name</
	name></provider><provider><uuid>provider03</uuid><name>provider0
	3name</name></provider></providers>

---

Accept: application/json

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/json http://localhost:8080/sla-service/providers?

	GET /sla-service/providers? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/json

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Thu, 06 Nov 2014 16:33:14 GMT
	8b
	[{"uuid":"provider01","name":"provider01name"},{"uuid":"provider
	02","name":"provider02name"},{"uuid":"provider03","name":"provid
	er03name"}]

###Delete a provider###



	$ /usr/bin/curl -u user:password -X DELETE -H Accept:application/xml http://localhost:8080/sla-service/providers/provider03

	DELETE /sla-service/providers/provider03 HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Transfer-Encoding: chunked
	Date: Thu, 06 Nov 2014 16:33:16 GMT
	3a
	The provider with uuid provider03 was deleted successfully

---

Provider not exists


	$ /usr/bin/curl -u user:password -X DELETE -H Accept:application/xml http://localhost:8080/sla-service/providers/notexists

	DELETE /sla-service/providers/notexists HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 404 Not Found
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Transfer-Encoding: chunked
	Date: Thu, 06 Nov 2014 16:33:16 GMT
	9e
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>.<error c
	ode="404" message="There is no provider with uuid notexists in t
	he SLA Repository Database"/>.

##Templates<a name="templates"></a>##

###Create a template###

Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/appendix/template01.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/templates

	POST /sla-service/templates HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 2535

	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/templates/template01
	Content-Type: application/xml
	Content-Length: 254
	Date: Thu, 06 Nov 2014 16:33:16 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><message 
	code="201" message="The template has been stored successfully in
	 the SLA Repository Database. It has location http://localhost:8
	080/sla-service/templates/template01" elementId="template01"/>

---

Content type: application/json

	$ /usr/bin/curl -u user:password -d@samples/appendix/template02.json -X POST -H Content-type:application/json -H Accept:application/json http://localhost:8080/sla-service/templates

	POST /sla-service/templates HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/json
	Accept:application/json
	Content-Length: 718
	{"templateId":"template02","context":{"agreementInitiator":"prov
	ider02","agreementResponder":null,"serviceProvider":"AgreementIn
	itiator","templateId":"template02","service":"service02","expira
	tionTime":"2014-03-07T12:00:00CET"},"name":"ExampleTemplate","te
	rms":{"allTerms":{"serviceDescriptionTerm":{"name":null,"service
	Name":null},"serviceProperties":[{"name":null,"serviceName":null
	,"variableSet":null},{"name":null,"serviceName":null,"variableSe
	t":null}],"guaranteeTerms":[{"name":"FastReaction","serviceScope
	":{"serviceName":"GPS0001","value":"               http://www.gp
	s.com/coordsservice/getcoords            "},"serviceLevelObjetiv
	e":{"kpitarget":{"kpiName":"FastResponseTime","customServiceLeve
	l":null}}}]}}}

	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/templates/template02
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Thu, 06 Nov 2014 16:33:17 GMT
	c2
	{"code":201,"message":"The template has been stored successfully
	 in the SLA Repository Database. It has location http://localhos
	t:8080/sla-service/templates/template02","elementId":"template02
	"}

---

Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/appendix/template02b.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/templates

	POST /sla-service/templates HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 2537

	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/templates/template02
	b
	Content-Type: application/xml
	Content-Length: 256
	Date: Thu, 06 Nov 2014 16:33:17 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><message 
	code="201" message="The template has been stored successfully in
	 the SLA Repository Database. It has location http://localhost:8
	080/sla-service/templates/template02b" elementId="template02b"/>

---

Template exists.
Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/appendix/template01.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/templates

	POST /sla-service/templates HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 2535

	HTTP/1.1 409 Conflict
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 157
	Date: Thu, 06 Nov 2014 16:33:17 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><error co
	de="409" message="Element with id:template01 already exists in t
	he SLA Repository Database"/>

---

Linked provider not exists.
Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/appendix/template03.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/templates

	POST /sla-service/templates HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 2535

	HTTP/1.1 409 Conflict
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 144
	Date: Thu, 06 Nov 2014 16:33:18 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><error co
	de="409" message="Provider with UUID provider03 doesn't exist in
	 the database"/>

###Get a template###

Accept: application/xml

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/xml http://localhost:8080/sla-service/templates/template02?

	GET /sla-service/templates/template02? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 1001
	Date: Thu, 06 Nov 2014 16:33:18 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><wsag:Tem
	plate wsag:TemplateId="template02" xmlns:wsag="http://www.ggf.or
	g/namespaces/ws-agreement" xmlns:sla="http://sla.atos.eu"><wsag:
	Name>ExampleTemplate</wsag:Name><wsag:Context><wsag:AgreementIni
	tiator>provider02</wsag:AgreementInitiator><wsag:ServiceProvider
	>AgreementInitiator</wsag:ServiceProvider><wsag:ExpirationTime>2
	014-03-07T12:00:00CET</wsag:ExpirationTime><wsag:TemplateId>temp
	late02</wsag:TemplateId><sla:Service>service02</sla:Service></ws
	ag:Context><wsag:Terms><wsag:All><wsag:ServiceDescriptionTerm/><
	wsag:ServiceProperties/><wsag:ServiceProperties/><wsag:Guarantee
	Term wsag:Name="FastReaction"><wsag:ServiceScope wsag:ServiceNam
	e="GPS0001">               http://www.gps.com/coordsservice/getc
	oords            </wsag:ServiceScope><wsag:ServiceLevelObjective
	><wsag:KPITarget><wsag:KPIName>FastResponseTime</wsag:KPIName></
	wsag:KPITarget></wsag:ServiceLevelObjective></wsag:GuaranteeTerm
	></wsag:All></wsag:Terms></wsag:Template>

---

Accept: application/json

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/json http://localhost:8080/sla-service/templates/template02?

	GET /sla-service/templates/template02? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/json

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/json
	Content-Length: 745
	Date: Thu, 06 Nov 2014 16:33:18 GMT
	{"templateId":"template02","context":{"agreementInitiator":"prov
	ider02","agreementResponder":null,"serviceProvider":"AgreementIn
	itiator","templateId":"template02","service":"service02","expira
	tionTime":"2014-03-07T12:00:00CET"},"name":"ExampleTemplate","te
	rms":{"allTerms":{"serviceDescriptionTerm":{"name":null,"service
	Name":null},"serviceProperties":[{"name":null,"serviceName":null
	,"variableSet":null},{"name":null,"serviceName":null,"variableSe
	t":null}],"guaranteeTerms":[{"name":"FastReaction","serviceScope
	":{"serviceName":"GPS0001","value":"               http://www.gp
	s.com/coordsservice/getcoords            "},"qualifyingCondition
	":null,"serviceLevelObjetive":{"kpitarget":{"kpiName":"FastRespo
	nseTime","customServiceLevel":null}}}]}}}

---

Template not exists.
Accept: application/xml

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/xml http://localhost:8080/sla-service/templates/notexists?

	GET /sla-service/templates/notexists? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 404 Not Found
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 154
	Date: Thu, 06 Nov 2014 16:33:19 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><error co
	de="404" message="There is no template with id notexists in the 
	SLA Repository Database"/>

###Get all the templates###

Accept: application/xml

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/xml http://localhost:8080/sla-service/templates?

	GET /sla-service/templates? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 6020
	Date: Thu, 06 Nov 2014 16:33:19 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><template
	s><wsag:Template xmlns:wsag="http://www.ggf.org/namespaces/ws-ag
	reement" xmlns:sla="http://sla.atos.eu" wsag:TemplateId="templat
	e01">.<wsag:Name>ExampleTemplate01</wsag:Name>.<wsag:Context>.  
	  <wsag:AgreementResponder>provider01</wsag:AgreementResponder>.
	.<wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider>
	..<wsag:ExpirationTime>2014-03-07T12:00:00+0100</wsag:Expiration
	Time>..<sla:Service xmlns:sla="http://sla.atos.eu">service02</sl
	a:Service>.  .</wsag:Context>.<wsag:Terms>..<wsag:All>...<wsag:S
	erviceDescriptionTerm wsag:Name="SDTName1" wsag:ServiceName="Ser
	viceName">....DSL expression...</wsag:ServiceDescriptionTerm>...
	<wsag:ServiceDescriptionTerm wsag:Name="SDTName2" wsag:ServiceNa
	me="ServiceName">....DSL expression...</wsag:ServiceDescriptionT
	erm>......<wsag:ServiceProperties wsag:Name="NonFunctional" wsag
	:ServiceName="ServiceName">....<wsag:Variables>.....<wsag:Variab
	le wsag:Name="ResponseTime" wsag:Metric="xs:double">......<wsag:
	Location>qos:ResponseTime</wsag:Location>.....</wsag:Variable>..
	...<wsag:Variable wsag:Name="Performance" wsag:Metric="xs:double
	">......<wsag:Location>qos:Performance</wsag:Location>.....</wsa
	g:Variable>....</wsag:Variables>...</wsag:ServiceProperties>...<
	wsag:GuaranteeTerm wsag:Name="GT_ResponseTime">....<wsag:Service
	Scope>ServiceName</wsag:ServiceScope>....<wsag:ServiceLevelObjec
	tive>.....<wsag:KPITarget>......<wsag:KPIName>ResponseTime</wsag
	:KPIName>......<wsag:CustomServiceLevel>{"constraint" : "Respons
	eTime LT qos:ResponseTime"}</wsag:CustomServiceLevel>.....</wsag
	:KPITarget>....</wsag:ServiceLevelObjective>...</wsag:GuaranteeT
	erm>...<wsag:GuaranteeTerm wsag:Name="GT_Performance">....<wsag:
	ServiceScope>ServiceName</wsag:ServiceScope>....<wsag:ServiceLev
	elObjective>.....<wsag:KPITarget>......<wsag:KPIName>Performance
	</wsag:KPIName>......<wsag:CustomServiceLevel>{"constraint" : "P
	erformance GT qos:Performance"}</wsag:CustomServiceLevel>.....</
	wsag:KPITarget>....</wsag:ServiceLevelObjective>....<wsag:Busine
	ssValueList>.....<wsag:Importance>3</wsag:Importance>.....<wsag:
	Penalty>......<wsag:AssessmentInterval>.......<wsag:Count>10</ws
	ag:Count>......</wsag:AssessmentInterval>......<wsag:ValueUnit>E
	UR</wsag:ValueUnit>......<wsag:ValueExpression>99</wsag:ValueExp
	ression>.....</wsag:Penalty>..........<wsag:Reward></wsag:Reward
	>.....<wsag:Preference></wsag:Preference>.....<wsag:CustomBusine
	ssValue></wsag:CustomBusinessValue>....</wsag:BusinessValueList>
	...</wsag:GuaranteeTerm>..</wsag:All>.</wsag:Terms></wsag:Templa
	te><wsag:Template wsag:TemplateId="template02" xmlns:wsag="http:
	//www.ggf.org/namespaces/ws-agreement" xmlns:sla="http://sla.ato
	s.eu"><wsag:Name>ExampleTemplate</wsag:Name><wsag:Context><wsag:
	AgreementInitiator>provider02</wsag:AgreementInitiator><wsag:Ser
	viceProvider>AgreementInitiator</wsag:ServiceProvider><wsag:Expi
	rationTime>2014-03-07T12:00:00CET</wsag:ExpirationTime><wsag:Tem
	plateId>template02</wsag:TemplateId><sla:Service>service02</sla:
	Service></wsag:Context><wsag:Terms><wsag:All><wsag:ServiceDescri
	ptionTerm/><wsag:ServiceProperties/><wsag:ServiceProperties/><ws
	ag:GuaranteeTerm wsag:Name="FastReaction"><wsag:ServiceScope wsa
	g:ServiceName="GPS0001">               http://www.gps.com/coords
	service/getcoords            </wsag:ServiceScope><wsag:ServiceLe
	velObjective><wsag:KPITarget><wsag:KPIName>FastResponseTime</wsa
	g:KPIName></wsag:KPITarget></wsag:ServiceLevelObjective></wsag:G
	uaranteeTerm></wsag:All></wsag:Terms></wsag:Template><wsag:Templ
	ate xmlns:wsag="http://www.ggf.org/namespaces/ws-agreement" xmln
	s:sla="http://sla.atos.eu" wsag:TemplateId="template02b">.<wsag:
	Name>ExampleTemplate02b</wsag:Name>.<wsag:Context>.    <wsag:Agr
	eementResponder>provider02</wsag:AgreementResponder>..<wsag:Serv
	iceProvider>AgreementResponder</wsag:ServiceProvider>..<wsag:Exp
	irationTime>2014-03-07T12:00:00+0100</wsag:ExpirationTime>..<sla
	:Service xmlns:sla="http://sla.atos.eu">service02</sla:Service>.
	  .</wsag:Context>.<wsag:Terms>..<wsag:All>...<wsag:ServiceDescr
	iptionTerm wsag:Name="SDTName1" wsag:ServiceName="ServiceName">.
	...DSL expression...</wsag:ServiceDescriptionTerm>...<wsag:Servi
	ceDescriptionTerm wsag:Name="SDTName2" wsag:ServiceName="Service
	Name">....DSL expression...</wsag:ServiceDescriptionTerm>......<
	wsag:ServiceProperties wsag:Name="NonFunctional" wsag:ServiceNam
	e="ServiceName">....<wsag:Variables>.....<wsag:Variable wsag:Nam
	e="ResponseTime" wsag:Metric="xs:double">......<wsag:Location>qo
	s:ResponseTime</wsag:Location>.....</wsag:Variable>.....<wsag:Va
	riable wsag:Name="Performance" wsag:Metric="xs:double">......<ws
	ag:Location>qos:Performance</wsag:Location>.....</wsag:Variable>
	....</wsag:Variables>...</wsag:ServiceProperties>...<wsag:Guaran
	teeTerm wsag:Name="GT_ResponseTime">....<wsag:ServiceScope>Servi
	ceName</wsag:ServiceScope>....<wsag:ServiceLevelObjective>.....<
	wsag:KPITarget>......<wsag:KPIName>ResponseTime</wsag:KPIName>..
	....<wsag:CustomServiceLevel>{"constraint" : "ResponseTime LT qo
	s:ResponseTime"}</wsag:CustomServiceLevel>.....</wsag:KPITarget>
	....</wsag:ServiceLevelObjective>...</wsag:GuaranteeTerm>...<wsa
	g:GuaranteeTerm wsag:Name="GT_Performance">....<wsag:ServiceScop
	e>ServiceName</wsag:ServiceScope>....<wsag:ServiceLevelObjective
	>.....<wsag:KPITarget>......<wsag:KPIName>Performance</wsag:KPIN
	ame>......<wsag:CustomServiceLevel>{"constraint" : "Performance 
	GT qos:Performance"}</wsag:CustomServiceLevel>.....</wsag:KPITar
	get>....</wsag:ServiceLevelObjective>....<wsag:BusinessValueList
	>.....<wsag:Importance>3</wsag:Importance>.....<wsag:Penalty>...
	...<wsag:AssessmentInterval>.......<wsag:Count>10</wsag:Count>..
	....</wsag:AssessmentInterval>......<wsag:ValueUnit>EUR</wsag:Va
	lueUnit>......<wsag:ValueExpression>99</wsag:ValueExpression>...
	..</wsag:Penalty>..........<wsag:Reward></wsag:Reward>.....<wsag
	:Preference></wsag:Preference>.....<wsag:CustomBusinessValue></w
	sag:CustomBusinessValue>....</wsag:BusinessValueList>...</wsag:G
	uaranteeTerm>..</wsag:All>.</wsag:Terms></wsag:Template></templa
	tes>

---

Accept: application/json

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/json http://localhost:8080/sla-service/templates?

	GET /sla-service/templates? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/json

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/json
	Content-Length: 2675
	Date: Thu, 06 Nov 2014 16:33:19 GMT
	[{"templateId":"template01","context":{"agreementInitiator":null
	,"agreementResponder":"provider01","serviceProvider":"AgreementR
	esponder","templateId":null,"service":"service02","expirationTim
	e":"2014-03-07T12:00:00CET"},"name":"ExampleTemplate01","terms":
	{"allTerms":{"serviceDescriptionTerm":{"name":"SDTName2","servic
	eName":"ServiceName"},"serviceProperties":[{"name":"NonFunctiona
	l","serviceName":"ServiceName","variableSet":null}],"guaranteeTe
	rms":[{"name":"GT_ResponseTime","serviceScope":{"serviceName":nu
	ll,"value":"ServiceName"},"qualifyingCondition":null,"serviceLev
	elObjetive":{"kpitarget":{"kpiName":"ResponseTime","customServic
	eLevel":"{\"constraint\" : \"ResponseTime LT qos:ResponseTime\"}
	"}}},{"name":"GT_Performance","serviceScope":{"serviceName":null
	,"value":"ServiceName"},"qualifyingCondition":null,"serviceLevel
	Objetive":{"kpitarget":{"kpiName":"Performance","customServiceLe
	vel":"{\"constraint\" : \"Performance GT qos:Performance\"}"}}}]
	}}},{"templateId":"template02","context":{"agreementInitiator":"
	provider02","agreementResponder":null,"serviceProvider":"Agreeme
	ntInitiator","templateId":"template02","service":"service02","ex
	pirationTime":"2014-03-07T12:00:00CET"},"name":"ExampleTemplate"
	,"terms":{"allTerms":{"serviceDescriptionTerm":{"name":null,"ser
	viceName":null},"serviceProperties":[{"name":null,"serviceName":
	null,"variableSet":null},{"name":null,"serviceName":null,"variab
	leSet":null}],"guaranteeTerms":[{"name":"FastReaction","serviceS
	cope":{"serviceName":"GPS0001","value":"               http://ww
	w.gps.com/coordsservice/getcoords            "},"qualifyingCondi
	tion":null,"serviceLevelObjetive":{"kpitarget":{"kpiName":"FastR
	esponseTime","customServiceLevel":null}}}]}}},{"templateId":"tem
	plate02b","context":{"agreementInitiator":null,"agreementRespond
	er":"provider02","serviceProvider":"AgreementResponder","templat
	eId":null,"service":"service02","expirationTime":"2014-03-07T12:
	00:00CET"},"name":"ExampleTemplate02b","terms":{"allTerms":{"ser
	viceDescriptionTerm":{"name":"SDTName2","serviceName":"ServiceNa
	me"},"serviceProperties":[{"name":"NonFunctional","serviceName":
	"ServiceName","variableSet":null}],"guaranteeTerms":[{"name":"GT
	_ResponseTime","serviceScope":{"serviceName":null,"value":"Servi
	ceName"},"qualifyingCondition":null,"serviceLevelObjetive":{"kpi
	target":{"kpiName":"ResponseTime","customServiceLevel":"{\"const
	raint\" : \"ResponseTime LT qos:ResponseTime\"}"}}},{"name":"GT_
	Performance","serviceScope":{"serviceName":null,"value":"Service
	Name"},"qualifyingCondition":null,"serviceLevelObjetive":{"kpita
	rget":{"kpiName":"Performance","customServiceLevel":"{\"constrai
	nt\" : \"Performance GT qos:Performance\"}"}}}]}}}]

###Delete a template###



	$ /usr/bin/curl -u user:password -X DELETE -H Accept:application/xml http://localhost:8080/sla-service/templates/template02b

	DELETE /sla-service/templates/template02b HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Transfer-Encoding: chunked
	Date: Thu, 06 Nov 2014 16:33:21 GMT
	35
	Template with uuidtemplate02bwas deleted successfully

---

Template not exists


	$ /usr/bin/curl -u user:password -X DELETE -H Accept:application/xml http://localhost:8080/sla-service/templates/notexists

	DELETE /sla-service/templates/notexists HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 404 Not Found
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Transfer-Encoding: chunked
	Date: Thu, 06 Nov 2014 16:33:21 GMT
	9e
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>.<error c
	ode="404" message="There is no template with uuid notexists in t
	he SLA Repository Database"/>.

##Agremeents<a name="agreements"></a>##

###Create an agreement###

Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/appendix/agreement01.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/agreements

	POST /sla-service/agreements HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 2747

	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/agreements/agreement
	01
	Content-Type: application/xml
	Content-Length: 258
	Date: Thu, 06 Nov 2014 16:33:21 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><message 
	code="201" message="The agreement has been stored successfully i
	n the SLA Repository Database. It has location http://localhost:
	8080/sla-service/agreements/agreement01" elementId="agreement01"
	/>

---

Content type: application/json

	$ /usr/bin/curl -u user:password -d@samples/appendix/agreement02.json -X POST -H Content-type:application/json -H Accept:application/json http://localhost:8080/sla-service/agreements

	POST /sla-service/agreements HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/json
	Accept:application/json
	Content-Length: 1632

	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/agreements/agreement
	02
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Thu, 06 Nov 2014 16:33:22 GMT
	c6
	{"code":201,"message":"The agreement has been stored successfull
	y in the SLA Repository Database. It has location http://localho
	st:8080/sla-service/agreements/agreement02","elementId":"agreeme
	nt02"}

---

Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/appendix/agreement02b.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/agreements

	POST /sla-service/agreements HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 2748

	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/agreements/agreement
	02b
	Content-Type: application/xml
	Content-Length: 260
	Date: Thu, 06 Nov 2014 16:33:22 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><message 
	code="201" message="The agreement has been stored successfully i
	n the SLA Repository Database. It has location http://localhost:
	8080/sla-service/agreements/agreement02b" elementId="agreement02
	b"/>

---

Linked provider not exists.
Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/appendix/agreement03.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/agreements

	POST /sla-service/agreements HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 2747

	HTTP/1.1 409 Conflict
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 150
	Date: Thu, 06 Nov 2014 16:33:23 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><error co
	de="409" message="Provider with id:provider03 doesn't exist SLA 
	Repository Database"/>

---

Linked template not exists.
Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/appendix/agreement04.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/agreements

	POST /sla-service/agreements HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 2747

	HTTP/1.1 409 Conflict
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 150
	Date: Thu, 06 Nov 2014 16:33:23 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><error co
	de="409" message="Template with id:template04 doesn't exist SLA 
	Repository Database"/>

---

Agreement exists.
Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/appendix/agreement01.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/agreements

	POST /sla-service/agreements HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 2747

	HTTP/1.1 409 Conflict
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 160
	Date: Thu, 06 Nov 2014 16:33:24 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><error co
	de="409" message="Agreement with id:agreement01 already exists i
	n the SLA Repository Database"/>

###Get an agreement###

Accept: application/xml

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/xml http://localhost:8080/sla-service/agreements/agreement01?

	GET /sla-service/agreements/agreement01? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 2764
	Date: Thu, 06 Nov 2014 16:33:24 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><wsag:Agr
	eement xmlns:wsag="http://www.ggf.org/namespaces/ws-agreement".x
	mlns:sla="http://sla.atos.eu" wsag:AgreementId="agreement01">.<w
	sag:Name>ExampleAgreement</wsag:Name>.<wsag:Context>..<wsag:Agre
	ementInitiator>RandomClient</wsag:AgreementInitiator>..<wsag:Agr
	eementResponder>provider01</wsag:AgreementResponder>..<wsag:Serv
	iceProvider>AgreementResponder</wsag:ServiceProvider>..<wsag:Exp
	irationTime>2014-03-07T12:00:00+0100</wsag:ExpirationTime>..<wsa
	g:TemplateId>template01</wsag:TemplateId>..<sla:Service>service0
	1</sla:Service>.....</wsag:Context>.<wsag:Terms>..<wsag:All>...<
	wsag:ServiceDescriptionTerm wsag:Name="SDTName1" wsag:ServiceNam
	e="ServiceName">....DSL expression...</wsag:ServiceDescriptionTe
	rm>...<wsag:ServiceDescriptionTerm wsag:Name="SDTName2" wsag:Ser
	viceName="ServiceName">....DSL expression...</wsag:ServiceDescri
	ptionTerm>...<wsag:ServiceProperties wsag:Name="NonFunctional" w
	sag:ServiceName="ServiceName">....<wsag:VariableSet>.....<wsag:V
	ariable wsag:Name="ResponseTime" wsag:Metric="xs:double">......<
	wsag:Location>qos:ResponseTime</wsag:Location>.....</wsag:Variab
	le>.....<wsag:Variable wsag:Name="Performance" wsag:Metric="xs:d
	ouble">......<wsag:Location>qos:Performance</wsag:Location>.....
	</wsag:Variable>....</wsag:VariableSet>...</wsag:ServiceProperti
	es>...<wsag:GuaranteeTerm wsag:Name="GT_ResponseTime">....<wsag:
	ServiceScope wsag:ServiceName="ServiceName">ScopeName1</wsag:Ser
	viceScope>....<wsag:ServiceLevelObjective>.....<wsag:KPITarget>.
	.....<wsag:KPIName>ResponseTime</wsag:KPIName> <!--  same name a
	s property for the moment -->......<wsag:CustomServiceLevel>{"co
	nstraint" : "ResponseTime LT 0.9"}</wsag:CustomServiceLevel>....
	.</wsag:KPITarget>....</wsag:ServiceLevelObjective>...</wsag:Gua
	ranteeTerm>...<wsag:GuaranteeTerm wsag:Name="GT_Performance">...
	.<wsag:ServiceScope wsag:ServiceName="ServiceName">ScopeName2</w
	sag:ServiceScope>....<wsag:ServiceLevelObjective>.....<wsag:KPIT
	arget>......<wsag:KPIName>Performance</wsag:KPIName> <!--  same 
	name as property for the moment -->......<wsag:CustomServiceLeve
	l>{"constraint" : "Performance GT 0.1"}</wsag:CustomServiceLevel
	>.....</wsag:KPITarget>....</wsag:ServiceLevelObjective>....<wsa
	g:BusinessValueList>.....<wsag:Importance>3</wsag:Importance>...
	..<wsag:Penalty>......<wsag:AssessmentInterval>.......<wsag:Coun
	t>10</wsag:Count>......</wsag:AssessmentInterval>......<wsag:Val
	ueUnit>EUR</wsag:ValueUnit>......<wsag:ValueExpression>99</wsag:
	ValueExpression>.....</wsag:Penalty>..........<wsag:Reward></wsa
	g:Reward>.....<wsag:Preference></wsag:Preference>.....<wsag:Cust
	omBusinessValue></wsag:CustomBusinessValue>....</wsag:BusinessVa
	lueList>...</wsag:GuaranteeTerm>..</wsag:All>.</wsag:Terms></wsa
	g:Agreement>

---

Accept: application/json

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/json http://localhost:8080/sla-service/agreements/agreement01?

	GET /sla-service/agreements/agreement01? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/json

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/json
	Content-Length: 1131
	Date: Thu, 06 Nov 2014 16:33:24 GMT
	{"agreementId":"agreement01","name":"ExampleAgreement","context"
	:{"agreementInitiator":"RandomClient","agreementResponder":"prov
	ider01","serviceProvider":"AgreementResponder","templateId":"tem
	plate01","service":"service01","expirationTime":"2014-03-07T12:0
	0:00CET"},"terms":{"allTerms":{"serviceDescriptionTerm":{"name":
	"SDTName2","serviceName":"ServiceName"},"serviceProperties":[{"n
	ame":"NonFunctional","serviceName":"ServiceName","variableSet":{
	"variables":[{"name":"ResponseTime","metric":"xs:double","locati
	on":"qos:ResponseTime"},{"name":"Performance","metric":"xs:doubl
	e","location":"qos:Performance"}]}}],"guaranteeTerms":[{"name":"
	GT_ResponseTime","serviceScope":{"serviceName":"ServiceName","va
	lue":"ScopeName1"},"qualifyingCondition":null,"serviceLevelObjet
	ive":{"kpitarget":{"kpiName":"ResponseTime","customServiceLevel"
	:"{\"constraint\" : \"ResponseTime LT 0.9\"}"}}},{"name":"GT_Per
	formance","serviceScope":{"serviceName":"ServiceName","value":"S
	copeName2"},"qualifyingCondition":null,"serviceLevelObjetive":{"
	kpitarget":{"kpiName":"Performance","customServiceLevel":"{\"con
	straint\" : \"Performance GT 0.1\"}"}}}]}}}

###Get all the agreements###

Accept: application/xml

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/xml http://localhost:8080/sla-service/agreements?

	GET /sla-service/agreements? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 8167
	Date: Thu, 06 Nov 2014 16:33:26 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><agreemen
	ts><wsag:Agreement xmlns:wsag="http://www.ggf.org/namespaces/ws-
	agreement".xmlns:sla="http://sla.atos.eu" wsag:AgreementId="agre
	ement01">.<wsag:Name>ExampleAgreement</wsag:Name>.<wsag:Context>
	..<wsag:AgreementInitiator>RandomClient</wsag:AgreementInitiator
	>..<wsag:AgreementResponder>provider01</wsag:AgreementResponder>
	..<wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider
	>..<wsag:ExpirationTime>2014-03-07T12:00:00+0100</wsag:Expiratio
	nTime>..<wsag:TemplateId>template01</wsag:TemplateId>..<sla:Serv
	ice>service01</sla:Service>.....</wsag:Context>.<wsag:Terms>..<w
	sag:All>...<wsag:ServiceDescriptionTerm wsag:Name="SDTName1" wsa
	g:ServiceName="ServiceName">....DSL expression...</wsag:ServiceD
	escriptionTerm>...<wsag:ServiceDescriptionTerm wsag:Name="SDTNam
	e2" wsag:ServiceName="ServiceName">....DSL expression...</wsag:S
	erviceDescriptionTerm>...<wsag:ServiceProperties wsag:Name="NonF
	unctional" wsag:ServiceName="ServiceName">....<wsag:VariableSet>
	.....<wsag:Variable wsag:Name="ResponseTime" wsag:Metric="xs:dou
	ble">......<wsag:Location>qos:ResponseTime</wsag:Location>.....<
	/wsag:Variable>.....<wsag:Variable wsag:Name="Performance" wsag:
	Metric="xs:double">......<wsag:Location>qos:Performance</wsag:Lo
	cation>.....</wsag:Variable>....</wsag:VariableSet>...</wsag:Ser
	viceProperties>...<wsag:GuaranteeTerm wsag:Name="GT_ResponseTime
	">....<wsag:ServiceScope wsag:ServiceName="ServiceName">ScopeNam
	e1</wsag:ServiceScope>....<wsag:ServiceLevelObjective>.....<wsag
	:KPITarget>......<wsag:KPIName>ResponseTime</wsag:KPIName> <!-- 
	 same name as property for the moment -->......<wsag:CustomServi
	ceLevel>{"constraint" : "ResponseTime LT 0.9"}</wsag:CustomServi
	ceLevel>.....</wsag:KPITarget>....</wsag:ServiceLevelObjective>.
	..</wsag:GuaranteeTerm>...<wsag:GuaranteeTerm wsag:Name="GT_Perf
	ormance">....<wsag:ServiceScope wsag:ServiceName="ServiceName">S
	copeName2</wsag:ServiceScope>....<wsag:ServiceLevelObjective>...
	..<wsag:KPITarget>......<wsag:KPIName>Performance</wsag:KPIName>
	 <!--  same name as property for the moment -->......<wsag:Custo
	mServiceLevel>{"constraint" : "Performance GT 0.1"}</wsag:Custom
	ServiceLevel>.....</wsag:KPITarget>....</wsag:ServiceLevelObject
	ive>....<wsag:BusinessValueList>.....<wsag:Importance>3</wsag:Im
	portance>.....<wsag:Penalty>......<wsag:AssessmentInterval>.....
	..<wsag:Count>10</wsag:Count>......</wsag:AssessmentInterval>...
	...<wsag:ValueUnit>EUR</wsag:ValueUnit>......<wsag:ValueExpressi
	on>99</wsag:ValueExpression>.....</wsag:Penalty>..........<wsag:
	Reward></wsag:Reward>.....<wsag:Preference></wsag:Preference>...
	..<wsag:CustomBusinessValue></wsag:CustomBusinessValue>....</wsa
	g:BusinessValueList>...</wsag:GuaranteeTerm>..</wsag:All>.</wsag
	:Terms></wsag:Agreement><wsag:Agreement wsag:AgreementId="agreem
	ent02" xmlns:wsag="http://www.ggf.org/namespaces/ws-agreement" x
	mlns:sla="http://sla.atos.eu"><wsag:Name>ExampleAgreement</wsag:
	Name><wsag:Context><wsag:AgreementInitiator>client-prueba</wsag:
	AgreementInitiator><wsag:AgreementResponder>provider02</wsag:Agr
	eementResponder><wsag:ServiceProvider>AgreementResponder</wsag:S
	erviceProvider><wsag:ExpirationTime>2014-03-07T12:00:00CET</wsag
	:ExpirationTime><wsag:TemplateId>template02</wsag:TemplateId><sl
	a:Service>service02</sla:Service></wsag:Context><wsag:Terms><wsa
	g:All><wsag:ServiceProperties wsag:Name="ServiceProperties" wsag
	:ServiceName="ServiceName"><wsag:VariableSet><wsag:Variable wsag
	:Name="metric1" wsag:Metric="xs:double"><wsag:Location>metric1</
	wsag:Location></wsag:Variable><wsag:Variable wsag:Name="metric2"
	 wsag:Metric="xs:double"><wsag:Location>metric2</wsag:Location><
	/wsag:Variable><wsag:Variable wsag:Name="metric3" wsag:Metric="x
	s:double"><wsag:Location>metric3</wsag:Location></wsag:Variable>
	<wsag:Variable wsag:Name="metric4" wsag:Metric="xs:double"><wsag
	:Location>metric4</wsag:Location></wsag:Variable></wsag:Variable
	Set></wsag:ServiceProperties><wsag:GuaranteeTerm wsag:Name="GT_M
	etric1"><wsag:ServiceScope wsag:ServiceName="ServiceName"></wsag
	:ServiceScope><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:
	KPIName>metric1</wsag:KPIName><wsag:CustomServiceLevel>.......{"
	constraint" : "metric1 BETWEEN (0.05, 1)"}......</wsag:CustomSer
	viceLevel></wsag:KPITarget></wsag:ServiceLevelObjective></wsag:G
	uaranteeTerm><wsag:GuaranteeTerm wsag:Name="GT_Metric2"><wsag:Se
	rviceScope wsag:ServiceName="ServiceName"></wsag:ServiceScope><w
	sag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>metric2<
	/wsag:KPIName><wsag:CustomServiceLevel>.......{"constraint" : "m
	etric2 BETWEEN (0.1, 1)"}......</wsag:CustomServiceLevel></wsag:
	KPITarget></wsag:ServiceLevelObjective></wsag:GuaranteeTerm><wsa
	g:GuaranteeTerm wsag:Name="GT_Metric3"><wsag:ServiceScope wsag:S
	erviceName="ServiceName"></wsag:ServiceScope><wsag:ServiceLevelO
	bjective><wsag:KPITarget><wsag:KPIName>metric3</wsag:KPIName><ws
	ag:CustomServiceLevel>.......{"constraint" : "metric3 BETWEEN (0
	.15, 1)"}......</wsag:CustomServiceLevel></wsag:KPITarget></wsag
	:ServiceLevelObjective></wsag:GuaranteeTerm><wsag:GuaranteeTerm 
	wsag:Name="GT_Metric4"><wsag:ServiceScope wsag:ServiceName="Serv
	iceName"></wsag:ServiceScope><wsag:ServiceLevelObjective><wsag:K
	PITarget><wsag:KPIName>metric4</wsag:KPIName><wsag:CustomService
	Level>.......{"constraint" : "metric4 BETWEEN (0.2, 1)"}......</
	wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObje
	ctive></wsag:GuaranteeTerm></wsag:All></wsag:Terms></wsag:Agreem
	ent><wsag:Agreement xmlns:wsag="http://www.ggf.org/namespaces/ws
	-agreement".xmlns:sla="http://sla.atos.eu" wsag:AgreementId="agr
	eement02b">.<wsag:Name>ExampleAgreement</wsag:Name>.<wsag:Contex
	t>..<wsag:AgreementInitiator>RandomClient</wsag:AgreementInitiat
	or>..<wsag:AgreementResponder>provider02</wsag:AgreementResponde
	r>..<wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvid
	er>..<wsag:ExpirationTime>2014-03-07T12:00:00+0100</wsag:Expirat
	ionTime>..<wsag:TemplateId>template02</wsag:TemplateId>..<sla:Se
	rvice>service02</sla:Service>.....</wsag:Context>.<wsag:Terms>..
	<wsag:All>...<wsag:ServiceDescriptionTerm wsag:Name="SDTName1" w
	sag:ServiceName="ServiceName">....DSL expression...</wsag:Servic
	eDescriptionTerm>...<wsag:ServiceDescriptionTerm wsag:Name="SDTN
	ame2" wsag:ServiceName="ServiceName">....DSL expression...</wsag
	:ServiceDescriptionTerm>...<wsag:ServiceProperties wsag:Name="No
	nFunctional" wsag:ServiceName="ServiceName">....<wsag:VariableSe
	t>.....<wsag:Variable wsag:Name="ResponseTime" wsag:Metric="xs:d
	ouble">......<wsag:Location>qos:ResponseTime</wsag:Location>....
	.</wsag:Variable>.....<wsag:Variable wsag:Name="Performance" wsa
	g:Metric="xs:double">......<wsag:Location>qos:Performance</wsag:
	Location>.....</wsag:Variable>....</wsag:VariableSet>...</wsag:S
	erviceProperties>...<wsag:GuaranteeTerm wsag:Name="GT_ResponseTi
	me">....<wsag:ServiceScope wsag:ServiceName="ServiceName">ScopeN
	ame1</wsag:ServiceScope>....<wsag:ServiceLevelObjective>.....<ws
	ag:KPITarget>......<wsag:KPIName>ResponseTime</wsag:KPIName> <!-
	-  same name as property for the moment -->......<wsag:CustomSer
	viceLevel>{"constraint" : "ResponseTime LT 0.9"}</wsag:CustomSer
	viceLevel>.....</wsag:KPITarget>....</wsag:ServiceLevelObjective
	>...</wsag:GuaranteeTerm>...<wsag:GuaranteeTerm wsag:Name="GT_Pe
	rformance">....<wsag:ServiceScope wsag:ServiceName="ServiceName"
	>ScopeName2</wsag:ServiceScope>....<wsag:ServiceLevelObjective>.
	....<wsag:KPITarget>......<wsag:KPIName>Performance</wsag:KPINam
	e> <!--  same name as property for the moment -->......<wsag:Cus
	tomServiceLevel>{"constraint" : "Performance GT 0.1"}</wsag:Cust
	omServiceLevel>.....</wsag:KPITarget>....</wsag:ServiceLevelObje
	ctive>....<wsag:BusinessValueList>.....<wsag:Importance>3</wsag:
	Importance>.....<wsag:Penalty>......<wsag:AssessmentInterval>...
	....<wsag:Count>10</wsag:Count>......</wsag:AssessmentInterval>.
	.....<wsag:ValueUnit>EUR</wsag:ValueUnit>......<wsag:ValueExpres
	sion>99</wsag:ValueExpression>.....</wsag:Penalty>..........<wsa
	g:Reward></wsag:Reward>.....<wsag:Preference></wsag:Preference>.
	....<wsag:CustomBusinessValue></wsag:CustomBusinessValue>....</w
	sag:BusinessValueList>...</wsag:GuaranteeTerm>..</wsag:All>.</ws
	ag:Terms></wsag:Agreement></agreements>

---

Accept: application/json

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/json http://localhost:8080/sla-service/agreements?

	GET /sla-service/agreements? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/json

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/json
	Content-Length: 4005
	Date: Thu, 06 Nov 2014 16:33:26 GMT
	[{"agreementId":"agreement01","name":"ExampleAgreement","context
	":{"agreementInitiator":"RandomClient","agreementResponder":"pro
	vider01","serviceProvider":"AgreementResponder","templateId":"te
	mplate01","service":"service01","expirationTime":"2014-03-07T12:
	00:00CET"},"terms":{"allTerms":{"serviceDescriptionTerm":{"name"
	:"SDTName2","serviceName":"ServiceName"},"serviceProperties":[{"
	name":"NonFunctional","serviceName":"ServiceName","variableSet":
	{"variables":[{"name":"ResponseTime","metric":"xs:double","locat
	ion":"qos:ResponseTime"},{"name":"Performance","metric":"xs:doub
	le","location":"qos:Performance"}]}}],"guaranteeTerms":[{"name":
	"GT_ResponseTime","serviceScope":{"serviceName":"ServiceName","v
	alue":"ScopeName1"},"qualifyingCondition":null,"serviceLevelObje
	tive":{"kpitarget":{"kpiName":"ResponseTime","customServiceLevel
	":"{\"constraint\" : \"ResponseTime LT 0.9\"}"}}},{"name":"GT_Pe
	rformance","serviceScope":{"serviceName":"ServiceName","value":"
	ScopeName2"},"qualifyingCondition":null,"serviceLevelObjetive":{
	"kpitarget":{"kpiName":"Performance","customServiceLevel":"{\"co
	nstraint\" : \"Performance GT 0.1\"}"}}}]}}},{"agreementId":"agr
	eement02","name":"ExampleAgreement","context":{"agreementInitiat
	or":"client-prueba","agreementResponder":"provider02","servicePr
	ovider":"AgreementResponder","templateId":"template02","service"
	:"service02","expirationTime":"2014-03-07T12:00:00CET"},"terms":
	{"allTerms":{"serviceDescriptionTerm":null,"serviceProperties":[
	{"name":"ServiceProperties","serviceName":"ServiceName","variabl
	eSet":{"variables":[{"name":"metric1","metric":"xs:double","loca
	tion":"metric1"},{"name":"metric2","metric":"xs:double","locatio
	n":"metric2"},{"name":"metric3","metric":"xs:double","location":
	"metric3"},{"name":"metric4","metric":"xs:double","location":"me
	tric4"}]}}],"guaranteeTerms":[{"name":"GT_Metric1","serviceScope
	":{"serviceName":"ServiceName","value":""},"qualifyingCondition"
	:null,"serviceLevelObjetive":{"kpitarget":{"kpiName":"metric1","
	customServiceLevel":"\t\t\t\t\t\t\t{\"constraint\" : \"metric1 B
	ETWEEN (0.05, 1)\"}\t\t\t\t\t\t"}}},{"name":"GT_Metric2","servic
	eScope":{"serviceName":"ServiceName","value":""},"qualifyingCond
	ition":null,"serviceLevelObjetive":{"kpitarget":{"kpiName":"metr
	ic2","customServiceLevel":"\t\t\t\t\t\t\t{\"constraint\" : \"met
	ric2 BETWEEN (0.1, 1)\"}\t\t\t\t\t\t"}}},{"name":"GT_Metric3","s
	erviceScope":{"serviceName":"ServiceName","value":""},"qualifyin
	gCondition":null,"serviceLevelObjetive":{"kpitarget":{"kpiName":
	"metric3","customServiceLevel":"\t\t\t\t\t\t\t{\"constraint\" : 
	\"metric3 BETWEEN (0.15, 1)\"}\t\t\t\t\t\t"}}},{"name":"GT_Metri
	c4","serviceScope":{"serviceName":"ServiceName","value":""},"qua
	lifyingCondition":null,"serviceLevelObjetive":{"kpitarget":{"kpi
	Name":"metric4","customServiceLevel":"\t\t\t\t\t\t\t{\"constrain
	t\" : \"metric4 BETWEEN (0.2, 1)\"}\t\t\t\t\t\t"}}}]}}},{"agreem
	entId":"agreement02b","name":"ExampleAgreement","context":{"agre
	ementInitiator":"RandomClient","agreementResponder":"provider02"
	,"serviceProvider":"AgreementResponder","templateId":"template02
	","service":"service02","expirationTime":"2014-03-07T12:00:00CET
	"},"terms":{"allTerms":{"serviceDescriptionTerm":{"name":"SDTNam
	e2","serviceName":"ServiceName"},"serviceProperties":[{"name":"N
	onFunctional","serviceName":"ServiceName","variableSet":{"variab
	les":[{"name":"ResponseTime","metric":"xs:double","location":"qo
	s:ResponseTime"},{"name":"Performance","metric":"xs:double","loc
	ation":"qos:Performance"}]}}],"guaranteeTerms":[{"name":"GT_Resp
	onseTime","serviceScope":{"serviceName":"ServiceName","value":"S
	copeName1"},"qualifyingCondition":null,"serviceLevelObjetive":{"
	kpitarget":{"kpiName":"ResponseTime","customServiceLevel":"{\"co
	nstraint\" : \"ResponseTime LT 0.9\"}"}}},{"name":"GT_Performanc
	e","serviceScope":{"serviceName":"ServiceName","value":"ScopeNam
	e2"},"qualifyingCondition":null,"serviceLevelObjetive":{"kpitarg
	et":{"kpiName":"Performance","customServiceLevel":"{\"constraint
	\" : \"Performance GT 0.1\"}"}}}]}}}]

###Get agreement status###

Accept: application/xml

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/xml http://localhost:8080/sla-service/agreements/agreement02/guaranteestatus?

	GET /sla-service/agreements/agreement02/guaranteestatus? HTTP/1.
	1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 391
	Date: Thu, 06 Nov 2014 16:33:27 GMT
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><guarante
	estatus AgreementId="agreement02" value="NON_DETERMINED"><guaran
	teetermstatus name="GT_Metric1" value="NON_DETERMINED"/><guarant
	eetermstatus name="GT_Metric2" value="NON_DETERMINED"/><guarante
	etermstatus name="GT_Metric3" value="NON_DETERMINED"/><guarantee
	termstatus name="GT_Metric4" value="NON_DETERMINED"/></guarantee
	status>

---

Accept: application/json

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/json http://localhost:8080/sla-service/agreements/agreement02/guaranteestatus?

	GET /sla-service/agreements/agreement02/guaranteestatus? HTTP/1.
	1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/json

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Thu, 06 Nov 2014 16:33:27 GMT
	113
	{"AgreementId":"agreement02","guaranteestatus":"NON_DETERMINED",
	"guaranteeterms":[{"name":"GT_Metric1","status":"NON_DETERMINED"
	},{"name":"GT_Metric2","status":"NON_DETERMINED"},{"name":"GT_Me
	tric3","status":"NON_DETERMINED"},{"name":"GT_Metric4","status":
	"NON_DETERMINED"}]}

###Delete an agreement###



	$ /usr/bin/curl -u user:password -X DELETE -H Accept:application/xml http://localhost:8080/sla-service/agreements/agreement02b

	DELETE /sla-service/agreements/agreement02b HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Transfer-Encoding: chunked
	Date: Thu, 06 Nov 2014 16:33:27 GMT
	4f
	The agreement id agreement02bwith it's enforcement job was succe
	ssfully deleted

---

Agreement not exists


	$ /usr/bin/curl -u user:password -X DELETE -H Accept:application/xml http://localhost:8080/sla-service/agreements/notexists

	DELETE /sla-service/agreements/notexists HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml

	HTTP/1.1 404 Not Found
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Transfer-Encoding: chunked
	Date: Thu, 06 Nov 2014 16:33:28 GMT
	9d
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>.<error c
	ode="404" message="There is no agreement with id notexists in th
	e SLA Repository Database"/>.

##Violations<a name="violations"></a>##

###Get all the violations###

