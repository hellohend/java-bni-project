name: Trigger Openshift Build
on :
    Push:
        branches:
            - main

jobs:
    trigger-build:
        runs-on: ubuntu-latest
        steps:
            - name: Checkout code
              uses: actions/checkout@v3

            - name: Install OC CLI
                run: |
                    curl -LO https://mirror.openshift.com/pub/openshift-v4/clients/oc/latest/linux/oc.tar.gz
                    tar -xzf oc.tar.gz
                    sudo mv oc /usr/local/bin/
            - name: Login to OpenShift
              run: |
                oc login --token=sha256~WUXhNli_bn7G1XOauEncTxNvFUnRXRmBTab0dklN66E --server=https://api.rm3.7wse.p1.openshiftapps.com:6443
            - name: Trigger Build
              run: |
                
