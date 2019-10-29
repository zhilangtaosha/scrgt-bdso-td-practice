helm fetch --untar --untardir . 'stable/keycloak'
helm template ./keycloak --name keycloak | oc delete -f -
oc delete -f route.yaml

rm -rf keycloak
oc delete secret realm-secret
