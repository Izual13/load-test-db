#!/bin/bash

echo init-rs.sh.sh time now: `date +"%T" `

#todo: add waiting
sleep 5

mongosh --host mongo1:27017 <<EOF
var cfg = {
    "_id": "rs666",
    "version": 1,
    "members": [
        {
            "_id": 0,
            "host": "mongo1:27017",
            "priority": 2
        },
        {
            "_id": 1,
            "host": "mongo2:27018",
            "priority": 0
        },
        {
            "_id": 2,
            "host": "mongo3:27019",
            "priority": 0
        }
    ],settings: {chainingAllowed: true}
};
rs.initiate(cfg, { force: true });
rs.reconfig(cfg, { force: true });
rs.status();
db.getMongo().setReadPref('nearest');
EOF