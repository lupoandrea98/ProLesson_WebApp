import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from '../components/HomePage.vue'
import LogIn from '../components/LoginComp.vue'
import SigIn from '../components/SigInComp.vue'
import Prenotation from '../components/PrenotationPage.vue'
import Admin from '../components/AdminPage.vue'

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
  },
  {
    path: '/prenotation/:giorno/:ora',
    name: 'prenotation',
    component: Prenotation,
    props: true,

  },
  {
    path: '/admin',
    name: 'admin',
    component: Admin
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
