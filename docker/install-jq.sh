#!/bin/bash

JQ=/usr/bin/jq
curl https://stedolan.github.io/jq/download/linux64/jq > $JQ
chmod +x $JQ
ls -la $JQ