oc adm policy add-scc-to-user privileged -z default

helm fetch --untar --untardir . 'stable/postgresql'
helm template ./postgresql --name postgresql | oc apply -f -

rm -rf postgresql
rm -f *.tar.gz