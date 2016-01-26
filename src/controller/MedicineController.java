package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.Facade;
import model.core.condition.Condition;
import model.core.medicine.ECategory;
import model.core.medicine.MedicineAbs;
import model.core.medicine.MedicineFactory;

import javax.swing.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class MedicineController implements Initializable {
    private static Logger logger = Logger.getLogger(MedicineController.class.getName());
    private Facade model = Facade.getInstance();

    private boolean editFlag = false;

    List<MedicineAbs> medicines = new ArrayList<>();

    ///FXML variable region
    @FXML TableView<MedicineAbs> tv_medicine;
    @FXML TableColumn<MedicineAbs,String> col_name;
    @FXML TableColumn<MedicineAbs,Integer> col_EAN;
    @FXML TableColumn<MedicineAbs,String> col_date_introduction;
    @FXML TableColumn<MedicineAbs,String> col_date_expiration;
    @FXML TableColumn<MedicineAbs,Integer> col_packages;
    @FXML TableColumn<MedicineAbs,Integer> col_sachets;
    @FXML TableColumn<MedicineAbs,Integer> col_pills;

    @FXML ChoiceBox<ECategory> cb_type;
    @FXML TextField tf_name, tf_ean, tf_exp_date, tf_packages, tf_sachets, tf_pills;
    @FXML Button btn_update, btn_cancel, btn_delete;    //TODO btn_delete zrobiæ pewne "podswietlanie" gdy jest jakaœ opcja wybrana na tableView...
    //endregion


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("jestem w initialize!!!!");
        btn_update.setVisible(false);
        btn_cancel.setVisible(false);

        model.setAmbulanceID(1);    //TODO do wyodrebnienia!
        model.initAllMedicine();    //TODO do wyodrebnienia!

        model.setType(ECategory.DRESSING);

        initTableView();
        setMedicineArray();

        /**
         * Ladowanie do ChoiceBox
         */
        List<ECategory> listCategory = new ArrayList<>();
        listCategory = ECategory.getAllCategories();
        ObservableList obList = FXCollections.observableList(listCategory);
        cb_type.getItems().clear();
        cb_type.setItems(obList);
        cb_type.getSelectionModel().selectFirst();
    }

    //private method region
    private void initTableView() {
        tv_medicine.setEditable(true);
        //TODO dodac ID(najlepiej uzyc ORMa) i stworzyc modyfikacje dla nazwy.
        col_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        col_EAN.setCellValueFactory(new PropertyValueFactory<>("CodeEan"));
        col_date_introduction.setCellValueFactory(new PropertyValueFactory<>("DateIntroduction"));
        col_date_expiration.setCellValueFactory(new PropertyValueFactory<>("DateExpiration"));
        col_packages.setCellValueFactory(new PropertyValueFactory<>("Packages"));
        col_packages.setCellFactory(TextFieldTableCell.<MedicineAbs, Integer>forTableColumn(new IntegerStringConverter()));
        col_packages.setOnEditCommit(event -> {
            if (!Objects.equals(event.getOldValue(), event.getNewValue())) {
                int rowId = event.getTablePosition().getRow();  //pobranie ID wiersza ktory modifikujemy
                for (Node n : tv_medicine.lookupAll("TableRow")) {
                    if (n instanceof TableRow) {
                        TableRow row = (TableRow) n;
                        if (row.getIndex() == rowId) {
                            row.setStyle("-fx-background-color: blue; ");    //TODO jak zmienic kolor tylko tekstu do znalezienia

                                //TODO do uporzadkowania i przenisienia do metody private
                                Condition conditionToUpdate = tv_medicine.getSelectionModel().getSelectedItem().getCondition();
                                String name = tv_medicine.getSelectionModel().getSelectedItem().getName();
                                //zmien nie na trwalo liste w Facade ale nie update do BD:
                                model.updateTempMedicinesArray(name, conditionToUpdate);
                                //reload widoku:
                                setMedicineArray();
                                logger.info("Zamieniono tymczasowo");

                            //row.setStyle("-fx-text-inner-color: blue;");
                            break;
                        }
                    }
                }

                btn_update.setVisible(true);
            }
        });
        col_sachets.setCellValueFactory(new PropertyValueFactory<>("Sachets"));
        col_sachets.setCellFactory(TextFieldTableCell.<MedicineAbs, Integer>forTableColumn(new IntegerStringConverter()));
        col_sachets.setOnEditCommit(event -> {
            //col_packages.setStyle("-fx-background-color: blue");
            if (!Objects.equals(event.getOldValue(), event.getNewValue())) {
                int rowId = event.getTablePosition().getRow();  //pobranie ID wiersza ktory modifikujemy
                for (Node n: tv_medicine.lookupAll("TableRow")) {
                    if (n instanceof TableRow) {
                        TableRow row = (TableRow) n;
                        if (row.getIndex() == rowId) {
                            row.setStyle("-fx-background-color: blue; ");    //TODO jak zmienic kolor tylko tekstu do znalezienia
                            //row.setStyle("-fx-text-inner-color: blue;");
                                //TODO do uporzadkowania i przenisienia do metody private
                                Condition conditionToUpdate = tv_medicine.getSelectionModel().getSelectedItem().getCondition();
                                String name = tv_medicine.getSelectionModel().getSelectedItem().getName();
                                //zmien nie na trwalo liste w Facade ale nie update do BD:
                                model.updateTempMedicinesArray(name, conditionToUpdate);
                                //reload widoku:
                                setMedicineArray();
                                logger.info("Zamieniono tymczasowo");
                            break;
                        }
                    }
                }

                btn_update.setVisible(true);
            }
        });
        col_pills.setCellValueFactory(new PropertyValueFactory<>("Pills"));
        col_pills.setCellFactory(TextFieldTableCell.<MedicineAbs, Integer>forTableColumn(new IntegerStringConverter()));
        col_pills.setOnEditCommit(event -> {
            if (!Objects.equals(event.getOldValue(), event.getNewValue())) {
                int rowId = event.getTablePosition().getRow();  //pobranie ID wiersza ktory modifikujemy
                for (Node n : tv_medicine.lookupAll("TableRow")) {
                    if (n instanceof TableRow) {
                        TableRow row = (TableRow) n;
                        if (row.getIndex() == rowId) {
                            row.setStyle("-fx-background-color: blue; ");    //TODO jak zmienic kolor tylko tekstu do znalezienia
                            //row.setStyle("-fx-text-inner-color: blue;");
                                //TODO do uporzadkowania i przenisienia do metody private
                                Condition conditionToUpdate = tv_medicine.getSelectionModel().getSelectedItem().getCondition();
                                String name = tv_medicine.getSelectionModel().getSelectedItem().getName();
                                //zmien nie na trwalo liste w Facade ale nie update do BD:
                                model.updateTempMedicinesArray(name, conditionToUpdate);
                                //reload widoku:
                                setMedicineArray();
                                logger.info("Zamieniono tymczasowo");
                            break;
                        }
                    }
                }

                btn_update.setVisible(true);
            }
        });
    }

    private void setMedicineArray() {
        medicines = model.getAllMedicineByType();
        ObservableList<MedicineAbs> dataMedicine = FXCollections.observableArrayList(medicines);
        tv_medicine.setItems(dataMedicine);
    }

    private boolean validateIntegers(List<String> texts) {
        for (String text : texts) {
            if (!(text.matches("\\d*")))
                return true;
        }
        return false;
    }

    private void resetAddSettings() {
        cb_type.getSelectionModel().selectFirst();
        tf_name.clear(); tf_ean.clear(); tf_exp_date.clear(); tf_packages.clear(); tf_sachets.clear(); tf_pills.clear();
    }
    //endregion


    //FXML method region
    public void btnShowDressing() {
        model.setType(ECategory.DRESSING);
        setMedicineArray();
    }

    public void btnShowPainkiller() {
        model.setType(ECategory.PAINKILLER);
        setMedicineArray();
    }

    public void btnShowOthers() {
        model.setType(ECategory.OTHERS);
        setMedicineArray();
    }

    public void btnAddMedicine() {
        ArrayList<String> atributes = new ArrayList<>();
        if(validateIntegers(Arrays.asList(tf_packages.getText(), tf_sachets.getText(), tf_pills.getText()))) {
            JOptionPane.showMessageDialog(null, "Please set an Integer into column \"Packages\", \"Sachets\" and \"Pills\"");
            resetAddSettings();
            tf_packages.setPromptText("Only Integer!");
            tf_sachets.setPromptText("Only Integer!");
            tf_pills.setPromptText("Only Integer!");
            return;
        }
        atributes.add(tf_packages.getText());
        atributes.add(tf_sachets.getText());
        atributes.add(tf_pills.getText());
        atributes.add(tf_name.getText());
        atributes.add(tf_exp_date.getText());
        atributes.add(tf_ean.getText());
        ECategory category = cb_type.getValue();

        logger.info(atributes.toString());

        if(atributes.size() != 0) { //nie wypelnione ZADNE pole.
            for (String string : atributes) {   //sprawdzam czy wypelnione WSZYSTKIE pola
                if (string.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all sections");
                    return;
                }
            }
            Condition con = new Condition(Integer.valueOf(atributes.get(0)), Integer.valueOf(atributes.get(1)), Integer.valueOf(atributes.get(2)));
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            MedicineAbs medicine = MedicineFactory.getMedicine(category, atributes.get(3), atributes.get(4), ft.format(new Date()), Integer.valueOf(atributes.get(5)), con);
            logger.info("Medicine: " + medicine.toString());
            //DB
            model.setType(category);
            model.insertMedicineToDB(medicine);
            //VIEW
            setMedicineArray();
            //flag = cb_type.getValue(); //TODO
            //changeToAddByType(flag);

        } else {
            JOptionPane.showMessageDialog(null, "Please fill all sections");
            return;
        }

    }

    public void btnDeleteMedicine() {
        logger.info("proba buttona delete");
        final int selectedIdx = tv_medicine.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            MedicineAbs medicineToRemove = tv_medicine.getSelectionModel().getSelectedItem();
            final int newSelectedIdx = (selectedIdx == tv_medicine.getItems().size() - 1) ? selectedIdx - 1 : selectedIdx;

            //usun z BD:
            model.deleteMedicineFromDB(medicineToRemove);
            //reload widoku:
            setMedicineArray();
            logger.info("Pousuwane");
            tv_medicine.getSelectionModel().select(newSelectedIdx);
        }
    }


    //TODO dodac ID leku i ID stanu i updatowac tylko te ID ktore sie zmienily nie wszystkie...
    public void btnUpdate() {
        model.updateMedicinesToDB();
        btn_update.setVisible(false);
        btn_cancel.setVisible(false);
    }

    public void btnCancel() {
        model.initAllMedicine();    //w przyszlosci stworzyc zwykla kopie
        btn_update.setVisible(false);
        btn_cancel.setVisible(false);
    }
    //endregion

}


//przydatny kod do editable TableColumn:
// SETTING THE CELL FACTORY FOR THE RATINGS COLUMN
/*rating.setCellFactory(new Callback<TableColumn<Music,Integer>,TableCell<Music,Integer>>(){
@Override
public TableCell<Music, Integer> call(TableColumn<Music, Integer> param) {
        TableCell<Music, Integer> cell = new TableCell<Music, Integer>(){
@Override
public void updateItem(Integer item, boolean empty) {
        if(item!=null){

        ChoiceBox choice = new ChoiceBox(ratingSample);
        choice.getSelectionModel().select(ratingSample.indexOf(item));
        //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
        setGraphic(choice);
        }
        }
        };
        return cell;
        }
        });*/


