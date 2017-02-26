package com.hascode.tutorial;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SecuredResource {
    @GET
    @Path("/date/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDate() {
        return Response.ok(String.format("{\"date\":\"%s\"}",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))).build();
    }
}
