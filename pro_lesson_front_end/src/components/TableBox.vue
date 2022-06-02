<!--componente con il quale vado a mostrare in tabella la prenotazione inerente-->

<template>
    <div class="lesson">
        <p v-for="prenotazione in prenotazioni.slice(0,1)" :key="prenotazione">
            {{ prenotazione.corso }} e altre {{ prenotazioni.length - 1}} 
        </p> 
        <p v-if="this.prenotazioni.length === 0 && this.action === 'prenotazione'"> Nessuna lezione disponibile </p>
        <p v-if="this.prenotazioni.length === 0 && this.action === 'booked'"> Nessuna prenotazione </p>
    </div>
    

</template>

<script lang="js">
import $ from '../../node_modules/jquery'

export default({

    data() {
        return{
            link_lessongetter: "http://localhost:8080/TWEB_war_exploded/api/lessongetter",
            link_booking: "http://localhost:8080/TWEB_war_exploded/api/booking",
            prenotazioni: [],
            sessionid: null
        }
    },

    mounted(){ 
        this.sessionid = this.$cookies.get("JSESSIONID");
        this.requestBox();
    },

    methods:{
        requestBox: function() {
            if(this.action === "prenotazione") {
                //richiama funzione post per richiedere le prenotazioni disponibili
                this.avaiableLesson();
            }else if(this.action === "booked") {
                //richiama funzione get per richiedere le prenotazioni effettuate
                this.bookedLesson();
            }
            
        },

        avaiableLesson: function() {
            var requestData = {
                JSESSIONID: this.sessionid,
                ora: this.ora,
                giorno: this.giorno
            }

            $.post(this.link_lessongetter, requestData, (data) => {
                
                this.prenotazioni = data;
                
            });
        },

        bookedLesson: function() {
            var requestData = {
                action: this.action,
                ora: this.ora,
                giorno: this.giorno,
                JSESSIONID: this.sessionid
            }

            $.get(this.link_booking, requestData, (data) => {
                this.prenotazioni = data; 
            });
        }

    },

    props:{
        giorno: String,
        ora: Number,
        action: String
    }

})
</script>
