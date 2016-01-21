package model.core.medicine;

import java.util.Date;

/**
 * Created by Marcin on 2016-01-20.
 */
public abstract class MedicineAbs {
    ECategory type = null;  //kategoria leku
    String name, description;
    Date dateExpiration, dateIntroduction;
    int codeEan;

    public MedicineAbs(ECategory type, String name, Date dateExpiration, Date dateIntroduction, String description, int codeEan) {
        this.type = type;
        this.name = name;
        this.dateExpiration = dateExpiration;
        this.dateIntroduction = dateIntroduction;
        this.description = description;
        this.codeEan = codeEan;
    }

    public MedicineAbs(ECategory type, String name, Date dateIntroduction, Date dateExpiration, int codeEan) {
        this.type = type;
        this.name = name;
        this.dateIntroduction = dateIntroduction;
        this.dateExpiration = dateExpiration;
        this.codeEan = codeEan;
    }

    //GETTERs:
    public ECategory getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public Date getDateExpiration() {
        return dateExpiration;
    }
    public Date getDateIntroduction() {
        return dateIntroduction;
    }
    public String getDescription() {
        return description;
    }
    public int getCodeEan() {
        return codeEan;
    }

    //SETTERs:
    public void setType(ECategory type) {
        this.type = type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
    public void setDateIntroduction(Date dateIntroduction) {
        this.dateIntroduction = dateIntroduction;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCodeEan(int codeEan) {
        this.codeEan = codeEan;
    }

    //METHODs:
    @Override
    public String toString() {
        return "MedicineAbs{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", dateExpiration='" + dateExpiration + '\'' +
                ", dateIntroduction='" + dateIntroduction + '\'' +
                ", codeEan=" + codeEan +
                '}';
    }
}
