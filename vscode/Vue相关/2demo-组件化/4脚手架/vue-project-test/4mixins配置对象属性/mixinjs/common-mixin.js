//全局配置
export const globalConfig ={
    data() {
        return {
            globalmsg:'我是全局配置'
        }
    },
}
//局部配置
export const localConfig ={
    data() {
        return {
            localmsg:'我是局部配置'
        }
    },
    mounted() {
        console.log('mounted',this)
    },
}