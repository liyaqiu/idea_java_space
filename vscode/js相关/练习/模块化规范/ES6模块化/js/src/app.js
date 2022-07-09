

import  $ from 'jquery'

import * as m1 from './module1.js'
import * as m2 from './module2.js'


m1.fun1()
//m1.fun2()

m2.fun1()
//m2.fun2()

$('#bt1').click(function(){
    alert(1)
})