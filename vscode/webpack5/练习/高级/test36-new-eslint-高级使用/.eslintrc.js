/* 
    "off" 或 0 - 关闭规则
    "warn" 或 1 - 开启规则，使用警告级别的错误：warn(不会导致程序退出)
    "error" 或 2 - 开启规则，使用错误级别的错误：error(当被触发的时候，程序会退出)
 */
module.exports = {
    //继承官方的Eslint规则
    extends: ["eslint:recommended"],
    env: {
        node: true, //启用node全局变量
        browser: true //启用浏览器中全局变量,比如console.log()
    },
    parserOptions: {
        ecmaVersion: "latest",
        sourceType: "module"
    },
    rules:{
        "no-var": 2, //不能使用var定义变量
        'no-undef': 1, //禁用未声明的变量，除非它们在 /*global */ 注释中被提到
        "no-unused-vars":1, //定义了变量，但是从未使用过
    }
}