/* ;(function(window,module2){
    module2.fun()
})(window,module2) */  //原始依赖


;(function(){
    //主模块

    requirejs.config({
        
        baseUrl: './js',
        //声明各种个模块
        paths: {
            module2: './modules/module2',
            mod1: './modules/module1',
            jquery: 'https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min'
        }
    });

    //定义依赖
    requirejs(['module2','jquery'],function(module2,$){
        module2.fun()
        $('#bt').click(function(){
            alert(1)
        })
    })
})()
