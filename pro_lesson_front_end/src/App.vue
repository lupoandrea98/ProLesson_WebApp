<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">ProLesson</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
              <li class="nav-item">
                <a v-if="isAdmin" class="nav-link" aria-current="page" href="#"> ControlPanel </a>
              </li>
          </ul>
          <form class="form-inline my-2 my-lg-0">
            <router-link class="btn btn-outline-primary my-2 my-sm-0" type="submit" to="/login"> LogIn/SigIn </router-link>
          </form>
        </div>
    </nav>
    <br>
    <router-view/> 
     
</template>

<script>
export default({

    data() {
        return { 
            login_link: "http://localhost:8080/TWEB_war_exploded/api/login",
        }
    },
    props:{
        isAdmin: Boolean
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

        &.router-link-exact-active {
            color: #42b983;
        }
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
