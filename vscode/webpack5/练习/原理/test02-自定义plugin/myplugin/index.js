module.exports = function () {
    console.log('myplugin 执行了')
    this.apply = function (compiler) {
        console.log('myplugin apply')

        compiler.hooks.environment.tap('MyPlugin', () => {
            console.log('environment')
         });

        compiler.hooks.emit.tapAsync('MyPlugin', (compilation,callback) => {
           console.log('emit tapAsync')
           setTimeout(()=>{
                callback()
           },3000)
        });

        compiler.hooks.emit.tapPromise('MyPlugin', (compilation,callback) => {
            console.log('emit tapPromise')
            return new Promise((resolve) =>{
                setTimeout(()=>{
                    resolve()
                },3000)
            })
         });

        compiler.hooks.emit.tap('MyPlugin', (compilation) => {
            console.log('emit tap')
         });
    }
}