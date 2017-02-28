package com.hascode.tutorial;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecuredResource {
    private static final Logger LOG = LoggerFactory.getLogger(SecuredResource.class);

    private final Map<String, String> tokenToUserid;

    public SecuredResource(Map<String, String> tokenToUserid) {
        this.tokenToUserid = tokenToUserid;
    }

    @GET
    @Path("/date")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDate(@HeaderParam("Auth-Token") String authToken) {
        LOG.info("trying to access secured resource with authToken '{}'", authToken);
        if (!authorized(authToken)) {
            LOG.warn("not authorized");
            return Response.status(Status.FORBIDDEN).build();
        }
        return Response.ok(String.format("{\"date\":\"%s\"}",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))).build();
    }

    private boolean authorized(String authToken) {
        if (authToken == null || authToken.isEmpty()) {
            LOG.warn("no auth-token given");
            return false;
        }
        if (!tokenToUserid.containsKey(authToken)) {
            LOG.warn("invalid auth-token given");
            return false;
        }
        return true;
    }
}
