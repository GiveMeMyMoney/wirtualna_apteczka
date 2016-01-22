package model.core.hospital;

/**
 * Created by Marcin on 2016-01-22.
 */
public enum EHospitalSize {
    SMALL (1),
    MEDIUM (2),
    BIG (3),
    HUGE (4);

    private int nr;
    EHospitalSize(int nr) {
        this.nr = nr;
    }

    public int getNr() { return nr; }
}
