package my.compary.psixol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlEnum
public enum AstartesCategory {
    DREADNOUGHT,
    AGGRESSOR,
    SUPPRESSOR;


    public String value() {
        return name();
    }

    public static AstartesCategory fromValue(String v) {
        return valueOf(v);
    }
}
