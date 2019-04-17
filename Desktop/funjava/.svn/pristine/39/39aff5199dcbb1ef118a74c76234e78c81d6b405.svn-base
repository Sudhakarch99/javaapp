var utils = require( "../shared/utils.js");



 async function registerDevice (context, req) {

    context.log ("registering device with sn :",req.params.passSerialNumber);
    
    var userPassDoc = context.bindings.inputDocument[0];

    if (  utils.isAuthorized ( context, req, false ) ){
        context.log ("request authorized successfully...");
        var currentDevice = utils.findDeviceById (userPassDoc, req.params.deviceId) ;
        var pushToken = req.body.pushToken;
        if (currentDevice){
            context.log ("device already in system...");
            if (currentDevice.active) {
                context.log ("device already active...doing nothing");
                context.res = {
                    status: 200, /* Defaults to 200 */
                    body: "device already active, doing nothing"
                }
                return;
            }else {
                currentDevice.active = true;
                context.log ("activating device...");
                
                currentDevice.pushToken = pushToken; 
                //TODO need to confirm if we need to store this.
                currentDevice.modified = new Date().getTime();
            }
        }else {
            context.log ("adding device and generating pushToken");
              
            context.log ("generated new pushToken for this device ", pushToken);
            if (userPassDoc.devices == undefined){
                devices = [];
            }else {
                devices = userPassDoc.devices;
            }
            devices.push({"deviceId": req.params.deviceId, "pushToken": pushToken, "active": true, "modified" : new Date().getTime()});
            userPassDoc.devices = devices;
          
        }
        context.log("adding device...");
        context.bindings.outputDocument = userPassDoc;
    }
    else{
        context.log ("request not-authorized ...");
    }
};

module.exports = registerDevice;