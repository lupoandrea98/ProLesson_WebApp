<template>
<blockquote class="blockquote text-center">
    <h3> Admin Control Panel </h3>
</blockquote>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="card" style="width: 100%;">
                <h5 class="card-title"> Pannello Docente </h5>
                    <form action="sigin" method="POST">
                        <div class="form-group" id="this">
                            <span v-if="seen_doc_success" class="successLog"> Operazione avvenuta con successo </span>
                            <span v-if="seen_doc" class="errLog"> Errore </span>
                            <input v-model="nome_docente" class="form-control"  placeholder="Nome">
                            <input v-model="cognome_docente" class="form-control"  placeholder="Cognome">
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <button type="button" class="btn btn-success btn-lg btn-block" v-on:click="insertDoc">Inserisci</button>
                                <button type="button" class="btn btn-danger btn-lg btn-block" v-on:click="removeDoc">Rimuovi</button>
                            </div>
                        </div>
                        <br>
                    </form>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card" style="width: 100%;">
                <h5 class="card-title"> Pannello Corso </h5>
                <form action="sigin" method="GET">
                    <div class="form-group" id="this">
                        <span v-if="seen_cors_success" class="successLog"> Operazione avvenuta con successo </span>
                        <span v-if="seen_cors" class="errLog"> Errore </span>
                        <input v-model="nome_corso" class="form-control" placeholder="Nome del corso">
                        <div class="btn-group" role="group" aria-label="Basic example">
                            <button type="button" class="btn btn-success btn-lg btn-block" v-on:click="insertCorso">Inserisci</button>
                            <button type="button" class="btn btn-danger btn-lg btn-block" v-on:click="removeCorso">Rimuovi</button>
                        </div>
                    </div>
                    <br>
                </form>
            </div>
        </div>
        
        <div class="col-md-6">
            <div class="card" style="width: 100%;">
                <h5 class="card-title"> Rimuovi Utente </h5>
                    <form action="sigin" method="POST">
                        <div class="form-group" id="this">
                            <span v-if="seen_user_success" class="successLog"> Utente rimosso correttamente </span>
                            <span v-if="seen_user" class="errLog"> Errore rimozione utente </span>
                            <input v-model="username" class="form-control"  aria-describedby="emailHelp" placeholder="Username">

                            <button type="button" class="btn btn-danger btn-lg btn-block" v-on:click="removeUser" >Rimuovi utente </button>
                        </div>
                        
                    </form>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card" style="width: 100%;">
                <h5 class="card-title"> Pannello insegnamento </h5>
                <form action="sigin" method="GET">
                    <div class="form-group" id="this">
                        <span v-if="seen_insegna_success" class="successLog"> Insegnamento rimosso correttamente </span>
                        <span v-if="seen_insegna" class="errLog"> Errore rimozione insegnamento </span>
                        <input v-model="nome_corso_ins" class="form-control" placeholder="Nome del corso">
                        <input v-model="cognome_doc" class="form-control" placeholder="Cognome docente">
                        <div class="btn-group" role="group" aria-label="Basic example">
                            <button type="button" class="btn btn-success btn-lg btn-block" v-on:click="insertInsegna">Inserisci</button>
                            <button type="button" class="btn btn-danger btn-lg btn-block" v-on:click="removeInsegna">Rimuovi</button>
                        </div>
                    </div>
                    <br>
                </form>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card" style="width: 100%;">
                <h5 class="card-title"> Visualizza prenotazioni </h5>
                    <form action="sigin" method="POST">
                        <div class="form-group" id="this">
                            <input v-model="user" class="form-control"  aria-describedby="emailHelp" placeholder="Username">
                            <button type="button" class="btn btn-primary btn-lg btn-block" v-on:click="insertUser" > Visualizza </button>
                        </div>
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
            link:"http://localhost:8080/TWEB_war_exploded/api/adminpanel",
            username: "",
            user: "",
            nome_docente:"",
            cognome_docente:"",
            nome_corso: "",
            nome_corso_ins: "",
            cognome_doc: "",
            seen_user_success: false,
            seen_user: false,
            seen_doc_success: false,
            seen_doc: false,
            seen_cors: false,
            seen_cors_success: false,
            seen_insegna: false,
            seen_insegna_success: false,
            sessionid: null
        }
    },

    methods: {
        insertDoc: function() {
            this.sessionid = this.$cookies.get("JSESSIONID");
            var newDoc = {
                action: "insertDoc",
                docname: this.nome_docente,
                docsname: this.cognome_docente,
                JSESSIONID: this.sessionid
            }

            $.post(this.link, newDoc, (data) => {
                if(data)
                    this.seen_doc_success = true;
                else
                    this.seen_doc_success = false;
            })
        },

        removeDoc: function() {
            this.sessionid = this.$cookies.get("JSESSIONID");
            var Doc = {
                action: "removeDoc",
                nome_docente: this.nome_docente,
                cognome_docente: this.cognome_docente,
                JSESSIONID: this.sessionid
            }

            $.post(this.link, Doc, (data) => {
                if(data)
                    this.seen_doc_success = true;
                else
                    this.seen_doc_success = false;
            })
        },

        removeUser: function(){
            this.sessionid = this.$cookies.get("JSESSIONID");
            var User = {
                action: "removeUser",
                username: this.username,
                JSESSIONID: this.sessionid
            }
            
            $.post(this.link, User, (data) => {
                if(data) 
                    this.seen_user_success = true;
                else
                    this.seen_user_success = false;
            });
            
        },
        insertCorso: function(){
            this.sessionid = this.$cookies.get("JSESSIONID");
            var newCorso = {
                action: "insertCorso",
                title: this.nome_corso,
                JSESSIONID: this.sessionid
            }
            
            $.post(this.link, newCorso, (data) => {
                if(data) 
                    this.seen_cors_success = true;
                else
                    this.seen_cors_success = false;
            });
        },
        removeCorso: function(){
            this.sessionid = this.$cookies.get("JSESSIONID");
            var Corso = {
                action: "removeCorso",
                title: this.nome_corso,
                JSESSIONID: this.sessionid
            }
            
            $.post(this.link, Corso, (data) => {
                if(data) 
                    this.seen_cors_success = true;
                else
                    this.seen_cors_success = false;
            });
        },
        insertInsegna: function() {
            this.sessionid = this.$cookies.get("JSESSIONID");
            var newInsegna = {
                action: "insertInsegna",
                nome_corso: this.nome_corso_ins,
                cognome_docente: this.cognome_doc,
                JSESSIONID: this.sessionid
            }

            $.post(this.link, newInsegna, (data) => {
                if(data)
                    this.seen_insegna_success = true;
                else
                    this.seen_insegna_success = false;
            })
        },
        removeInsegna: function() {
            this.sessionid = this.$cookies.get("JSESSIONID");
            var Insegna = {
                action: "removeInsegna",
                name_corso: this.nome_corso_ins,
                cognome_docente: this.cognome_doc,
                JSESSIONID: this.sessionid
            }

            $.post(this.link, Insegna, (data) => {
                if(data)
                    this.seen_insegna_success = true;
                else
                    this.seen_insegna_success = false;
            })
        },
    }
}

</script>

<style scoped>
.card {
    padding-top: 5%;
    margin: 2%;
}

.form-control{ 
    margin-top: 3%;
    margin-bottom: 2%;
}

.btn{
    margin-top: 10%;
}

</style>