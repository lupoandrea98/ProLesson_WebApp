<template>
    <h1 v-if=" this.giorno === 'Lun' "> Lezioni disponibili lunedì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Mar' "> Lezioni disponibili martedì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Mer' "> Lezioni disponibili mercoledì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Gio' "> Lezioni disponibili giovedì alle {{ this.ora }} </h1>
    <h1 v-if=" this.giorno === 'Ven' "> Lezioni disponibili venerdì alle {{ this.ora }} </h1>

    <div v-if="this.prenotazioni.length > 0" class="container">
        <div class="row">
            <div class="card" style="width: 100%;">
                <ul class="list-group">
                    <li v-for="prenotazione in prenotazioni" :key="prenotazione" class="list-group-item">
                        <input class="form-check-input me-1" type="checkbox" value="" aria-label="..."> 
                            {{ prenotazione.corso }} 
                    </li>
                </ul>
            </div>
            <div class="col-md-0">
                <button v-if="this.logged" class="btn btn-primary my-2 my-sm-0" type="submit"> Prenota </button> 
            </div>
        </div>
    </div>

    <h3 v-if="this.prenotazioni.length === 0"> Nessuna lezione disponibile</h3>


</template>

<script>
import $ from '../../node_modules/jquery'
//ti devo passare una singola props con la lista delle prenotazioni provenienti dal tablebox
export default({

    data() {
        return{
            prenotazioni:[],
            link: "http://localhost:8080/TWEB_war_exploded/api/tablebox",
            logged: false
        }
    },

    mounted(){
        this.requestPren();
        this.isLogged();
    },

    methods:{
        requestPren: function() {
            var requestData = {
                ora: this.ora,
                giorno: this.giorno
            }
      
            $.post(this.link, requestData, (data) => {
                
                this.prenotazioni = data;
            
            });
        },
        isLogged() {
            if(this.$cookies.isKey("user"))
                this.logged = true;
            else
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