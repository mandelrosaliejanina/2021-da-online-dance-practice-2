package at.htl.boundary;

import at.htl.control.FileRepository;
import at.htl.control.UsageRepository;
import at.htl.entity.ContentType;
import at.htl.entity.Course;
import at.htl.entity.D_File;
import at.htl.entity.Level;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;


@RequestScoped
@Path("/file")
public class FileEndpoint {

    @Inject
    FileRepository fileRepository;

    @Inject
    UsageRepository usageRepository;

    @Context
    private ServletContext context;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall() {
        return Response.ok(fileRepository.listAll()).build();
        /*JsonObject hello = Json.createObjectBuilder().add("name", "sandy").build();
        return Response.ok(hello).build();*/
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(MultipartFormDataInput input) throws IOException {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        // Get file data to save
        List<InputPart> inputParts = uploadForm.get("thumbnail");
        for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                String fileName = getFileName(header);
                // convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                byte[] bytes = IOUtils.toByteArray(inputStream);

                /*
                 * TODO create folder target/mediafiles/video/ or target/mediafiles/video/
                 *
                 * following if statement is probably not validating if the mentioned dir exists in the target folder,
                 * which is mandatory for quarkus?
                 *
                 * the media files should also be store at a lfs,
                 * because the target dir gets cleaned each time quarkus starts
                 *
                 * */

                File customDir = new File("mediafiles/video");
                if (!customDir.exists()) {
                    customDir.mkdir();
                }
                fileName = customDir.getCanonicalPath() + File.separator + fileName;
                writeFile(bytes, fileName);
                return Response.status(200).entity("Uploaded file name : " + fileName+" . <br/> <a href='"+context.getContextPath()+"'>Back</a>").build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

    private void writeFile(byte[] content, String filename) throws IOException {
        System.out.println(filename);
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        return Response.ok(fileRepository.findById(id)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {

            //usageRepository.find("select * from usage u where u.file = )

            fileRepository.delete(fileRepository.findById(id));
            return Response
                    .noContent()
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(400)
                    .header("Reason","File with id" +id  + "does not exist")
                    .build();
        }
    }
}
