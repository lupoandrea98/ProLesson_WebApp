<template>
    <h1>
          {{ cookie }}
      </h1>
  <table class="table">
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
  <button @click="getCookie"> getCookie </button>

</template>

<script>
import TableBox from '@/components/TableBox.vue'

export default {
    name: 'HomePage',
    data() {
        return {
            cookie: null
        }
    },

    components: {
        TableBox
    },

    methods: {
        getCookie() {
            const mamt = this.$cookies.get('user');
            console.log(mamt);
        }
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
    margin-top: 5px;
    margin-bottom: 5px;
    margin-right: 5px;
    margin-left: 5px;
    padding-top: 15px;
    background: #BEE5EB
}

.tableBox:hover {
  opacity: 0.5;
}
</style>
