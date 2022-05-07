#!/bin/bash

harbor_url=$1
harbor_respository=$2
project_name=$3
image_version=$4
service_port=$5
spring_profile_active=$6

imageName=$harbor_url/$harbor_respository/$project_name:$image_version

echo "部署的镜像---> $imageName"

#查询容器是否运行，运行就删除
containerId=`docker ps -a | grep -w $project_name-$image_version | awk '{print $1}'`
echo "存在的镜像ID--> $containerId"
if [ "$containerId" != "" ] ; then
    #停止容器
    docker stop $containerId
    echo "停止容器成功"
    #删除容器
    docker rm $containerId
    echo "删除容器成功"
fi

#查询镜像是否存在,存在就删除
imageId=`docker images | grep -w $harbor_url/$harbor_respository/$project_name | awk '{print $3}'`
if [ "$imageId" != "" ] ; then
    #删除镜像
    docker rmi $imageId
    echo "删除镜像成功"
fi

#登陆harbor
docker login -u eric -p Eric123456 $harbor_url

#下载镜像
echo "下载镜像--> $imageName"
docker pull $imageName

#启动容器
echo "启动容器"
docker run -d --name $project_name-$image_version -p $service_port:$service_port $imageName $spring_profile_active
echo "启动容器成功"




