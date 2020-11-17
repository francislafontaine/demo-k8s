./mvnw clean install -B

docker build -t mrdocker007/quote:latest -t mrdocker007/quote:"$SHA" -f ./quote/Dockerfile ./quote

docker push mrdocker007/quote:latest
docker push mrdocker007/quote:$SHA

kubectl apply -f k8s
kubectl set image deployments/quote-deployment quote=mrdocker007/quote:$SHA
