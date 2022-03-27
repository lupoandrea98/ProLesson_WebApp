<script>
var app = new Vue({
    el:'#app',
    data: {
        account: "",
        password: "",
        check: "",
        link: '/login'
    },
    mounted() {
        this.sendLog();
    },
    methods: {
        sendLog: function () {
            //La funzione di callback sarà eseguita a post conclusa
            $.post(this.link, {account:this.data.account, password:this.data.password, check:this.data.check},function(data) {
                if(data.check === "OK") {
                    alert("Utente loggato con successo");
                }else{
                    alert("Errore nel login");
                }
            });
        }
    }
})
</script>
