package my.compary.psixol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "meleeWeapon")
@XmlEnum
public enum MeleeWeapon {
    CHAIN_AXE,
    MANREAPER,
    LIGHTING_CLAW,
    POWER_FIST;

    public String value() {
        return name();
    }

    public static MeleeWeapon fromValue(String v) {
        return valueOf(v);

    }
    public static List<MeleeWeapon> getListPowerMinimalCurrent(Integer id){
        List<MeleeWeapon> list = new ArrayList<>();

        if(getById(CHAIN_AXE) <= id){
            list.add(CHAIN_AXE);
        }
        if(getById(MANREAPER) <= id){
            list.add(MANREAPER);
        }
        if(getById(LIGHTING_CLAW) <= id){
            list.add(LIGHTING_CLAW);
        }
        if(getById(POWER_FIST) <= id){
            list.add(POWER_FIST);
        }
        return list;
    }

    public static List<MeleeWeapon> getListPowerMaximalCurrent(Integer id){
        List<MeleeWeapon> list = new ArrayList<>();

        if(getById(CHAIN_AXE) >= id){
            list.add(CHAIN_AXE);
        }
        if(getById(MANREAPER) >= id){
            list.add(MANREAPER);
        }
        if(getById(LIGHTING_CLAW) >= id){
            list.add(LIGHTING_CLAW);
        }
        if(getById(POWER_FIST) >= id){
            list.add(POWER_FIST);
        }
        return list;
    }
    public static Integer getById(MeleeWeapon meleeWeapon){
        if(meleeWeapon==CHAIN_AXE){
            return 3;
        }
        else if(meleeWeapon==MANREAPER){
            return 2;
        }
        else if(meleeWeapon==LIGHTING_CLAW){
            return 1;
        }
        else if(meleeWeapon==POWER_FIST){
            return 0;
        }else{
            return 0;
        }

    }
}
