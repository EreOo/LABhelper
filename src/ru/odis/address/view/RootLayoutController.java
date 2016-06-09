package ru.odis.address.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import ru.odis.address.MainApp;
import ru.odis.address.model.Analyzer;


/**
 * ���������� ��� ��������� ������. �������� ����� ������������� �������
 * ����� ����������, ���������� ������ ���� � �����, ��� ����� ���������
 * ��������� �������� JavaFX.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController {

    // ������ �� ������� ����������
    private MainApp mainApp;

    /**
     * ���������� ������� �����������, ����� �������� ������ �� ������ ����.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * ������ ������ �������� �����.
     */
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setFilePath(null);
    }

    /**
     * ��������� FileChooser, ����� ������������ ���� �����������
     * ������� �������� ����� ��� ��������.
     */
    @FXML
    private void handleOpen() {
    	 
        FileChooser fileChooser = new FileChooser();

        // ����� ������ ����������
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // ���������� ������ �������� �����
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        mainApp.getPersonData().clear();
        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /**
     * ��������� ���� � ���� ���������, ������� � ��������� ����� ������.
     * ���� ���� �� ������, �� ������������ ������ "save as".
     */
    @FXML
    private void handleSave() {
        File personFile = mainApp.getFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * ��������� FileChooser, ����� ������������ ���� �����������
     * ������� ����, ���� ����� ��������� ������
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // ����� ������ ����������
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // ���������� ������ ���������� �����
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }

    
    @FXML
    private void handleAbout() {
    	
    	
    	
        Alert alert = new Alert(AlertType.INFORMATION);
       alert.setTitle("LABHelper");
    	alert.initOwner(mainApp.getPrimaryStage());
        alert.setHeaderText("�������, ��� ���������� � ����������� LAB Helper.");
       alert.setContentText("LAB Helper - ��� open source ������ ��� ����������� ������������ ����� � �����������. \n\n"
       		+ "���� � ��� �������� ������� - ���������� � �����������, ������� ��������� � �������� ����� ���������.\n\n"
       		+ "��� �� �� ������ ��������� � ������������� �� ��. ����� LabHelperSupport@gmail.com \n\n\n"
       		+ "by Vladimir Shekhavtsov 2016.");
      

        alert.showAndWait();
    }

    
    @FXML
    private void handleExit() {
    	handleSave();
        System.exit(0);
    }
    
  //����� ������ � �������
    @FXML
    private void newAnalyzer() {
     
        boolean okClicked = mainApp.showAddDialog();
       
    }
    
    
  
    
    
    
}