<template>
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
          <th><TableBox :ora=15 giorno="Lun"></TableBox></th>
          <th><TableBox :ora=15 giorno="Mar"></TableBox></th>
          <th><TableBox :ora=15 giorno="Mer"></TableBox></th>
          <th><TableBox :ora=15 giorno="Gio"></TableBox></th>
          <th><TableBox :ora=15 giorno="Ven"></TableBox></th>
      </tr>
      <tr>
          <th scope="row" class="table-info">16-17</th>
          <th><TableBox :ora=16 giorno="Lun"></TableBox></th>
          <th><TableBox :ora=16 giorno="Mar"></TableBox></th>
          <th><TableBox :ora=16 giorno="Mer"></TableBox></th>
          <th><TableBox :ora=16 giorno="Gio"></TableBox></th>
          <th><TableBox :ora=16 giorno="Ven"></TableBox></th>
      </tr>
      <tr>
          <th scope="row" class="table-info">17-18</th>
          <th><TableBox :ora=17 giorno="Lun"></TableBox></th>
          <th><TableBox :ora=17 giorno="Mar"></TableBox></th>
          <th><TableBox :ora=17 giorno="Mer"></TableBox></th>
          <th><TableBox :ora=17 giorno="Gio"></TableBox></th>
          <th><TableBox :ora=17 giorno="Ven"></TableBox></th>
      </tr>
      <tr>
          <th scope="row" class="table-info">18-19</th>
          <th><TableBox :ora=18 giorno="Lun"></TableBox></th>
          <th><TableBox :ora=18 giorno="Mar"></TableBox></th>
          <th><TableBox :ora=18 giorno="Mer"></TableBox></th>
          <th><TableBox :ora=18 giorno="Gio"></TableBox></th>
          <th><TableBox :ora=18 giorno="Ven"></TableBox></th>
      </tr>
      </tbody>
  </table>

</template>

<script>
import TableBox from '@/components/TableBox.vue'

export default {
  name: 'HomePage',
  components: {
      TableBox
  }
 
}
</script>

