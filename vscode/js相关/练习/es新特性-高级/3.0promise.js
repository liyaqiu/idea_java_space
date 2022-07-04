;(function(window){

    const PENDING = 'pending'
    const RESOLVED = 'resolved'
    const REJECTED = 'rejected'

    function Promise(executor){
        const _this = this
        _this.data = undefined
        _this.callbacks = []
        _this.state = PENDING
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


        console.log('my promise')
    }
    

    //定义原型方法,实例对象可以通过隐式原型访问
    Promise.prototype.then = function(onResolved,onRejectd){
        const _this = this
        
        if(_this.state === RESOLVED){
            console.log('RESOLVED RESOLVED')
            setTimeout(()=>{
                const result = onResolved(_this.data)
                //console.log(result)
                resolve(result)
            })
        }else if(_this.state === REJECTED){
            console.log('REJECTED REJECTED')
            setTimeout(()=>{
                onRejectd(_this.data)
            })
        }else if(_this.state === PENDING){
            console.log('PENDING PENDING')
            _this.callbacks.push({
                onResolved,
                onRejectd
            })
        }

    }
    Promise.prototype.catch = function(onRejectd){

    }

    //定义函数对象方法，通过函数对象可以直接访问

    //创建一个成功返回的Promise对象
    Promise.resolve = function(value){

    }
    //创建一个失败返回的Promise对象
    Promise.reject = function(reason){

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
