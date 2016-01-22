package model;

import dataBase.DBquery;
import dataBase.IDBquery;
import model.core.ambulance.Ambulance;
import model.core.medicine.DI.SettingClient;
import model.core.medicine.DI.ISettingClient;
import model.core.medicine.ECategory;
import model.core.medicine.MedicineAbs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Marcin on 2016-01-20.
 */

/**
 * 3 wzorzec: Fasada (Facade)
 */
public class Facade implements IFacade {
    private static Logger logger = Logger.getLogger(Facade.class.getName());
    //ArrayList<Contact> contacts = new ArrayList<>();

    private static volatile Facade instance = null;
    private static DBquery dbQuery = null;
    private ISettingClient settings = new SettingClient();

    public static Facade getInstance() {
        if (instance == null) {
            synchronized (Facade.class) {
                if (instance == null) {
                    logger.info("Tworze instancje Facade");
                    instance = new Facade();
                    try {
                        dbQuery = DBquery.getInstance();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    private Facade() {
    }

    ///DI
    @Override
    public void setAmbulanceID(Integer ambID) {
        settings.setAmbulanceID(ambID);
    }

    @Override
    public void setType(ECategory type) {
        settings.setType(type);
    }

    ///endregion


    ///INSERT to DB
    @Override
    public void insertMedicineToDB(MedicineAbs medicine) {
        if (medicine != null) {
            dbQuery.insertMedicineToDB(medicine);
        }
    }

    @Override
    public void insertAmbulanceToDB(Ambulance ambulance) {
        if (ambulance != null) {
            dbQuery.insertAmbulanceToDB(ambulance);
        }
    }
    ///endregion

    ///SELECT from DB

    @Override
    public List<MedicineAbs> selectAllMedicineFromDB() {
        return dbQuery.selectAllMedicineFromDB(settings.getType(), settings.getAmbulanceID());
    }

    @Override
    public List<Ambulance> selectAllAmbulanceFromDB() {
        return dbQuery.selectAllAmbulanceFromDB();
    }

    public List<ECategory> getAllCategories() {
        return ECategory.getAllCategories();
    }



    ///endregion










}
