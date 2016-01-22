package model;

import model.core.ambulance.Ambulance;
import model.core.medicine.ECategory;
import model.core.medicine.DI.ISettingClient;
import model.core.medicine.MedicineAbs;
import dataBase.IDBquery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2016-01-22.
 */
public interface IFacade {
    /**
     * @see ISettingClient
     */
    void setType(ECategory type);
    void setAmbulanceID(Integer ambID);

    /**
     * @see IDBquery
     */
    ///INSERT
    void insertMedicineToDB(MedicineAbs medicine);
    void insertAmbulanceToDB(Ambulance ambulance);

    ///SELECT
    List<MedicineAbs> selectAllMedicineFromDB();
    List<Ambulance> selectAllAmbulanceFromDB();
}
