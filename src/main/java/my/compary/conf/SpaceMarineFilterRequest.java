package my.compary.conf;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "SpaceMarineFilterRequest")
@XmlType(name = "TypeCode")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpaceMarineFilterRequest {

    @XmlElement(name = "page")
    private Integer page;

    @XmlElement(name = "limit")
    private Integer limit;

    @XmlElement(name = "id")
    private Integer id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "health")
    private Double health;


    @XmlElement(name = "maxhealth")
    private Double maxhealth;


    @XmlElement(name = "creationDate")
    private String creationDate;

    @XmlElement(name = "category")
    private String category;

    @XmlElement(name = "weaponType")
    private String weaponType;

    @XmlElement(name = "meleeWeapon")
    private String meleeWeapon;

    @XmlElement(name = "sortBy")
    private String sortBy;


    public SpaceMarineFilterRequest(){

    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
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

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }


    public Double getMaxhealth() {
        return maxhealth;
    }

    public void setMaxhealth(Double maxhealth) {
        this.maxhealth = maxhealth;
    }
}
