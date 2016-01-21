package dataBase;

import model.core.medicine.MedicineAbs;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
     * @param //Ambulance
     */
    /*public void insertAmbulanceToDB(Ambulance ambulance) {
        logger.info("putAmbulanceToDB: " + ambulance.toString());
        try {
            prepStmt = conn.prepareStatement("insert into karetka values (null, ?, ?, ?, ?, ?);");
            //----//
            int i=0;
            prepStmt.setInt(++i, ambulance.getMarka());
            prepStmt.setString(++i, ambulance.getName());
            prepStmt.setString(++i, ambulance.getPrice());
            prepStmt.setString(++i, ambulance.getAvibility());
            prepStmt.setString(++i, ambulance.getAvibility());    //jak zdjecie?
            //----//
            prepStmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }*/

    /**
     * metoda sluzaca do ladowania Hospital do BD.
     * @param Hospital
     */
    /*public void insertHospitalToDB(Hospital hospital) {
        logger.info("putAmbulanceToDB: " + hospital.toString());
        try {
            prepStmt = conn.prepareStatement("insert into szpital values (null, ?, ?, ?);");
            //----//
            int i=0;
            prepStmt.setString(++i, hospital.getName());
            prepStmt.setInt(++i, hospital.getPrice());
            prepStmt.setInt(++i, hospital.getAvibility());
            //----//
            prepStmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }*/

    /**
     * metoda sluzaca do ladowania Medicine do BD.
     * @param
     */
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

}
