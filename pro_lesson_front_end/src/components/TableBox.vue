<!--componente con il quale vado a mostrare in tabella la prenotazione inerente-->

<template>
    <div>
        <ul id="example-1">
            <li v-for="prenotazione in prenotazioni" :key="prenotazione">
                {{ prenotazione.corso }} - {{ prenotazione.docente}}
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

            $.post(this.link, requestData, (data) => {
                //this.prenotazioni.add(JSON.parse(data).text)
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