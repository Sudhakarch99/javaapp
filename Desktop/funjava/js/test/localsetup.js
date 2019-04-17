module.exports.context = {
    bindings : {
        inputDocument : {
            authorizationToken :  "ApplePass TestToken",


    }
        },
    log : function (msg){
        console.log(msg);
    }
};
module.exports.request = {
    query :{
        name : "Anil"
    },
    params : {},
    headers : {
        authorization : "ApplePass TestToken"
    }
};




