package dataBase;

import model.core.ambulance.Ambulance;
import model.core.medicine.MedicineAbs;

import java.util.List;

/**
 * Created by Marcin on 2016-01-21.
 */
public interface IDBquery {
    ///INSERT
    boolean insertMedicineToDB(MedicineAbs medicine, Integer ambulanceID);
    void insertAmbulanceToDB(Ambulance ambulance);

    ///SELECT
    List<MedicineAbs> selectAllMedicineFromDB(Integer ambulanceID);
    List<Ambulance> selectAllAmbulanceFromDB();

    ///DELETE
    boolean deleteMedicineFromDB(MedicineAbs medicine);


}
