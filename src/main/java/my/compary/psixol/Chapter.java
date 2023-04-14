package my.compary.psixol;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "chapter")
@XmlRootElement(name = "Chapter")
@XmlType(name = "TypeCode5")
@XmlAccessorType(XmlAccessType.FIELD)
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id = null;

    @Column(name = "name")
    @XmlElement(name = "name")
    private String name = null;; //Поле не может быть null, Строка не может быть пустой

    @Column(name = "parentLegion")
    @XmlElement(name = "parentLegion")
    private String parentLegion = null;

    @Column(name = "marinesCount")
    @XmlElement(name = "marinesCount")
    private Integer marinesCount = null;; //Значение поля должно быть больше 0, Максимальное значение поля: 1000

    @Column(name = "world")
    @XmlElement(name = "world")
    private String world = null;; //Поле не может быть null


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

    public String getParentLegion() {
        return parentLegion;
    }

    public void setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
    }

    public Integer getMarinesCount() {
        return marinesCount;
    }

    public void setMarinesCount(Integer marinesCount) {
        this.marinesCount = marinesCount;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String toString(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",name);
            jsonObject.put("parentLegion",parentLegion);
            jsonObject.put("marinesCount",marinesCount);
            jsonObject.put("world",world);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            return XML.toString(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }




    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",name);
            jsonObject.put("parentLegion",parentLegion);
            jsonObject.put("marinesCount",marinesCount);
            jsonObject.put("world",world);

        } catch (JSONException e) {
            e.printStackTrace();
        }

       return jsonObject;

    }
}
