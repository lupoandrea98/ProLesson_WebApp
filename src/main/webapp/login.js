var LogIn = new Vue({
    el:'#LogIn',
    data: {
        action:"login",
        account: "",
        password: "",
        link: "login",
        seen: false
    },
    //TODO: il front end del login va a troie.
    methods: {
        sendLog: function (event){
            var login_data = {
                account:this.account,
                password:this.password,
                action: this.action
            };
            $.post(this.link, login_data, function(data) {
                console.log("Valore ritornato= " + data);
                //posso controllare direttamente data senza passare da un'altra variabile?
                //NO mi conviene mantenere il valore all'interno di una variabile in modo permettere anche successivi utilizzi nella pagina
                if(data === true) {
                    LogIn.seen = false;
                    console.log("login effettuato");
                }else {
                    LogIn.seen = true;
                    console.log("errore login");
                }
            });
        }
    }
})
