<template>
    <h1 v-if=" this.giorno === 'Lun' "> Lezioni prenotate lunedì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Mar' "> Lezioni prenotate martedì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Mer' "> Lezioni prenotate mercoledì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Gio' "> Lezioni prenotate giovedì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Ven' "> Lezioni prenotate venerdì alle {{ this.ora }} </h1>

    <div v-if="this.prenotazioni.length > 0" class="container">
        <p v-if="this.dismissed" class="successLog"> Prenotazione disdetta con successo </p>
        <div class="row">
            <div class="card" style="width: 100%;">
                <ul class="list-group">
                    <li v-for="prenotazione in prenotazioni" :key="prenotazione" class="list-group-item">
                        <input v-if="prenotazione.avaiable === 0 " class="form-check-input me-1" type="checkbox" v-model="selected" :value="prenotazione"> 
                            {{ prenotazione.corso }}: {{ prenotazione.docente}} ({{ prenotazione.state }})
                    </li>
                </ul>
            </div>
            <div class="col-md-0" v-if=this.showButtons>
                <button class="btn btn-danger my-2 my-sm-0" type="submit" v-on:click="commitDismiss"> Disdici </button> 
            </div>
            <div class="col-md-0" v-if=this.showButtons>
                <button class="btn btn-success my-2 my-sm-0" type="submit" v-on:click="commitDone"> Effettuata </button> 
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
            selected: [],
            link_lez: "http://localhost:8080/TWEB_war_exploded/api/booking",
            logged: false,
            dismissed: false,
            showButtons: false
        }
    },

    mounted(){
        this.requestPren();
        this.isLogged();
    },

    methods:{
        showButtonCancDism: function() {
            for(let i=0; i<this.prenotazioni.length; i++)
                if(this.prenotazioni[i].state == "attiva")
                    this.showButtons = true;
        },
        requestPren: function() {
            this.sessionid = this.$cookies.get("JSESSIONID");
            var requestData = {
                ora: this.ora,
                giorno: this.giorno,
                JSESSIONID: this.sessionid,
                action: "booked"
            }
            $.get(this.link_lez, requestData, (data) => {
                
                this.prenotazioni = data;
                this.showButtonCancDism();
            
            });
        },
        commitDismiss: function() {
            this.sessionid = this.$cookies.get("JSESSIONID");
            var dismissData = {
                JSESSIONID: this.sessionid,
                booking: JSON.stringify(this.selected),
                action: "dismiss"
            }

            $.post(this.link_lez, dismissData, (data) => {
                if(data)
                    this.dismissed = true;
                else
                    this.dismissed = false;
            })
        },
        commitDone: function() {
            this.sessionid = this.$cookies.get("JSESSIONID");
            var doneData = {
                JSESSIONID: this.sessionid,
                booking: JSON.stringify(this.selected),
                action: "done"
            }

            $.post(this.link_lez, doneData, (data) => {
                if(data)
                    this.dismissed = true;
                else
                    this.dismissed = false;
            })
        },
        isLogged() {
            if(this.$cookies.isKey("user")){
                this.logged = true;
                this.sessionid = this.$cookies.get("JSESSIONID");
            }else
                this.logged = false;
        },
        
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