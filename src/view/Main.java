package view;

import dataBase.DBconnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Facade;
import model.core.ambulance.Ambulance;
import model.core.hospital.EHospitalSize;
import model.core.hospital.Hospital;
import model.core.medicine.ECategory;
import model.core.medicine.MedicineAbs;
import model.core.medicine.MedicineFactory;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);
        /*Date dateNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, 14); // Adds 15 days
        Date date = c.getTime();

        System.out.println(ft.format(date));
        System.out.println(date.toString());
        MedicineAbs medicine = MedicineFactory.getMedicine(ECategory.PAINKILLER, "Ibuprofen", ft.format(date), ft.format(dateNow), "To je zaje bandaz", 1231231231);

        System.out.println(medicine.toString());

        Ambulance ambulance = new Ambulance(1, "KTT 60SM", "A4", "Audi");*/

        Facade model = Facade.getInstance();
        //model.insertAmbulanceToDB(ambulance);

        Hospital hospital = new Hospital(1, "Szpital œw. Kostka", EHospitalSize.BIG);

        System.out.println(hospital.toString());

        //model.insertHospitalToDB(hospital);
        model.setAmbulanceID(2);
        model.setType(ECategory.DRESSING);

        List<MedicineAbs> medicines = new ArrayList<>();
        medicines = model.selectAllMedicineFromDB();

        System.out.println(medicines.get(0).toString());
        JOptionPane.showMessageDialog(null, medicines.toString());





    }
}
