package my.compary.conf;



import my.compary.psixol.AstartesCategory;
import my.compary.psixol.Coordinates;
import my.compary.psixol.*;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "SpaceMarine")
@XmlType(name = "TypeCode")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpaceMarineUpdate implements Serializable {


    @XmlElement(name = "id")
    private Long id = null;

   @XmlElement(name = "name")
    private String name = null;

    @XmlElement(name = "coordinates")
    private Coordinates coordinates = null;

    @XmlElement(name = "health")
    private Double health = null;


    @XmlElement(required = true,name = "category")
    private AstartesCategory category = null;

    @XmlElement(required = true,name = "weaponType")
    private Weapon weaponType = null;

    @XmlElement(required = true,name = "meleeWeapon")
    private MeleeWeapon meleeWeapon = null;

    @XmlElement(name = "chapter")
    private Chapter chapter = null;

    @XmlElement(name = "starshipId")
    private Long starshipId = null;

    public SpaceMarineUpdate() {

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

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
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
}
