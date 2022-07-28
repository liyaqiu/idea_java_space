onload = function(){
    function bindEvent(){
        /* 为每个a标签绑定一个点击事件 */
        let aTags = document.querySelectorAll('.yifu_right ul li')
        for(let i=0,length =aTags.length;i<length;i++  ){
            aTags[i].onclick = function(){
                let tab_bodys = document.querySelectorAll('.yifu_right ol li')
                for(let j=0,length =tab_bodys.length;j<length;j++  ){
                    if(j===i){
                        tab_bodys[j].childNodes[0].className = 'show'
                    }else{
                        tab_bodys[j].childNodes[0].className = 'hidden'
                    }
                }
                return false;
            }
        }
    }
    bindEvent()
}