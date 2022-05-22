<!--componente con il quale vado a mostrare in tabella la prenotazione inerente-->

<template>
    <div class="lesson">
        <p v-for="prenotazione in prenotazioni.slice(0,1)" :key="prenotazione">
            {{ prenotazione.corso }} e altre {{ prenotazioni.length - 1}} 
        </p> 
        <p v-if="this.prenotazioni.length === 0"> Nessuna lezione disponibile </p>
    </div>
    

</template>

<script lang="js">
import $ from '../../node_modules/jquery'

export default({

    data() {
        return{
            link: "http://localhost:8080/TWEB_war_exploded/api/lessongetter",
            prenotazioni: []
        }
    },

    mounted(){ this.requestBox() },

    methods:{
        requestBox: function() {
            var requestData = {
                ora: this.ora,
                giorno: this.giorno
            }

            $.post(this.link, requestData, (data) => {
                
                this.prenotazioni = data;
                
            });
        }
    },

    props:{
        giorno: String,
        ora: Number
    }

})
</script>
