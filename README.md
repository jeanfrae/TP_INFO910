**Récupération du projet sur git:**

```sh
git clone https://github.com/jeanfrae/TP_INFO910.git
```

## Partie docker

On a une application java web qui permet de lister et d'ajouter une personne.

L'application utilise une base de données mysql.

Pour cela, on a créé deux conteneurs à partir du fichier. docker-compose.yml (cf. fichier docker-compose.yml)

Dans notre fichier [docker-compose.yml](https://github.com/jeanfrae/TP_INFO910/blob/master/docker-compose.yml) on a deux services :

- mysql
- springboot-app

Le service `mysql` est fournit au moyen d'un conteneur `mysql` créé à partir de l’image `mysql`:

```yml
version: "3"
services:
  mysql: 
    container_name: mysql
    image: mysql
    ports: 
     - 3306:3306   
     ...
 ```   
     
Le service `springboot-app` est fournit au moyen d'un conteneur `spring-app` créé à partir de l’image `jeanfrae/tp-info910` de l'application java web après avoir 
construite à partir du [Dockerfile](https://github.com/jeanfrae/TP_INFO910/blob/master/app/Dockerfile) et de pousser sur le registre Docker hub.

```yml
   springboot-app:
    container_name: spring-app
    image: jeanfrae/tp-info910

    restart: always
    build: ./app
    ports:
      - 8080:8080
    depends_on:
      mysql:
        condition: service_healthy
```

Commande pour construire l'image de l'application java web

```bash
docker build -t jeanfrae/tp-info910 .
```

Commande pour pousser l'image sur Docker hub

```bash
docker push jeanfrae/tp-info910:latest

```
Addresse URL de l'image jeanfrae/tp-info910 sur Docker hub:

```bash
 https://hub.docker.com/repository/docker/jeanfrae/tp-info910
 ```
 
**Lancement et test de l’application**

Se mettre au niveau de la répertoire `TP_INFO910` et lancer la commande ci-dessous afin de créer et de lancer les deux conteneurs:

```bash
docker-compose up
```

Maintenant l'application est accessible sur la machine locale via l'adresse : http://localhost:8080/person (pour afficher les listes des personnes) et via l'adresse URL: http://localhost:8080/showNewPerson (pour ajouter une nouvelle personne)

## Partie Kubernetes

On a utilisé `Google Cloud Platform (GCP)` pour créer un cluster kubernetes à partir du service jke engine.


- deploiement : [TP_INFO910/kubernetes/deploy_mysql.yaml](https://github.com/jeanfrae/TP_INFO910/blob/master/kubernetes/deploy_mysql.yaml)
- service de type `ClusterIp`par defaut (https://github.com/jeanfrae/TP_INFO910/blob/master/kubernetes/service_mysql.yaml)
---

- deploiement : [TP_INFO910/kubernetes/deploy_app.yaml](https://github.com/jeanfrae/TP_INFO910/blob/master/kubernetes/deploy_app.yaml)
- service de type `LoadBalancer`(https://github.com/jeanfrae/TP_INFO910/blob/master/kubernetes/service_app.yaml)

Pour deployer l'application, se mettre au niveau de la répertoire `TP_INFO910/kubernetes`

Il faut tout d'abord deployer en premier`pvc_mysql.yaml`[TP_INFO910/kubernetes/pvc_mysql.yaml](https://github.com/jeanfrae/TP_INFO910/blob/master/kubernetes/pvc_mysql.yaml) pour reclamer un persistent volume au provider gcp

Lancer les commandes ci-dessous par ordre:

commande 1:

```bash
kubectl apply -f pvc_mysql.yaml
```
commande 2:

```bash
kubectl apply -f deploy_mysql.yaml
```
commande 3:

```bash
kubectl apply -f service_mysql.yaml
```
commande 4:
```bash
kubectl apply -f deploy_app.yaml
```
commande 5:
```bash
kubectl apply -f service_app.yaml
```

**L'application est accessible sur l'adresse :**

http://35.205.209.77:8080/











