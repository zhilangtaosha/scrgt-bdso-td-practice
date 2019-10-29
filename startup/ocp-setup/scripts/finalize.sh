#!/usr/bin/env bash

pushd setup
./initialize_environment.sh
popd

pushd ocp-apps/elastic
oc project dev
./deploy.sh
oc project test
./deploy.sh
oc project prod
./deploy.sh
popd

pushd ocp-apps/keycloak
oc project dev
./deploy.sh
oc project test
./deploy.sh
oc project prod
./deploy.sh
popd

pushd ocp-apps/elastic-db
oc project dev
./deploy.sh
oc project test
./deploy.sh
oc project prod
./deploy.sh
popd

pushd ocp-apps/elastic-kibana
oc project dev
./deploy.sh
oc project test
./deploy.sh
oc project prod
./deploy.sh
popd