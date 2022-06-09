package co.edu.unbosque.projectrest.resources;
import co.edu.unbosque.projectrest.model.UserApp;
import co.edu.unbosque.projectrest.services.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/resource")
public class UserResource {

    private UserService service;

    public UserResource(){
        service = new UserService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registered(UserApp userApp){
        System.out.println(userApp.toString());
        return Response.ok()
                       .status(201)
                       .entity(service.addElement(userApp.getName(), userApp.getEmail(), userApp.getPassword(), userApp.getRole()))
                       .build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserApp userApp){
        return Response.ok()
                       .entity(service.login(userApp.getName(), userApp.getPassword()))
                       .build();
    }

    @GET
    @Path("/text")
    @Produces(MediaType.TEXT_PLAIN)
    public String text(){
        return "TEXT";
    }
}
