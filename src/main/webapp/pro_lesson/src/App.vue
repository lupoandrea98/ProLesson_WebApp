<template>
    <NavBar @attivaLogIn = "login_seen = $event"></NavBar>
    <!-- <nav>
        <router-link to="/">Home</router-link> |
        <router-link to="/about">About</router-link>
    </nav>
    <router-view/> -->
    <LogIn v-if=login_seen></LogIn> 
  
</template>

<script>
import LogIn from '@/components/LoginComp.vue'
import NavBar from '@/components/NavBar.vue'

export default({

    data() {
        return { 
            login_seen: false
        }
    },
  
    components: {
        LogIn, 
        NavBar
    }
}) 
</script>


<style lang="scss">
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
    }

    nav {
        padding: 30px;

    a {
        font-weight: bold;
        color: #2c3e50;

        &.router-link-exact-active {
            color: #42b983;
        }
    }
    }
</style>
