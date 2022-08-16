/* 引入需要路由的相关组件 */
import Home from '@/pages/Home'
import Login from '@/pages/Login'
import Register from '@/pages/Register'
import Search from '@/pages/Search'
import Detail from '@/pages/Detail'
import AddCartSuccess from '@/pages/AddCartSuccess'
import ShopCart from '@/pages/ShopCart'

/* 定义路由配置 */
export default [
    {
        //路由重定向
        path: '*',
        redirect: "/home",
    },
    {
        name: 'shopcart',
        path: '/shopcart',
        component: ShopCart,
        meta: {
            footerIsShow: true
        }
    },
    {
        name: 'addcartsuccess',
        path: '/addcartsuccess/:buyNum',
        component: AddCartSuccess,
        meta: {
            footerIsShow: true
        }
    },
    {
        name: 'detail',
        path: '/detail/:goodsId',
        component: Detail,
        meta: {
            footerIsShow: true
        }
    },
    {
        path: '/home',
        component: Home,
        meta: {
            footerIsShow: true
        }
    },
    {
        path: '/login',
        component: Login,
        meta: {
            footerIsShow: false
        }
    },
    {
        path: '/register',
        component: Register,
        meta: {
            footerIsShow: false
        }
    },
    {
        name: 'search',
        path: '/search/:keyword?',
        component: Search,
        meta: {
            footerIsShow: true
        }
    },
]