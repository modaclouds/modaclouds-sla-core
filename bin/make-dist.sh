#!/usr/bin/env bash
if [ "$0" != "bin/make-dist.sh" ]; then
        echo "Must be executed from project root"
        exit 1
fi

DESTDIR=dist/target
DESTFILE=sla-service.tar.gz
SOURCEFILE=sla-service.war

#mvn clean package

if [ -e "$DESTDIR" ]; then
        echo "Directory $DESTDIR exists. You must remove it by hand"
        exit 2
fi

mkdir "$DESTDIR"

cd sla-service/target

tar cvzf "$DESTFILE" "$SOURCEFILE"

cd -

mv sla-service/target/$DESTFILE $DESTDIR

echo "Resulting $DESTFILE is in $DESTDIR"

MOSDIR="$DESTDIR/mos"
MOSFILE=sla-core-distribution.tar.gz

rm -Rf "$MOSDIR"
mkdir "$MOSDIR"
mkdir "$MOSDIR/bin"
mkdir "$MOSDIR/etc"
mkdir "$MOSDIR/lib"
mkdir "$MOSDIR/share"


cp sla-service/target/dependency/jetty-runner.jar $MOSDIR/lib
cp sla-service/target/sla-service.war $MOSDIR/lib
cp dist/bin/restoreDatabase.sh $MOSDIR/bin
cp sla-repository/src/main/resources/sql/database.sql $MOSDIR/share
cp sla-repository/src/main/resources/sql/atossla.sql $MOSDIR/share/schema.sql

tar -cvzf $DESTDIR/$MOSFILE -C $MOSDIR .

echo "mOS package $MOSFILE is in $DESTDIR"
