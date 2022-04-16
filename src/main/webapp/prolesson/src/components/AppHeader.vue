<template>
  <header>
    <img alt="Vue logo" src="../assets/logo.png" height="50">
    <p>My Vue.js Application</p>
      <nav>
          <ul>
              <li><router-link to="/">Home</router-link></li>
              <li><router-link to="/about">About</router-link></li>
          </ul>
      </nav>
  </header>
</template>

<script>
export default {
  name: 'AppHeader.vue'
}
</script>

<style lang="scss">

</style>
