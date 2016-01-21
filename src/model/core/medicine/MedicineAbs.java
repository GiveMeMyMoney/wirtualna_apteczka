package model.core.medicine;

/**
 * Created by Marcin on 2016-01-20.
 */
public abstract class MedicineAbs {
    ECategory type = null;  //kategoria leku
    String name, dateExpiration, dateIntroduction, description;
    int codeEan;

    public MedicineAbs(ECategory type, String name, String dateExpiration, String dateIntroduction, String description, int codeEan) {
        this.type = type;
        this.name = name;
        this.dateExpiration = dateExpiration;
        this.dateIntroduction = dateIntroduction;
        this.description = description;
        this.codeEan = codeEan;
    }

    public MedicineAbs(ECategory type, String name, String dateIntroduction, String dateExpiration, int codeEan) {
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
    public String getDateExpiration() {
        return dateExpiration;
    }
    public String getDateIntroduction() {
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
    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
    public void setDateIntroduction(String dateIntroduction) {
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
