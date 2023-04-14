package my.compary.psixol;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

@Entity
@Table(name = "SpaceMarine")

@XmlRootElement(name = "SpaceMarine3")
@XmlType(name = "TypeCode")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpaceMarine implements Serializable {




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = "id")
    private Long id=null; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически



    @Column(name = "name")
    @XmlElement(name = "name")
    private String name=null; //Поле не может быть null, Строка не может быть пустой


    @XmlElement(name = "coordinates")
    private Coordinates coordinates=null; //Поле не может быть null


    @Column(name = "creationDate")
    private String creationDate = null;


    @Column(name = "creationDateUnix")
    private Long creatinoDateUnix = null;

    private ZonedDateTime creationDates=null; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(name = "health")
    @XmlElement(name = "health")
    private Double health=null; //Значение поля должно быть больше 0


    @Column(name = "category")
    @XmlElement(name = "category")
    private String category = null;

    @Column(name = "weaponType")
    @XmlElement(name = "weaponType")
    private String weaponType = null;

    @Column(name = "meleeWeapon")
    @XmlElement(name = "meleeWeapon")
    private String meleeWeapon = null;


    @Column(name = "starshipId")
    private Long starshipId = null;


    private AstartesCategory categorys=null; //Поле может быть null
    private Weapon weaponTypes=null; //Поле не может быть null
    private MeleeWeapon meleeWeapons=null; //Поле не может быть null


    @XmlElement(name = "chapter")
    private Chapter chapter=null; //Поле не может быть null


    @XmlElement(name = "starship")
    private StarShip starShip =  null;



    public StarShip getStarShip() {
        return starShip;
    }

    public void setStarShip(StarShip starShip) {
        this.starShip = starShip;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public ZonedDateTime getCreationDates() {
        return creationDates;
    }

    public void setCreationDates(ZonedDateTime creationDates) {
        this.creationDates = creationDates;
    }

    public Long getCreatinoDateUnix() {
        return creatinoDateUnix;
    }

    public void setCreatinoDateUnix(Long creatinoDateUnix) {
        this.creatinoDateUnix = creatinoDateUnix;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getMeleeWeapon() {
        return meleeWeapon;
    }

    public void setMeleeWeapon(String meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public AstartesCategory getCategorys() {
        return categorys;
    }

    public void setCategorys(AstartesCategory categorys) {
        this.categorys = categorys;
    }

    public Weapon getWeaponTypes() {
        return weaponTypes;
    }

    public void setWeaponTypes(Weapon weaponTypes) {
        this.weaponTypes = weaponTypes;
    }

    public MeleeWeapon getMeleeWeapons() {
        return meleeWeapons;
    }

    public void setMeleeWeapons(MeleeWeapon meleeWeapons) {
        this.meleeWeapons = meleeWeapons;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Long getStarshipId() {
        return starshipId;
    }

    public void setStarshipId(Long starshipId) {
        this.starshipId = starshipId;
    }

    public String toString()  {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
JSONObject creationTime = new JSONObject();
        try {
                jsonObject.put("id",id);
            jsonObject.put("name",name);

            jsonObject.put("coordinates",coordinates.toJSON());
            creationTime.put("time",creationDates.toString());
            jsonObject.put("creationDate",creationTime);



            jsonObject.put("health",health);
            jsonObject.put("category",categorys.toString());
            jsonObject.put("weaponType",weaponTypes.toString());
            jsonObject.put("meleeWeapon",meleeWeapons.toString());

            jsonObject.put("chapter",chapter.toJSON());
            // jsonObject.put("starship",starShip.toJSON());
            if(starShip!=null) {
                jsonObject.put("starship", starShip.toJSON());
            }else{
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("id","null");
                jsonObject2.put("name","null");

                jsonObject.put("starship", jsonObject2);

            }



            jsonObject1.put("SpaceMarineResponse",jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            return XML.toString(jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void loadTime(){
        Instant instant = Instant.ofEpochSecond(this.creatinoDateUnix);
        ZoneId zoneId = ZoneId.of("Europe/London");

        //this.creationDates = instant.atZone(zoneId);

        // Alternatively
        creationDates = ZonedDateTime.ofInstant(instant, zoneId);



    }


    public void loadTImes(){
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        this.creationDate = nowAsISO;
    }

    public String toSearchXML() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject creationTime = new JSONObject();
        try {
            jsonObject.put("id",id);
            jsonObject.put("name",name);

            jsonObject.put("coordinates",coordinates.toJSON());
            creationTime.put("time",creationDates.toString());
            jsonObject.put("creationDate",creationTime);



            jsonObject.put("health",health);
            jsonObject.put("category",categorys.toString());
            jsonObject.put("weaponType",weaponTypes.toString());
            jsonObject.put("meleeWeapon",meleeWeapons.toString());

            jsonObject.put("chapter",chapter.toJSON());
            if(starShip!=null) {
                jsonObject.put("starship", starShip.toJSON());
            }else{
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("id","null");
                jsonObject2.put("name","null");
                jsonObject.put("starship", jsonObject2);

            }

            jsonObject1.put("SpaceMarine",jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            return XML.toString(jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
