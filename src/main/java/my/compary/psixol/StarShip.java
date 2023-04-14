package my.compary.psixol;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "starship")
@XmlRootElement(name = "starship")
@XmlType(name = "TypeCode3")
@XmlAccessorType(XmlAccessType.FIELD)
public class StarShip {

    @Id
    @Column(name = "id")
    @XmlElement(name = "id")
    private Long id = null;

    @Column(name = "name")
    @XmlElement(name = "name")
    private String name = null;

    @Column(name = "campacity")
    @XmlElement(name = "campacity")
    private Integer campacity = null;

    @Column(name = "length")
    @XmlElement(name = "length")
    private Double length = null;

    @Column(name = "width")
    @XmlElement(name = "width")
    private Double width = null;

    @Column(name = "height")
    @XmlElement(name = "height")
    private Double height = null;

    @Column(name = "starShipType")
    @XmlElement(required = true,name = "starShipType")
    public String starShipType = null;


    private StarShipType starShipTypes = null;

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

    public Integer getCampacity() {
        return campacity;
    }

    public void setCampacity(Integer campacity) {
        this.campacity = campacity;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getStarShipType() {
        return starShipType;
    }

    public void setStarShipType(String starShipType) {
        this.starShipType = starShipType;
    }

    public StarShipType getStarShipTypes() {
        return starShipTypes;
    }

    public void setStarShipTypes(StarShipType starShipTypes) {
        this.starShipTypes = starShipTypes;
    }


    public String toString(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",id);
            jsonObject.put("name",name);
            jsonObject.put("campacity",campacity);
            jsonObject.put("length",length);
            jsonObject.put("width",width);
            jsonObject.put("height",height);
            jsonObject.put("starShipType",starShipTypes.toString());

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
            jsonObject.put("id",id);
            jsonObject.put("name",name);
            jsonObject.put("campacity",campacity);
            jsonObject.put("length",length);
            jsonObject.put("width",width);
            jsonObject.put("height",height);
            jsonObject.put("starShipType",starShipTypes.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

           return jsonObject;


    }
}
