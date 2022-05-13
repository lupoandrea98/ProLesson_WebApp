<template>
<blockquote class="blockquote text-center">
    <h3>Registrati</h3>
</blockquote>
<div class="container">

    <div class="col">
        <form action="sigin" method="POST">
            <div class="form-group" id="this">
                <span v-if="seen_success" class="successLog"> Nuovo utente registrato correttamente </span>
                <span v-if="seen_pw" class="errLog"> Le password inserite sono diverse </span>
                <span v-if="seen_user" class="errLog"> Nome utente già esistente </span>
                <input v-model="account" class="form-control" id="inputUsername" aria-describedby="emailHelp" placeholder="Username">
                <input v-model="password" type="password" class="form-control" id="inputPassword" placeholder="Password">
                <input v-model="conf_pw" type="password" class="form-control" id="controlPassword" placeholder="Password">
                <br>
                <button type="button" class="btn btn-primary btn-lg btn-block" v-on:click="sendSig" >Registrati</button>
            </div>
            <br>
        </form>
    </div>

</div>


</template>

<script>
import $ from '../../node_modules/jquery'

export default {
    name: 'SigIn',
    data() {
        return{
            account: "",
            password: "",
            conf_pw: "",
            link:"http://localhost:8080/TWEB_war_exploded/api/sigin",
            seen_user:false,
            seen_pw:false,
            seen_success: false
        }
    },
    methods :{
        sendSig: function (){
            this.seen_pw = false;
            this.seen_user = false;
            this.seen_success = false;

            var this_data = {
                account: this.account,
                password: this.password,
            };
            if(this.password === this.conf_pw){
                this.seen_pw = false;
                console.log("password corrette");
                $.post(this.link, this_data, (data) => {
                    var recieved = [];
                    recieved = data;
                    if(recieved[0] === true) {
                        console.log("utente esistente");
                        this.seen_user = true;
                    }
                    if(recieved[1] === true) {
                        console.log("utente registrato con successo")
                        this.seen_success = true;
                    }else {
                        console.log("utente non registrato correttamente");
                    }
                });
            }else{
                this.seen_pw = true;
            }
        }
    }
}
</script>
