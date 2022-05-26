<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#" v-on:click="getCookie">ProLesson</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
              <li class="nav-item">
                <router-link v-if="isAdmin" class="nav-link" aria-current="page" to="/admin"> ControlPanel </router-link>
              </li>
              <li class="nav-item">
                  <router-link v-if="logged" class="nav-link" to="/prenlist"> Prenotation List </router-link>
              </li>
          </ul>
          <form class="form-inline my-2 my-lg-0">
            <router-link v-if="!logged" class="btn btn-primary my-2 my-sm-0" type="submit" to="/login"> LogIn/SigIn </router-link>
            <router-link v-if="logged" class="btn btn-danger my-2 my-sm-0" type="submit" v-on:click="logOut" to="/"> LogOut </router-link>
          </form>
        </div>
    </nav>
    <br>
    <router-view/> 
    <footer>
        <blockquote class="blockquote text-center">
            <p class="text-secondary"> ProLesson © 2021-2022 </p>
        </blockquote>
    </footer>
</template>

<script>
//import func from "vue-temp/vue-editor-bridge";
import $ from '../node_modules/jquery'


export default({

    data() {
        return { 
            login_link: "http://localhost:8080/TWEB_war_exploded/api/login",
            logout_link: "http://localhost:8080/TWEB_war_exploded/api/logout",
            isAdmin: false,
            logged: false,
            sessionid: null
        }
    },

    methods: {
        getCookie() {
            if(this.$cookies.isKey("user"))     //controllo l'esistenza del cookie per evitare operazioni che genererebbero errori
                if(this.$cookies.get("user").isAdmin === 1)
                    this.isAdmin = true;
                else
                    this.isAdmin = false;
            this.isLogged();
        },

        logOut: function(){
            console.log("logout called ");
            $.get(this.logout_link, this.sessionid);
            this.$cookies.remove("user");
            this.isAdmin = false;
            this.getCookie();
            this.isLogged();
        },

        isLogged() {
            if(this.$cookies.isKey("user")){
                this.logged = true;
                this.sessionid = this.$cookies.get("JSESSIONID");
            }else
                this.logged = false;
        }
    },

    mounted() {
        this.getCookie();
    }
}) 
</script>


<style lang="scss">
    #app {
        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
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
        }
    }

    .form-group, .form-reg{
        margin-left: auto;
        margin-right: auto;
        width: 40%;
    }

    #inputPassword, #inputUsername{
        margin-top: 2%;
        margin-bottom: 2%;
    }

    .form-group.form-check{
        margin-top: auto;
    }
    h1, h3{
        margin-top: 3%;
        margin-bottom: 2%;
    }
    .errLog {
        color: red;
    }

    .successLog {
        color: limegreen;
    }

</style>
