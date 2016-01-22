package model.core.ambulance;

/**
 * Created by Marcin on 2016-01-20.
 */
public class Ambulance {
    String registration, mark, model;

    public Ambulance(String registration, String model, String mark) {
        this.registration = registration;
        this.model = model;
        this.mark = mark;
    }

    //METHODs:

    //GETTERs:
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
                ", registration='" + registration + '\'' +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
