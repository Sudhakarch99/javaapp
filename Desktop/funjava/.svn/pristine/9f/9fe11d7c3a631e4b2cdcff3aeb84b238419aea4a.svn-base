module.exports = async function (context, req) {
    context.log ("inside logger");
    context.log ("log ",req.body.logs);
    context.bindings.outputDocument = {docType : "log", log : req.body.logs };
    context.res = {
        status : 200
    }
};