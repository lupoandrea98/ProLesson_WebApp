<template>
    <h3> Lezioni prenotate </h3>
    <table class="table table-bordered">
      <caption> Lezioni Prenotate </caption>
      
      <thead>
      <tr>
          <th scope="col" >Days </th>
          <th scope="col" class="table-info">Lun</th>
          <th scope="col" class="table-info">Mar</th>
          <th scope="col" class="table-info">Mer</th>
          <th scope="col" class="table-info">Gio</th>
          <th scope="col" class="table-info">Ven</th>
      </tr>
      </thead>
      <tbody>
      <tr>
          <th scope="row" class="table-info">15-16</th>
          <th><router-link to="/lessonlist/Lun/15"><TableBox :ora=15 giorno="Lun" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Mar/15"><TableBox :ora=15 giorno="Mar" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Mer/15"><TableBox :ora=15 giorno="Mer" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Gio/15"><TableBox :ora=15 giorno="Gio" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Ven/15"><TableBox :ora=15 giorno="Ven" action="booked" class="tableBox"></TableBox></router-link></th>
      </tr>
      <tr>
          <th scope="row" class="table-info">16-17</th>
          <th><router-link to="/lessonlist/Lun/16"><TableBox :ora=16 giorno="Lun" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Mar/16"><TableBox :ora=16 giorno="Mar" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Mer/16"><TableBox :ora=16 giorno="Mer" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Gio/16"><TableBox :ora=16 giorno="Gio" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Ven/16"><TableBox :ora=16 giorno="Ven" action="booked" class="tableBox"></TableBox></router-link></th>
      </tr>
      <tr>
          <th scope="row" class="table-info">17-18</th>
          <th><router-link to="/lessonlist/Lun/17"><TableBox :ora=17 giorno="Lun" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Mar/17"><TableBox :ora=17 giorno="Mar" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Mer/17"><TableBox :ora=17 giorno="Mer" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Gio/17"><TableBox :ora=17 giorno="Gio" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Ven/17"><TableBox :ora=17 giorno="Ven" action="booked" class="tableBox"></TableBox></router-link></th>
      </tr>
      <tr>
          <th scope="row" class="table-info">18-19</th>
          <th><router-link to="/lessonlist/Lun/18"><TableBox :ora=18 giorno="Lun" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Mar/18"><TableBox :ora=18 giorno="Mar" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Mer/18"><TableBox :ora=18 giorno="Mer" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Gio/18"><TableBox :ora=18 giorno="Gio" action="booked" class="tableBox"></TableBox></router-link></th>
          <th><router-link to="/lessonlist/Ven/18"><TableBox :ora=18 giorno="Ven" action="booked" class="tableBox"></TableBox></router-link></th>
      </tr>
      </tbody>
  </table>
</template>

<script>
import TableBox from '@/components/TableBox.vue'

export default {
    name: 'PrenotationList',
    data() {
        return {
            login_link: "http://localhost:8080/TWEB_war_exploded/api/login",
            isAdmin: false
        }
    },

    methods: {
        getSessionInfo: function(){
            this.$cookies.get("userAdmin");
            this.$cookies.get("userID");
            this.$cookies.get("username");
        }
    },

    mounted(){
        this.getSessionInfo();
    },

    components: {
        TableBox,
    }

 
}
</script>

<style scoped>

table {
    margin-top: auto;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: auto;
    text-align: center;
}

.tableBox{
    border: 2px solid ;
    border-radius: 18px;
    margin: 5px;
    padding-top: 11px;
    padding-left: 5px;
    padding-right: 5px;
    background: #BEE5EB
}

.tableBox:hover {
  opacity: 0.5;
}
</style>
