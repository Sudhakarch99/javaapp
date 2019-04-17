
var local = require("./localsetup.js");
var registerDevice = require("../registerDevice/index.js");
var serialNumber = "ABCDAzure";
var deviceId = "device1";
var inputDocument ;
// db.find({}, function (err, docs) {
//     console.log(docs);
// });
local.db.find({"serialNumber" : serialNumber}, function (err, doc){
    inputDocument = doc;

    local.context.bindings.inputDocument = inputDocument;
    local.request.query.deviceId = deviceId;
  console.log("before registerDEvice");
  registerDevice( local.context , local.request);
  console.log(local.context.bindings.outputDocument);
});
//}).listen(8080);