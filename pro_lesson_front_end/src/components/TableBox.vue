<!--componente con il quale vado a mostrare in tabella la prenotazione inerente-->

<template>
    <!--vado ad usare un v-for per stampare tutti i risultati nella casella, facendo un bind con i dati che ricevo dal server -->
    <div>
        <ul id="example-1">
            <li v-for="prenotazione in prenotazioni" :key="prenotazione">
                {{ prenotazione }}
            </li>
        </ul>
    </div>

</template>

<script lang="js">
import $ from '../../node_modules/jquery'

export default({

    data() {
        return{
            link: "http://localhost:8080/TWEB_war_exploded/api/tablebox",
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
            console.log("im the post");
            $.post(this.link, requestData, (data) => {
                console.log("post response");
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