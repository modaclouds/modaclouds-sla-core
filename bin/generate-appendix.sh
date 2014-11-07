#!/usr/bin/env bash
#
#

SLA_MANAGER_URL="http://localhost:8080/sla-service"
XML="application/xml"
JSON="application/json"
in_case=0

		#####

#shopt -s expand_aliases
set -o history -o histexpand
#alias curl='/usr/bin/curl --trace-ascii /tmp/curl.out '

if [ "$0" != "bin/generate-appendix.sh" ]; then
	echo "Must be executed from project root"
	exit 1
fi
>&2 bin/restoreDatabase.sh

function curl_cmd() {
	in_case=1
	CMD="/usr/bin/curl -u user:password ""$@"
	/usr/bin/curl --trace-ascii /tmp/curl.out -u user:password "$@" > /dev/null 2>&1
	local code=$?
	
	return $code
}

function curl_post() {
	#
	# $1: relative url
	# $2: file to send
	# $3: content type header
	# $4: accept header
	md_text "Content type: $3\n"

	local type_header=${3:+-H Content-type:$3}
	local accept_header=${4:+-H Accept:$4}
	local url="$SLA_MANAGER_URL/$1"
	curl_cmd "-d@samples/appendix/$2" -X POST $type_header $accept_header "$url"
	local code=$?

	md_rest
	return $code
}

function curl_put() {
	# $1: relative url
	# $2: file to send
	# $3: content type header
	md_text "Content type: $3\n"

	local url="$SLA_MANAGER_URL/$1"
	local file_param=${2:+-d@samples/appendix/$3}
	local type_header=${3:+-H Content-type:$3}
	curl_cmd -X PUT $file_param $type_header "$url"
	
	local code=$?

	md_rest
	return $code
}

function curl_get() {
	# $1: relative url
	# $2: query string (without ?)
	# $3: accept header
	md_text "Accept: $3\n"

	local url="$SLA_MANAGER_URL/$1?$2"
	local accept_header=${3:+-H Accept:$3}
	curl_cmd -X GET $accept_header "$url"
	
	local code=$?

	md_rest
	return $code
}

function curl_delete() {
	# $1: relative url
	md_text "\n"

	local url="$SLA_MANAGER_URL/$1"
	local accept_header="-H Accept:$XML"
	curl_cmd -X DELETE $accept_header "$url"
	
	local code=$?

	md_rest
	return $code
}

function md_rest() {
	local out=$(grep -e "^[0-9a-f]\{1,4\}:" /tmp/curl.out | sed -e's/^.\{5\} /\t/')

	local request=$(
	echo "$out" | awk -e'BEGIN{flag=1}/^\tHTTP\/1./{flag=0}{if (flag && ($0 !~ /(^\t?$)|Expect: 100/)) print;}'
	)

	local response=$(
		echo "$out" | awk -e'BEGIN{flag=0}/^\tHTTP\/1\.. [^1]/{flag=1}{if (flag && $0 !~ /^\t?0?$/) print;}'
	)

	echo -e "\t\$ $CMD"
	echo
	echo "$request"
	echo
	echo "$response"
	echo 
	#get_status
}

function md_text() {
	[ "$in_case" = "1" ] && md_hr
	echo -e "$@"
	in_case=0
}

function md_hr() {
	echo "---"
	echo
}

function md_title() {
	# $1: importance
	# $2: title
	# $3: anchor
	in_case=0
	local hashes="#"
	for (( i = 1; i < $1; i++ )); do
		hashes="$hashes#"
	done
	local anchor=${3:+<a name=\"$3\"></a>}
	echo "$hashes$2$anchor$hashes"
	echo
}

function get_status() {
	filter_status=$(awk "/^.{5} HTTP\/1.1 [^1]/ {print \$3;}" /tmp/curl.out)
	echo "$filter_status"
}

md_title 1 "Appendix REST API examples"
md_title 2 "Providers" "providers"				###### PROVIDERS

md_title 3 "Create a provider"					###

curl_post "providers" "provider01.xml" "$XML" "$XML"
curl_post "providers" "provider02.xml" "$XML" "$XML"
curl_post "providers" "provider03.json" "$JSON" "$JSON"

md_text "Provider exists."
curl_post "providers" "provider02.xml" "$XML" "$XML"

md_title 3 "Get a provider"						###
curl_get "providers/provider02" "" "$XML"
curl_get "providers/provider02" "" "$JSON"

md_text "Provider not exists."
curl_get "providers/notexists"  "" "$XML"

md_title 3 "Get all the providers"				###
curl_get "providers" "" "$XML"
curl_get "providers" "" "$JSON"

md_title 3 "Delete a provider"					###
curl_delete "providers/provider03"

md_text "Provider not exists"
curl_delete "providers/notexists"


md_title 2 "Templates" "templates"				###### TEMPLATES

md_title 3 "Create a template"					###
curl_post "templates" "template01.xml" "$XML" "$XML"
curl_post "templates" "template02.json" "$JSON" "$JSON"
curl_post "templates" "template02b.xml" "$XML" "$XML"

md_text "Template exists."
curl_post "templates" "template01.xml" "$XML" "$XML"

md_text "Linked provider not exists."
curl_post "templates" "template03.xml" "$XML" "$XML"

md_title 3 "Get a template"						###
curl_get "templates/template02" "" "$XML"
curl_get "templates/template02" "" "$JSON"

md_text "Template not exists."
curl_get "templates/notexists" "" "$XML"

md_title 3 "Get all the templates"				###
curl_get "templates" "" "$XML"
curl_get "templates" "" "$JSON"

md_title 3 "Delete a template"					###
curl_delete "templates/template02b"

md_text "Template not exists"
curl_delete "templates/notexists"


md_title 2 "Agremeents" "agreements"			###### AGREEMENTS

md_title 3 "Create an agreement"				###
curl_post "agreements" "agreement01.xml" "$XML" "$XML"
curl_post "agreements" "agreement02.json" "$JSON" "$JSON"
curl_post "agreements" "agreement02b.xml" "$XML" "$XML"

md_text "Linked provider not exists."
curl_post "agreements" "agreement03.xml" "$XML" "$XML"
md_text "Linked template not exists."
curl_post "agreements" "agreement04.xml" "$XML" "$XML"

md_text "Agreement exists."
curl_post "agreements" "agreement01.xml" "$XML" "$XML"

md_title 3 "Get an agreement"					###
curl_get "agreements/agreement01" "" "$XML"
curl_get "agreements/agreement01" "" "$JSON"


md_title 3 "Get all the agreements"				###
curl_get "agreements" "" "$XML"
curl_get "agreements" "" "$JSON"

md_title 3 "Get agreement status"				###
curl_get "agreements/agreement02/guaranteestatus" "" "$XML"
curl_get "agreements/agreement02/guaranteestatus" "" "$JSON"


md_title 3 "Delete an agreement"				###
curl_delete "agreements/agreement02b"

md_text "Agreement not exists"
curl_delete "agreements/notexists"

md_title 2 "Violations" "violations"			######

md_title 3 "Get all the violations"				###

