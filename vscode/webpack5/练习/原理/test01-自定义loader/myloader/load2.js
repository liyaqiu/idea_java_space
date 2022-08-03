//同步loader
module.exports = function(content,sourceMap,meta,x){
    console.log('load2.js',content,sourceMap,meta,x)
    this.callback(null,content,'sourceMap','meta','hello')
}

module.exports.pitch = function(){
    console.log('pitch load2')
    //return "中断操作"
}