
import request from './request'
import mockRequest from './mockRequest'

/* home */
export const getCategoryListRequest = () => request({url:'/product/getBaseCategoryList',method: 'GET',})

export const getBannerListRequest = () => mockRequest({url:'/getBannerList',method: 'GET',})
export const getFloorListRequest = () => mockRequest({url:'/getFloorList',method: 'GET',})



/* search */
export const getProductListRequest = (data) => request({url:'/list', method: 'POST',data})






