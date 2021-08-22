# load-test-db

#### init mongo replica set
> cd docker
> 
> docker-compose up -d

#### hints
> docker run -it --network docker_mongo_network mongo sh
> 
> docker rm -f $(docker ps -aq)
> 
> docker rmi -f $(docker images -aq)