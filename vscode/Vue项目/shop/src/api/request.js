
import axios from 'axios'

import store from '@/store'

/**
 * cnpm i nprogress -S
 * import nprogress from 'nprogress'
 * import  'nprogress/nprogress.css'
 * nprogress.start()
 * nprogress.done()
 * 
*/
import nprogress from 'nprogress'
import  'nprogress/nprogress.css'

//局部配置
const req = axios.create({
    baseURL: '/api',
    timeout: 5000,
})

//请求拦截器
req.interceptors.request.use(
    (config) => {
        //console.log('request2', config)
        nprogress.start()
        //在请求头设置用户token
        if(store.state.detail.userToken){
            config.headers.userTempId = store.state.detail.userToken
        }
        return config
        //throw config
    },
    (error) => {
        //console.log('error request2', error)
        throw error
    }
)

//响应拦截器
req.interceptors.response.use(
    (response) => {
        //console.log('response1', response)
        nprogress.done()
        return response.data
    },
    (error) => {
        //console.log('error response1', error)
        throw error
    }
)


export default req