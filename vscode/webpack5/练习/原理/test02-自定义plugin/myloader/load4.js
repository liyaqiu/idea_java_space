//异步loader
module.exports = function(content){
    console.log('load4.js',content)
    const callback = this.async()
    setTimeout(()=>{
        callback(null,content,'sourceMap','meta','hello')
    },3000)
}

module.exports.pitch = function(){
    console.log('pitch load4')
}

//module.exports.raw = true //读取到的为buff数据，默认读取为文本数据