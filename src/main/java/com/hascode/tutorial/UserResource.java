package com.hascode.tutorial;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/user")
public class UserResource {
    private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

    private static final Map<String, User> fakeStore = new ConcurrentHashMap<>();
    private static final Map<String, User> tokenToUser = new ConcurrentHashMap<>();

    @XmlRootElement
    static class Credential {
        private String id;
        private String password;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Collection<User> users = fakeStore.values();
        LOG.info("{} users found", users.size());

        return Response.ok(new GenericEntity<Collection<User>>(users) {
        }).build();
    }

    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("userId") String userId) {
        LOG.info("fetching user by user-id: {}", userId);
        return Response.ok(fakeStore.get(userId)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(User user) {
        user.setId(UUID.randomUUID().toString().toUpperCase());
        fakeStore.put(user.getId(), user);

        LOG.info("new user {} saved", user);
        return Response.ok(user).build();
    }

    @DELETE
    public Response purgeUsers() {
        LOG.info("removing all {} users", fakeStore.size());
        fakeStore.clear();
        return Response.ok().build();
    }

    @POST
    @Path("/login")
    public Response login(Credential credential) {
        LOG.info("login user with id: '{}' and password '{}'", credential.id, credential.password);
        return Response.ok("{\"authToken\":\"xxxx\"}").build();
    }

    @Path("/secured")
    public SecuredResource getSecuredResource() {
        return new SecuredResource();
    }
}
