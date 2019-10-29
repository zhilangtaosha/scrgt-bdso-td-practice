#/bin/bash
destroy_ocp () {
  echo "======== DESTROY_OCP START";
  pushd ocp-setup;
    make destroy;
  popd;
  echo "======== DESTROY_OCP END";
}

destroy_tools () {
  echo "======== DESTROY_TOOLS START";
  pushd pipeline-tools/terraform;
    terraform destroy -var pass=$1 -auto-approve;
  popd;

  echo "destroy_tools end";
  echo "======== DESTROY_TOOLS END";
}

destroy_db () {
  echo "======== DESTROY_DB START";
  pushd database;
    terraform destroy -auto-approve;
  popd;
  echo "======== DESTROY_DB END";
}


# runs in parallel for faster time to completion
# The above causes an issue. It does not complete. The prompt does not appear and appears hung. Testing with running them separately shows the
# command prompt after completion
destroy_ocp
destroy_tools
destroy_db
echo "destroy tools and ocp complete!"
