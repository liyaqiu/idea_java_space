//方式1
/* import * as m1 from './17module.js'
console.log(m1.name)
m1.sayHello() */

/* import {name as aname,sayHello} from './17module.js'
console.log(aname)
sayHello() */

//方式2
/* import * as m2 from './17module.js'
console.log(m2.name)
m2.sayHello() */

/* import {name as aname,sayHello} from './17module.js'
console.log(aname)
sayHello() */

//方式3
/* import * as m3 from './17module.js'
console.log(m3.default.name)
m3.default.sayHello() */

/* import {default as m3} from './17module.js'
console.log(m3.name)
m3.sayHello() */

import m3 from  './17module.js'
console.log(m3.name)
m3.sayHello()