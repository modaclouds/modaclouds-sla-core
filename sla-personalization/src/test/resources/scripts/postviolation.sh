set -e -E -u -o pipefail -o noclobber -o noglob +o braceexpand || exit 1
trap 'printf "[ee] failed: %s\n" "${BASH_COMMAND}" >&2' ERR || exit 1

if [ $# -lt 3 ]; then
    echo "Usage: $0 agreementid stream metricValue"
    exit 1
fi
SLA_URL=${SLA_URL:-http://localhost:9040}
./genviolation.py $2 $3 | curl -X POST -d@- $SLA_URL/metrics/$1
