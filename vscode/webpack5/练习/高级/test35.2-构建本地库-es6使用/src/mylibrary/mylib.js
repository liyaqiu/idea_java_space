function add_(x,y){
    return new Promise((resolve,reject)=>{
        resolve(x+y)
    })
}

export async function add(x,y){
    const result = await add_(x,y)
    console.log('这个是我工具库输出',result)
}
