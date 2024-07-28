<#--不可见注释-->
<!--可见注释 文档 http://freemarker.foofun.cn/index.html -->

<#--变量定义-->
<#assign a=1 b=[2,2,3,4] c={'name':'eric'}>
${a} ${b?join("-")} ${c.name}

<#--if语句-->
<#assign show=false >
<#if show?? && show>
    ${show?c}显示
<#else>
    不显示
</#if>


<#assign show=100 >
<#if show gt 10 >
    显示
</#if>

<#--宏指令-->
<#macro test isShow>
    <#if isShow>
        你好！！！！！${people.name}
        <#else>
            不显示${people.name}--<#nested>
    </#if>
</#macro>
<@test false>
    <#--nested插槽指令-->
    ${people.age}
</@test>

<#--import导入指令-->
<#import './util.ftl' as util>
<@util.test2></@util.test2>


<#--include包含指令-->
<#macro content>
    <#include './util.ftl'>
    <#include './content.html'>
</#macro>

<@content></@content>



