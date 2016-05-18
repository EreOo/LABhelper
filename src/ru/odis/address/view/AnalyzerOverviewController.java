package ru.odis.address.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LocalDateTimeStringConverter;
import ru.odis.address.MainApp;
import ru.odis.address.model.Analyzer;
import ru.osdis.address.util.DateUtil;

public class AnalyzerOverviewController {
	
	    @FXML
	    private TableView<Analyzer> analyzerTable;
	    @FXML
	    private TableColumn<Analyzer, String> analyzerNameColumn;
	    @FXML
	    private TableColumn<Analyzer, String> materialNameColumn;
	    @FXML
	    private TableColumn<Analyzer, Number> countBoxColumn;
	    @FXML
	    private TableColumn<Analyzer, LocalDate> expColumn;
	    

	    @FXML
	    private Label analyzerNameLable;
	    @FXML
	    private Label materialNameLabel;
	    @FXML
	    private Label idMAterialLabel;
	    @FXML
	    private Label countBoxLabel;
	    @FXML
	    private Label countINTOBoxLabel;
	    @FXML
	    private Label expLabel;
	    @FXML
	    private Label addDateLabel;
	    @FXML
	    private Label typeMaterial ;

	    // ������ �� ������� ����������.
		private MainApp mainApp;

	    /**
	     * �����������.
	     * ����������� ���������� ������ ������ initialize().
	     */
	    public AnalyzerOverviewController() {
	    }

	    /**
	     * ������������� ������-�����������. ���������� �������������
	     * ����� ����, ��� fxml-���� ����� ��������.
	     */
	    
	    @FXML
	    private void initialize() {
	        
	    	// ������������� ������� 
	    	//����������
	        analyzerNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().analyzerNameProperty());
	        //��������
	        materialNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().materialNameProperty());
	        //���-�� �������
	        countBoxColumn.setCellValueFactory(
	                cellData -> cellData.getValue().countBoxProperty());
	        //���� ��������
	        expColumn.setCellValueFactory(
	                cellData -> cellData.getValue().expProperty());
	        
	        // ������� �������������� ���������� 
	        showAnalyzerDetails(null);

	        // ������� ��������� ������, � ��� ��������� ����������
	        // �������������� ����������
	        analyzerTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showAnalyzerDetails(newValue));
	    }

	    
	     //���������� ������� �����������, ������� ��� �� ���� ������.
	    
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	        // ���������� � ������� ������ �� ������������ ������
	        analyzerTable.setItems(mainApp.getPersonData());
	    }
	    
	    /**������� �������������� ����**/
	    private void showAnalyzerDetails(Analyzer analyzer) {
	        if (analyzer != null) {
	            // ��������� ����� ����������� �� ������� ����������.
	            analyzerNameLable.setText(analyzer.getAnalyzerName());
	            materialNameLabel.setText(analyzer.getMaterialName());
	            idMAterialLabel.setText(analyzer.getIdMAterial());
	            countBoxLabel.setText(Integer.toString(analyzer.get�ountBox()));
	            countINTOBoxLabel.setText(Integer.toString(analyzer.get�ountINTOBox()));
	            expLabel.setText(DateUtil.format(analyzer.getExp()));
	            addDateLabel.setText(DateUtil.format(analyzer.getDateAdd()));
	            typeMaterial.setText(analyzer.getTypeMaterial());
	             
	        } else {
	            // ���� analyzer = null, �� ������� ���� �����.
	        	analyzerNameLable.setText("");
	            materialNameLabel.setText("");
	            idMAterialLabel.setText("");
	            countBoxLabel.setText("");
	            countINTOBoxLabel.setText("");
	            expLabel.setText("");
	            addDateLabel.setText("");
	            typeMaterial.setText("");
	        }
	    }
	    
	    /**
	     * �������� ����������� (������).
	     */
	    @FXML
	    private void deleteAnalyzer() {
	    	
	    	int selectedIndex = analyzerTable.getSelectionModel().getSelectedIndex();
	    
	    	//����������, ����� �� ����� �������?
	        if (selectedIndex >= 0) {
	        	
	        	Alert alert = new Alert(AlertType.CONFIRMATION);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("��������");
	            alert.setHeaderText(null);
	            alert.setContentText("�� ��������, ��� ������ ������� ������?");
	           
	            //������
	            ButtonType yes = new ButtonType("��");
	            ButtonType no = new ButtonType("���");
	            
	            //��������� ������ � ���� �������
	            alert.getButtonTypes().setAll(yes, no);
	           
	            alert.showAndWait();
	            
	            //���� �� - �� �������
	            if(alert.getResult() == yes){
	        	analyzerTable.getItems().remove(selectedIndex);}
	            
	        } else {
	            // ������ �� �������.
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("������");
	            alert.setHeaderText("�� ������� ������!");
	            alert.setContentText("����������, �������� ������ ��� ��������.");

	            alert.showAndWait();
	    }
	        
	}
	    
	    
	    
	    //����� ������ � �������
	    @FXML
	    private void newAnalyzer() {
	     
	        boolean okClicked = mainApp.showAddDialog();
	       
	    }
	    
	    
	    //��������
	    @FXML
	    private void editAnalyzer() {
	    	
	    
	            
	            Analyzer selectedA = analyzerTable.getSelectionModel().getSelectedItem();
	            if (selectedA != null) {
	                boolean okClicked = mainApp.showEditDialog(selectedA);
	                
	            } else {
	                // ������ �� �������.
	                Alert alert = new Alert(AlertType.WARNING);
	                alert.initOwner(mainApp.getPrimaryStage());
	                alert.setTitle("������");
	                alert.setHeaderText("�� ������� ������");
	                alert.setContentText("�������� ������ ��� ��������� � �������.");

	                alert.showAndWait();
	            }
	     
	       
	       
	    }
	    
	    }



