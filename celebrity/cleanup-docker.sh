#!/bin/bash
docker images -a | grep 'celebrity' | awk '{print $3}' | xargs docker rmi -f 
