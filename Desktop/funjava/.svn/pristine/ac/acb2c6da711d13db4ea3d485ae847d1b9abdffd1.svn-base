package org.hphc.poc.passkit.function;

//import java.io.File;
import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.hphc.poc.passkit.function.pojo.Pass;


/**
 * Azure Functions with HTTP Trigger.
 */
public class Dummy {
    /**
     This function delivers a passkit file. 
     It retrieves an existing pass from DB by passSerialNumber and checks security.
     */
    @FunctionName("Dummy")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS
                    //route="dummy/v2"
            ) HttpRequestMessage<String> request,
           // @BindingName("{req.serialNumber}" ) String query1,
            final ExecutionContext context ) {

        String authorizationHeader = request.getHeaders().get("authorization");
        context.getLogger().info("authorizationHeader = " + authorizationHeader);

        //String passSerialNumber = request.getQueryParameters().get("passSerialNumber");
        
        //context.getLogger().info("Java HTTP trigger processed a request." + query1 );


        

        // Parse query parameter
        String query = request.getQueryParameters().get("name");
       // String serialNumber= request.getBody().getSerialNumber();
        //context.getLogger().info("Java HTTP trigger processed a request." + serialNumber);
        

        if (query== null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
        } else {
            //return request.createResponseBuilder(HttpStatus.OK).body("op").build();
            return request.createResponseBuilder(HttpStatus.OK).body("Hello, " + query).build();
        }
    }
}
