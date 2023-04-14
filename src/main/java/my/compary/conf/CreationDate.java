package my.compary.conf;

import javax.xml.bind.annotation.*;

@XmlType(name = "TypeCode6")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="creationDate")
public class CreationDate {

    @XmlElement(name = "time")
    public String time = null;



    public CreationDate(){}



}
