#/bin/bash

setup_pull_secret() {
  oc create -f dockerhub.yml
  oc secrets add serviceaccount/default secrets/dockerhub --for=pull
  oc secrets link default dockerhub --for=pull
}

setup_application() {
  for ENV in dev test prod
  do
    oc project $ENV
	# removed scrgt-bdso-cc-ms-useradmin, scrgt-bdso-facialrecognition
    for IMAGE in scrgt-bdso-cc-ms-celebrity scrgt-bdso-cc-frontend
    do
      echo "$IMAGE IN $ENV"
      oc import-image $IMAGE:latest --from docker.io/salientcrgt2/$IMAGE:latest --confirm
      oc process -f deploy-template.yml \
        -p APP_NAME=$IMAGE \
        -p APP_VERSION=latest \
        -p PROJECT_NAME=$ \
        | oc apply -f -
    done
  done
}
oc adm policy add-cluster-role-to-user cluster-admin admin
oc new-project dev
setup_pull_secret
oc create -f dev_secrets.yml
oc policy add-role-to-user edit admin
oc adm policy add-role-to-user admin admin

oc new-project test
setup_pull_secret
oc create -f test_secrets.yml
oc policy add-role-to-user edit admin
oc adm policy add-role-to-user admin admin

oc new-project prod
setup_pull_secret
oc create -f prod_secrets.yml
oc policy add-role-to-user edit admin
oc adm policy add-role-to-user admin admin

setup_application
