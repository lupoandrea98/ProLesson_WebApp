<template>
    
    <div v-if="this.prenotazioni.length > 0" class="container">
        <div class="row">
            <div class="card" style="width: 100%;">
                <ul class="list-group">
                    <li v-for="prenotazione in prenotazioni" :key="prenotazione" class="list-group-item">
                            {{ prenotazione.corso }}: {{ prenotazione.docente }} ({{ prenotazione.stato }})
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <h3 v-if="this.prenotazioni.length === 0"> Nessuna lezione prenotata</h3>


</template>

<script>
import $ from '../../node_modules/jquery'

export default({

    data() {
        return{
            sessionid: null,
            link:"http://localhost:8080/TWEB_war_exploded/api/adminpanel",
            prenotazioni:[],
            logged: false
        }
    },

    mounted(){
        this.isLogged();
        this.requestPren();
    },

    methods:{
        requestPren: function() {
            this.sessionid = this.$cookies.get("JSESSIONID");
            var username = this.$cookies.get("userPrenotation");

            var us = {
                JSESSIONID: this.sessionid,
                nome_utente: username
            }

            $.get(this.link, us, (data) =>{
                if(data[0]){
                    this.prenotazioni = data[1];
                }else
                    alert("L'utente non ha effettuato alcuna prenotazione");
            });

        },
        isLogged() {
            if(this.$cookies.isKey("user")){
                this.logged = true;
                this.sessionid = this.$cookies.get("JSESSIONID");
            }else
                this.logged = false;
        }
    }
})
</script>

<style scoped>
.container{
    margin-top: 5%;
    margin-bottom: 5%;
}
.col-md-0{
    margin-top: 3%;
}
h3{
    color: red;
}
</style>