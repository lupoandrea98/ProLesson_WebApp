import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LogIn from '../components/LoginComp.vue'
import SigIn from '../components/SigInComp.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component : LogIn
  },
  {
    path: '/sigin',
    name: 'sigin',
    component: SigIn
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
