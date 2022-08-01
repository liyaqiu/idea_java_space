function add(x,y){
    const abc = 'hello'
    console.log(abc)
    return new Promise((resolve,reject)=>{
        resolve(x+y)
    })
}

export default async function hello(x,y){
    const result = await add(x,y)
    console.log(result)
}