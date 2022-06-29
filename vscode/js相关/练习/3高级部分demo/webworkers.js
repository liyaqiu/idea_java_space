
var onmessage = function(event){
    console.log(this) //DedicatedWorkerGlobalScope 
    console.log('分线程接受到任务')
    var count = event.data
    for(var i=0;i< count; i++){}
    postMessage(count)
    console.log('分线程执行任务完毕')
}