package my.compary.psixol;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

@XmlEnum
@XmlRootElement(name = "starShipType")
public enum StarShipType{
    FRIGATE,
    BATTLESHIP,
    CRUISER;



    public String value() {
        return name();
    }

    public static StarShipType fromValue(String v) {
        return valueOf(v);
    }


}
