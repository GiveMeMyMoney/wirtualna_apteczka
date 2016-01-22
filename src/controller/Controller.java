package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Facade;
import model.core.condition.Condition;
import model.core.medicine.ECategory;
import model.core.medicine.MedicineAbs;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Controller implements Initializable {
    private static Logger logger = Logger.getLogger(Controller.class.getName());
    private Facade model = Facade.getInstance();

    List<MedicineAbs> medicines = new ArrayList<>();
    List<Condition> conditions = new ArrayList<>();



    ///FXML variable region
    /*@FXML
    private TextField etIndex, etField, etName, etSpeciality, etFaculty,
            etLastname, etYears, etCity, etUniversity, etAddress;*/
    @FXML private TableView<MedicineAbs> tv_medicine;
    @FXML TableColumn<MedicineAbs,String> col_name;
    @FXML TableColumn<MedicineAbs,Integer> col_EAN;
    @FXML TableColumn<MedicineAbs,String> col_date_introduction;
    @FXML TableColumn<MedicineAbs,String> col_date_expiration;

    @FXML private TableView<Condition> tv_condition;
    @FXML TableColumn<Condition,Integer> col_packages;
    @FXML TableColumn<Condition,Integer> col_sachets;
    @FXML TableColumn<Condition,Integer> col_pills;
    //endregion


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model.setAmbulanceID(1);
        model.setType(ECategory.DRESSING);

        col_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        col_EAN.setCellValueFactory(new PropertyValueFactory<>("CodeEan"));
        col_date_introduction.setCellValueFactory(new PropertyValueFactory<>("DateIntroduction"));
        col_date_expiration.setCellValueFactory(new PropertyValueFactory<>("DateExpiration"));

        col_packages.setCellValueFactory(new PropertyValueFactory<>("Packages"));
        col_sachets.setCellValueFactory(new PropertyValueFactory<>("Sachets"));
        col_pills.setCellValueFactory(new PropertyValueFactory<>("Pills"));
        setMedicineArray();






    }

    //private method region
    private void setMedicineArray() {
        medicines = model.selectAllMedicineFromDB();
        ObservableList<MedicineAbs> dataMedicine = FXCollections.observableArrayList(medicines);
        tv_medicine.setItems(dataMedicine);

        for (MedicineAbs medicine : medicines) {
            conditions.add(medicine.getCondition());
        }
        ObservableList<Condition> dataCondition = FXCollections.observableArrayList(conditions);
        tv_condition.setItems(dataCondition);
    }



    //endregion


    //FXML method region
    public void btnShowDressing() {
        JOptionPane.showMessageDialog(null, "hello BITCHES!");
    }

    public void btnShowPainkiller() {
    }

    public void btnShowOthers() {
    }



    //endregion
}
