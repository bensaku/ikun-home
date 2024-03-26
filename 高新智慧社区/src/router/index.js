import Vue from 'vue'
import VueRouter from 'vue-router'

import IndexView from '../views/IndexView.vue'
import IndexViewYe from '../views/IndexViewYe.vue'
import Vote from '../views/Vote.vue'
import AffairView from '../views/AffairView.vue'
import ComplainView from '../views/ComplainView.vue'
import Car from '../views/Car.vue'
import House from '../views/House.vue'
import Problem from '../views/Problem.vue'
import Complain from '../views/Complain.vue'
import Family from '../views/Family.vue'
import Count from '../views/VoteView.vue'
import RealName from '../views/RealName.vue'
import AutoFill from '../views/AutoFill.vue'
import Login from '../views/Login.vue'
import RegisterView from '../views/Register.vue'
import Manual from '../views/Manual.vue'
import Retrieve from '../views/Retrieve.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/count',
    name: 'count',
    component:Count
},
  {
    path: '/index',
    name: 'index',
    component: IndexView
  },

  {
    path: '/affair',
    name: 'affair',
    component:AffairView
  },
  {
    path: '/suggestion',
    name: 'suggestion',
    component:ComplainView
  },
  {
    path: '/indexYe',
    name: 'indexYe',
    component: IndexViewYe
  },
  {
    path: '/car',
    name: 'car',
    component: Car
  },
  {
    path: '/house',
    name: 'house',
    component: House
  },
  {
    path: '/problem',
    name: 'problem',
    component:Problem
  },
  {
    path: '/complain',
    name: 'complain',
    component:Complain
  },
  {
    path: '/family',
    name: 'famliy',
    component:Family
  },
  {
    path: '/budget',
    name: 'vote',
    component:Vote
  },
  {
  path: '/',
  name: 'Login',
  component: Login,
  meta: {
    title: '登录首页'
  }
},
{
  path: '/RealName',
  name: 'RealName',
  component: RealName,
  meta: {
    title: '实名认证'
  }
},
{
  path: '/AutoFill',
  name: 'AutoFill',
  component: AutoFill,
  meta: {
    title: '自动填写'
  }
},
{
  path: '/RegisterView',
  name: 'RegisterView',
  component: RegisterView,
  meta: {
    title: '注册首页'
  }
},
{
  path: '/Manual',
  name: 'Manual',
  component: Manual,
  meta: {
    title: '手动填写'
  }
},
{
  path: '/Retrieve',
  name: 'Retrieve',
  component: Retrieve,
  meta: {
    title: '找回密码'
  }
},
 
 
]

const router = new VueRouter({
  routes
})

export default router
