helm fetch --untar --untardir . 'elastic/elasticsearch'
helm template ./elasticsearch -f values.yaml --name elasticsearchdb | oc delete -f -
oc delete -f route.yaml

rm -rf elasticsearch
rm -f *.tar.gz