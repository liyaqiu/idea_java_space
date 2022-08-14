
import request from './request'
import mockRequest from './mockRequest'

/* home */
export function getCategoryListRequest(){
    return request({ 
        url: '/product/getBaseCategoryList', 
        method: 'GET', 
    })
}

/* home mock */
export function getBannerListRequest(){
    return mockRequest({
         url: '/getBannerList', 
         method: 'GET', 
    })
}

export function getFloorListRequest(){
    return mockRequest({ 
        url: '/getFloorList', 
        method: 'GET', 
    })
}



/* search */
export function getProductListRequest (queryParams){
    //console.log('getProductListRequest')
    return request({ 
        url: '/list', 
        method: 'POST', 
        data: queryParams 
    })
}



/* detail */
export function getProductDetailRequest (goodsId) {
    return request({
        url: `/item/${goodsId}`,
        method: 'GET',
    })
}





