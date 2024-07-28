<#--不可见注释-->
<!--可见注释 文档 http://freemarker.foofun.cn/index.html -->

<#--内置函数链式操作-->
<h2>name：${name?upper_case?replace("IC","IICC")?lower_case}</h2>

<#--不存在字段输出方式-->
<h2>不存在：${foo!}</h2>
<h2>不存在：${foo!"默认值12"}</h2>
<h2>不存在：${(foo??)?string}</h2>
<h2>不存在：${foo???string}</h2>

<#--boolean输出方式-->
<h2>health：${health?string}</h2>
<h2>health：${health?string("是的","不是")}</h2>
<h2>health：${health?c}</h2>
<h2>health：${health?then("是的","不是")}</h2>

<#-------------------------------------------------------------------------------------------->

<#--常用输出方式-->
<h2>name：${name}</h2>
<h2>age：${age}</h2>
<h2>price：${price?string("0.##")}</h2>
<h2>localDateTime：${localDateTime}</h2>

<#--日期类型输出方式-->
<h2>birthday：${birthday?date}</h2>
<h2>birthday：${birthday?time}</h2>
<h2>birthday：${birthday?datetime}</h2>
<h2>birthday：${birthday?string("yyyy年MM月dd日")}</h2>

<#--对象处理-->
<h2>people.name：${people.name}</h2>
<h2>people.age：${people.age}</h2>

<#--Map处理-->
<h2>map.name：${map.name}</h2>
<h2>map.age：${map.age}</h2>

<#list map?keys as key>
    key:${key} --- value:${map[key]}
</#list>

<#list map?values as value>
    value:${value}
</#list>


<#--数组和集合处理-->
<#list arrStr as el>
<h2>下标：${el?index} --- 值：${el}</h2>
</#list>

<#list arrList?reverse as el>
<h2>下标：${el?index} --- 值：${el}</h2>
</#list>

<#list arrs as arr>
<#list arr as el>
<h2>下标：${arr?index} --- 值：${el}</h2>
</#list>
</#list>

<#list peoples?sort?reverse as people>
    <h2>下标：${people?index} --- name：${people.name} --- age：${people.age}</h2>
</#list>

<#list peoples?sort_by("age")?reverse as people>
    <h2>下标：${people?index} --- name：${people.name} --- age：${people.age}</h2>
</#list>



