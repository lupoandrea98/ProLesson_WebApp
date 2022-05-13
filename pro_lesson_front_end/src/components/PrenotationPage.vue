<template>
<blockquote class="blockquote text-center">
    <h3> Admin Control Panel </h3>
</blockquote>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="card" style="width: 100%;">
                <h5 class="card-title"> Inserisci Docente </h5>
                    <form action="sigin" method="POST">
                        <div class="form-group" id="this">
                            <span v-if="seen_user_success" class="successLog"> Nuovo utente registrato correttamente </span>
                            <span v-if="seen_pw" class="errLog"> Le password inserite sono diverse </span>
                            <span v-if="seen_user" class="errLog"> Nome utente già esistente </span>
                            <input v-model="username" class="form-control" id="inputUsername" aria-describedby="emailHelp" placeholder="Username">
                            <input v-model="userpw" type="password" class="form-control" id="inputUserPw" placeholder="Password">
                            <input v-model="conf_pw" type="password" class="form-control" id="controlUserPw" placeholder="Password">
                            <br>
                            <button type="button" class="btn btn-primary btn-lg btn-block" v-on:click="sendSig" >Registrati</button>
                        </div>
                        <br>
                    </form>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card" style="width: 100%;">
                <h5 class="card-title"> Inserisci Utente </h5>
                    <form action="sigin" method="POST">
                        <div class="form-group" id="this">
                            <span v-if="seen_doc_success" class="successLog"> Nuovo utente registrato correttamente </span>
                            <span v-if="seen_pw" class="errLog"> Le password inserite sono diverse </span>
                            <span v-if="seen_doc" class="errLog"> Docente già esistente </span>
                            <input v-model="docname" class="form-control" id="inputDocname" aria-describedby="emailHelp" placeholder="Username">
                            <input v-model="docpw" type="password" class="form-control" id="inputDocPw" placeholder="Password">
                            <input v-model="conf_pw" type="password" class="form-control" id="controlDocPw" placeholder="Password">
                            <br>
                            <button type="button" class="btn btn-primary btn-lg btn-block" v-on:click="sendSig" >Registrati</button>
                        </div>
                        <br>
                    </form>
            </div>
        </div>
    </div>
</div>


</template>

<script>

export default {
    name: 'SigIn',
    data() {
        return{
            username: "",
            userpw: "",
            conf_pw: "",
            docname:"",
            docpw:"",
            link:"http://localhost:8080/TWEB_war_exploded/api/insdoc",
            seen_user:false,
            seen_pw:false,
            seen_success: false
        }
    }
}

</script>

<style scoped>
.card {
    padding-top: 5%;
}
</style>