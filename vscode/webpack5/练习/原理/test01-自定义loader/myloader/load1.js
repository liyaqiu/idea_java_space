//同步loader
module.exports = function(content,sourceMap,meta,x){
    console.log('load1.js',content,sourceMap,meta,x)
    //this.callback(new Error('打包出错'),content,'sourceMap','meta','hello')
    return "打包成功"
}

module.exports.pitch = function(){
    console.log('pitch load1')
}