<template>
<blockquote class="blockquote text-center">
    <h3>LogIn</h3>
</blockquote>
<div class="container">
    <div class="col">
        <form>
            <div class="form-group">
                <span v-if="seen" class="errLog"> Utente o password errati </span>
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

<footer>
    <blockquote class="blockquote text-center">
        <p class="text-secondary"> ProLesson © 2021-2022 </p>
    </blockquote>
</footer>

</template>

<script>
import $ from '../../node_modules/jquery'

export default({
    name: 'LogIn',
    data() {
        return {
            action:"login",
            account: "",
            password: "",
            link: "login",
            seen: false
        }
    },

    methods: {
        sendLog: function (){
            var login_data = {
                account:this.account,
                password:this.password,
                action: this.action
            };
            $.post(this.link, login_data, function(data) {
                //posso controllare direttamente data senza passare da un'altra variabile?
                //NO mi conviene mantenere il valore all'interno di una variabile in modo permettere anche successivi utilizzi nella pagina
                if(data === true) {
                    this.seen = false;
                    console.log("login effettuato");
                }else {
                    this.seen = true;
                    console.log("errore login");
                }
            });
        }
    }
})
</script>
