#!/bin/bash

## chunk::dd0589220f7c9bd3c2b62f4e1edc9b95::begin ##
set -e -E -u -o pipefail -o noclobber -o noglob +o braceexpand || exit 1
trap 'printf "[ee] failed: %s\n" "${BASH_COMMAND}" >&2' ERR || exit 1

if test "$( getent passwd -- mos-services | cut -f 3 -d : )" -ne "${UID}" ; then
	exec sudo -u mos-services -g mos-services -E -n -- "${0}" "${@}"
	exit 1
fi

if ! test "${#}" -eq 0 ; then
	printf '[ii] invalid arguments; aborting!\n' >&2
	exit 1
fi

umask 0027

exec </dev/null >&2
## chunk::dd0589220f7c9bd3c2b62f4e1edc9b95::end ##

_variable_defaults=(
		
		_BIN='@{definitions:environment:LIB}/distribution/bin'
		_LIB='@{definitions:environment:LIB}/distribution/lib'
		
		_TOWER4CLOUDS_MANAGER_ENDPOINT_IP='@{definitions:environment:TOWER4CLOUDS_MANAGER_ENDPOINT_IP}'
		_TOWER4CLOUDS_MANAGER_ENDPOINT_PORT='@{definitions:environment:TOWER4CLOUDS_MANAGER_ENDPOINT_PORT}'

		_SLACORE_ENDPOINT_IP='@{definitions:environment:SLACORE_ENDPOINT_IP}'
		_SLACORE_ENDPOINT_PORT='@{definitions:environment:SLACORE_ENDPOINT_PORT}'
		_SLACORE_PUBLIC_ENDPOINT_IP='@{definitions:environment:SLACORE_PUBLIC_ENDPOINT_IP}'
		_SLACORE_PUBLIC_ENDPOINT_PORT='@{definitions:environment:SLACORE_PUBLIC_ENDPOINT_PORT}'

		_MYSQL_ENDPOINT_IP='@{definitions:environment:MYSQL_ENDPOINT_IP}'
		_MYSQL_ENDPOINT_PORT='@{definitions:environment:MYSQL_ENDPOINT_PORT}'
		_MYSQL_DATABASE='@{definitions:environment:MYSQL_DATABASE}'
		_MYSQL_USERNAME='@{definitions:environment:MYSQL_USERNAME}'
		_MYSQL_PASSWORD='@{definitions:environment:MYSQL_PASSWORD}'

		_JAVA_HOME='@{definitions:environment:JAVA_HOME}'
		_PATH='@{definitions:environment:PATH}'
		_TMPDIR='@{definitions:environment:TMPDIR}'
)
declare "${_variable_defaults[@]}"

## chunk::fdccb2b60ff605167433bb1c89bd0b84::begin ##
export PATH="${_PATH}"

_identifier="${modaclouds_service_identifier:-0000000000000000000000000000000000000000}"

if test -n "${modaclouds_service_temporary:-}" ; then
	_TMPDIR="${modaclouds_service_temporary:-}"
elif test -n "${modaclouds_temporary:-}" ; then
	_TMPDIR="${modaclouds_temporary}/services/${_identifier}"
else
	_TMPDIR="${_TMPDIR}/${_identifier}"
fi
## chunk::fdccb2b60ff605167433bb1c89bd0b84::end ##

_variable_overrides=(

		_TOWER4CLOUDS_MANAGER_ENDPOINT_IP="${MODACLOUDS_TOWER4CLOUDS_MANAGER_ENDPOINT_IP:-${_TOWER4CLOUDS_MANAGER_ENDPOINT_IP}}"
		_TOWER4CLOUDS_MANAGER_ENDPOINT_PORT="${MODACLOUDS_TOWER4CLOUDS_MANAGER_ENDPOINT_PORT:-${_TOWER4CLOUDS_MANAGER_ENDPOINT_PORT}}"

		_SLACORE_ENDPOINT_IP="${MODACLOUDS_SLACORE_ENDPOINT_IP:-${_SLACORE_ENDPOINT_IP}}"
		_SLACORE_ENDPOINT_PORT="${MODACLOUDS_SLACORE_ENDPOINT_PORT:-${_SLACORE_ENDPOINT_PORT}}"
		_SLACORE_PUBLIC_ENDPOINT_IP="${MODACLOUDS_SLACORE_PUBLIC_ENDPOINT_IP:-${_SLACORE_PUBLIC_ENDPOINT_IP}}"
		_SLACORE_PUBLIC_ENDPOINT_PORT="${MODACLOUDS_SLACORE_PUBLIC_ENDPOINT_PORT:-${_SLACORE_PUBLIC_ENDPOINT_PORT}}"

		_MYSQL_ENDPOINT_IP="${MODACLOUDS_MYSQL_ENDPOINT_IP:-${_MYSQL_ENDPOINT_IP}}"
		_MYSQL_ENDPOINT_PORT="${MODACLOUDS_MYSQL_ENDPOINT_PORT:-${_MYSQL_ENDPOINT_PORT}}"
		_MYSQL_DATABASE="${MODACLOUDS_MYSQL_DATABASE:-${_MYSQL_DATABASE}}"
		_MYSQL_USERNAME="${MODACLOUDS_MYSQL_USERNAME:-${_MYSQL_USERNAME}}"
		_MYSQL_PASSWORD="${MODACLOUDS_MYSQL_PASSWORD:-${_MYSQL_PASSWORD}}"
		
		_TMPDIR="${MODACLOUDS_SLACORE_TMPDIR:-${_TMPDIR}}"
)
declare "${_variable_overrides[@]}"

## chunk::3a0efc2555cc97891ac1266f5065920a::begin ##
if test ! -e "${_TMPDIR}" ; then
	mkdir -p -- "${_TMPDIR}"
	mkdir -- "${_TMPDIR}/tmp"
	mkdir -- "${_TMPDIR}/home"
fi

exec {_lock}<"${_TMPDIR}"
if ! flock -x -n "${_lock}" ; then
	printf '[ee] failed to acquire lock; aborting!\n' >&2
	exit 1
fi

if test -d "${_TMPDIR}/cwd" ; then
	chmod -R u+w -- "${_TMPDIR}/cwd"
	rm -R -- "${_TMPDIR}/cwd"
fi
mkdir -- "${_TMPDIR}/cwd"

cd -- "${_TMPDIR}/cwd"

_environment=(
		PATH="${_PATH}"
		TMPDIR="${_TMPDIR}/tmp"
		HOME="${_TMPDIR}/home"
		USER='modaclouds-services'
)
## chunk::3a0efc2555cc97891ac1266f5065920a::end ##

_environment+=(
		
		MODACLOUDS_MONITORING_MANAGER_URL="http://${_TOWER4CLOUDS_MANAGER_ENDPOINT_IP}:${_TOWER4CLOUDS_MANAGER_ENDPOINT_PORT}"
		MODACLOUDS_SLACORE_URL="http://${_SLACORE_PUBLIC_ENDPOINT_IP}:${_SLACORE_PUBLIC_ENDPOINT_PORT}"
		MODACLOUDS_MYSQL_URL="jdbc:mysql://${_MYSQL_ENDPOINT_IP}:${_MYSQL_ENDPOINT_PORT}/${_MYSQL_DATABASE}"
		MODACLOUDS_MYSQL_USERNAME="${_MYSQL_USERNAME}"
		MODACLOUDS_MYSQL_PASSWORD="${_MYSQL_PASSWORD}"
		
)
_JAVA_TMPDIR="${_TMPDIR}/tmp"

printf '[--]\n' >&2
printf '[ii]   * sla listen address: `%s`;\n' "${_SLACORE_ENDPOINT_IP}" >&2
printf '[ii]   * sla listen port: `%s`;\n' "${_SLACORE_ENDPOINT_PORT}" >&2
printf '[ii]   * environment:\n' >&2
for _variable in "${_environment[@]}" ; do
	printf '[ii]       * `%s`;\n' "${_variable}" >&2
done
printf '[ii]   * working directory: `%s`;\n' "${PWD}" >&2
printf '[ii]   * java home: `%s`;\n' "${_JAVA_HOME}" >&2
printf '[ii]   * java tmp dir: `%s`;\n' "${_JAVA_TMPDIR}" >&2
printf '[ii]   * bin dir: `%s`;\n' "${_BIN}" >&2
printf '[ii]   * lib dir: `%s`;\n' "${_LIB}" >&2
printf '[--]\n' >&2

printf '[ii] starting sla core...\n' >&2
printf '[--]\n' >&2

printf '\n\n'

exec \
	env \
			-i "${_environment[@]}" \
	"${_JAVA_HOME}/bin/java" \
			-Djava.io.tmpdir="${_JAVA_TMPDIR}" \
			-jar ${_LIB}/jetty-runner.jar \
			--host ${_SLACORE_ENDPOINT_IP} \
			--port ${_SLACORE_ENDPOINT_PORT} \
			--path / ${_LIB}/sla-service.war
exit 1
