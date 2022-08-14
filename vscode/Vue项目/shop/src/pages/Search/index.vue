<template>
  <div>
    <TypeNav />
    <div class="main">
      <div class="py-container">
        <!--bread-->
        <div class="bread">
          <ul class="fl sui-breadcrumb">
            <li>
              <a href="#">全部结果</a>
            </li>
          </ul>
          <ul class="fl sui-tag">
            <li class="with-x" v-if="queryParams.categroyName">{{queryParams.categroyName}}<i @click="removeBreadCategroyName">×</i></li>
            <li class="with-x" v-if="queryParams.keyword">{{queryParams.keyword}}<i @click="removeBreadKeyword">×</i></li>
            <li class="with-x" v-if="queryParams.trademark">{{queryParams.trademark.split(":")[1]}}<i @click="removeBreadTrademark">×</i></li>
            <li class="with-x" v-for="(prop,index) in queryParams.props" :key="index">
                {{prop.split(":")[1]}}<i @click="removeBreadProps(index)">×</i>
            </li>
          </ul>
        </div>

        <!--selector-->
        <!-- 绑定自定义事件给子组件 -->
        <SearchSelector @trademarkInfo="trademarkInfo" @propsInfo="propsInfo"/>

        <!--details-->
        <div class="details clearfix">
          <div class="sui-navbar">
            <div class="navbar-inner filter">
              <ul class="sui-nav">
                <li :class="{active:queryParams.order.indexOf('1')!==-1}" @click="changeOrder('1')">
                  <a>
                    综合
                    <span class="iconfont icon-up" v-if="queryParams.order.indexOf('asc')!==-1 && queryParams.order.indexOf('1')!==-1"></span>
                    <span class="iconfont icon-down" v-if="queryParams.order.indexOf('desc')!==-1 && queryParams.order.indexOf('1')!==-1"></span>
                  </a>
                </li>
                <li :class="{active:queryParams.order.indexOf('2')!==-1}" @click="changeOrder('2')">
                  <a>
                    价格
                    <span class="iconfont icon-up" v-if="queryParams.order.indexOf('asc')!==-1 && queryParams.order.indexOf('2')!==-1"></span>
                    <span class="iconfont icon-down" v-if="queryParams.order.indexOf('desc')!==-1 && queryParams.order.indexOf('2')!==-1"></span>
                  </a>
                </li>
              </ul>
            </div>
          </div>
          <div class="goods-list">
            <ul class="yui3-g">
              <li class="yui3-u-1-5" v-for="goods in goodsList" :key="goods.id">
                <div class="list-wrap">
                  <div class="p-img">
                    <router-link :to="`/detail/${goods.id}`"><img :src="goods.defaultImg" /></router-link>
                  </div>
                  <div class="price">
                    <strong>
                      <em>¥</em>
                      <i>{{goods.price}}.00</i>
                    </strong>
                  </div>
                  <div class="attr">
                    <a target="_blank" href="item.html" title="seo相关的优化信息" v-html="goods.title"></a>
                  </div>
                  <div class="commit">
                    <i class="command">已有<span>2000</span>人评价</i>
                  </div>
                  <div class="operate">
                    <a href="success-cart.html" target="_blank" class="sui-btn btn-bordered btn-danger">加入购物车</a>
                    <a href="javascript:void(0);" class="sui-btn btn-bordered">收藏</a>
                  </div>
                </div>
              </li>
            </ul>
          </div>
          <!-- 分页器 -->
          <Pagination :pageNo='queryParams.pageNo' :pageSize='queryParams.pageSize' :total='total' :continues='5' @pageNoInfo='pageNoInfo'/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  import SearchSelector from './SearchSelector'
  export default {
    name: 'Search',
    components: {SearchSelector},
    data() {
      return {
        queryParams:{
          categroy1Id: undefined,
          categroy2Id: undefined,
          categroy3Id: undefined,
          categroyName: undefined,
          keyword: undefined,
          order: "1:desc",
          pageNo: 1,
          pageSize: 10,
          props: [],
          trademark: undefined
        }
      }
    },
    methods: {
      changeOrder(flag){
        const rawFlag = this.queryParams.order.split(':')[0]
        if(rawFlag===flag){
          //console.log('原始等于',rawFlag===flag)
          this.queryParams.order = `${flag}:${this.queryParams.order.split(':')[1]==='desc'?'asc':'desc'}`
        }else{
          //console.log('原始不等于',rawFlag===flag)
          this.queryParams.order = `${flag}:desc`
        }
        this.getData()
      },
      getData(){
        console.log('当前请求参数',this.queryParams)
        this.$store.dispatch('search/getProductList',this.queryParams)  
      },
      removeBreadCategroyName(){
        console.log('removeBreadCategroyName 重新路由')
        //更改路由地址
        this.$router.push({
          name:'search',
          params:this.$route.params,
          query: {
            categroyName: undefined //查询参数置空，当路由参数变更时，变监听到，并且在监听逻辑上做了对象合并
          } 
        })
      },
      removeBreadKeyword(){
        console.log('removeBreadKeyword 重新路由')
        this.$bus.$emit('removeKeyword') //清空header组件上Keyword
        //更改路由地址
        this.$router.push({
          name:'search',
          params:{
            keyword:undefined //查询参数置空，当路由参数变更时，变监听到，并且在监听逻辑上做了对象合并
          },
          query: this.$route.query
        })
        
      },
      trademarkInfo(trademark){
        console.log("trademarkInfo到子组件传递过来的参数",trademark)
        const newTrademark = `${trademark.tmId}:${trademark.tmName}`
        if(newTrademark!== this.queryParams.trademark){
          this.queryParams.trademark = newTrademark
          this.getData()
        }
      },
      removeBreadTrademark(){
          console.log('removeBreadTrademark')
          this.queryParams.trademark = undefined
          this.getData()
      },
      propsInfo(props){
        console.log("propsInfo收到子组件传递过来的参数",props)
        if(this.queryParams.props.indexOf(props)===-1){
          this.queryParams.props.push(props)
          this.getData()
        }
      },
      removeBreadProps(index){
        console.log('removeBreadProps')
        this.queryParams.props.splice(index,1)
        this.getData()
      },
      pageNoInfo(pageNo){
        console.log('pageNoInfo',pageNo)
        this.queryParams.pageNo = pageNo
        this.getData()
      }
    },
    computed:{
      ...mapGetters('search',['goodsList','total']),
    },
    beforeMount() {
      Object.assign(this.queryParams,this.$route.query,this.$route.params)
    },
    mounted() {
      console.log('Search 挂载完毕')
      this.getData()
    },
    watch:{
      //监听路由变化
      $route(newValue,oldValue){
        //console.log(newValue,oldValue)
        console.log('Search 监听到路由变化')
        this.queryParams.pageNo = 1 //重置当前页
        Object.assign(this.queryParams,{categroy1Id:undefined,categroy2Id:undefined,categroy3Id:undefined,},this.$route.query,this.$route.params)
        this.getData() 
      }
    },
    beforeDestroy() {
      console.log('search 销毁')
    },
  }
</script>

<style lang="less" scoped>
  .main {
    margin: 10px 0;

    .py-container {
      width: 1200px;
      margin: 0 auto;

      .bread {
        margin-bottom: 5px;
        overflow: hidden;

        .sui-breadcrumb {
          padding: 3px 15px;
          margin: 0;
          font-weight: 400;
          border-radius: 3px;
          float: left;

          li {
            display: inline-block;
            line-height: 18px;

            a {
              color: #666;
              text-decoration: none;

              &:hover {
                color: #4cb9fc;
              }
            }
          }
        }

        .sui-tag {
          margin-top: -5px;
          list-style: none;
          font-size: 0;
          line-height: 0;
          padding: 5px 0 0;
          margin-bottom: 18px;
          float: left;

          .with-x {
            font-size: 12px;
            margin: 0 5px 5px 0;
            display: inline-block;
            overflow: hidden;
            color: #000;
            background: #f7f7f7;
            padding: 0 7px;
            height: 20px;
            line-height: 20px;
            border: 1px solid #dedede;
            white-space: nowrap;
            transition: color 400ms;
            cursor: pointer;

            i {
              padding-left: 10px;
              cursor: pointer;
              font: 400 14px tahoma;
              display: inline-block;
              height: 100%;
              vertical-align: middle;
            }

            &:hover {
              color: #28a3ef;
            }
          }
        }
      }

      .details {
        margin-bottom: 5px;

        .sui-navbar {
          overflow: visible;
          margin-bottom: 0;

          .filter {
            min-height: 40px;
            padding-right: 20px;
            background: #fbfbfb;
            border: 1px solid #e2e2e2;
            padding-left: 0;
            border-radius: 0;
            box-shadow: 0 1px 4px rgba(0, 0, 0, 0.065);

            .sui-nav {
              position: relative;
              left: 0;
              display: block;
              float: left;
              margin: 0 10px 0 0;

              li {
                float: left;
                line-height: 18px;

                a {
                  display: block;
                  cursor: pointer;
                  padding: 11px 15px;
                  color: #777;
                  text-decoration: none;
                }

                &.active {
                  a {
                    background: #e1251b;
                    color: #fff;
                  }
                }
              }
            }
          }
        }

        .goods-list {
          margin: 20px 0;

          ul {
            display: flex;
            flex-wrap: wrap;

            li {
              height: 100%;
              width: 20%;
              margin-top: 10px;
              line-height: 28px;

              .list-wrap {
                .p-img {
                  padding-left: 15px;
                  width: 215px;
                  height: 255px;

                  a {
                    color: #666;

                    img {
                      max-width: 100%;
                      height: auto;
                      vertical-align: middle;
                    }
                  }
                }

                .price {
                  padding-left: 15px;
                  font-size: 18px;
                  color: #c81623;

                  strong {
                    font-weight: 700;

                    i {
                      margin-left: 3px;
                    }
                  }
                }

                .attr {
                  padding-left: 15px;
                  width: 85%;
                  overflow: hidden;
                  margin-bottom: 8px;
                  min-height: 38px;
                  cursor: pointer;
                  line-height: 1.8;
                  display: -webkit-box;
                  -webkit-box-orient: vertical;
                  -webkit-line-clamp: 2;

                  a {
                    color: #333;
                    text-decoration: none;
                  }
                }

                .commit {
                  padding-left: 15px;
                  height: 22px;
                  font-size: 13px;
                  color: #a7a7a7;

                  span {
                    font-weight: 700;
                    color: #646fb0;
                  }
                }

                .operate {
                  padding: 12px 15px;

                  .sui-btn {
                    display: inline-block;
                    padding: 2px 14px;
                    box-sizing: border-box;
                    margin-bottom: 0;
                    font-size: 12px;
                    line-height: 18px;
                    text-align: center;
                    vertical-align: middle;
                    cursor: pointer;
                    border-radius: 0;
                    background-color: transparent;
                    margin-right: 15px;
                  }

                  .btn-bordered {
                    min-width: 85px;
                    background-color: transparent;
                    border: 1px solid #8c8c8c;
                    color: #8c8c8c;

                    &:hover {
                      border: 1px solid #666;
                      color: #fff !important;
                      background-color: #666;
                      text-decoration: none;
                    }
                  }

                  .btn-danger {
                    border: 1px solid #e1251b;
                    color: #e1251b;

                    &:hover {
                      border: 1px solid #e1251b;
                      background-color: #e1251b;
                      color: white !important;
                      text-decoration: none;
                    }
                  }
                }
              }
            }
          }
        }

        
      }
    }
  }
</style>