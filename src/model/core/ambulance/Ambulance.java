package model.core.ambulance;

/**
 * Created by Marcin on 2016-01-20.
 */
public class Ambulance {
    int hospitalID;
    String registration, mark, model;

    public Ambulance(int hospitalID, String registration, String model, String mark) {
        this.hospitalID = hospitalID;
        this.registration = registration;
        this.model = model;
        this.mark = mark;
    }

    //METHODs:

    //GETTERs:
    public int getHospitalID() {
        return hospitalID;
    }
    public String getRegistration() {
        return registration;
    }
    public String getMark() {
        return mark;
    }
    public String getModel() {
        return model;
    }

    //SETTERs:
    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }
    public void setRegistration(String registration) {
        this.registration = registration;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Ambulance{" +
                "hospitalID=" + hospitalID +
                ", registration='" + registration + '\'' +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
