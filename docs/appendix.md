Cleaning database: DB=atossla USER=atossla
#Appendix REST API examples#

##Providers<a name="providers"></a>##

###Create a provider###

Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/provider01.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/providers

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
	Content-Length: 231
	Date: Fri, 03 Oct 2014 09:45:56 GMT
	
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><message 
	code="201" message="The provider has been stored successfully in
	 the SLA Repository Database. It has location http://localhost:8
	080/sla-service/providers/provider01"/>
---

Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/provider02.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/providers

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
	Content-Length: 231
	Date: Fri, 03 Oct 2014 09:45:56 GMT
	
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><message 
	code="201" message="The provider has been stored successfully in
	 the SLA Repository Database. It has location http://localhost:8
	080/sla-service/providers/provider02"/>
---

Content type: application/json

	$ /usr/bin/curl -u user:password -d@samples/provider03.json -X POST -H Content-type:application/json -H Accept:application/json http://localhost:8080/sla-service/providers

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
	Date: Fri, 03 Oct 2014 09:45:56 GMT
	
	a9
	{"code":201,"message":"The provider has been stored successfully
	 in the SLA Repository Database. It has location http://localhos
	t:8080/sla-service/providers/provider03"}
	0
	
---

Provider exists.
Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/provider02.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/providers

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
	Date: Fri, 03 Oct 2014 09:45:57 GMT
	
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
	Date: Fri, 03 Oct 2014 09:45:57 GMT
	
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
	Date: Fri, 03 Oct 2014 09:45:57 GMT
	
	2d
	{"uuid":"provider02","name":"provider02name"}
	0
	
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
	Date: Fri, 03 Oct 2014 09:45:58 GMT
	
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
	Date: Fri, 03 Oct 2014 09:45:58 GMT
	
	8b
	[{"uuid":"provider01","name":"provider01name"},{"uuid":"provider
	02","name":"provider02name"},{"uuid":"provider03","name":"provid
	er03name"}]
	0
	
###Delete a provider###

	$ /usr/bin/curl -u user:password -X DELETE -H Accept:application/xml http://localhost:8080/sla-service/providers/provider04

	DELETE /sla-service/providers/provider04 HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml
	

	HTTP/1.1 404 Not Found
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Transfer-Encoding: chunked
	Date: Fri, 03 Oct 2014 09:45:58 GMT
	
	9f
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>.<error c
	ode="404" message="There is no provider with uuid provider04 in 
	the SLA Repository Database"/>.
	0
	
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
	Date: Fri, 03 Oct 2014 09:46:00 GMT
	
	9e
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>.<error c
	ode="404" message="There is no provider with uuid notexists in t
	he SLA Repository Database"/>.
	0
	
##Templates<a name="templates"></a>##

###Create a template###

Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/template02.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/templates

	POST /sla-service/templates HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 3034
	Expect: 100-continue
	

	HTTP/1.1 100 Continue
	<?xml version="1.0" encoding="UTF-8"?><wsag:Template xmlns:wsag=
	"http://www.ggf.org/namespaces/ws-agreement" xmlns:sla="http://s
	la.atos.eu" wsag:TemplateId="template02">.<wsag:Name>ExampleTemp
	late2</wsag:Name>.<wsag:Context>.    <wsag:AgreementResponder>pr
	ovider03</wsag:AgreementResponder>..<wsag:ServiceProvider>Agreem
	entResponder</wsag:ServiceProvider>..<wsag:ExpirationTime>2014-0
	3-07T12:00:00+0100</wsag:ExpirationTime>..<sla:Service xmlns:sla
	="http://sla.atos.eu">service03</sla:Service>.  .</wsag:Context>
	.<wsag:Terms>..<wsag:All>...<!-- FUNCTIONAL DESCRIPTION -->...<w
	sag:ServiceDescriptionTerm wsag:Name="SDTName1" wsag:ServiceName
	="ServiceName">....DSL expression...</wsag:ServiceDescriptionTer
	m>...<wsag:ServiceDescriptionTerm wsag:Name="SDTName2" wsag:Serv
	iceName="ServiceName">....DSL expression...</wsag:ServiceDescrip
	tionTerm>......<!-- OPTIONAL SERVICE REFERENCE -->......<!-- OPT
	IONAL SERVICE PROPERTIES : non funcional properties-->...<wsag:S
	erviceProperties wsag:Name="NonFunctional" wsag:ServiceName="Ser
	viceName">....<wsag:Variables>.....<wsag:Variable wsag:Name="Res
	ponseTime" wsag:Metric="xs:double">......<wsag:Location>qos:Resp
	onseTime</wsag:Location>.....</wsag:Variable>.....<wsag:Variable
	 wsag:Name="Performance" wsag:Metric="xs:double">......<wsag:Loc
	ation>qos:Performance</wsag:Location>.....</wsag:Variable>....</
	wsag:Variables>...</wsag:ServiceProperties>...<wsag:GuaranteeTer
	m wsag:Name="GT_ResponseTime">....<wsag:ServiceScope>ServiceName
	</wsag:ServiceScope>....<!-- The qualifying conditions that must
	 be met before the guarantee is evaluated -->....<!-- ....<wsag:
	QualifyingCondition>state EQ 'ready'</wsag:QualifyingCondition>.
	...-->....<wsag:ServiceLevelObjective>.....<wsag:KPITarget>.....
	.<wsag:KPIName>ResponseTime</wsag:KPIName> <!--  same name as pr
	operty for the moment -->......<wsag:CustomServiceLevel>{"constr
	aint" : "ResponseTime LT qos:ResponseTime"}</wsag:CustomServiceL
	evel> <!--  the ServiceProperty is referenced here -->.....</wsa
	g:KPITarget>....</wsag:ServiceLevelObjective>...</wsag:Guarantee
	Term>...<wsag:GuaranteeTerm wsag:Name="GT_Performance">....<wsag
	:ServiceScope>ServiceName</wsag:ServiceScope>....<wsag:ServiceLe
	velObjective>.....<wsag:KPITarget>......<wsag:KPIName>Performanc
	e</wsag:KPIName> <!--  same name as property for the moment -->.
	.....<wsag:CustomServiceLevel>{"constraint" : "Performance GT qo
	s:Performance"}</wsag:CustomServiceLevel>.....</wsag:KPITarget>.
	...</wsag:ServiceLevelObjective>....<wsag:BusinessValueList>....
	.<wsag:Importante>3</wsag:Importante>.<!-- optional importance (
	integer) -->.....<wsag:Penalty>......<wsag:AssessmentInterval>..
	.....<wsag:Count>10</wsag:Count>......</wsag:AssessmentInterval>
	......<wsag:ValueUnit>EUR</wsag:ValueUnit>......<wsag:ValueExpre
	ssion>99</wsag:ValueExpression>.....</wsag:Penalty>..........<ws
	ag:Reward></wsag:Reward>.....<wsag:Preference></wsag:Preference>
	.....<wsag:CustomBusinessValue></wsag:CustomBusinessValue>....</
	wsag:BusinessValueList>...</wsag:GuaranteeTerm>..</wsag:All>.</w
	sag:Terms></wsag:Template>
	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/templates/template02
	Content-Type: application/xml
	Content-Length: 232
	Date: Fri, 03 Oct 2014 09:46:00 GMT
	
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><message 
	code="201" message="The agreement has been stored successfully i
	n the SLA Repository Database. It has location http://localhost:
	8080/sla-service/templates/template02"/>
---

Content type: application/json

	$ /usr/bin/curl -u user:password -d@samples/template05.json -X POST -H Content-type:application/json -H Accept:application/json http://localhost:8080/sla-service/templates

	POST /sla-service/templates HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/json
	Accept:application/json
	Content-Length: 717
	
	{"templateId":"template05","context":{"agreementInitiator":"prov
	ider02","agreementResponder":null,"serviceProvider":"AgreementIn
	itiator","templateId":"template01","service":"service3","expirat
	ionTime":"2014-03-07T12:00:00CET"},"name":"ExampleTemplate","ter
	ms":{"allTerms":{"serviceDescriptionTerm":{"name":null,"serviceN
	ame":null},"serviceProperties":[{"name":null,"serviceName":null,
	"variableSet":null},{"name":null,"serviceName":null,"variableSet
	":null}],"guaranteeTerms":[{"name":"FastReaction","serviceScope"
	:{"serviceName":"GPS0001","value":"               http://www.gps
	.com/coordsservice/getcoords            "},"serviceLevelObjetive
	":{"kpitarget":{"kpiName":"FastResponseTime","customServiceLevel
	":null}}}]}}}

	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/templates/template05
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Fri, 03 Oct 2014 09:46:01 GMT
	
	aa
	{"code":201,"message":"The agreement has been stored successfull
	y in the SLA Repository Database. It has location http://localho
	st:8080/sla-service/templates/template05"}
	0
	
###Get a template###

Accept: application/xml

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/xml http://localhost:8080/sla-service/templates/template012?

	GET /sla-service/templates/template012? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml
	

	HTTP/1.1 404 Not Found
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 156
	Date: Fri, 03 Oct 2014 09:46:01 GMT
	
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><error co
	de="404" message="There is no template with id template012 in th
	e SLA Repository Database"/>
---

Accept: application/json

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/json http://localhost:8080/sla-service/templates/template05?

	GET /sla-service/templates/template05? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/json
	

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/json
	Content-Length: 744
	Date: Fri, 03 Oct 2014 09:46:01 GMT
	
	{"templateId":"template05","context":{"agreementInitiator":"prov
	ider02","agreementResponder":null,"serviceProvider":"AgreementIn
	itiator","templateId":"template01","service":"service3","expirat
	ionTime":"2014-03-07T12:00:00CET"},"name":"ExampleTemplate","ter
	ms":{"allTerms":{"serviceDescriptionTerm":{"name":null,"serviceN
	ame":null},"serviceProperties":[{"name":null,"serviceName":null,
	"variableSet":null},{"name":null,"serviceName":null,"variableSet
	":null}],"guaranteeTerms":[{"name":"FastReaction","serviceScope"
	:{"serviceName":"GPS0001","value":"               http://www.gps
	.com/coordsservice/getcoords            "},"qualifyingCondition"
	:null,"serviceLevelObjetive":{"kpitarget":{"kpiName":"FastRespon
	seTime","customServiceLevel":null}}}]}}}
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
	Content-Length: 4019
	Date: Fri, 03 Oct 2014 09:46:02 GMT
	
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><template
	s><wsag:Template xmlns:wsag="http://www.ggf.org/namespaces/ws-ag
	reement" xmlns:sla="http://sla.atos.eu" wsag:TemplateId="templat
	e02">.<wsag:Name>ExampleTemplate2</wsag:Name>.<wsag:Context>.   
	 <wsag:AgreementResponder>provider03</wsag:AgreementResponder>..
	<wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider>.
	.<wsag:ExpirationTime>2014-03-07T12:00:00+0100</wsag:ExpirationT
	ime>..<sla:Service xmlns:sla="http://sla.atos.eu">service03</sla
	:Service>.  .</wsag:Context>.<wsag:Terms>..<wsag:All>...<!-- FUN
	CTIONAL DESCRIPTION -->...<wsag:ServiceDescriptionTerm wsag:Name
	="SDTName1" wsag:ServiceName="ServiceName">....DSL expression...
	</wsag:ServiceDescriptionTerm>...<wsag:ServiceDescriptionTerm ws
	ag:Name="SDTName2" wsag:ServiceName="ServiceName">....DSL expres
	sion...</wsag:ServiceDescriptionTerm>......<!-- OPTIONAL SERVICE
	 REFERENCE -->......<!-- OPTIONAL SERVICE PROPERTIES : non funci
	onal properties-->...<wsag:ServiceProperties wsag:Name="NonFunct
	ional" wsag:ServiceName="ServiceName">....<wsag:Variables>.....<
	wsag:Variable wsag:Name="ResponseTime" wsag:Metric="xs:double">.
	.....<wsag:Location>qos:ResponseTime</wsag:Location>.....</wsag:
	Variable>.....<wsag:Variable wsag:Name="Performance" wsag:Metric
	="xs:double">......<wsag:Location>qos:Performance</wsag:Location
	>.....</wsag:Variable>....</wsag:Variables>...</wsag:ServiceProp
	erties>...<wsag:GuaranteeTerm wsag:Name="GT_ResponseTime">....<w
	sag:ServiceScope>ServiceName</wsag:ServiceScope>....<!-- The qua
	lifying conditions that must be met before the guarantee is eval
	uated -->....<!-- ....<wsag:QualifyingCondition>state EQ 'ready'
	</wsag:QualifyingCondition>....-->....<wsag:ServiceLevelObjectiv
	e>.....<wsag:KPITarget>......<wsag:KPIName>ResponseTime</wsag:KP
	IName> <!--  same name as property for the moment -->......<wsag
	:CustomServiceLevel>{"constraint" : "ResponseTime LT qos:Respons
	eTime"}</wsag:CustomServiceLevel> <!--  the ServiceProperty is r
	eferenced here -->.....</wsag:KPITarget>....</wsag:ServiceLevelO
	bjective>...</wsag:GuaranteeTerm>...<wsag:GuaranteeTerm wsag:Nam
	e="GT_Performance">....<wsag:ServiceScope>ServiceName</wsag:Serv
	iceScope>....<wsag:ServiceLevelObjective>.....<wsag:KPITarget>..
	....<wsag:KPIName>Performance</wsag:KPIName> <!--  same name as 
	property for the moment -->......<wsag:CustomServiceLevel>{"cons
	traint" : "Performance GT qos:Performance"}</wsag:CustomServiceL
	evel>.....</wsag:KPITarget>....</wsag:ServiceLevelObjective>....
	<wsag:BusinessValueList>.....<wsag:Importante>3</wsag:Importante
	>.<!-- optional importance (integer) -->.....<wsag:Penalty>.....
	.<wsag:AssessmentInterval>.......<wsag:Count>10</wsag:Count>....
	..</wsag:AssessmentInterval>......<wsag:ValueUnit>EUR</wsag:Valu
	eUnit>......<wsag:ValueExpression>99</wsag:ValueExpression>.....
	</wsag:Penalty>..........<wsag:Reward></wsag:Reward>.....<wsag:P
	reference></wsag:Preference>.....<wsag:CustomBusinessValue></wsa
	g:CustomBusinessValue>....</wsag:BusinessValueList>...</wsag:Gua
	ranteeTerm>..</wsag:All>.</wsag:Terms></wsag:Template><wsag:Temp
	late wsag:TemplateId="template05" xmlns:wsag="http://www.ggf.org
	/namespaces/ws-agreement" xmlns:sla="http://sla.atos.eu"><wsag:N
	ame>ExampleTemplate</wsag:Name><wsag:Context><wsag:AgreementInit
	iator>provider02</wsag:AgreementInitiator><wsag:ServiceProvider>
	AgreementInitiator</wsag:ServiceProvider><wsag:ExpirationTime>20
	14-03-07T12:00:00CET</wsag:ExpirationTime><wsag:TemplateId>templ
	ate01</wsag:TemplateId><sla:Service>service3</sla:Service></wsag
	:Context><wsag:Terms><wsag:All><wsag:ServiceDescriptionTerm/><ws
	ag:ServiceProperties/><wsag:ServiceProperties/><wsag:GuaranteeTe
	rm wsag:Name="FastReaction"><wsag:ServiceScope wsag:ServiceName=
	"GPS0001">               http://www.gps.com/coordsservice/getcoo
	rds            </wsag:ServiceScope><wsag:ServiceLevelObjective><
	wsag:KPITarget><wsag:KPIName>FastResponseTime</wsag:KPIName></ws
	ag:KPITarget></wsag:ServiceLevelObjective></wsag:GuaranteeTerm><
	/wsag:All></wsag:Terms></wsag:Template></templates>
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
	Content-Length: 1708
	Date: Fri, 03 Oct 2014 09:46:02 GMT
	
	[{"templateId":"template02","context":{"agreementInitiator":null
	,"agreementResponder":"provider03","serviceProvider":"AgreementR
	esponder","templateId":null,"service":"service03","expirationTim
	e":"2014-03-07T12:00:00CET"},"name":"ExampleTemplate2","terms":{
	"allTerms":{"serviceDescriptionTerm":{"name":"SDTName2","service
	Name":"ServiceName"},"serviceProperties":[{"name":"NonFunctional
	","serviceName":"ServiceName","variableSet":null}],"guaranteeTer
	ms":[{"name":"GT_ResponseTime","serviceScope":{"serviceName":nul
	l,"value":"ServiceName"},"qualifyingCondition":null,"serviceLeve
	lObjetive":{"kpitarget":{"kpiName":"ResponseTime","customService
	Level":"{\"constraint\" : \"ResponseTime LT qos:ResponseTime\"}"
	}}},{"name":"GT_Performance","serviceScope":{"serviceName":null,
	"value":"ServiceName"},"qualifyingCondition":null,"serviceLevelO
	bjetive":{"kpitarget":{"kpiName":"Performance","customServiceLev
	el":"{\"constraint\" : \"Performance GT qos:Performance\"}"}}}]}
	}},{"templateId":"template05","context":{"agreementInitiator":"p
	rovider02","agreementResponder":null,"serviceProvider":"Agreemen
	tInitiator","templateId":"template01","service":"service3","expi
	rationTime":"2014-03-07T12:00:00CET"},"name":"ExampleTemplate","
	terms":{"allTerms":{"serviceDescriptionTerm":{"name":null,"servi
	ceName":null},"serviceProperties":[{"name":null,"serviceName":nu
	ll,"variableSet":null},{"name":null,"serviceName":null,"variable
	Set":null}],"guaranteeTerms":[{"name":"FastReaction","serviceSco
	pe":{"serviceName":"GPS0001","value":"               http://www.
	gps.com/coordsservice/getcoords            "},"qualifyingConditi
	on":null,"serviceLevelObjetive":{"kpitarget":{"kpiName":"FastRes
	ponseTime","customServiceLevel":null}}}]}}}]
###Delete a template###

	$ /usr/bin/curl -u user:password -X DELETE -H Accept:application/xml http://localhost:8080/sla-service/templates/template05

	DELETE /sla-service/templates/template05 HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml
	

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Transfer-Encoding: chunked
	Date: Fri, 03 Oct 2014 09:46:02 GMT
	
	34
	Template with uuidtemplate05was deleted successfully
	0
	
##Agremeents<a name="agreements"></a>##

###Create an agreement###

Content type: application/xml

	$ /usr/bin/curl -u user:password -d@samples/agreement02.xml -X POST -H Content-type:application/xml -H Accept:application/xml http://localhost:8080/sla-service/agreements

	POST /sla-service/agreements HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/xml
	Accept:application/xml
	Content-Length: 3255
	Expect: 100-continue
	

	HTTP/1.1 100 Continue
	<?xml version="1.0" encoding="UTF-8"?><wsag:Agreement xmlns:wsag
	="http://www.ggf.org/namespaces/ws-agreement".xmlns:sla="http://
	sla.atos.eu" wsag:AgreementId="agreement02">.<wsag:Name>ExampleA
	greement</wsag:Name>.<wsag:Context>..<wsag:AgreementInitiator>Ra
	ndomClient</wsag:AgreementInitiator>..<wsag:AgreementResponder>p
	rovider02</wsag:AgreementResponder>..<!--..The AgreementResponde
	r (in this case) is mandatory if sla is multi service provider  
	 ..-->..<wsag:ServiceProvider>AgreementResponder</wsag:ServicePr
	ovider>..<wsag:ExpirationTime>2014-03-07T12:00:00+0100</wsag:Exp
	irationTime>..<wsag:TemplateId>template02</wsag:TemplateId>..<sl
	a:Service>service02</sla:Service>.....</wsag:Context>.<wsag:Term
	s>..<wsag:All>...<!-- FUNCTIONAL DESCRIPTION -->...<wsag:Service
	DescriptionTerm wsag:Name="SDTName1" wsag:ServiceName="ServiceNa
	me">....DSL expression...</wsag:ServiceDescriptionTerm>...<wsag:
	ServiceDescriptionTerm wsag:Name="SDTName2" wsag:ServiceName="Se
	rviceName">....DSL expression...</wsag:ServiceDescriptionTerm>..
	....<!-- OPTIONAL SERVICE REFERENCE -->......<!-- OPTIONAL SERVI
	CE PROPERTIES : non funcional properties-->...<wsag:ServicePrope
	rties wsag:Name="NonFunctional" wsag:ServiceName="ServiceName">.
	...<wsag:VariableSet>.....<wsag:Variable wsag:Name="ResponseTime
	" wsag:Metric="xs:double">......<wsag:Location>qos:ResponseTime<
	/wsag:Location>.....</wsag:Variable>.....<wsag:Variable wsag:Nam
	e="Performance" wsag:Metric="xs:double">......<wsag:Location>qos
	:Performance</wsag:Location>.....</wsag:Variable>....</wsag:Vari
	ableSet>...</wsag:ServiceProperties>...<wsag:GuaranteeTerm wsag:
	Name="GT_ResponseTime">....<wsag:ServiceScope wsag:ServiceName="
	ServiceName">ScopeName1</wsag:ServiceScope>....<!-- The qualifyi
	ng conditions that must be met before the guarantee is evaluated
	 -->....<!-- ....<wsag:QualifyingCondition>state EQ 'ready'</wsa
	g:QualifyingCondition>....-->....<wsag:ServiceLevelObjective>...
	..<wsag:KPITarget>......<wsag:KPIName>ResponseTime</wsag:KPIName
	> <!--  same name as property for the moment -->......<wsag:Cust
	omServiceLevel>{"constraint" : "ResponseTime LT 0.9"}</wsag:Cust
	omServiceLevel> <!--  the ServiceProperty is referenced here -->
	.....</wsag:KPITarget>....</wsag:ServiceLevelObjective>...</wsag
	:GuaranteeTerm>...<wsag:GuaranteeTerm wsag:Name="GT_Performance"
	>....<wsag:ServiceScope wsag:ServiceName="ServiceName">ScopeName
	2</wsag:ServiceScope>....<wsag:ServiceLevelObjective>.....<wsag:
	KPITarget>......<wsag:KPIName>Performance</wsag:KPIName> <!--  s
	ame name as property for the moment -->......<wsag:CustomService
	Level>{"constraint" : "Performance GT 0.1"}</wsag:CustomServiceL
	evel>.....</wsag:KPITarget>....</wsag:ServiceLevelObjective>....
	<wsag:BusinessValueList>.....<wsag:Importante>3</wsag:Importante
	>.<!-- optional importance (integer) -->.....<wsag:Penalty>.....
	.<wsag:AssessmentInterval>.......<wsag:Count>10</wsag:Count>....
	..</wsag:AssessmentInterval>......<wsag:ValueUnit>EUR</wsag:Valu
	eUnit>......<wsag:ValueExpression>99</wsag:ValueExpression>.....
	</wsag:Penalty>..........<wsag:Reward></wsag:Reward>.....<wsag:P
	reference></wsag:Preference>.....<wsag:CustomBusinessValue></wsa
	g:CustomBusinessValue>....</wsag:BusinessValueList>...</wsag:Gua
	ranteeTerm>..</wsag:All>.</wsag:Terms></wsag:Agreement>
	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/agreements/agreement
	02
	Content-Type: application/xml
	Content-Length: 234
	Date: Fri, 03 Oct 2014 09:46:04 GMT
	
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><message 
	code="201" message="The agreement has been stored successfully i
	n the SLA Repository Database. It has location http://localhost:
	8080/sla-service/agreements/agreement02"/>
---

Content type: application/json

	$ /usr/bin/curl -u user:password -d@samples/agreement07.json -X POST -H Content-type:application/json -H Accept:application/json http://localhost:8080/sla-service/agreements

	POST /sla-service/agreements HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Content-type:application/json
	Accept:application/json
	Content-Length: 1631
	Expect: 100-continue
	

	HTTP/1.1 100 Continue
	{"agreementId":"agreement07","name":"ExampleAgreement","context"
	:{"agreementInitiator":"client-prueba","expirationTime":"2014-03
	-07T12:00:00+0100","templateId":"template02","service":"service5
	","serviceProvider":"AgreementResponder","agreementResponder":"p
	rovider03"},"terms":{"allTerms":{"serviceDescriptionTerm":null,"
	serviceProperties":[{"name":"ServiceProperties","serviceName":"S
	erviceName","variableSet":{"variables":[{"name":"metric1","metri
	c":"xs:double","location":"metric1"},{"name":"metric2","metric":
	"xs:double","location":"metric2"},{"name":"metric3","metric":"xs
	:double","location":"metric3"},{"name":"metric4","metric":"xs:do
	uble","location":"metric4"}]}}],"guaranteeTerms":[{"name":"GT_Me
	tric1","serviceScope":{"serviceName":"ServiceName","value":""},"
	serviceLevelObjetive":{"kpitarget":{"kpiName":"metric1","customS
	erviceLevel":"\t\t\t\t\t\t\t{\"constraint\" : \"metric1 BETWEEN 
	(0.05, 1)\"}\t\t\t\t\t\t"}}},{"name":"GT_Metric2","serviceScope"
	:{"serviceName":"ServiceName","value":""},"serviceLevelObjetive"
	:{"kpitarget":{"kpiName":"metric2","customServiceLevel":"\t\t\t\
	t\t\t\t{\"constraint\" : \"metric2 BETWEEN (0.1, 1)\"}\t\t\t\t\t
	\t"}}},{"name":"GT_Metric3","serviceScope":{"serviceName":"Servi
	ceName","value":""},"serviceLevelObjetive":{"kpitarget":{"kpiNam
	e":"metric3","customServiceLevel":"\t\t\t\t\t\t\t{\"constraint\"
	 : \"metric3 BETWEEN (0.15, 1)\"}\t\t\t\t\t\t"}}},{"name":"GT_Me
	tric4","serviceScope":{"serviceName":"ServiceName","value":""},"
	serviceLevelObjetive":{"kpitarget":{"kpiName":"metric4","customS
	erviceLevel":"\t\t\t\t\t\t\t{\"constraint\" : \"metric4 BETWEEN 
	(0.2, 1)\"}\t\t\t\t\t\t"}}}]}}}
	HTTP/1.1 201 Created
	Server: Apache-Coyote/1.1
	location: http://localhost:8080/sla-service/agreements/agreement
	07
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Fri, 03 Oct 2014 09:46:04 GMT
	
	ac
	{"code":201,"message":"The agreement has been stored successfull
	y in the SLA Repository Database. It has location http://localho
	st:8080/sla-service/agreements/agreement07"}
	0
	
###Get an agreement###

Accept: application/xml

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/xml http://localhost:8080/sla-service/agreements/agreement02?

	GET /sla-service/agreements/agreement02? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/xml
	

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Content-Type: application/xml
	Content-Length: 3272
	Date: Fri, 03 Oct 2014 09:46:05 GMT
	
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><wsag:Agr
	eement xmlns:wsag="http://www.ggf.org/namespaces/ws-agreement".x
	mlns:sla="http://sla.atos.eu" wsag:AgreementId="agreement02">.<w
	sag:Name>ExampleAgreement</wsag:Name>.<wsag:Context>..<wsag:Agre
	ementInitiator>RandomClient</wsag:AgreementInitiator>..<wsag:Agr
	eementResponder>provider02</wsag:AgreementResponder>..<!--..The 
	AgreementResponder (in this case) is mandatory if sla is multi s
	ervice provider   ..-->..<wsag:ServiceProvider>AgreementResponde
	r</wsag:ServiceProvider>..<wsag:ExpirationTime>2014-03-07T12:00:
	00+0100</wsag:ExpirationTime>..<wsag:TemplateId>template02</wsag
	:TemplateId>..<sla:Service>service02</sla:Service>.....</wsag:Co
	ntext>.<wsag:Terms>..<wsag:All>...<!-- FUNCTIONAL DESCRIPTION --
	>...<wsag:ServiceDescriptionTerm wsag:Name="SDTName1" wsag:Servi
	ceName="ServiceName">....DSL expression...</wsag:ServiceDescript
	ionTerm>...<wsag:ServiceDescriptionTerm wsag:Name="SDTName2" wsa
	g:ServiceName="ServiceName">....DSL expression...</wsag:ServiceD
	escriptionTerm>......<!-- OPTIONAL SERVICE REFERENCE -->......<!
	-- OPTIONAL SERVICE PROPERTIES : non funcional properties-->...<
	wsag:ServiceProperties wsag:Name="NonFunctional" wsag:ServiceNam
	e="ServiceName">....<wsag:VariableSet>.....<wsag:Variable wsag:N
	ame="ResponseTime" wsag:Metric="xs:double">......<wsag:Location>
	qos:ResponseTime</wsag:Location>.....</wsag:Variable>.....<wsag:
	Variable wsag:Name="Performance" wsag:Metric="xs:double">......<
	wsag:Location>qos:Performance</wsag:Location>.....</wsag:Variabl
	e>....</wsag:VariableSet>...</wsag:ServiceProperties>...<wsag:Gu
	aranteeTerm wsag:Name="GT_ResponseTime">....<wsag:ServiceScope w
	sag:ServiceName="ServiceName">ScopeName1</wsag:ServiceScope>....
	<!-- The qualifying conditions that must be met before the guara
	ntee is evaluated -->....<!-- ....<wsag:QualifyingCondition>stat
	e EQ 'ready'</wsag:QualifyingCondition>....-->....<wsag:ServiceL
	evelObjective>.....<wsag:KPITarget>......<wsag:KPIName>ResponseT
	ime</wsag:KPIName> <!--  same name as property for the moment --
	>......<wsag:CustomServiceLevel>{"constraint" : "ResponseTime LT
	 0.9"}</wsag:CustomServiceLevel> <!--  the ServiceProperty is re
	ferenced here -->.....</wsag:KPITarget>....</wsag:ServiceLevelOb
	jective>...</wsag:GuaranteeTerm>...<wsag:GuaranteeTerm wsag:Name
	="GT_Performance">....<wsag:ServiceScope wsag:ServiceName="Servi
	ceName">ScopeName2</wsag:ServiceScope>....<wsag:ServiceLevelObje
	ctive>.....<wsag:KPITarget>......<wsag:KPIName>Performance</wsag
	:KPIName> <!--  same name as property for the moment -->......<w
	sag:CustomServiceLevel>{"constraint" : "Performance GT 0.1"}</ws
	ag:CustomServiceLevel>.....</wsag:KPITarget>....</wsag:ServiceLe
	velObjective>....<wsag:BusinessValueList>.....<wsag:Importante>3
	</wsag:Importante>.<!-- optional importance (integer) -->.....<w
	sag:Penalty>......<wsag:AssessmentInterval>.......<wsag:Count>10
	</wsag:Count>......</wsag:AssessmentInterval>......<wsag:ValueUn
	it>EUR</wsag:ValueUnit>......<wsag:ValueExpression>99</wsag:Valu
	eExpression>.....</wsag:Penalty>..........<wsag:Reward></wsag:Re
	ward>.....<wsag:Preference></wsag:Preference>.....<wsag:CustomBu
	sinessValue></wsag:CustomBusinessValue>....</wsag:BusinessValueL
	ist>...</wsag:GuaranteeTerm>..</wsag:All>.</wsag:Terms></wsag:Ag
	reement>
---

Accept: application/json

	$ /usr/bin/curl -u user:password -X GET -H Accept:application/json http://localhost:8080/sla-service/agreements/agreement05?

	GET /sla-service/agreements/agreement05? HTTP/1.1
	Authorization: Basic dXNlcjpwYXNzd29yZA==
	User-Agent: curl/7.38.0
	Host: localhost:8080
	Accept:application/json
	

	HTTP/1.1 404 Not Found
	Server: Apache-Coyote/1.1
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Fri, 03 Oct 2014 09:46:05 GMT
	
	61
	{"code":404,"message":"There is no agreement with id agreement05
	 in the SLA Repository Database"}
	0
	
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
	Content-Length: 5964
	Date: Fri, 03 Oct 2014 09:46:05 GMT
	
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><agreemen
	ts><wsag:Agreement xmlns:wsag="http://www.ggf.org/namespaces/ws-
	agreement".xmlns:sla="http://sla.atos.eu" wsag:AgreementId="agre
	ement02">.<wsag:Name>ExampleAgreement</wsag:Name>.<wsag:Context>
	..<wsag:AgreementInitiator>RandomClient</wsag:AgreementInitiator
	>..<wsag:AgreementResponder>provider02</wsag:AgreementResponder>
	..<!--..The AgreementResponder (in this case) is mandatory if sl
	a is multi service provider   ..-->..<wsag:ServiceProvider>Agree
	mentResponder</wsag:ServiceProvider>..<wsag:ExpirationTime>2014-
	03-07T12:00:00+0100</wsag:ExpirationTime>..<wsag:TemplateId>temp
	late02</wsag:TemplateId>..<sla:Service>service02</sla:Service>..
	...</wsag:Context>.<wsag:Terms>..<wsag:All>...<!-- FUNCTIONAL DE
	SCRIPTION -->...<wsag:ServiceDescriptionTerm wsag:Name="SDTName1
	" wsag:ServiceName="ServiceName">....DSL expression...</wsag:Ser
	viceDescriptionTerm>...<wsag:ServiceDescriptionTerm wsag:Name="S
	DTName2" wsag:ServiceName="ServiceName">....DSL expression...</w
	sag:ServiceDescriptionTerm>......<!-- OPTIONAL SERVICE REFERENCE
	 -->......<!-- OPTIONAL SERVICE PROPERTIES : non funcional prope
	rties-->...<wsag:ServiceProperties wsag:Name="NonFunctional" wsa
	g:ServiceName="ServiceName">....<wsag:VariableSet>.....<wsag:Var
	iable wsag:Name="ResponseTime" wsag:Metric="xs:double">......<ws
	ag:Location>qos:ResponseTime</wsag:Location>.....</wsag:Variable
	>.....<wsag:Variable wsag:Name="Performance" wsag:Metric="xs:dou
	ble">......<wsag:Location>qos:Performance</wsag:Location>.....</
	wsag:Variable>....</wsag:VariableSet>...</wsag:ServiceProperties
	>...<wsag:GuaranteeTerm wsag:Name="GT_ResponseTime">....<wsag:Se
	rviceScope wsag:ServiceName="ServiceName">ScopeName1</wsag:Servi
	ceScope>....<!-- The qualifying conditions that must be met befo
	re the guarantee is evaluated -->....<!-- ....<wsag:QualifyingCo
	ndition>state EQ 'ready'</wsag:QualifyingCondition>....-->....<w
	sag:ServiceLevelObjective>.....<wsag:KPITarget>......<wsag:KPINa
	me>ResponseTime</wsag:KPIName> <!--  same name as property for t
	he moment -->......<wsag:CustomServiceLevel>{"constraint" : "Res
	ponseTime LT 0.9"}</wsag:CustomServiceLevel> <!--  the ServicePr
	operty is referenced here -->.....</wsag:KPITarget>....</wsag:Se
	rviceLevelObjective>...</wsag:GuaranteeTerm>...<wsag:GuaranteeTe
	rm wsag:Name="GT_Performance">....<wsag:ServiceScope wsag:Servic
	eName="ServiceName">ScopeName2</wsag:ServiceScope>....<wsag:Serv
	iceLevelObjective>.....<wsag:KPITarget>......<wsag:KPIName>Perfo
	rmance</wsag:KPIName> <!--  same name as property for the moment
	 -->......<wsag:CustomServiceLevel>{"constraint" : "Performance 
	GT 0.1"}</wsag:CustomServiceLevel>.....</wsag:KPITarget>....</ws
	ag:ServiceLevelObjective>....<wsag:BusinessValueList>.....<wsag:
	Importante>3</wsag:Importante>.<!-- optional importance (integer
	) -->.....<wsag:Penalty>......<wsag:AssessmentInterval>.......<w
	sag:Count>10</wsag:Count>......</wsag:AssessmentInterval>......<
	wsag:ValueUnit>EUR</wsag:ValueUnit>......<wsag:ValueExpression>9
	9</wsag:ValueExpression>.....</wsag:Penalty>..........<wsag:Rewa
	rd></wsag:Reward>.....<wsag:Preference></wsag:Preference>.....<w
	sag:CustomBusinessValue></wsag:CustomBusinessValue>....</wsag:Bu
	sinessValueList>...</wsag:GuaranteeTerm>..</wsag:All>.</wsag:Ter
	ms></wsag:Agreement><wsag:Agreement wsag:AgreementId="agreement0
	7" xmlns:wsag="http://www.ggf.org/namespaces/ws-agreement" xmlns
	:sla="http://sla.atos.eu"><wsag:Name>ExampleAgreement</wsag:Name
	><wsag:Context><wsag:AgreementInitiator>client-prueba</wsag:Agre
	ementInitiator><wsag:AgreementResponder>provider03</wsag:Agreeme
	ntResponder><wsag:ServiceProvider>AgreementResponder</wsag:Servi
	ceProvider><wsag:ExpirationTime>2014-03-07T12:00:00CET</wsag:Exp
	irationTime><wsag:TemplateId>template02</wsag:TemplateId><sla:Se
	rvice>service5</sla:Service></wsag:Context><wsag:Terms><wsag:All
	><wsag:ServiceProperties wsag:Name="ServiceProperties" wsag:Serv
	iceName="ServiceName"><wsag:VariableSet><wsag:Variable wsag:Name
	="metric1" wsag:Metric="xs:double"><wsag:Location>metric1</wsag:
	Location></wsag:Variable><wsag:Variable wsag:Name="metric2" wsag
	:Metric="xs:double"><wsag:Location>metric2</wsag:Location></wsag
	:Variable><wsag:Variable wsag:Name="metric3" wsag:Metric="xs:dou
	ble"><wsag:Location>metric3</wsag:Location></wsag:Variable><wsag
	:Variable wsag:Name="metric4" wsag:Metric="xs:double"><wsag:Loca
	tion>metric4</wsag:Location></wsag:Variable></wsag:VariableSet><
	/wsag:ServiceProperties><wsag:GuaranteeTerm wsag:Name="GT_Metric
	1"><wsag:ServiceScope wsag:ServiceName="ServiceName"></wsag:Serv
	iceScope><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPINa
	me>metric1</wsag:KPIName><wsag:CustomServiceLevel>.......{"const
	raint" : "metric1 BETWEEN (0.05, 1)"}......</wsag:CustomServiceL
	evel></wsag:KPITarget></wsag:ServiceLevelObjective></wsag:Guaran
	teeTerm><wsag:GuaranteeTerm wsag:Name="GT_Metric2"><wsag:Service
	Scope wsag:ServiceName="ServiceName"></wsag:ServiceScope><wsag:S
	erviceLevelObjective><wsag:KPITarget><wsag:KPIName>metric2</wsag
	:KPIName><wsag:CustomServiceLevel>.......{"constraint" : "metric
	2 BETWEEN (0.1, 1)"}......</wsag:CustomServiceLevel></wsag:KPITa
	rget></wsag:ServiceLevelObjective></wsag:GuaranteeTerm><wsag:Gua
	ranteeTerm wsag:Name="GT_Metric3"><wsag:ServiceScope wsag:Servic
	eName="ServiceName"></wsag:ServiceScope><wsag:ServiceLevelObject
	ive><wsag:KPITarget><wsag:KPIName>metric3</wsag:KPIName><wsag:Cu
	stomServiceLevel>.......{"constraint" : "metric3 BETWEEN (0.15, 
	1)"}......</wsag:CustomServiceLevel></wsag:KPITarget></wsag:Serv
	iceLevelObjective></wsag:GuaranteeTerm><wsag:GuaranteeTerm wsag:
	Name="GT_Metric4"><wsag:ServiceScope wsag:ServiceName="ServiceNa
	me"></wsag:ServiceScope><wsag:ServiceLevelObjective><wsag:KPITar
	get><wsag:KPIName>metric4</wsag:KPIName><wsag:CustomServiceLevel
	>.......{"constraint" : "metric4 BETWEEN (0.2, 1)"}......</wsag:
	CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective
	></wsag:GuaranteeTerm></wsag:All></wsag:Terms></wsag:Agreement><
	/agreements>
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
	Content-Length: 2871
	Date: Fri, 03 Oct 2014 09:46:06 GMT
	
	[{"agreementId":"agreement02","name":"ExampleAgreement","context
	":{"agreementInitiator":"RandomClient","agreementResponder":"pro
	vider02","serviceProvider":"AgreementResponder","templateId":"te
	mplate02","service":"service02","expirationTime":"2014-03-07T12:
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
	eement07","name":"ExampleAgreement","context":{"agreementInitiat
	or":"client-prueba","agreementResponder":"provider03","servicePr
	ovider":"AgreementResponder","templateId":"template02","service"
	:"service5","expirationTime":"2014-03-07T12:00:00CET"},"terms":{
	"allTerms":{"serviceDescriptionTerm":null,"serviceProperties":[{
	"name":"ServiceProperties","serviceName":"ServiceName","variable
	Set":{"variables":[{"name":"metric1","metric":"xs:double","locat
	ion":"metric1"},{"name":"metric2","metric":"xs:double","location
	":"metric2"},{"name":"metric3","metric":"xs:double","location":"
	metric3"},{"name":"metric4","metric":"xs:double","location":"met
	ric4"}]}}],"guaranteeTerms":[{"name":"GT_Metric1","serviceScope"
	:{"serviceName":"ServiceName","value":""},"qualifyingCondition":
	null,"serviceLevelObjetive":{"kpitarget":{"kpiName":"metric1","c
	ustomServiceLevel":"\t\t\t\t\t\t\t{\"constraint\" : \"metric1 BE
	TWEEN (0.05, 1)\"}\t\t\t\t\t\t"}}},{"name":"GT_Metric2","service
	Scope":{"serviceName":"ServiceName","value":""},"qualifyingCondi
	tion":null,"serviceLevelObjetive":{"kpitarget":{"kpiName":"metri
	c2","customServiceLevel":"\t\t\t\t\t\t\t{\"constraint\" : \"metr
	ic2 BETWEEN (0.1, 1)\"}\t\t\t\t\t\t"}}},{"name":"GT_Metric3","se
	rviceScope":{"serviceName":"ServiceName","value":""},"qualifying
	Condition":null,"serviceLevelObjetive":{"kpitarget":{"kpiName":"
	metric3","customServiceLevel":"\t\t\t\t\t\t\t{\"constraint\" : \
	"metric3 BETWEEN (0.15, 1)\"}\t\t\t\t\t\t"}}},{"name":"GT_Metric
	4","serviceScope":{"serviceName":"ServiceName","value":""},"qual
	ifyingCondition":null,"serviceLevelObjetive":{"kpitarget":{"kpiN
	ame":"metric4","customServiceLevel":"\t\t\t\t\t\t\t{\"constraint
	\" : \"metric4 BETWEEN (0.2, 1)\"}\t\t\t\t\t\t"}}}]}}}]
###Delete an agreement###

##Violations<a name="violations"></a>##

###Get all the violations###

