package dataBase;

import model.core.ambulance.Ambulance;
import model.core.medicine.ECategory;
import model.core.medicine.MedicineAbs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2016-01-21.
 */
public interface IDBquery {
    ///INSERT
    void insertMedicineToDB(MedicineAbs medicine);
    void insertAmbulanceToDB(Ambulance ambulance);

    ///SELECT
    List<MedicineAbs> selectAllMedicineFromDB(ECategory type, Integer ambulanceID);
    List<Ambulance> selectAllAmbulanceFromDB();

}
