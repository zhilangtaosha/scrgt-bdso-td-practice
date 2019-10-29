docker build -t docker.io/salientcrgt2/scrgt-bdso-keycloak-theme .
docker push docker.io/salientcrgt2/scrgt-bdso-keycloak-theme
oc import-image scrgt-bdso-keycloak-theme --from docker.io/salientcrgt2/scrgt-bdso-keycloak-theme --confirm

oc create secret generic realm-secret --from-file=realm.json
oc adm policy add-scc-to-user anyuid -z default

helm fetch --untar --untardir . 'stable/keycloak'
helm template ./keycloak -f values.yaml --name keycloak | oc apply -f -
oc apply -f route.yaml

rm -rf keycloak
rm -f *.tar.gz