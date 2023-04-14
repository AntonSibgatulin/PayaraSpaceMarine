package my.compary.psixol;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Entity
@Table(name = "coordinate")
@XmlRootElement(name = "coordinates")
@XmlType(name = "TypeCode2")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates implements Serializable {

    @Id
    @Column(name = "id")
    private Long id = null;

    @Column(name = "x")
    @XmlElement(name = "x")
    private Double x = null; //Поле не может быть null

    @Column(name = "y")
    @XmlElement(name = "y")
    private Double y = null; //Поле не может быть null


    public Coordinates(){

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String toString(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("x",x);
            jsonObject.put("y",y);
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
            jsonObject.put("x",x);
            jsonObject.put("y",y);
        } catch (JSONException e) {
            e.printStackTrace();
        }

       return jsonObject;
    }

}
