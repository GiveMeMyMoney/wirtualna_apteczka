package model;

import dataBase.DBquery;
import dataBase.IDBquery;
import model.core.medicine.MedicineAbs;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Marcin on 2016-01-20.
 */

/**
 * 3 wzorzec: Fasada (Facade)
 */
public class Facade implements IDBquery {
    private static Logger logger = Logger.getLogger(Facade.class.getName());
    //ArrayList<Contact> contacts = new ArrayList<>();

    private static volatile Facade instance = null;
    private static DBquery dbQuery = null;

    public static Facade getInstance() {
        if (instance == null) {
            synchronized (Facade.class) {
                if (instance == null) {
                    logger.info("Tworze instancje KsiazkiTelefonicznej");
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

    ///INSERT to DB
    @Override
    public void insertMedicineToDB(MedicineAbs medicine) {
        if (medicine != null) {
            dbQuery.insertMedicineToDB(medicine);
        }
    }

    ///endregion









}
