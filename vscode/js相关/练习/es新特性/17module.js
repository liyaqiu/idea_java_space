//方式1，逐个暴露
/* export let name = '逐个暴露'
export function sayHello(){
    console.log('逐个暴露','sayHello')
} */


//方式2，统一暴露
/* let name = '统一暴露'
function sayHello(){
    console.log('统一暴露','sayHello')
}
export {name,sayHello} */

//方式3，默认暴露
/* export default {
    name: '默认暴露',
    sayHello(){
        console.log('默认暴露','sayHello')
    }
} */
export default function(){
    return {
        name: '默认暴露',
        sayHello(){
            console.log('默认暴露','sayHello')
        }
    }
}
