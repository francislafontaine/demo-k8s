docker build -t mrdocker007/test:latest -t mrdocker007/test:$SHA -f ./demo-k8s/Dockerfile ./demo-k8s

docker push mrdocker007/test:latest

docker push mrdocker007/test:$SHA

kubectl apply -f k8s
kubectl set image deployments/cloud-demo server=mrdocker007/test:$SHA
