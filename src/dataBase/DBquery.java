package dataBase;

import model.core.ambulance.Ambulance;
import model.core.condition.Condition;
import model.core.medicine.ECategory;
import model.core.medicine.MedicineAbs;
import model.core.medicine.MedicineFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Marcin on 2016-01-21.
 */
public class DBquery implements IDBquery {
    private static Logger logger = Logger.getLogger(DBquery.class.getName());

    private Connection connection;
    private PreparedStatement prepStmt;

    private static volatile DBquery instance = null;

    public static DBquery getInstance() throws SQLException {
        if (instance == null) {
            synchronized (DBquery.class) {
                if (instance == null) {
                    logger.info("Tworze instancje DBquery");
                    instance = new DBquery();
                }
            }
        }
        return instance;
    }

    public DBquery() {
        try {
            connection = DBconnection.getInstance();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    ///INSERT methods:
    /**
     * metoda sluzaca do ladowania Ambulance do BD.
     * @param ambulance
     * @see Ambulance
     */
    @Override
    public void insertAmbulanceToDB(Ambulance ambulance) {
        logger.info("insertAmbulanceToDB: " + ambulance.toString());
        try {
            prepStmt = connection.prepareStatement("insert into karetka values (null, ?, ?, ?);");
            //----//
            int i=0;
            prepStmt.setString(++i, ambulance.getRegistration());
            prepStmt.setString(++i, ambulance.getMark());
            prepStmt.setString(++i, ambulance.getModel());
            //----//
            prepStmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    /**
     * metoda sluzaca do ladowania Medicine do BD.
     * @param medicine - lek
     * @see MedicineAbs
     */
    @Override
    public void insertMedicineToDB(MedicineAbs medicine) {
        logger.info("insertMedicineToDB: " + medicine.toString());
        try {
            prepStmt = connection.prepareStatement("insert into lek values (null, ?, ?, ?, ?, ?, ?);");
            //----//
            int id = medicine.getType().getId();
            int i=0;
            prepStmt.setInt(++i, id);
            prepStmt.setString(++i, medicine.getName());
            prepStmt.setInt(++i, medicine.getCodeEan());
            prepStmt.setString(++i, medicine.getDateExpiration());
            prepStmt.setString(++i, medicine.getDateIntroduction());
            prepStmt.setString(++i, medicine.getDescription());
            //----//
            prepStmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }
    ///endregion

    ///SELECT methods:
    /**
     * Metoda zwracajaca wszystkie leki z danej kategorii i o danym ID ambulansu (TRANZAKCJA)
     * @param ambulanceID
     * @return ArrayList<MedicineAbs>
     */
    @Override
    public List<MedicineAbs> selectAllMedicineFromDB(Integer ambulanceID) {
        logger.info("Ambulance ID: " + ambulanceID);
        ResultSet resultAmbulanceMedicine = null, resultCondition = null, resultMedicine = null;
        List<MedicineAbs> medicineByType = new ArrayList<>();
        PreparedStatement selectAmbulanceMedicine = null, selectCondition = null, selectMedicine = null;
        try {
            //najpierw pobieram do result wszystkie rekordy o zadanym ID ambulance:
            //TODO 1 sprawdzenie czy w ogole dziala bez podzialu na kategorie
            //TODO moze pobrac wszystkie nie zaleznie od kategori do listy a nastepnie po kliknieciu w btn
            //TODO getByCategory.......
            selectAmbulanceMedicine = connection.prepareStatement("SELECT kl_lek_id, kl_stan_id FROM karetka_lek WHERE kl_kar_id = ?");
            selectAmbulanceMedicine.setInt(1, ambulanceID);
            resultAmbulanceMedicine = selectAmbulanceMedicine.executeQuery();
            while (resultAmbulanceMedicine.next()) {
                //Condition
                selectCondition = connection.prepareStatement("SELECT * FROM stan WHERE stan_id = ?");
                selectCondition.setInt(1, resultAmbulanceMedicine.getInt("kl_stan_id"));
                resultCondition = selectCondition.executeQuery();
                Condition condition = new Condition(resultCondition.getInt("stan_opak"), resultCondition.getInt("stan_saszetki"), resultCondition.getInt("stan_tabletki"));

                //Medicine
                selectMedicine = connection.prepareStatement("SELECT * FROM lek WHERE lek_id = ?");
                selectMedicine.setInt(1, resultAmbulanceMedicine.getInt("kl_lek_id"));
                resultMedicine = selectMedicine.executeQuery();
                MedicineAbs medicine = MedicineFactory.getMedicine(ECategory.get(resultMedicine.getInt("lek_kt_id")), resultMedicine.getString("lek_nazwa"), resultMedicine.getString("lek_data_waznosci"),
                        resultMedicine.getString("lek_data_wprow"), resultMedicine.getString("lek_opis"), resultMedicine.getInt("lek_kod_ean"), condition);
                if (medicine != null) {
                    logger.info("Medicine: " + medicine.toString());
                    medicineByType.add(medicine);
                } else {
                    logger.info("Err Medicine == null");
                }
            }
            return medicineByType;
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ambulance> selectAllAmbulanceFromDB() {
        logger.info("selectAllAmbulanceFromDB()");
        ResultSet result = null;
        List<Ambulance> ambulances = new ArrayList<>();
        try {
            prepStmt = connection.prepareStatement("SELECT * FROM karetka ORDER BY kar_rejestracja");
            result = prepStmt.executeQuery();
            //pozniej za pomoca Factory tworze konkretny typ(nie musze wiedziec jaki) pojazdu i dopisuje do ArrayListy
            while (result.next()) {
                Ambulance ambulance = new Ambulance(result.getString("kar_rejestracja"), result.getString("kar_marka"), result.getString("kar_model"));
                if (ambulance != null) {
                    logger.info("Vehicle: " + ambulance.toString());
                    ambulances.add(ambulance);
                } else {
                    logger.info("Err Vehicle = null");
                }
            }
            return ambulances;
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
            return null;
        }
    }

    ///endregion

}
