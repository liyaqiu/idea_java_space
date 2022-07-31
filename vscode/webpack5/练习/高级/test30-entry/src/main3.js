//es6静态导入
import _ from 'lodash'
console.log(_.join(['es6静态导入main3','hello','world']))

//es6动态导入
import('lodash').then(
    ({default:_})=>{
        console.log(_.join(['es6动态导入main3','hello','world']))
    }
)