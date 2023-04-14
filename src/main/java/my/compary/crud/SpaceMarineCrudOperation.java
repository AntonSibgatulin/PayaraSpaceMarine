package my.compary.crud;


import my.compary.psixol.*;
import my.compary.conf.SpaceMarineFilterRequest;
import my.compary.conf.SpaceMarineUpdate;
import my.compary.database.DatabaseModel;
import my.compary.math.MathClass;
import my.compary.psixol.SpaceMarine;

import org.json.JSONException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Path("/spacemarines")
public class SpaceMarineCrudOperation {


    private DatabaseModel databaseModel = new DatabaseModel();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/get/{id}")
    public Response getById(@PathParam("id") String id) {
        if (!MathClass.isNumeric(id)) {
            return Response.status(Response.Status.OK).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Invalid ID supplied</text><code>400</code></action>").build();

        }
        SpaceMarine spaceMarine = databaseModel.getSpaceMarineById(Long.valueOf(id));
        if (spaceMarine == null) {
            return Response.status(Response.Status.OK).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>SpaceMarine not found</text><code>404</code></action>").build();

        }
        return Response.status(Response.Status.OK).entity(spaceMarine.toString()).build();
    }


    @DELETE
    @Produces(MediaType.APPLICATION_XML)
    @Path("/delete/{id}")
    public Response deleteResponseById(@PathParam("id") String id) {
        if (!MathClass.isNumeric(id)) {
            return Response.status(Response.Status.OK).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Invalid ID supplied</text><code>400</code></action>").build();

        }
        SpaceMarine spaceMarine = databaseModel.getSpaceMarineById(Long.valueOf(id));
        if (spaceMarine == null) {
            return Response.status(Response.Status.OK).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>SpaceMarine not found</text><code>404</code></action>").build();

        }
        databaseModel.deleteSpaceMarine(spaceMarine);
        return Response.status(Response.Status.OK).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>Successful Operation</text><code>200</code></action>").build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/search")
    public Response search(String x) {
        InputStream is = new ByteArrayInputStream(x.getBytes(StandardCharsets.UTF_8));
        JAXBContext jaxbContext;
        SpaceMarineFilterRequest employee = null;
        try {
            jaxbContext = JAXBContext.newInstance(SpaceMarineFilterRequest.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            employee = (SpaceMarineFilterRequest) jaxbUnmarshaller.unmarshal(is);

        } catch (JAXBException e) {
            e.printStackTrace();
            return Response.status(400).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Invalid input</text><code>400</code></action>").build();

        }
        String result = null;

        try {
            result = databaseModel.search(employee);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return Response.status(400).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Invalid input</text><code>400</code></action>").build();

        }
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/update/{id}")
    public Response update(@PathParam("id") String id, String x) {


        InputStream is = new ByteArrayInputStream(x.getBytes(StandardCharsets.UTF_8));
        JAXBContext jaxbContext;
        SpaceMarineUpdate employee = null;
        try {
            jaxbContext = JAXBContext.newInstance(SpaceMarineUpdate.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            employee = (SpaceMarineUpdate) jaxbUnmarshaller.unmarshal(is);

        } catch (JAXBException e) {
            e.printStackTrace();
            return Response.status(400).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Invalid input</text><code>400</code></action>").build();

        }

        if (employee.getHealth() == null || employee.getHealth()<=0 || employee.getChapter() == null || employee.getChapter().getName() == null || employee.getName() == null || employee.getName().length() == 3) {
            return Response.status(400).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Invalid input</text><code>400</code></action>").build();

        }
        if (employee.getStarshipId() < 0) {
            employee.setStarshipId(0L);
        }



        SpaceMarine spaceMarine = databaseModel.getSpaceMarineById(Long.valueOf(id));
        if (spaceMarine == null) {
            return Response.status(404).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>SpaceMarine not found</text><code>404</code></action>").build();

        }


        spaceMarine.setStarshipId(employee.getStarshipId());
        spaceMarine.setCoordinates(employee.getCoordinates());


        if (employee.getChapter().getMarinesCount() <= 0) employee.getChapter().setMarinesCount(1);
        if (employee.getChapter().getMarinesCount() > 1000) employee.getChapter().setMarinesCount(1000);

        spaceMarine.setChapter(employee.getChapter());

        spaceMarine.setName(employee.getName());
        spaceMarine.setHealth(employee.getHealth());


        spaceMarine.setMeleeWeapon(employee.getMeleeWeapon().toString());
        spaceMarine.setCategory(employee.getCategory().toString());
        spaceMarine.setWeaponType(employee.getWeaponType().toString());


        databaseModel.updateSpaceMarine(spaceMarine);


        return Response.status(Response.Status.OK).entity(x).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/create")
    public Response create(String x) {


        InputStream is = new ByteArrayInputStream(x.getBytes(StandardCharsets.UTF_8));
        JAXBContext jaxbContext;
        SpaceMarineUpdate employee = null;
        try {
            jaxbContext = JAXBContext.newInstance(SpaceMarineUpdate.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            employee = (SpaceMarineUpdate) jaxbUnmarshaller.unmarshal(is);

        } catch (JAXBException e) {
            e.printStackTrace();
            return Response.status(400).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Invalid input</text><code>400</code></action>").build();

        }

        if (employee.getHealth() == null || employee.getHealth()<=0 || employee.getChapter() == null || employee.getChapter().getName() == null || employee.getName() == null || employee.getName().length() == 3) {
            return Response.status(400).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Invalid input</text><code>400</code></action>").build();

        }
        if (employee.getStarshipId() < 0) {
            employee.setStarshipId(0L);
        }


        SpaceMarine spaceMarine = new SpaceMarine();

        spaceMarine.setCreatinoDateUnix(System.currentTimeMillis());
        spaceMarine.setStarshipId(employee.getStarshipId());
        spaceMarine.setCoordinates(employee.getCoordinates());


        if (employee.getChapter().getMarinesCount() <= 0) employee.getChapter().setMarinesCount(1);
        if (employee.getChapter().getMarinesCount() > 1000) employee.getChapter().setMarinesCount(1000);

        spaceMarine.setChapter(employee.getChapter());

        spaceMarine.setId(null);

        spaceMarine.setName(employee.getName());
        spaceMarine.setHealth(employee.getHealth());


        spaceMarine.setMeleeWeapon(employee.getMeleeWeapon().toString());
        spaceMarine.setCategory(employee.getCategory().toString());
        spaceMarine.setWeaponType(employee.getWeaponType().toString());

        spaceMarine.loadTImes();

        databaseModel.saveSpaceMarine(spaceMarine);


        x = x.replace("<id>" + employee.getId(), "<id>" + spaceMarine.getId());
        return Response.status(Response.Status.OK).entity(x).build();
    }


    @GET
    //@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_XML)
    @Path("/weapon-types/less-then-current")
    public Response less_then_current(/*@FormParam*/@QueryParam("weapon") String weaponType) {
        Weapon weapon = null;
        try {
            weapon = Weapon.valueOf(weaponType);
        } catch (IllegalArgumentException e) {
            weapon = null;
        }
        if (weapon == null) {
            return Response.status(400).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Invalid SpaceMarine weaponType</text><code>400</code></action>").build();

        }
        List<Weapon> list = Weapon.getListPowerMinimalCurrent(Weapon.getById(weapon));
        String xml = databaseModel.getListLessThenCurrent(list);
        if (xml == null) {
            return Response.status(204).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Collection is Empty</text><code>204</code></action>").build();

        }

        return Response.status(200).entity(xml).build();

    }


    @Path("/weapon-types/minimum/{weapon}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response minimumWeapon(@PathParam("weapon") String weapons) {
        Weapon weapon = null;

        try {
            weapon = Weapon.valueOf(weapons);
        } catch (IllegalArgumentException e) {
            weapon = null;
        }
        if (weapon == null) {
            return Response.status(400).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Invalid SpaceMarine weaponType</text><code>400</code></action>").build();

        }


        SpaceMarine spaceMarine = databaseModel.getSpaceMarineMinimalByMeleeWeapon(weapon);
        if (spaceMarine == null) {
            return Response.status(204).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Collection is Empty</text><code>204</code></action>").build();

        }

        return Response.status(200).entity(spaceMarine.toString()).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/chapters/marines")
    public Response marines(/*@FormParam*/@QueryParam("marines") String count) {
        if (MathClass.isNumeric(count) == false) {
            return Response.status(400).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Invalid spacemarine chapter</text><code>400</code></action>").build();

        }
        List<Chapter> chapters = databaseModel.getChapterById(Long.valueOf(count));
        String xml = null;
        if (chapters.size() == 0) {
            return Response.status(400).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "Invalid spacemarine chapter</text><code>400</code></action>").build();

        } else {
            xml = "<SpaceMarines>";
               xml += "<SpaceMarine>\n" +
                        "\t\t<spaceMarineCount>" + chapters.size() + "</spaceMarineCount>\n" +
                        "\t</SpaceMarine>";

            xml += "</SpaceMarines>";
        }

        return Response.status(200).entity(xml).build();

    }


    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/enter/{id}/{id2}")
    public Response enter(@PathParam("id") Long id,@PathParam("id2") Long id2){

        SpaceMarine spaceMarine = databaseModel.getSpaceMarineById(id2);

        if(spaceMarine==null){
            return Response.status(404).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                    "SpaceMarine not found</text><code>404</code></action>").build();

        }
        databaseModel.enter(spaceMarine, id);
        return Response.status(200).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                "Successful operation</text><code>200</code></action>").build();
    }



    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/upload/{id}/")
    public Response upload(@PathParam("id") Long id){


        databaseModel.unloadAll(id);
        return Response.status(200).entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<action><text>\t\n" +
                "Successful operation</text><code>200</code></action>").build();

    }


}
