helm repo add bitnami https://charts.bitnami.com/bitnami

helm fetch --untar --untardir . 'bitnami/nginx'
helm template ./nginx -f values.yaml --name nginx | oc apply -f -
#oc apply -f route.yaml

#rm -rf nginx
#rm -f *.tar.gz