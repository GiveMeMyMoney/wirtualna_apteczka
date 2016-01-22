package model.core.medicine;

import model.core.condition.Condition;

/**
 * Created by Marcin on 2016-01-20.
 */
public abstract class MedicineAbs {
    ECategory type = null;  //kategoria leku
    String name, dateExpiration, dateIntroduction, description;
    int codeEan;
    Condition condition = null;

    public MedicineAbs(ECategory type, String name, String dateExpiration, String dateIntroduction, String description, int codeEan, Condition condition) {
        this.type = type;
        this.name = name;
        this.dateExpiration = dateExpiration;
        this.dateIntroduction = dateIntroduction;
        this.description = description;
        this.codeEan = codeEan;
        this.condition = condition;
    }

    public MedicineAbs(ECategory type, String name, String dateIntroduction, String dateExpiration, int codeEan, Condition condition) {
        this.type = type;
        this.name = name;
        this.dateIntroduction = dateIntroduction;
        this.dateExpiration = dateExpiration;
        this.codeEan = codeEan;
        this.condition = condition;
    }

    //METHODs:

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
    //Condition:
    public Condition getCondition() {
        return condition;
    }
    public int getPackages() {
        return condition.getPackages();
    }
    public boolean isEMPTY() {
        return condition.isEMPTY();
    }
    public int getSachets() {
        return condition.getSachets();
    }
    public int getPills() {
        return condition.getPills();
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
    //Condition:
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    public void setPackages(int packages) {
        condition.setPackages(packages);
    }
    public void setPills(int pills) {
        condition.setPills(pills);
    }
    public void setSachets(int sachets) {
        condition.setSachets(sachets);
    }

    @Override
    public String toString() {
        return "MedicineAbs{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", dateExpiration='" + dateExpiration + '\'' +
                ", dateIntroduction='" + dateIntroduction + '\'' +
                ", description='" + description + '\'' +
                ", codeEan=" + codeEan +
                ", condition(packages)=" + getPackages() +
                ", condition(sachets)=" + getSachets() +
                ", condition(pills)=" + getPills() +
                '}';
    }
}
