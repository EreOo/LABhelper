package ru.odis.address.view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ru.odis.address.MainApp;
import ru.odis.address.model.Analyzer;

public class AddDialogController {
	

	@FXML
    private TextField analyzerName;
    @FXML
    private TextField materialName;
    @FXML
    private TextField idMAterial;
    @FXML
    private  TextField countBox;
    @FXML
    private TextField countINTOBox;
    @FXML
    private DatePicker exp;
    @FXML
    private DatePicker  addDate;
    @FXML
    private ChoiceBox<String> typeMaterial;
    
    //������ ��� ���������
    private  ArrayList<String> type = new ArrayList<>();
    
  
    
    
    @FXML
    Button add;
    @FXML
    Button close;


    private Stage dialogStage;
    private Analyzer analyzer;
    private boolean okClicked = false;
    
    
    
    

    /**
     * �������������� �����-����������. ���� ����� ���������� �������������
     * ����� ����, ��� fxml-���� ����� ��������.
     */
    @FXML
    private void initialize() {
    }

    /**
     * ������������� ����� ��� ����� ����.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
    	type.add("���� �������");
    	type.add("��������");
    	type.add("������");
    	typeMaterial.setItems(FXCollections.observableArrayList(type));
    	
        this.dialogStage = dialogStage;
        
    }

  
    
    /**
     * M���� ��������� ����� ������
     */
    @FXML
    public void newAnalyzer() {
    	
    	
    	//�������� ���������� ������
        if(analyzerName.getText().equals("") ||
        		materialName.getText().equals("") ||
        		countBox.getText().equals("") || 
        				exp.getValue() == null ||
        				typeMaterial.getValue() == null)
        {
        	Alert alert = new Alert(AlertType.WARNING);
        	
        	alert.setTitle("������");
            alert.setHeaderText(null);
            alert.setContentText("����������, ��������� ��������� ��� ���� � ������� ����������.");
            alert.showAndWait();
        }
        
        
       	
        else
        {
        	//�������� �� ������ � ������� ����������, ������ ���� ����� ����� ����� ������.
        	try{
    	MainApp.getData().add(new Analyzer(analyzerName.getText(),
    			typeMaterial.getValue(),
    			        materialName.getText(),
    			             idMAterial.getText(),
    			                  Integer.parseInt(countBox.getText()),
    			                      Integer.parseInt(countINTOBox.getText()),
    			                         exp.getValue(),
    			                            addDate.getValue()));
    	 
    	okClicked = true;
        dialogStage.close();
        
         }catch(NumberFormatException ex){
        	 //�� �����!
        	 Alert alert = new Alert(AlertType.WARNING);
         	 alert.setTitle("������");
             alert.setHeaderText(null);
             alert.setContentText("� ������ \"����������\" ������� ������������ �������! ");
             alert.showAndWait();
         }
        }
        	
    }

    

    /**
     * ����������, ����� ������������ ������� �� ������ Cancel.
     */
    @FXML
    private void cancel() {
        dialogStage.close();
    }

	public boolean isOkClicked() {
		
		return okClicked;
	}
    
    
    
    


	
    
    
    

    

}
