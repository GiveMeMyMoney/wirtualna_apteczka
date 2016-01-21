package model.core.medicine.types;

import model.core.medicine.ECategory;
import model.core.medicine.MedicineAbs;

/**
 * Created by Marcin on 2016-01-21.
 */
public class Painkiller extends MedicineAbs {
    public Painkiller(ECategory type, String name, String dateExpiration, String dateIntroduction, String description, int codeEan) {
        super(type, name, dateExpiration, dateIntroduction, description, codeEan);
    }

    public Painkiller(ECategory type, String name, String dateIntroduction, String dateExpiration, int codeEan) {
        super(type, name, dateIntroduction, dateExpiration, codeEan);
    }
}
