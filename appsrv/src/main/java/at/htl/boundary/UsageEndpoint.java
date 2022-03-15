package at.htl.boundary;

import at.htl.control.CourseRepository;
import at.htl.control.FileRepository;
import at.htl.control.UsageRepository;
import at.htl.entity.Course;
import at.htl.entity.D_File;
import at.htl.entity.Usage;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/usage")
public class UsageEndpoint {

    @Inject
    UsageRepository usageRepository;

    @Inject
    CourseRepository courseRepository;

    @Inject
    FileRepository fileRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("TEACHER")
    public Response findAll() {
        return Response.ok(usageRepository.listAll()).build();
    }

    @POST
    @Path("/create/{courseId}/{fileId}")
    @RolesAllowed("TEACHER")
    public Response create(@PathParam("courseId") long courseId, @PathParam("fileId") long fileId, @Context UriInfo info) {
        Course course = courseRepository.findById(courseId);
        D_File file = fileRepository.findById(fileId);

        Usage usage = new Usage(course, file);
        usageRepository.persist(usage);

        return Response.created(URI.create(info.getPath() + "/" + usage.id)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"TEACHER", "STUDENT"})
    public Response findById(@PathParam("id") long id) {
        return Response.ok(usageRepository.findById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("TEACHER")
    public Response delete(@PathParam("id") Long id) {
        try {
            usageRepository.deleteById(id);
            return Response
                    .noContent()
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Reason", "Usage with id " + id + " does not exist")
                    .build();
        }
    }
}
