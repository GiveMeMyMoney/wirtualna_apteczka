package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import model.Facade;
import model.core.ambulance.Ambulance;

import java.util.List;

/**
 * Created by Marcin on 2016-01-29.
 */
public class Proba implements Runnable {
    Facade model = Facade.getInstance();
    ChoiceBox<Ambulance> cb_ambulance;

    public Proba(ChoiceBox<Ambulance> cb_ambulance) {
        this.cb_ambulance = cb_ambulance;
    }

    private void setAmbulanceCB() {
        List<Ambulance> listAmbulance;
        listAmbulance = model.getAllAmbulances();
        if (listAmbulance != null && listAmbulance.size() > 0) {
            ObservableList<Ambulance> obList2 = FXCollections.observableList(listAmbulance);
            cb_ambulance.getItems().clear();
            cb_ambulance.setItems(obList2);
            cb_ambulance.getSelectionModel().selectFirst();
        }
    }

    public void btnDeleteAmbulance() {
        //logger.info("proba buttona btnDeleteAmbulance");
        Ambulance ambulance = cb_ambulance.getSelectionModel().getSelectedItem();
        if (ambulance != null) {
            //cb_ambulance.getSelectionModel().selectPrevious();

            //usun z Modelu:
            model.deleteAmbulanceFromDB(ambulance);
            setAmbulanceCB();
            //obList2
            Integer ambulanceID = cb_ambulance.getSelectionModel().getSelectedItem().getAmbID();
            model.setAmbulanceID(ambulanceID);
            //model.setAmbulanceID(ambulanceID);
            model.initAllMedicine();

            //reload widoku:
            //setMedicineArray();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1500);
            btnDeleteAmbulance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
