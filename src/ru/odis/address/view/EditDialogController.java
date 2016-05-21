package ru.odis.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ru.odis.address.model.Analyzer;
import ru.odis.address.util.DateUtil;

public class EditDialogController {

	@FXML
    private Label analyzerLabel;
	@FXML
    private Label nameLabel;
	@FXML
    private Label countLabel;
	@FXML
    private Label dataLabel;
	@FXML
    private Button minus;
	@FXML
    private Button plus;
	@FXML
    private Button ok;
	@FXML
    private Button no;


    private Stage dialogStage;
    private Analyzer analyzer;
    private boolean okClicked = false;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Устанавливает сцену для этого окна.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Выводим общую инфу, что будем менять
     * 
     * @param person
     */
    public void setAnalyzerE(Analyzer analyzer) {
        this.analyzer = analyzer;

       analyzerLabel.setText(analyzer.getAnalyzerName());
       nameLabel.setText(analyzer.getMaterialName());
       countLabel.setText(Integer.toString(analyzer.getСountBox()));
       dataLabel.setText(DateUtil.format(analyzer.getExp()));
       
        
    }

    /**
     * Returns true, если пользователь кликнул OK, в другом случае false.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    @FXML
    private void minusB() {
        
    	  int i =  Integer.parseInt(countLabel.getText()) - 1;
    	  if(i>= 0){
      	    countLabel.setText(Integer.toString(i));
      	  }
        
    }
    
    @FXML
    private void plusB() {
        
    	  int i =  Integer.parseInt(countLabel.getText()) + 1;
    	 
    	    countLabel.setText(Integer.toString(i));
    	  
    	  
    		  
    	  
        
    }
    
    

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void okButton() {
        
    	    analyzer.setСountBox(Integer.parseInt(countLabel.getText()));
           
            okClicked = true;
            dialogStage.close();
        
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void noButton() {
        dialogStage.close();
    }

    
   
    }


