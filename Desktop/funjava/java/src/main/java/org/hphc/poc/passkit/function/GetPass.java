package org.hphc.poc.passkit.function;

//import java.io.File;
import java.util.*;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.hphc.poc.passkit.function.pojo.Pass;
import org.hphc.poc.passkit.function.service.PasskitService;

/**
 * Azure Functions with HTTP Trigger.
 */
public class GetPass {
    /**
     This function delivers a passkit file. It retrieves an existing pass from DB by passSerialNumber and checks security.
     */
    @FunctionName("GetApplePassAsPassKit")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route="applepass/v1/passes/{passTypeIdentifier}/{passSerialNumber}"
            ) HttpRequestMessage<Optional<String>> request,
            @CosmosDBInput(name = "database",
                    databaseName = "hphc-poc-cosmo1",
                    collectionName = "cosmo-coll1",
                    connectionStringSetting = "hphc-poc-passkit-db_DOCUMENTDB",
                    sqlQuery = "select * from Items r where r.serialNumber= {passSerialNumber} and r.docType='applepass'"
                    )
                    Pass[] passes,
            @BindingName("passTypeIdentifier") String passTypeIdentifier,
            @BindingName("passSerialNumber") String passSerialNumber,
            final ExecutionContext context ) {

        String authorizationHeader = request.getHeaders().get("authorization");
        context.getLogger().info("Get applepassrequest:passSerialNumber:" + passSerialNumber+", passTypeIdentifier:"+passTypeIdentifier +", Authorization:"+authorizationHeader);

        /**
         * TODO : CHeck header of If-Modified-Since and  return HTTP status code 304 if the pass has not changed.
         */

        context.getLogger().info("Found " + passes.length +" passes for serial number :" + passSerialNumber);
        if (passes.length!=1)
        {
            context.getLogger().severe("Returning Response Not found for serial#:" + passSerialNumber);
            return request.createResponseBuilder(HttpStatus.NOT_FOUND).build();
        }
        Pass pEntity= passes[0];
        if (!isValidAuth(authorizationHeader,pEntity))
        {
            context.getLogger().severe("Returning Auth Error for passSerial:" + passSerialNumber);
            return request.createResponseBuilder(HttpStatus.UNAUTHORIZED).build();
        }
        if (!passTypeIdentifier.equalsIgnoreCase(PasskitService.PASS_TYPE_IDENTIFIER))
        {
            context.getLogger().severe("PassType not matching. Found:" + passTypeIdentifier + ", Expected:" +PasskitService.PASS_TYPE_IDENTIFIER );
            return request.createResponseBuilder(HttpStatus.NOT_FOUND).build();
        }
        byte[] op=null;
        try{
            op=PasskitService.getPassBytes(pEntity,context.getLogger());
        }
        catch(Exception e)
        {
            context.getLogger().severe("In exception" + e.getMessage());
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()).build();
        }
        return request.createResponseBuilder(HttpStatus.OK).header("Content-Type", "application/vnd.apple.pkpass").body(op).build();


        /*// Parse query parameter
        String query = request.getQueryParameters().get("name");
        String name = request.getBody().orElse(query);

        if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body(op).build();
            //return request.createResponseBuilder(HttpStatus.OK).body("Hello, " + name).build();
        }*/
    }

    private boolean isValidAuth(String authorization, Pass pEntity) {
            return (authorization!=null && (authorization.contains("ApplePass " + pEntity.getAuthenticationToken())));
    }

}
