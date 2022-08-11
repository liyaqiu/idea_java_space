import Mock from 'mockjs'

import bannerList from './banner.json'
import floorList from './floor.json'

Mock.mock('/mock/getBannerList',{code:200,data:bannerList})
Mock.mock('/mock/getFloorList',{code:200,data:floorList})