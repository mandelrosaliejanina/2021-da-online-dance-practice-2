package at.htl.boundary;

import at.htl.control.UserRepository;
import at.htl.entity.User;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/user")
public class UserEndpoint {

    @Inject
    UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(userRepository.listAll()).build();
    }

    @POST
    @Path("/create")
    public Response create(User user, @Context UriInfo info) {
        userRepository.persist(user);
        return Response.created(URI.create(info.getPath() + "/" + user.id)).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        return Response.ok(userRepository.findById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            userRepository.deleteById(id);
            return Response
                    .noContent()
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Reason", "User with id " + id + " does not exist")
                    .build();
        }
    }

    /**
     * @param jsonValue {
     *                  username: "musterfrau",
     *                  password: "123456789",
     *                  }
     */
    @POST
    @Path("/authenticate")
    public Response authenticate(JsonValue jsonValue) {
        if (jsonValue.getValueType().equals(JsonValue.ValueType.OBJECT)) {
            try {
                User user = userRepository
                        .find("username", jsonValue.asJsonObject().getString("username"))
                        .stream()
                        .findFirst()
                        .orElse(null);

                if (user == null) {
                    return Response.status(Response.Status.NOT_FOUND).build();
                }

                if (BcryptUtil.matches(jsonValue.asJsonObject().getString("password"), user.password)) {
                    return Response.ok(user).build();
                }

                return Response.status(Response.Status.UNAUTHORIZED).build();
            } catch (Exception e) {
                return Response.status(Response.Status.NOT_MODIFIED).build();
            }
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
