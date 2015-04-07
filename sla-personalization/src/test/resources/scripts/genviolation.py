#!/usr/bin/env python
import sys
import uuid
from pprint import pprint
import time
import json

if len(sys.argv) < 3:
    print(sys.argv[0] + " stream value")
    sys.exit(1)


vid = str(uuid.uuid4())
stream = sys.argv[1]
value = sys.argv[2]
millis = int(round(time.time() * 1000))

inner = {
    "http://www.modaclouds.eu/rdfs/1.0/monitoringdata#metric" : [
        {
          "type" : "literal" ,
          "value" : stream
        }
    ],
    "http://www.modaclouds.eu/rdfs/1.0/monitoringdata#timestamp" : [
      {
        "type" : "literal" ,
        "value" : millis,
        "datatype" : "http://www.w3.org/2001/XMLSchema#integer"
      }
    ],
    "http://www.modaclouds.eu/rdfs/1.0/monitoringdata#value" : [
      {
        "type" : "literal" ,
        "value" : value,
        "datatype" : "http://www.w3.org/2001/XMLSchema#double"
      }
    ] ,
    "http://www.modaclouds.eu/rdfs/1.0/monitoringdata#resourceId" : [
      {
        "type" : "literal" ,
        "value" : "resource",
        "datatype" : "http://www.w3.org/2001/XMLSchema#string"
      }
    ]
}
root = {vid : inner}

result = json.dumps(root, indent=True)
print(result)
