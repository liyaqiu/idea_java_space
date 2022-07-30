
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