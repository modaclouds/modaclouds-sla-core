#!/usr/bin/env bash
if [ "$0" != "bin/make-dist.sh" ]; then
        echo "Must be executed from project root"
        exit 1
fi

DESTDIR=sla-service/target
DESTFILE=sla-service.tar.gz
SOURCEFILE=sla-service.war

mvn clean package


cd $DESTDIR

tar cvzf "$DESTFILE" "$SOURCEFILE"

cd -

echo "Resulting $DESTFILE is in $DESTDIR"
