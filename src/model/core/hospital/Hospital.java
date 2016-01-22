package model.core.hospital;

/**
 * Created by Marcin on 2016-01-22.
 */
public class Hospital {
    String name;
    EHospitalSize size;
    Resort resort;


    public Hospital(String name, EHospitalSize size, Resort resort) {
        this.name = name;
        this.size = size;
        this.resort = resort;
    }

    //METHODs:

    //GETTERs:
    public EHospitalSize getSize() {
        return size;
    }
    public String getName() {
        return name;
    }
    public Resort getResort() {
        return resort;
    }

    //SETTERs:
    public void setSize(EHospitalSize size) {
        this.size = size;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setResort(Resort resort) {
        this.resort = resort;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", resort=" + resort.toString() +
                '}';
    }
}
