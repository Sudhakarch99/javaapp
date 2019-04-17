var Datastore = require('nedb')
    , db = new Datastore({ filename: 'c:/workspace/digitalId/data/data.txt', autoload: true });


var local = require("./localsetup.js");
var getSerialNumbersForDevice = require("../getSerialNumbersForDevice/index.js");

  console.log("before registerDEvice");
  var serialNumber = "ABCDAzure";
  var deviceId = "device1";
  var inputDocument ;
// db.find({}, function (err, docs) {
//     console.log(docs);
// });
  db.find({"serialNumber" : serialNumber}, function (err, doc){
      inputDocument = doc;

      local.context.bindings.inputDocument = inputDocument;
      local.request.query.deviceId = deviceId;
      getSerialNumbersForDevice( local.context , local.request);
      console.log(local.context.res);
  });

//}).listen(8080);