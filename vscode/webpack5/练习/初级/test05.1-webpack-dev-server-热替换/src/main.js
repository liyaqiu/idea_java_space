function hello(x,y){
    console.log('hello-world',x+y)
}

hello(100,300)
hello(400,200)


import './test.css'

//引入
import './abc.js'
//对此js进行热更新监听
if(module.hot){
    console.log(module.hot)
    module.hot.accept('./abc.js')
}
