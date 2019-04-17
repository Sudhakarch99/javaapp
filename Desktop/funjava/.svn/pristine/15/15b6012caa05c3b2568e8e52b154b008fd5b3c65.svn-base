utils = require("../shared/utils.js");

async function unregisterDevice(context, req) {

    context.log ("received unregister request for sn :"+req.params.passSerialNumber +" and deviceId " +req.params.deviceId);
    
    var userPassDoc = context.bindings.inputDocument[0];


    if (utils.isAuthorized(context, req, true ) ) {
        
        var currentDevice = utils.findDeviceById (userPassDoc, req.params.deviceId) ;
        
        if (currentDevice) {
            context.log ("device with id found...");
            currentDevice.active = false;
            context.log ("device deactivated...");
            currentDevice.modified = new Date().getTime();
            context.res = {
                status: 200
            };
            context.bindings.outputDocument = userPassDoc;
        } else {
            context.log ("no device with id found...");
            context.res = {
                status: 404,
                body: "no pass found for the device"
            };
        }
    }
};

module.exports = unregisterDevice;