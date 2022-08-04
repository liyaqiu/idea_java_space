//同步loader
module.exports = function(content,sourceMap,meta,x){
    console.log('load3.js',content,sourceMap,meta,x)
    this.callback(null,content,'sourceMap','meta','hello')
}

module.exports.pitch = function(){
    console.log('pitch load3')
}