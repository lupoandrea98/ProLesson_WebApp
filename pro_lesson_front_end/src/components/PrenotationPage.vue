<template>
    <h1 v-if=" this.giorno === 'Lun' "> Lezioni disponibili lunedì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Mar' "> Lezioni disponibili martedì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Mer' "> Lezioni disponibili mercoledì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Gio' "> Lezioni disponibili giovedì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Ven' "> Lezioni disponibili venerdì alle {{ this.ora }} </h1>

    <label v-if="fail" class="errLog"> Prenotazione non andata a buon fine </label>
    <label v-if="success" class="successLog"> Prenotazione avvenuta con successo </label>
    <div v-if="this.prenotazioni.length > 0" class="container">
        <div class="row">
            <div class="card" style="width: 100%;">
                <ul class="list-group">
                    <li v-for="prenotazione in prenotazioni" :key="prenotazione" class="list-group-item">
                        <input v-if="prenotazione.avaiable === 0 " class="form-check-input me-1" type="checkbox" v-model="booking" :value="prenotazione"> 
                            {{ prenotazione.corso }}: {{ prenotazione.docente}}
                    </li>
                </ul>
            </div>
            <div class="col-md-0">
                <button v-if="this.logged" class="btn btn-primary my-2 my-sm-0" type="submit" v-on:click="commitBooking"> Prenota </button> 
            </div>
        </div>
    </div>

    <h3 v-if="this.prenotazioni.length === 0"> Nessuna lezione disponibile</h3>


</template>

<script>
import $ from '../../node_modules/jquery'

export default({

    data() {
        return{
            prenotazioni:[],
            booking: [],
            link_lez: "http://localhost:8080/TWEB_war_exploded/api/lessongetter",
            link_booking: "http://localhost:8080/TWEB_war_exploded/api/booking",
            logged: false,
            success: false,
            fail: false,
            sessionid: null
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
                JSESSIONID: this.sessionid
            }
            $.post(this.link_lez, requestData, (data) => {
                
                this.prenotazioni = data;
            
            });
        },
        isLogged() {
            if(this.$cookies.isKey("user")){
                this.logged = true;
                this.sessionid = this.$cookies.get("JSESSIONID");
            }else
                this.logged = false;
        },
        commitBooking: function() {
            this.sessionid = this.$cookies.get("JSESSIONID");
            var commitData = {
                //spedisco l'array con tutte le prenotazioni selezionate contenenti tutti i dati che mi interessano
                action: "booking",
                booking: JSON.stringify(this.booking),
                prenotazioni: JSON.stringify(this.prenotazioni),
                JSESSIONID: this.sessionid
            };
            console.log(this.sessionid);
            $.post(this.link_booking, commitData, (data) => {
                
                if(data){
                    this.success = true;
                    this.fail = false;
                }else{
                    this.success = false;
                    this.fail = true;
                }

            }); 
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