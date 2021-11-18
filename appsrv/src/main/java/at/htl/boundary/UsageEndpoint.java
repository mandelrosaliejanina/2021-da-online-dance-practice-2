package at.htl.boundary;

import at.htl.control.CourseRepository;
import at.htl.control.FileRepository;
import at.htl.control.UsageRepository;
import at.htl.entity.Course;
import at.htl.entity.D_File;
import at.htl.entity.Usage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.net.URI;

@RequestScoped
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
    public Response findAll() {
        return Response.ok(usageRepository.listAll()).build();
    }

    @POST
    @Path("/create/{courseId}/{fileId}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("courseId") long courseId ,@PathParam("fileId") long fileId, @Context UriInfo info) {

        Course course = courseRepository.findById(courseId);
        D_File file =  fileRepository.findById(fileId);
        Usage usage = new Usage(course,file);
        usageRepository.persist(usage);
        return Response.created(URI.create(info.getPath() + "/"+ usage.id)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        return Response.ok(usageRepository.findById(id)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            usageRepository.deleteById(id);
            return Response
                    .noContent()
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(400)
                    .header("Reason","Usage with id" + id  + "does not exist")
                    .build();
        }
    }
}
