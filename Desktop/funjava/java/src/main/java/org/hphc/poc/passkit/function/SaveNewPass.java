package org.hphc.poc.passkit.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.hphc.poc.passkit.function.pojo.Pass;

/**
 * Azure Functions with HTTP Trigger.
 */
public class SaveNewPass{
    /**
     * This function will save a brand new pass
     */
    @FunctionName("savepass")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route="hphc/savenewpass"
            ) HttpRequestMessage<Pass> request,
            @CosmosDBOutput(name = "database",
                    databaseName = "hphc-poc-cosmo1",
                    collectionName = "cosmo-coll1",
                    connectionStringSetting = "hphc-poc-passkit-db_DOCUMENTDB")
                    OutputBinding<Pass> outputItem,
            final ExecutionContext context) {
        context.getLogger().info("Save New Pass request");
        Pass inputPass = request.getBody();
        context.getLogger().info("Java HTTP trigger processed a request. About to save :" + inputPass.getSerialNumber());

        inputPass.setCreated(new Date());
        inputPass.setModified(new Date());

        /*// Parse query parameter
        String query = request.getQueryParameters().get("name");
        String name = request.getBody().orElse(query);*/
        outputItem.setValue(inputPass);
        return request.createResponseBuilder(HttpStatus.CREATED).body("Created Document").build();


    }
}
