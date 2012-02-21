#!/bin/bash
if [ ! -d "db" ]; then
    sudo sysctl -w kern.sysv.shmall=65536
    sudo sysctl -w kern.sysv.shmmax=16777216
    mkdir db
    initdb db
    postgres -D db -p 5555 &
    PGPID=$!
    while [ ! -S "/tmp/.s.PGSQL.5555" ]
    do
        sleep 0.5
    done
    createdb -p 5555 shouter
else
    postgres -D db -p 5555 &
    PGPID=$!
fi

wait $PGPID
