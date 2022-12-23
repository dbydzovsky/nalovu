#!/bin/bash

docker run \
    --name nalovuDb \
    -p 5454:5432 \
    -e POSTGRES_USER=root \
    -e POSTGRES_PASSWORD=Password1* \
    -e POSTGRES_DB=nalovu \
    -d \
    postgres