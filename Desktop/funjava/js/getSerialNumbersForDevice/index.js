utils = require("../shared/utils.js");
 
 async function getSerialNumbersForDevice (context, req) {
    context.log ("in getSerialNumbersForDevice");

    context.log ("finding sns for device id ", req.params.deviceId );
    context.log ("for updated since tag ", req.query.passesUpdatedSince );
    
    var userPasses = context.bindings.inputDocument;
    context.log ("found ", userPasses.length , " passes for the devive" );

    if (userPasses && userPasses.length > 0){

    
        var passesUpdatedSince ;
        if (req.query.passesUpdatedSince){
            passesUpdatedSince = parseInt(req.query.passesUpdatedSince);
        }
        var serialNumbersToReturn = new Array();
        
        userPasses.forEach(function (userPass){
            if ( !passesUpdatedSince || userPass.modified > passesUpdatedSince){
                serialNumbersToReturn.push(userPass.serialNumber);
            }
        });

        context.res = {
            status : 200,
            body : {
                lastUpdated : new Date().getTime(),
                serialNumber : serialNumbersToReturn
            }
        }
        
    }else {
        context.res = {
            status : 204,
            body : "no passes found"
        }
    }


};

module.exports = getSerialNumbersForDevice;