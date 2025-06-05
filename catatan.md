
COMMAND © bariq pradipa mahendra ekofani

cara push github

git status /cek status github yang akan di pilih untuk di upload
git add. /mengupload semua package yang akan di siapkan
git status /cek status
git commit -m "message" /cek commit dengan memberikan massage
git push origin master /push github

-> membuat hpa
menurut chatgpt 1. buat dependecy 

2. tambahkan adminer-hpa.yaml


command oc
//cek hpa aktif
oc get hpa
//add limit
oc set resources deployment/java-bni-project-git   --requests=cpu=250m,memory=512Mi   --limits=cpu=500m,memory=1Gi
//menghapus limit
oc patch deployment/adminer   -p '{"spec":{"template":{"spec":{"containers":[{"name":"adminer","resources":{"limits":null}}]}}}}'
oc rollout status  deployment/adminer /rollout ulang deployment bagian adminer
oc get pods cek pods
oc apply -f openshift/adminer/adminer-deployment.yaml //apply oc ke openshift

buka openshift nyalakan semua pods dan cek apakah sudah terlimit apa belum
->
//Mulai build sekarang juga dan tampilkan lognya langsung di layar sampai selesai
oc start-build java-bni-project-git --follow
oc get bc
/buka secret
oc get bc java-bni-project-git -o jsonpath="{.spec.triggers[?(@.type=='GitHub')].github.secret}"
oc describe bc java-bni-project-git //describe oc
massukan url github yang ada di oc describe diatas(webhook github) tambahkan secret key
()https://api.rm3.7wse.p1.openshiftapps.com:6443/apis/build.openshift.io/v1/namespaces/bariq123456-dev/buildconfigs/java-bni-project-git/webhooks/<secret>/github
secret dari line /buka secret

....
buka github reporsitory
settings->webhook
response 403 karena gratisan

test dengan cek github (settiings/webhook/recent delivery)

->common deployment error in openshift

jenis error
1. Major
2. Minor
3. Hotflix(butuh cepat)
 ex : ovo metode bayar metode pembayaran maka termasuk major

