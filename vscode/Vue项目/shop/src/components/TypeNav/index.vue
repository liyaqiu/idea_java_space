<template>
  <!-- 商品分类导航 -->
    <div class="type-nav">
        <div class="container">
            <div @mouseenter="mouseEnterIsShow" @mouseleave="mouseLeaveIsShow">
                <h2 class="all">全部商品分类</h2>
                <div class="sort" @click="onSearch" v-show="isShow" >
                    <div class="all-sort-list2">
                        <div class="item" v-for="c1 in categoryListData" :key="c1.categoryId">
                            <h3>
                                <a :data-categroyname='c1.categoryName' :data-categroy1id='c1.categoryId'>{{c1.categoryName}}</a>
                            </h3>
                            <div class="item-list clearfix">
                                <div class="subitem">
                                    <dl class="fore" v-for="c2 in c1.categoryChild" :key="c2.categoryId">
                                        <dt>
                                            <a :data-categroyname='c2.categoryName' :data-categroy2id='c2.categoryId'>{{c2.categoryName}}</a>
                                        </dt>
                                        <dd>
                                            <em v-for="c3 in c2.categoryChild" :key="c3.categoryId">
                                                <a :data-categroyname='c3.categoryName' :data-categroy3id='c3.categoryId'>{{c3.categoryName}}</a>
                                            </em>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <nav class="nav">
                <a href="###">服装城</a>
                <a href="###">美妆馆</a>
                <a href="###">尚品汇超市</a>
                <a href="###">全球购</a>
                <a href="###">闪购</a>
                <a href="###">团购</a>
                <a href="###">有趣</a>
                <a href="###">秒杀</a>
            </nav>
        </div>
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    export default {
        name: 'TypeNav',
        data() {
            return {
                isShow:true,
            }
        },
        methods: {
            onSearch(event){
                const {categroyname,categroy1id,categroy2id,categroy3id} = event.target.dataset
                
                const query = {}
                const location = {
                    name:'search',
                    query,
                    params:this.$route.params
                }

                if(categroyname){
                    query.categroyname = categroyname
                    if(categroy1id){
                        query.categroy1id = categroy1id
                    }else if(categroy2id){
                        query.categroy2id = categroy2id
                    }else if(categroy3id){
                        query.categroy3id = categroy3id
                    }
                }
                
                this.$router.push(location)
            },
            mouseEnterIsShow(){
                this.isShow = true
            },
            mouseLeaveIsShow(){
                if(this.$route.name==='search'){
                    this.isShow=false
                }
            },
        },
        computed: {
            ...mapState('home',['categoryListData']),
        },
        mounted() {
            console.log('TypeNav',this)
            if(this.$route.name==='search'){
                this.isShow=false
            }
        },
    }
</script>

<style scoped lang='less'>
    .type-nav {
        border-bottom: 2px solid #e1251b;
        a{
            &:hover{
                text-decoration: underline;
                cursor: pointer;
            }
        }
        .container {
            width: 1200px;
            margin: 0 auto;
            display: flex;
            position: relative;

            .all {
                width: 210px;
                height: 45px;
                background-color: #e1251b;
                line-height: 45px;
                text-align: center;
                color: #fff;
                font-size: 14px;
                font-weight: bold;
            }

            .nav {
                a {
                    height: 45px;
                    margin: 0 22px;
                    line-height: 45px;
                    font-size: 16px;
                    color: #333;
                }
            }

            .sort {
                position: absolute;
                left: 0;
                top: 45px;
                width: 210px;
                height: 461px;
                position: absolute;
                background: #fafafa;
                z-index: 999;

                .all-sort-list2 {
                    
                    .item {
                        &:hover{
                            background: skyblue;
                        }
                        h3 {
                            line-height: 30px;
                            font-size: 14px;
                            font-weight: 400;
                            overflow: hidden;
                            padding: 0 20px;
                            margin: 0;

                            a {
                                color: #333;
                            }
                        }

                        .item-list {
                            display: none;
                            position: absolute;
                            width: 734px;
                            min-height: 460px;
                            background: #f7f7f7;
                            left: 210px;
                            border: 1px solid #ddd;
                            top: 0;
                            z-index: 9999 !important;

                            .subitem {
                                float: left;
                                width: 650px;
                                padding: 0 4px 0 8px;

                                dl {
                                    border-top: 1px solid #eee;
                                    padding: 6px 0;
                                    overflow: hidden;
                                    zoom: 1;

                                    &.fore {
                                        border-top: 0;
                                    }

                                    dt {
                                        float: left;
                                        width: 54px;
                                        line-height: 22px;
                                        text-align: right;
                                        padding: 3px 6px 0 0;
                                        font-weight: 700;
                                    }

                                    dd {
                                        float: left;
                                        width: 415px;
                                        padding: 3px 0 0;
                                        overflow: hidden;

                                        em {
                                            float: left;
                                            height: 14px;
                                            line-height: 14px;
                                            padding: 0 8px;
                                            margin-top: 5px;
                                            border-left: 1px solid #ccc;
                                        }
                                    }
                                }
                            }
                        }

                        &:hover {
                            .item-list {
                                display: block;
                            }
                        }
                    }
                }
            }
        }
    }
</style>