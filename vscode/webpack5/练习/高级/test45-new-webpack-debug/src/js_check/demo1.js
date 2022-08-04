
function add(x,y){
    const abc = 'hello'
    console.log(abc)
    return new Promise((resolve,reject)=>{
        resolve(x+y)
    })
}
var aaaa = 123;
;const abc = ()=>{
  console.log('hello')  
}


export default async function hello(x,y){
    const result = await add(x,y)
    console.log(result)
}
