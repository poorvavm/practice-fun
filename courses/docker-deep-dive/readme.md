Pluralsite self learning deep dive for dockers

All the slides source is :

https://app.pluralsight.com/library/courses/docker-deep-dive/exercise-files
by Nigel Poulton.

#########################################################
# docker commands
#########################################################

root@dockHost:~# docker version

root@dockHost:~# docker info 

root@dockHost:~# docker images

root@dockHost:~# docker images --tree

root@dockHost:~# docker history img

root@dockHost:~# docker pull coreos/etcd

root@dockHost:~# docker run -it ubuntu:14.04 /bin/bash

root@contId :/# ctrl + P + Q                  (detach and come out to host)

root@dockHost:~# docker attach contId               (reattach the terminal to container command prompt)

root@dockHost:~# docker ps                    (check docker containers running) 

root@dockHost:~# docker stop contId           (stop containers with contId)

root@dockHost:~# docker ps -l                 (shows last container running)

root@dockHost:~# docker start contId          (start containers with contId)

root@dockHost:~# docker restart contId        (restart containers with contId)

root@dockHost:~# docker info                  (shows number of containers and images)

root@dockHost:~# ls -la /var/lib/docker/containers/          (shows containers on our local machine)

root@dockHost:~# docker rm contId                     (remove/delete a container, should be stopped earlier)

root@dockHost:~# docker rm -f contId                     (remove/delete a running container)

root@dockHost:~# docker top contId                     (processes running on container with more details)

root@dockHost:~# docker logs contId                     (docker logs for that container)

root@dockHost:~# docker inspect imageId                 (Image low level detailed information)

root@dockHost:~# ls /var/lib/docker/containers/contId/   (all the json files for metadata)

root@dockHost:~# cat /var/lib/docker/containers/contId/config.json         (config information)


root@dockHost:~# docker inspect contId | grep Pid
            "Pid" : 1923
root@dockHost:~# nsenter -m -u -n -p -i -t 1923             (enter docker with that process id nsenter : 
                                                            namespace enter)

root@dockHost:~# docker-enter contId                        (enter docker prompt)

root@dockHost:~# docker exec -it contId /bin/bash           (run bash terminal on docker)

root@dockHost:~# docker run -v /usr/local/bin:/target jpetazzo/nsenter                
Installing nsenter to /target
Installing docker-enter to /target
                                                                        (Installing/mounting nsenter)....

#########################################################
# Dockerfile (to build an image)
#########################################################
    -  FROM has to be first line
    -  RUN commands creates a new layer, like the following one creates three layers on the top of each other with base as UBUNTU 15.04
    -  keep the directory empty where Dockerfile is there, otherwise all the files are included in that image as well
    -  On docker hub read the Dockerfile for full information about that image
    -  MAINTAINER and CMD also adds one layer each. So if Ubuntu:15.04 has 5 layers following Dockerfile will create an image with 11 layers

root@dockHost:~# vim Dockerfile

# this is comment
# Ubuntu based Hello World container
FROM ubuntu:15.04
MAINTAINER vishalemail@gmail.com
RUN apt-get update
RUN apt-get install -y nginx
RUN apt-get install -y golang
CMD ["echo","Hello World"]

:wq

root@dockHost:~# docker build -t helloworld:0.1 .             (create the image using above Dockerfile
                                                            run it from the same directory of Dockerfile)

root@dockHost:~# docker --tree imageId                      (shows the image layers)

root@dockHost:~# docker history imageId                 (shows everything/commands run earlier on image)

root@dockHost:~# docker run helloworld:0.1               (spin off a container using image just built)

-----------------------
-   The build cache
-----------------------
root@dockHost:~# docker build -t "build1" .          (slower)
root@dockHost:~# docker build -t "build2" .          (lightning fast because of the build cache)

-----------------------
-   Build a webserver Dockerfile
-----------------------
root@dockHost:~# mkdir ~/web
root@dockHost:~# cd ~/web
root@dockHost:~# vim Dockerfile

# Ubuntu based webserver container
FROM ubuntu:15.04
MAINTAINER vishalemail@gmail.com
RUN apt-get update
RUN apt-get install -y apache2
RUN apt-get install -y apache2-utils
RUN apt-get install -y vim
RUN apt-get clean
EXPOSE 80                                                   (exposes port 80)
CMD ["apache2ctl", "-D", "FOREGROUND"]


:wq

root@dockHost:~# docker build -t "webserver" .

root@dockHost:~# docker run -d -p 80:80 webserver
                          (run webserver as a daemon and expose mapped port 80)

root@dockHost:~# docker ps 

-   Use a webpage to connect to webserver and you should see apache welcome page
-   above Dockerfile will create many layers. so to avoid that use following. Only adds 1 layer for RUN

root@dockHost:~# vim Dockerfile

# Ubuntu based webserver container
FROM ubuntu:15.04
MAINTAINER vishalemail@gmail.com
RUN apt-get update \
    && apt-get install -y apache2 \
    apache2-utils \
    vim \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*
EXPOSE 80
CMD ["apache2ctl", "-D", "FOREGROUND"]


:wq

root@dockHost:~# docker build -t "webserver-small" .

root@dockHost:~# docker history webserver-small                   (shows fewer layers in history now)

-----------------------
-   Dockerfile instructions
-----------------------
-   CMD: 
    -   run-time, 
    -   runs commands in container at launch time, 
    -   One per Dockerfile
    -   equivalent of 
        docker run <args> <command>

    -   Shell Form
        -   Commands are expressed the same way as shell commands
        -   commands get prepended by "/bin/sh -C"

    -   Exec form
        -   Json array style
            ["command", "arg1"]
        -   Containers don't need a shell
        -   Avoid string munging by the shell
        -   no shell features:
            -   No variable expansion
            -   No special characters (&&, ||, >.....)

-   RUN: 
    -   build-time, 
    -   add layers to images, 
    -   used to install apps

-   ENTRYPOINT
***************

root@dockHost:~# vim Dockerfile

FROM ubuntu:15.04
RUN apt-get update && apt-get install -y iputils-ping
ENTRYPOINT ["echo"]


:wq
    
root@dockHost:~# docker build -t="hw2" .

root@dockHost:~# docker run hw2 Hellooooo There!
Hellooooo There!
root@dockHost:~# docker run hw2 /bin/bash
/bin/bash

***************

    -   in the above example anything we passed to run command has been taken as arguments to ENTRYPOINT command which is "echo". 
    -   because of that even if we gave /bin/bash it echoed "/bin/bash"
    -   Kind of making this container as a binary

***************

root@dockHost:~# vim Dockerfile

FROM ubuntu:15.04
RUN apt-get update && apt-get install -y iputils-ping apache2
ENTRYPOINT ["apache2ctl"]


:wq

root@dockHost:~# docker build -t="web2" .

root@dockHost:~# docker run web2 -D FOREGROUND

***************
    -   Above is same as creating a webserver but using ENTRYPOINT binary container


-   ENV
    -   Environment variables in the image

***************

root@dockHost:~# vim Dockerfile

FROM ubuntu:15.04
RUN apt-get update && apt-get install -y iputils-ping apache2
ENV var1=Vishal var2=Mittal
ENTRYPOINT ["apache2ctl"]


:wq

root@dockHost:~# docker build -t="vishal1" .

root@dockHost:~# docker run -it vishal1 /bin/bash
root@contId :/# env
var1=vishal
.....
***************


    -   can be also used within the Dockerfile
**************
root@dockHost:~# vim Dockerfile

FROM ubuntu:15.04
RUN apt-get update && apt-get install -y iputils-ping apache2
ENV var1=ping var2=8.8.8.8
CMD $var1 $var2


:wq

root@dockHost:~# docker build -t="pinger" .

root@dockHost:~# docker run -d pinger
root@dockHost:~# docker logs -f pinger
***************


#########################################################
# Push image to docker hub
#########################################################
-   Only new layers gets pushed
-   existing images are skipped

root@dockHost:~# docker images                      (see all the images)

root@dockHost:~# docker tag imageId vishalDocHubId/helloworld:1.0       (this is actually the repo name)

root@dockHost:~# docker push vishalDocHubId/helloworld:1.0

root@dockHost:~# docker rmi imageId1 imageId2 imageId3            (delete/remove images)

root@dockHost:~# docker pull vishalDocHubId/helloworld:1.0        (pull fresh image from doc hub)

#########################################################
# private registry
#########################################################

root@dockRegHost:~# docker run -d -p 5000:5000 registry      (expose port 5000 from container to outside 
                                                              and launch the container)

root@dockHost1:~# docker images
root@dockHost1:~# docker tag imageId debian.docker.course:5000/priv-reg-test 
                                (debian.... is the host name of private registry running as a container)
root@dockHost1:~# docker push imageId debian.docker.course:5000/priv-reg-test       
                            (image pushed to private repo)

root@dockHost2:~# docker run -d debian.docker.course:5000/priv-reg-test  
                            (pulls the image and runs it )


#########################################################
# Docker Volumes
#########################################################
-   decoupled data on a mount point to a container

root@dockHost:~# docker run -it -v /test-vol --name=volVishal ubuntu:15.04 /bin/bash

root@contId :/# ls -l                                       (shows the /test-vol as dir)


root@dockHost:~# docker inspect volVishal                 (shows local container details from volVishal container)

.....
"Volumes" : {
    "/test-vol" : "/var/lib/docker/vfs/dir/contId"
},
"VolumesRW" : {
    "/test-vol": true
}
.....

root@dockHost:~# docker run -it --volumes-from=volVishal --name=volPoorva ubuntu:15.04 /bin/bash
                                                            (run container volPoorva with volVishal volume settings)

-   Volumes in Dockerfile
**************
root@dockHost:~# vim Dockerfile

FROM ubuntu:15.04
RUN apt-get update && apt-get install -y iputils-ping
VOLUME /data


:wq

root@dockHost:~# docker build -t="volumeCont" .

root@dockHost:~# docker run -d volumeCont
***************

root@dockHost:~# docker rm -v contId            (deletes/removes container with the volume data)

#########################################################
# Docker networking
#########################################################
-----------------------
-   docker0 bridge
-----------------------
    -   docker host has a docker0 bridge/virtual switch/connection which is used for container communication
    -   just like another ethernet port 
    -   software created switch
    -   install "bridge-utils" package on docker host to see more communication details

root@dockHost:~# brctl show docker0

root@dockHost:~# docker run -d --name="net1" net-img
root@dockHost:~# brctl show
                                    (shows only one interface connected to the docker0 bridge)

root@dockHost:~# docker run -d --name="net2" net-img
root@dockHost:~# brctl show
                                    (now it shows two interfaces connected to the docker0 bridge)


root@dockHost:~# docker attach net1
root@contId :/# ip a
                                    (networking details of the container)
root@contId :/# traceroute 8.8.8.8
                                    (shows dockerhost ip in the route)


root@dockHost:~# ls -l /var/lib/docker/containers/contId 
config.json
hostconfig.json
hostname
hosts
resolv.conf                            
                                    (container's network settings)
                                    (in current versions hosts, and resolve.conf can be modified while container is running)
-----------------------
-   Exposing ports at runtime
-----------------------
root@dockHost:~# vim Dockerfile

FROM ubuntu:15.04
RUN apt-get update && apt-get install -y iputils-ping traceroute apache2
EXPOSE 80
ENTRYPOINT ["apache2ctl"]
CMD ["-D", "FOREGROUND"]


:wq

root@dockHost:~# docker build -t="apache-img" .
root@dockHost:~# docker run -d -p 5001:80 --name=web1 apache-img
                                            (local 5001 port to container port 80)
root@dockHost:~# docker ps

root@dockHost:~# docker port web1
80/tcp -> 0.0.0.0:5001


root@dockHost:~# docker run -d -p 5002:80/udp --name=web2 apache-img
                                            (local udp 5002 port to container port 80)
root@dockHost:~# docker port web1
80/udp -> 0.0.0.0:5002


root@dockHost:~# docker run -d -p 192.168.56.50:5003:80 --name=web3 apache-img
                                            (map for only that IP address and not all)
root@dockHost:~# docker port -P web1
80/tcp -> 192.168.56.50:5003


-   -P exposes all ports and exposed ports are mapped to random high number ports on docker host

-----------------------
-   Linking containers
-----------------------
    -   Only used for container to container communication
    -   no outside world communication
    -   No ports exposed here 
    -   one src to many receivers is supported
    -   many src to one receiver is also supported

root@dockHost:~# vim Dockerfile

FROM ubuntu:15.04
RUN apt-get update && apt-get install -y iputils-ping traceroute apache2
EXPOSE 80
ENTRYPOINT ["apache2ctl"]
CMD ["-D", "FOREGROUND"]


:wq

root@dockHost:~# docker build -t="apache-img" .
root@dockHost:~# docker run -d --name=sourceCont apache-img

root@dockHost:~# docker run --name=receiverCont --link=sourceCont:ali-sourceCont -it ubuntu:15.04 /bin/bash
                                                            (ali is for alias)

root@receiverContId:~# env | grep ALI
bunch of env params for mapping
                                                    (apps can use these env variables to make use of linking)
                                                    (apps need to know these variables ahead of time)

#########################################################
# Docker Troubleshooting
#########################################################

-   Logging levels : (debug, info, error, fatal)

root@dockHost:~# service docker stop
root@dockHost:~# docker -d -l debug &

root@dockHost:~# service docker start
                            (equivalent to /usr/bin/docker -d --log-level=fatal)
