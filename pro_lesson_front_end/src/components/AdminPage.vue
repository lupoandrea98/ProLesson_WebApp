<template>
<blockquote class="blockquote text-center">
    <h3> Admin Control Panel </h3>
</blockquote>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="card" style="width: 100%;">
                <h5 class="card-title"> Inserisci Utente </h5>
                    <form action="sigin" method="POST">
                        <div class="form-group" id="this">
                            <span v-if="seen_user_success" class="successLog"> Nuovo utente registrato correttamente </span>
                            <span v-if="seen_pw" class="errLog"> Le password inserite sono diverse </span>
                            <span v-if="seen_user" class="errLog"> Nome utente già esistente </span>
                            <input v-model="username" class="form-control" id="inputUsername" aria-describedby="emailHelp" placeholder="Username">
                            <input v-model="userpw" type="password" class="form-control" id="inputUserPw" placeholder="Password">
                            <input v-model="conf_pw" type="password" class="form-control" id="controlUserPw" placeholder="Password">
                            <div class="form-group form-check">
                                <input type="checkbox" class="form-check-input" id="exampleCheck1" v-model="selectAdmin">
                                <label class="form-check-label" for="selectAdmin" value=1> Admin </label>
                            </div>
                    
                            <button type="button" class="btn btn-primary btn-lg btn-block" id="adminButton1" v-on:click="insertUser" >Inserisci utente </button>
                        </div>
                        
                    </form>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card" style="width: 100%;">
                <h5 class="card-title"> Inserisci Docente </h5>
                    <form action="sigin" method="POST">
                        <div class="form-group" id="this">
                            <span v-if="seen_doc_success" class="successLog"> Nuovo docente aggiunto correttamente </span>
                            <span v-if="seen_doc" class="errLog"> Docente già esistente </span>
                            <input v-model="docname" class="form-control" id="inputDocname" placeholder="Nome">
                            <input v-model="docsname" class="form-control" id="inputDocSname" placeholder="Cognome">
                            <button type="button" class="btn btn-primary btn-lg btn-block" id="adminButton2" v-on:click="insertDoc"> Inerisci Docente </button>
                        </div>
                        <br>
                    </form>
            </div>
        </div>
    </div>
</div>


</template>

<script>
import $ from '../../node_modules/jquery'

export default {
    name: 'SigIn',
    data() {
        return{
            username: "",
            userpw: "",
            conf_pw: "",
            selectAdmin: 0,
            docname:"",
            docsname:"",
            link_insdoc:"http://localhost:8080/TWEB_war_exploded/api/insdoc",
            link_insuser:"http://localhost:8080/TWEB_war_exploded/api/insuser",
            seen_user_success: false,
            seen_pw: false, 
            seen_user: false,
            seen_doc_success: false,
            seen_doc: false,

        }
    },

    methods: {
        insertDoc: function() {

            var newDoc = {
                docname: this.docname,
                docsname: this.docsname
            }

            $.post(this.link_insdoc, newDoc, (data) => {
                if(data)
                    this.seen_doc_success = true;
                else
                    this.seen_doc_success = false;
            })
        },

        insertUser: function(){
            var newUser = {
                username: this.username,
                password: this.userpw,
                selectAdmin: this.selectAdmin
            }
            if(this.userpw === this.conf_pw){
                this.seen_pw = false;
                console.log("password corrette");
                $.post(this.link_insuser, newUser, (data) => {
                    if(data) 
                        this.seen_user_success = true;
                    else
                        this.seen_user_success = false;
                });
            }else{
                this.seen_pw = true;
            }
        }
    }
}

</script>

<style scoped>
.card {
    padding-top: 5%;
}
#inputUserPw, #inputUsername,#controlUserPw, #inputDocname, #inputDocSname{
        margin-top: 2%;
        margin-bottom: 2%;
    }

#adminButton1{
    margin-bottom: 3%;
}
#adminButton2{
    margin-top: 10%;
}

</style>