package at.htl.boundary;

import at.htl.control.AccessTokenRepository;
import at.htl.control.CourseRepository;
import at.htl.control.LevelRepository;
import at.htl.control.UsageRepository;
import at.htl.entity.AccessToken;
import at.htl.entity.Course;
import at.htl.entity.D_File;
import at.htl.entity.Level;
import org.jboss.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/course")
public class CourseEndpoint {

    @Inject
    CourseRepository courseRepository;

    @Inject
    LevelRepository levelRepository;

    @Inject
    UsageRepository usageRepository;

    @Inject
    AccessTokenRepository accessTokenRepository;

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"STUDENT", "TEACHER"})
    public Response findAll() {
        return Response.ok(courseRepository.listAll()).build();
    }

    @POST
    @Path("/create")
    @RolesAllowed("TEACHER")
    public Response create(@Context UriInfo info, Course course) {

        courseRepository.persist(course);

        return Response
                .ok(course)
                .build();
    }


    @GET
    @Path("/{id}")
    @RolesAllowed({"STUDENT", "TEACHER"})
    public Response findById(@PathParam("id") long id) {
        return Response.ok(courseRepository.findById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("TEACHER")
    public Response delete(@PathParam("id") Long id) {
        try {
            courseRepository.deleteById(id);
            return Response
                    .noContent()
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Reason", "Course with id " + id + " does not exist")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"STUDENT", "TEACHER"})
    public Response update(@PathParam("id") long id, Course course) {

        return Response
                .ok(courseRepository.save(course)).build();
    }

    @GET
    @Path("/findByLevel/{levelId}")
    @RolesAllowed({"STUDENT", "TEACHER"})
    public Response findCourseByLevel(@PathParam("levelId") String levelId) {
        Level level = new Level(levelId.toUpperCase(), levelId.toUpperCase());
        return Response.ok().entity(courseRepository.findCourseByLevel(level)).build();
    }


    @GET
    @Path("/filesByCourse/{courseId}/user")
    @RolesAllowed({"STUDENT", "TEACHER"})
    public Response findMediaFileByCourse(@PathParam("courseId") long courseId) {
        List<D_File> files = usageRepository.findFilesByCourseId(courseId);
        return Response
                .ok(files)
                .build();
    }

    @GET
    @Path("/filesByCourse/{courseId}/token")
    @PermitAll
    public Response findMediaFileByCourseWithToken(@PathParam("courseId") long courseId, @HeaderParam("X-Token") String token) {
        AccessToken accessToken = accessTokenRepository.find("token", token)
                .stream()
                .findFirst()
                .orElse(null);

        logger.info(token);
        if (accessToken != null) {
            accessToken = accessTokenRepository.activateToken(accessToken);


            if (accessTokenRepository.validate(accessToken) && accessToken.course.id == courseId) {
                List<D_File> files = usageRepository.findFilesByCourseId(courseId);
                return Response
                        .ok(files)
                        .build();
            }
        }

        return Response
                .status(Response.Status.UNAUTHORIZED)
                .build();
    }


}
