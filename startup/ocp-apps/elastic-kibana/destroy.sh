
helm fetch --untar --untardir . 'elastic/kibana'
helm template ./kibana -f values.yaml --name kibana | oc delete -f -
#oc apply -f route.yaml

rm -rf kibana
rm -f *.tar.gz