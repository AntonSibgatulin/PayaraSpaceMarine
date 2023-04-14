package my.compary.psixol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "weaponType")
@XmlEnum
public enum Weapon {
    BOLTGUN,
    MELTAGUN,
    MISSILE_LAUNCHER;

    public String value() {
        return name();
    }

    public static Weapon fromValue(String v) {
        return valueOf(v);
    }



    public static List<Weapon> getListPowerMinimalCurrent(Integer id){
        List<Weapon> list = new ArrayList<>();

       
        if(getById(BOLTGUN) <= id){
            list.add(BOLTGUN);
        }
        if(getById(MELTAGUN) <= id){
            list.add(MELTAGUN);
        }
        if(getById(MISSILE_LAUNCHER) <= id){
            list.add(MISSILE_LAUNCHER);
        }
        return list;
    }

    public static List<Weapon> getListPowerMaximalCurrent(Integer id){
        List<Weapon> list = new ArrayList<>();

       
        if(getById(BOLTGUN) >= id){
            list.add(BOLTGUN);
        }
        if(getById(MELTAGUN) >= id){
            list.add(MELTAGUN);
        }
        if(getById(MISSILE_LAUNCHER) >= id){
            list.add(MISSILE_LAUNCHER);
        }
        return list;
    }
    public static Integer getById(Weapon meleeWeapon){
        
        if(meleeWeapon==BOLTGUN){
            return 2;
        }
        else if(meleeWeapon==MELTAGUN){
            return 1;
        }
        else if(meleeWeapon==MISSILE_LAUNCHER){
            return 0;
        }else{
            return 0;
        }

    }
}
