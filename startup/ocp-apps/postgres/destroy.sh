helm fetch --untar --untardir . 'stable/postgresql'
helm template ./postgresql --name postgresql | oc delete -f -

rm -rf postgresql
rm -f *.tar.gz