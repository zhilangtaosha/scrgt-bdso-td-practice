helm repo add elastic https://helm.elastic.co
oc adm policy add-scc-to-user privileged -z default

helm fetch --untar --untardir . 'elastic/elasticsearch'
helm template ./elasticsearch -f values.yaml --name elasticsearch | oc apply -f -
oc apply -f route.yaml

rm -rf elasticsearch
rm -f *.tar.gz