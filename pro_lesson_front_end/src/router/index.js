import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from '../components/HomePage.vue'
import LogIn from '../components/LoginComp.vue'
import SigIn from '../components/SigInComp.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomePage
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
