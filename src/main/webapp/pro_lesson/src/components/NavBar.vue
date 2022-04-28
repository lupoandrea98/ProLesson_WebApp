<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">ProLesson</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto"></ul>
          <form class="form-inline my-2 my-lg-0">
            <a class="btn btn-outline-primary my-2 my-sm-0" type="submit" @click="changeLogSeen">LogIn</a>
          </form>
        </div>
    </nav>
</template>


<script>

export default({
    name: 'NavBar',
    methods: {
        changeLogSeen() {
            console.log("login seen ");
            this.$emit('attivaLogIn', true);
        }
    },

})
</script>
