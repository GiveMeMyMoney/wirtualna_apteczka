package dataBase;

import model.core.ambulance.Ambulance;
import model.core.medicine.MedicineAbs;

import java.util.List;

/**
 * Created by Marcin on 2016-01-21.
 */
public interface IDBquery {
    ///INSERT
    void insertMedicineToDB(MedicineAbs medicine);
    void insertAmbulanceToDB(Ambulance ambulance);

    ///SELECT
    List<MedicineAbs> selectAllMedicineFromDB(Integer ambulanceID);
    List<Ambulance> selectAllAmbulanceFromDB();

}
