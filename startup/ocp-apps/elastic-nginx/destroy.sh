helm repo add bitnami https://charts.bitnami.com/bitnami

helm fetch --untar --untardir . 'bitnami/nginx'
helm template ./nginx -f values.yaml --name nginx | oc delete -f -
#oc delete -f route.yaml

rm -rf keycloak
rm -f *.tar.gz