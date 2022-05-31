<template>
    <h1 v-if=" this.giorno === 'Lun' "> Lezioni prenotate lunedì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Mar' "> Lezioni prenotate martedì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Mer' "> Lezioni prenotate mercoledì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Gio' "> Lezioni prenotate giovedì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Ven' "> Lezioni prenotate venerdì alle {{ this.ora }} </h1>

    <div v-if="this.prenotazioni.length > 0" class="container">
        <div class="row">
            <div class="card" style="width: 100%;">
                <ul class="list-group">
                    <li v-for="prenotazione in prenotazioni" :key="prenotazione" class="list-group-item">
                            {{ prenotazione.corso }}: {{ prenotazione.docente}}
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
            prenotazioni:[],
            link_lez: "http://localhost:8080/TWEB_war_exploded/api/booking",
            logged: false
        }
    },

    mounted(){
        this.requestPren();
        this.isLogged();
    },

    methods:{
        requestPren: function() {
            this.sessionid = this.$cookies.get("JSESSIONID");
            var requestData = {
                ora: this.ora,
                giorno: this.giorno,
                JSESSIONID: this.sessionid,
                action: "booked"
            }
            console.log(this.sessionid);
            $.get(this.link_lez, requestData, (data) => {
                
                this.prenotazioni = data;
            
            });
        },
        isLogged() {
            if(this.$cookies.isKey("user")){
                this.logged = true;
                this.sessionid = this.$cookies.get("JSESSIONID");
            }else
                this.logged = false;
        }
    },

    props:{
        giorno: { type: String, required: true},
        ora: { type: String, required: true}
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