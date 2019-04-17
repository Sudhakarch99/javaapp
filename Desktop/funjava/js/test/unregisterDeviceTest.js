


var local = require("./localsetup.js");

var fs = require('fs');
var doc = JSON.parse(fs.readFileSync('C:/Workspace/digitalId/test/data/unregister_data.txt', 'utf8'));
var unregisterDevice = require("../unregisterDevice/index.js");

  console.log("before unregisterDEvice");
  var passSerialNumber = "SERIALNEW1";
  var deviceId = "test";
  var inputDocument ;

     inputDocument = doc;

      local.context.bindings.inputDocument = inputDocument;
      local.request.params.deviceId = deviceId;
        local.request.params.passSerialNumber = passSerialNumber;
console.log(local.context.bindings.inputDocument);
      unregisterDevice( local.context , local.request);
        console.log(local.context.res);
      console.log(local.context.bindings.outputDocument);
