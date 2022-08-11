
import request from './request'

export const getCategoryListRequest = () => request({url:'/product/getBaseCategoryList',method: 'GET',})



import mockRequest from './mockRequest'

export const getBannerListRequest = () => mockRequest({url:'/getBannerList',method: 'GET',})