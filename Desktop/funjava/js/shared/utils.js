

utils = {
    isAuthorized :  function (context, req, hasAuthToken) {

        var userPassDoc = context.bindings.inputDocument[0];
        context.log ("in isAuthorized , userpass:");
        var authHeader = req.headers['authorization'];
        context.log ("in isAuthorized , userpass, authHeader : ",authHeader);
        if (userPassDoc == undefined) {
            context.log ("no pass found, returning 404");
            context.res = {
                status: 404,
                body: "There is no record of pass in our system."
            };
        } else
        if( !hasAuthToken || authHeader && ! authHeader.includes("ApplePass "+userPassDoc.authorizationToken)){
            context.log ("authorized successfully");
            return true;
        }else {
            context.log ("not authorized ...");
            context.res = {
                status : 401,
                body : "Not authorized"
            };
            

        }
        return false;
    },
    findDeviceById :  function (userPassDoc , currentDeviceId){
        var currentDevice;
        if (userPassDoc.devices) {
            userPassDoc.devices.forEach(function (device){
                if(currentDeviceId === device.deviceId)
                {
                    currentDevice = device;
                }
            })
            ;
        }
        return currentDevice;
    }
}
module.exports = utils;
