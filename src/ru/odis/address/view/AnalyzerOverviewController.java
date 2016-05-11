package ru.odis.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.odis.address.MainApp;
import ru.odis.address.model.Analyzer;

public class AnalyzerOverviewController {
	
	    @FXML
	    private TableView<Analyzer> analyzerTable;
	    @FXML
	    private TableColumn<Analyzer, String> analyzerNameColumn;
	    @FXML
	    private TableColumn<Analyzer, String> materialNameColumn;
	    

	    @FXML
	    private Label firstNameLabel;
	    @FXML
	    private Label lastNameLabel;
	    @FXML
	    private Label streetLabel;
	    @FXML
	    private Label postalCodeLabel;
	    @FXML
	    private Label cityLabel;
	    @FXML
	    private Label birthdayLabel;

	    // ������ �� ������� ����������.
	  
		private MainApp mainApp;

	    /**
	     * �����������.
	     * ����������� ���������� ������ ������ initialize().
	     */
	    public AnalyzerOverviewController() {
	    }

	    /**
	     * ������������� ������-�����������. ���� ����� ���������� �������������
	     * ����� ����, ��� fxml-���� ����� ��������.
	     */
	    
	    @FXML
	    private void initialize() {
	        // ������������� ������� ��������� � ����� ���������.
	        analyzerNameColumn.setCellValueFactory(cellData -> cellData.getValue().analyzerNameProperty());
	        materialNameColumn.setCellValueFactory(cellData -> cellData.getValue().materialNameProperty());
	    }

	    
	     //���������� ������� �����������, ������� ��� �� ���� ������.
	    
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	        // ���������� � ������� ������ �� ������������ ������
	        analyzerTable.setItems(mainApp.getPersonData());
	    }
	}

