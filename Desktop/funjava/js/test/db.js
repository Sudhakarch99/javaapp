
 var docs = [
//     { hello: 'world'
//         , n: 5
//         , today: new Date()
//         , nedbIsAwesome: true
//         , notthere: null
//         , notToBeSaved: undefined  // Will not be saved
//         , fruits: [ 'apple', 'orange', 'pear' ]
//         , infos: { name: 'nedb' }
//     };

{
        "id": "123",
        "serialNumber": "ABCDAzure",

        "devices": [
            {
                "deviceId": "device1",
                "pushToken": "pushToken1",
                "active": true
            }
        ],

    },
    {
        "id": "123",
        "serialNumber": "ABCDBhatia"

    },
    {
        "id": "123",
        "serialNumber": "ABCDAzure2"

    },
    {
        "id": "IDNEW1",
        "serialNumber": "SERIALNEW1",
        "passFields": [
            {
                "key": "nm",
                "label": "Name"
            }
        ]

    }
   ]
;

// docs.forEach( function (doc) {
// db.insert(doc, function (err, newDoc) {   // Callback is optional
//     // newDoc is the newly inserted document, including its _id
//     // newDoc has no key called notToBeSaved since its value was undefined
// });
// });
db.find({}, function (err, docs) {
    console.log(docs);
});