#!/usr/bin/env bash
#
# Installs sla-core distribution into /opt/modaclouds-sla-core-<version>
#   * Unpacks distribution package
#   * Creates database and user to be used by sla-core
#
# Prerequisites:
#   * /opt/modaclouds-sla-core-<version> exists
#   * mysql is installed
#   * jre is installed
#   * mysql run as root does not need pwd
#
VERSION=0.4.0

PACKAGE_ROOT="/opt/modaclouds-services-sla-core-$VERSION"
DIST_ROOT="$PACKAGE_ROOT/lib/distribution"

if [ "$(id -u)" != "0" ]; then
   echo "This script must be run as root"
   exit 1
fi

#
#Retrieve the distribution archive
#
TMPFILE=$(mktemp --tmpdir slacoredist.XXXXXXXX)
wget -O $TMPFILE "ftp://ftp.modaclouds.eu/public/distribution/sla-core/$VERSION/sla-core-distribution.tar.gz"

#
# Unpack
#
mkdir -p "$DIST_ROOT"
tar -xvzf $TMPFILE -C "$DIST_ROOT"

#
# Create database and user
#
mysql -u root < "$DIST_ROOT/share/database.sql"


echo "sla-core-$VERSION installed at $PACKAGE_ROOT"
