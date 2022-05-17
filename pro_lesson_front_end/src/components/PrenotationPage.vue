<template>
    <h1>mocc a soreta</h1>
    <ul>
        <li v-for="prenotazione in this.pre" :key="prenotazione">
            puppa {{ prenotazione.corso }}
        </li>
    </ul>
</template>

<script>
//ti devo passare una singola props con la lista delle prenotazioni provenienti dal tablebox
export default({
    props:{
        pre: Array
    }
})
</script>
