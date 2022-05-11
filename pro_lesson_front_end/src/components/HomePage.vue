<template>

  <table class="table table-bordered">
      <caption> Lezioni disponibili </caption>
      
      <thead>
      <tr>
          <th scope="col" >Days</th>
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
          <th><TableBox :ora=15 giorno="Lun" class="tableBox"></TableBox></th>
          <th><TableBox :ora=15 giorno="Mar" class="tableBox"></TableBox></th>
          <th><TableBox :ora=15 giorno="Mer" class="tableBox"></TableBox></th>
          <th><TableBox :ora=15 giorno="Gio" class="tableBox"></TableBox></th>
          <th><TableBox :ora=15 giorno="Ven" class="tableBox"></TableBox></th>
      </tr>
      <tr>
          <th scope="row" class="table-info">16-17</th>
          <th><TableBox :ora=16 giorno="Lun" class="tableBox"></TableBox></th>
          <th><TableBox :ora=16 giorno="Mar" class="tableBox"></TableBox></th>
          <th><TableBox :ora=16 giorno="Mer" class="tableBox"></TableBox></th>
          <th><TableBox :ora=16 giorno="Gio" class="tableBox"></TableBox></th>
          <th><TableBox :ora=16 giorno="Ven" class="tableBox"></TableBox></th>
      </tr>
      <tr>
          <th scope="row" class="table-info">17-18</th>
          <th><TableBox :ora=17 giorno="Lun" class="tableBox"></TableBox></th>
          <th><TableBox :ora=17 giorno="Mar" class="tableBox"></TableBox></th>
          <th><TableBox :ora=17 giorno="Mer" class="tableBox"></TableBox></th>
          <th><TableBox :ora=17 giorno="Gio" class="tableBox"></TableBox></th>
          <th><TableBox :ora=17 giorno="Ven" class="tableBox"></TableBox></th>
      </tr>
      <tr>
          <th scope="row" class="table-info">18-19</th>
          <th><TableBox :ora=18 giorno="Lun" class="tableBox"></TableBox></th>
          <th><TableBox :ora=18 giorno="Mar" class="tableBox"></TableBox></th>
          <th><TableBox :ora=18 giorno="Mer" class="tableBox"></TableBox></th>
          <th><TableBox :ora=18 giorno="Gio" class="tableBox"></TableBox></th>
          <th><TableBox :ora=18 giorno="Ven" class="tableBox"></TableBox></th>
      </tr>
      </tbody>
  </table>
</template>

<script>
import TableBox from '@/components/TableBox.vue'
import $ from '../../node_modules/jquery'

export default {
    name: 'HomePage',
    data() {
        return {
            login_link: "http://localhost:8080/TWEB_war_exploded/api/login",
            isAdmin: false
        }
    },

    methods: {
        getSessionInfo: function(){
            $.get(this.login_link, (data) => {
                if(data.isAdmin === 1)
                    this.isAdmin = true;
            });
            this.$emit('isAdminEvent', this.isAdmin)
            console.log(this.isAdmin);
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
    padding-top: 10px;
    padding-bottom: 5px;
    padding-left: 5px;
    padding-right: 5px;
    background: #BEE5EB
}

.tableBox:hover {
  opacity: 0.5;
}
</style>
