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
MOSFILE=distribution.tar.gz

rm -Rf "$MOSDIR"
mkdir "$MOSDIR"
mkdir "$MOSDIR/bin"
mkdir "$MOSDIR/etc"
mkdir "$MOSDIR/lib"
mkdir "$MOSDIR/share"


cp sla-service/target/dependency/jetty-runner.jar $MOSDIR/lib
cp sla-service/target/sla-service.war $MOSDIR/lib
cp dist/lib/distribution/bin/restoreDatabase.sh $MOSDIR/bin
cp sla-repository/src/main/resources/sql/database.sql $MOSDIR/share
cp sla-repository/src/main/resources/sql/atossla.sql $MOSDIR/share/schema.sql

tar -cvzf $DESTDIR/$MOSFILE -C $MOSDIR .

cp dist/bin/service-run.bash $DESTDIR

echo "mOS package $MOSFILE and wrapper script are in $DESTDIR"

echo "
Execute the following commands to upload distribution:
$ lftp ftp.modaclouds.eu -u $MODACLOUDS_FTP_CREDENTIALS -e "cd public/distributions/sla-core"
> mkdir -f \$version
> cd \$version
> put dist/target/distribution.tar.gz dist/target/service-run.bash"
