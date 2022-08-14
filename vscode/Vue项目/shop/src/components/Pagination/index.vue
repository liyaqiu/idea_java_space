<template>
    <div class="pagination">
        <button :disabled="pageNo===1" @click="$emit('pageNoInfo',pageNo-1)">上一页</button>
        <button v-if="startAndEnd.start > 1" @click="$emit('pageNoInfo',1)" :class="{active:pageNo === 1}">1</button>
        <button v-if="startAndEnd.start > 2">···</button>

        <span v-for="(page,index) in startAndEnd.end" :key="index">
            <button v-if="page>=startAndEnd.start" @click="$emit('pageNoInfo',page)" :class="{active:pageNo === page}" :disabled="pageNo === page">{{page}}</button>
        </span>
        
        <!-- <button>3</button>
        <button>4</button>
        <button>5</button>
        <button>6</button>
        <button>7</button> -->

        <button v-if="startAndEnd.end < totalPage -1">···</button>
        <button v-if="startAndEnd.end < totalPage" @click="$emit('pageNoInfo',totalPage)" :class="{active:pageNo === totalPage}">{{totalPage}}</button>
        <button :disabled="pageNo===totalPage" @click="$emit('pageNoInfo',pageNo+1)">下一页</button>

        <button style="margin-left: 30px">共 {{total}} 条</button>
    </div>
</template>

<script>
    export default {
        name: "Pagination",
        /*
            pageNo 当前页
            pageSize 每页条数
            total 总条数
            continues 连续分页页数
        */
        props:['pageNo','pageSize','total','continues'],
        computed:{
            totalPage(){
                return Math.ceil(this.total / this.pageSize)
            },
            startAndEnd(){
                const {continues,pageNo,totalPage} = this
                let start = 0
                let end = 0
                if(continues > totalPage){
                    start = 1
                    end = totalPage
                }else{
                    start = pageNo - parseInt(continues / 2)
                    end = pageNo + parseInt(continues / 2)
                    if(start < 1){
                        start = 1
                        end = continues
                    }
                    if(end > totalPage){
                        end = totalPage
                        start = totalPage - continues + 1
                    }
                }
                /* console.log(continues,pageNo,totalPage)
                console.log('startAndEnd',start,end) */
                return {start,end}
            }
        },
    }
</script>

<style lang="less" scoped>
    .pagination {
        text-align: center;
        button {
            margin: 0 5px;
            background-color: #f4f4f5;
            color: #606266;
            outline: none;
            border-radius: 2px;
            padding: 0 4px;
            vertical-align: top;
            display: inline-block;
            font-size: 13px;
            min-width: 35.5px;
            height: 28px;
            line-height: 28px;
            cursor: pointer;
            box-sizing: border-box;
            text-align: center;
            border: 0;

            &[disabled] {
                color: #c0c4cc;
                cursor: not-allowed;
            }

            &.active {
                cursor: not-allowed;
                background-color: #409eff;
                color: #fff;
            }
        }
    }
</style>