package at.htl.boundary;

import at.htl.control.AccessTokenRepository;
import at.htl.entity.AccessToken;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/accessToken")
public class AccessTokenEndpoint {

    @Inject
    AccessTokenRepository repository;

    @GET
    @RolesAllowed("TEACHER")
    public Response getAll() {
        return Response.ok(repository.findAll().list()).build();
    }

    @POST
    @RolesAllowed("TEACHER")
    public Response create(AccessToken accessToken) {
        repository.persist(accessToken);

        return Response.ok(accessToken).build();
    }

    @PUT
    @RolesAllowed("TEACHER")
    public Response update(AccessToken token) {
        AccessToken accessToken = repository.find("token", token.token)
                .stream()
                .findFirst()
                .orElse(null);

        if (accessToken != null) {
            accessToken = repository.save(token);
            return Response.ok(accessToken).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{token}")
    @RolesAllowed("TEACHER")
    public Response delete(@PathParam("token") String token) {
        AccessToken accessToken = repository.find("token", token)
                .stream()
                .findFirst()
                .orElse(null);

        if (accessToken != null) {
            repository.delete("token", accessToken.token);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/validate")
    @PermitAll
    public Response validate(JsonValue jsonValue) {
        if (jsonValue.getValueType().equals(JsonValue.ValueType.OBJECT)) {
            try {
                String token = jsonValue.asJsonObject().getString("token");
                AccessToken accessToken = repository.find("token", token)
                        .stream()
                        .findFirst()
                        .orElse(null);
                if (accessToken != null) {
                    accessToken = repository.activateToken(accessToken);
                    boolean isValid = repository.validate(accessToken);
                    JsonObjectBuilder builder = Json.createObjectBuilder()
                            .add("token", accessToken.token)
                            .add("courseId", accessToken.course.id)
                            .add("courseName", accessToken.course.title)
                            .add("levelId", accessToken.course.level.id)
                            .add("isValid", isValid);

                    return Response.ok(builder.build()).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            } catch (Exception e) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
