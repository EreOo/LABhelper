package ru.odis.address.view;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.converter.LocalDateTimeStringConverter;
import ru.odis.address.MainApp;
import ru.odis.address.model.Analyzer;
import ru.odis.address.util.DateUtil;

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
	   
	    @FXML
	    private TextField filterField ;
	    @FXML
	    private Label chengeTime;
	    
	    
	    //setter TableView
		public  TableView<Analyzer> getAnalyzerTable() {
			return analyzerTable;
		}
	    

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
	        
           //�������� ���������
	        materialNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().materialNameProperty());
	        
	        
	        //���-�� �������
	        countBoxColumn.setCellValueFactory(
	                cellData -> cellData.getValue().countBoxProperty());
	        
	        
	        //���� ��������
	        expColumn.setCellValueFactory(
	                cellData -> cellData.getValue().expProperty());
	        
	        
	        //��������� ������ �����      
	        //���� ����������� ��� ���� �������
	        analyzerTable.setRowFactory(tv -> new TableRow<Analyzer>() {
	            @Override
	            public void updateItem(Analyzer item, boolean empty) {
	                super.updateItem(item, empty) ;
	                if (item == null) {
	                    setStyle("");}
	                
	                else if (item.getExp().isBefore(LocalDate.now())) {
	                    setStyle("-fx-background-color: tomato;");}
	                
	                else if (item.getExp().isBefore(LocalDate.now().plus(Period.ofDays(30)))) {
	                    setStyle("-fx-background-color: orange;");} 
	                
	                else if (item.get�ountBox() <= 5) {
	                    setStyle("-fx-background-color: khaki;");}
	                  
	                else {
	                    setStyle("");
	                }
	            }
	        });
	        
	        
	        
	    
	        //������
	        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
	        FilteredList<Analyzer> filteredData = new FilteredList<>(MainApp.getData(), p -> true);

	        // 2. Set the filter Predicate whenever the filter changes.
	        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
	            filteredData.setPredicate(analyzer -> {
	                // If filter text is empty, display all persons.
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }

	                // Compare first name and last name of every person with filter text.
	                String lowerCaseFilter = newValue.toLowerCase();

	                if (analyzer.getAnalyzerName().toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // filter Analyzer name
	                } else if (analyzer.getMaterialName().toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter Material
	                }
	                return false; 
	            });
	        });

	        // 3. Wrap the FilteredList in a SortedList. 
	        SortedList<Analyzer> sortedData = new SortedList<>(filteredData);

	        // 4. Bind the SortedList comparator to the TableView comparator.
	        sortedData.comparatorProperty().bind(analyzerTable.comparatorProperty());

	        // 5. ���������� ���������� � �������.
	        analyzerTable.setItems(sortedData);
	        
	        
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
	            
	           
	          //  chengeTime.setText(analyzer.getChengeTime().toString());
            for(int i = 0; i < analyzer.getChengeTime().size(); i++){
       			chengeTime.setText((String) (analyzer.getChengeTime().get(i))  );
       			};
	             
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
	            chengeTime.setText("");
	        }
	    }
	    
	    /**
	     * �������� ����������� (������).
	     */
	    @FXML
	    private  void  deleteAnalyzer() {
	    	
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
	            	//MainApp.getData().remove(analyzerTable.getSelectionModel().getFocusedIndex());
	            	MainApp.getData().remove(analyzerTable.getSelectionModel().getSelectedItem());
	        	//analyzerTable.getItems().remove(analyzerTable.getSelectionModel());
	            	}
	            
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
	    
	    
	    @FXML
	    private void handleNew() {
	        mainApp.getPersonData().clear();
	        mainApp.setFilePath(null);
	    }
	    
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
	    
	    @FXML
	    private void handleSave() {
	        File personFile = mainApp.getFilePath();
	        if (personFile != null) {
	            mainApp.savePersonDataToFile(personFile);
	        } else {
	           {FileChooser fileChooser = new FileChooser();

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
	            }};
	        }
	    }

	    
	   

		
	    
	    
	    
	    }



