package model.core.medicine.types;

import model.core.condition.Condition;
import model.core.medicine.ECategory;
import model.core.medicine.MedicineAbs;

/**
 * Created by Marcin on 2016-01-21.
 */
public class Other extends MedicineAbs {
    public Other(ECategory type, String name, String dateExpiration, String dateIntroduction, String description, int codeEan, Condition condition) {
        super(type, name, dateExpiration, dateIntroduction, description, codeEan, condition);
    }

    public Other(ECategory type, String name, String dateIntroduction, String dateExpiration, int codeEan, Condition condition) {
        super(type, name, dateIntroduction, dateExpiration, codeEan, condition);
    }
}
