<template>

<blockquote class="blockquote text-center">
    <h3>LogIn</h3>
</blockquote>
<div class="container">
    <div class="col">
        <form action="login" method="POST">
            <div class="form-group">
                <label v-if="fail" class="errLog"> Utente o password errati </label>
                <label v-if="success" class="successLog"> LogIn effettuato con successo </label>
                <input v-model="account" class="form-control" id="inputUsername" aria-describedby="emailHelp" placeholder="Username">
                <input v-model="password" type="password" class="form-control" id="inputPassword" placeholder="Password">
                <button type="button" class="btn btn-primary btn-lg btn-block" v-on:click="sendLog">LogIn</button>
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Remember me</label>
            </div>
            <div class="form-reg" >
                <router-link href="sigin.html" title="Vai a schermata di registrazione" to="/sigin"> Non sei ancora registrato? Clicca qui!</router-link>
            </div>
        </form>
    </div>
</div>



</template>

<script>
import $ from '../../node_modules/jquery'

export default({
    name: 'LogIn',
    data() {
        return {
            account: "",
            password: "",
            link: "http://localhost:8080/TWEB_war_exploded/api/login",
            fail: false,
            success: false,
            biscotto: null
        }
    },

    methods: {
        sendLog: function (){
            var login_data = {
                account:this.account,
                password:this.password,
            };
          
            $.post(this.link, login_data, (data) => {
                if(data[0] === true) {
                    this.success = true;
                    this.fail = false;
                    this.$cookies.set("user", data[1]);
                    console.log("login effettuato ");
                }else {
                    this.fail = true;
                    this.success = false;
                    this.$cookies.set("user", "guest");
                    console.log("errore login");
                }
            });
        },

        getCookies() {
            this.biscotto = this.$cookies.get('user');
        }

    }
})
</script>

