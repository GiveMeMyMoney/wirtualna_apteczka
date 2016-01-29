package model.core.medicine.types;

import model.core.condition.Condition;
import model.core.medicine.ECategory;
import model.core.medicine.MedicineAbs;

/**
 * Created by Marcin on 2016-01-20.
 */
public class Dressing extends MedicineAbs {
    public Dressing(Integer medID, ECategory type, String name, String dateIntroduction, String dateExpiration, int codeEan, Condition condition) {
        super(medID, type, name, dateIntroduction, dateExpiration, codeEan, condition);
    }

    public Dressing(ECategory type, String name, String dateIntroduction, String dateExpiration, int codeEan, Condition condition) {
        super(type, name, dateIntroduction, dateExpiration, codeEan, condition);
    }
}
