;(function(window){

    const PENDING = 'pending'
    const RESOLVED = 'resolved'
    const REJECTED = 'rejected'

    function Promise(executor){
        const _this = this
        _this.data = undefined
        _this.callbacks = []
        _this.state = PENDING

        //添加数据，并且更改状态，执行回调函数把数据返回
        function resolve(value){
            //只能改一次状态
            if(PENDING !== _this.state ){
                return
            }
            _this.state = RESOLVED
            _this.data = value
            if(_this.callbacks.length>0){
                setTimeout(()=>{
                    _this.callbacks.forEach(el =>{
                        el.onResolved(_this.data)
                    })
                })
            }
        }

        //添加数据，并且更改状态，执行回调函数把数据返回
        function reject(reason){
            //只能改一次状态
            if(PENDING !== _this.state ){
                return
            }
            _this.state = REJECTED
            _this.data = reason
            if(_this.callbacks.length>0){
                setTimeout(()=>{
                    _this.callbacks.forEach(el =>{
                        el.onRejectd(_this.data)
                    })
                })
            }
        }
        
        try {
           // reject("失败") resolve("成功")
            const result = executor(resolve,reject)
        } catch (error) {
            //throw "失败1"
            reject(error)
        }
    }
    

    //定义原型方法,实例对象可以通过隐式原型访问
    Promise.prototype.then = function(onResolved,onRejectd){
        //调用者的_this
        const _this = this
   
        onResolved = (typeof onResolved) === 'function' ? onResolved:value => {return value}
        onRejectd = (typeof onRejectd) === 'function' ? onRejectd:reason =>{ throw reason}

        return new Promise((resolve,reject)=>{
            
            //改了状态和增加了数据，但未返回数据
            if(_this.state === RESOLVED){
                //console.log('RESOLVED RESOLVED')
                setTimeout(()=>{
                    handle(onResolved)
                })
            //改了状态和增加了数据，但未返回数据
            }else if(_this.state === REJECTED){
                //console.log('REJECTED REJECTED')
                setTimeout(()=>{
                    handle(onRejectd)
                })

            //未改状态和没增加数据，所以只能保存回调函数
            }else if(_this.state === PENDING){
                //console.log('PENDING PENDING')
                _this.callbacks.push({
                    onResolved(value){
                        handle(onResolved)
                    },
                    onRejectd(reason){
                        handle(onRejectd)
                    }
                })
            }

            function handle(fun){
                try {
                    let result = fun(_this.data)
                    
                    if(result instanceof Promise){ //Promise执行器函数,进入递归模式
                        
                        let promise = result
                        promise.then(
                            value =>{
                                resolve(value)
                            },
                            reason =>{
                                reject(reason)
                            }
                        )
                        //promise.then(resolve,reject)

                    }else{//正常的return返回
                        //更改本次Promise状态，以及设置本次Promise数据
                        resolve(result)
                    }
                } catch (error) {//throw返回
                    //更改本次Promise状态，以及设置本次Promise数据
                    reject(error)
                }
            }

            
        })

    }

    //返回新的失败Promise对象
    Promise.prototype.catch = function(onRejectd){
        //let _this = this
        //console.log('catch执行')
        return this.then(undefined,onRejectd);
    }

    //定义函数对象方法，通过函数对象可以直接访问

        //返回新的成功Promise对象
        Promise.resolve = function(value){


        }
        //返回新的失败Promise对象
        Promise.reject = function(reason){
            return new Promise((resolve,reject)=>{
                throw reason
            })
        }

        //全部成功时，返回Promise数组
        //失败1个，返回失败的Promiose实例
        Promise.all = function(promises){

        }

        //有一个成功或者一个失败，立即返回Promose实例对象
        Promise.race = function(promises){

        }

    window.Promise = Promise
})(window)
