package model.core.condition;

/**
 * Created by Marcin on 2016-01-22.
 */
public class Condition {
    private boolean EMPTY;
    int packages, sachets, pills;

    public Condition(int packages, int sachets, int pills) {
        this.packages = packages;
        this.sachets = sachets;
        this.pills = pills;
        checkIfEmpty();
    }

    void checkIfEmpty() {
        EMPTY = (packages == 0 && sachets == 0 && pills == 0);
    }

    //GETTERs:
    public int getPackages() {
        return packages;
    }
    public boolean isEMPTY() {
        return EMPTY;
    }
    public int getSachets() {
        return sachets;
    }
    public int getPills() {
        return pills;
    }

    //SETTERs:
    public void setPackages(int packages) {
        this.packages = packages;
        checkIfEmpty();
    }
    public void setPills(int pills) {
        this.pills = pills;
        checkIfEmpty();
    }
    public void setSachets(int sachets) {
        this.sachets = sachets;
        checkIfEmpty();
    }


}
