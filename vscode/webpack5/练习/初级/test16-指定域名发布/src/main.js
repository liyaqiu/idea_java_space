
import test from './js/test.js'
test(100,300)



//asset/resource
import shanshui from './assets/shanshui.png'
console.log('asset/resource',shanshui)

//asset/inline
import fengjing from './assets/fengjing.jpg'
console.log('asset/inline',fengjing)

//asset 在resource(>8k文件,找全局定义assetModuleFilename)和inline(<8k文件)种自动选择一种
import Demo小于8k from './assets/Demo小于8k.zip'
console.log('asset 小于8k',Demo小于8k)

import Demo大于8k from './assets/Demo大于8k.zip'
console.log('asset 大于8k',Demo大于8k)

//asset/source
import wenjian from './assets/wenjian.txt'
console.log('asset/source',wenjian)


import './css/test.css'
import './less/test1.less'

/* csv数据引入 */
import scvData from './data/data.csv'
console.log('scvData',scvData)
/* xml数据引入 */
import xmlData from './data/data.xml'
console.log('xmlData',xmlData)
/* 默认支持json */
import jsonData from './data/data.json'
console.log(jsonData)

/* yaml yml 引入 */
import yamlData from './data/data.yaml'
console.log('yamlData',yamlData)
import ymlData from './data/data.yml'
console.log('ymlData',ymlData)

//es6静态导入
import _ from 'lodash'
console.log(_.join(['es6静态导入main','hello','world']))

//es6动态导入
import('lodash').then(
    ({default:_})=>{
        console.log(_.join(['es6动态导入main','hello','world']))
    }
)