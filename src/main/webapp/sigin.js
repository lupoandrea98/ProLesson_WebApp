var SigIn = new Vue({
    el:'#SigIn',
    data:{
        action: "sigin",
        account: "",
        password: "",
        conf_pw: "",
        link:"login",
        seen_user:false,
        seen_pw:false,
        seen_success: false
    },
    methods :{
        sendSig: function (event){
            SigIn.seen_pw = false;
            SigIn.seen_user = false;
            SigIn.seen_success = false;

            var sigin_data = {
                account: this.account,
                password: this.password,
                action: this.action
            };
            if(SigIn.password === SigIn.conf_pw){
                SigIn.seen_pw = false;
                console.log("password corrette");
                $.post(this.link, sigin_data, function (data){
                    var recieved = [];
                    recieved = data;
                    if(recieved[0] === true) {
                        console.log("utente esistente");
                        SigIn.seen_user = true;
                    }
                    if(recieved[1] === true) {
                        console.log("utente registrato con successo")
                        SigIn.seen_success = true;
                    }else {
                        console.log("utente non registrato correttamente");
                    }
                });
            }else{
                SigIn.seen_pw = true;
            }

        }
    }
})