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

	    // Ссылка на главное приложение.
		private MainApp mainApp;

	    /**
	     * Конструктор.
	     * Конструктор вызывается раньше метода initialize().
	     */
	    public AnalyzerOverviewController() {
	    }

	    /**
	     * Инициализация класса-контроллера. вызывается автоматически
	     * после того, как fxml-файл будет загружен.
	     */
	    
	    @FXML
	    private void initialize() {
	        
	    	// Инициализация таблицы 
	    	//анализатор
	        analyzerNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().analyzerNameProperty());
	        //расходка
	        materialNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().materialNameProperty());
	        //кол-во коробок
	        countBoxColumn.setCellValueFactory(
	                cellData -> cellData.getValue().countBoxProperty());
	        //срок годности
	        expColumn.setCellValueFactory(
	                cellData -> cellData.getValue().expProperty());
	        
	        // Очистка дополнительной информации 
	        showAnalyzerDetails(null);

	        // Слушаем изменения выбора, и при изменении отображаем
	        // дополнительную информацию
	        analyzerTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showAnalyzerDetails(newValue));
	    }

	    
	     //Вызывается главным приложением, которое даёт на себя ссылку.
	    
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	        // Добавление в таблицу данных из наблюдаемого списка
	        analyzerTable.setItems(mainApp.getPersonData());
	    }
	    
	    /**выводим дополнительное инфо**/
	    private void showAnalyzerDetails(Analyzer analyzer) {
	        if (analyzer != null) {
	            // Заполняем метки информацией из объекта анализатор.
	            analyzerNameLable.setText(analyzer.getAnalyzerName());
	            materialNameLabel.setText(analyzer.getMaterialName());
	            idMAterialLabel.setText(analyzer.getIdMAterial());
	            countBoxLabel.setText(Integer.toString(analyzer.getСountBox()));
	            countINTOBoxLabel.setText(Integer.toString(analyzer.getСountINTOBox()));
	            expLabel.setText(DateUtil.format(analyzer.getExp()));
	            addDateLabel.setText(DateUtil.format(analyzer.getDateAdd()));
	            typeMaterial.setText(analyzer.getTypeMaterial());
	             
	        } else {
	            // Если analyzer = null, то убираем весь текст.
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
	     * Удаление анализатора (записи).
	     */
	    @FXML
	    private void deleteAnalyzer() {
	    	
	    	int selectedIndex = analyzerTable.getSelectionModel().getSelectedIndex();
	    
	    	//спришивает, точно ли хотят удалить?
	        if (selectedIndex >= 0) {
	        	
	        	Alert alert = new Alert(AlertType.CONFIRMATION);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("Внимание");
	            alert.setHeaderText(null);
	            alert.setContentText("Вы уверенны, что хотите удалить данные?");
	           
	            //кнопки
	            ButtonType yes = new ButtonType("Да");
	            ButtonType no = new ButtonType("Нет");
	            
	            //добавляем кнопки в окно диалога
	            alert.getButtonTypes().setAll(yes, no);
	           
	            alert.showAndWait();
	            
	            //если да - то удаляем
	            if(alert.getResult() == yes){
	        	analyzerTable.getItems().remove(selectedIndex);}
	            
	        } else {
	            // Ничего не выбрано.
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("Ошибка");
	            alert.setHeaderText("Не выбрана запись!");
	            alert.setContentText("Пожалуйста, выберите данные для удаления.");

	            alert.showAndWait();
	    }
	        
	}
	    
	    
	    
	    //новая запись в таблицу
	    @FXML
	    private void newAnalyzer() {
	     
	        boolean okClicked = mainApp.showAddDialog();
	       
	    }
	    
	    
	    //редактор
	    @FXML
	    private void editAnalyzer() {
	    	
	    
	            
	            Analyzer selectedA = analyzerTable.getSelectionModel().getSelectedItem();
	            if (selectedA != null) {
	                boolean okClicked = mainApp.showEditDialog(selectedA);
	                
	            } else {
	                // Ничего не выбрано.
	                Alert alert = new Alert(AlertType.WARNING);
	                alert.initOwner(mainApp.getPrimaryStage());
	                alert.setTitle("Ошибка");
	                alert.setHeaderText("Не выбрана запись");
	                alert.setContentText("Выберите данные для изминения в таблице.");

	                alert.showAndWait();
	            }
	     
	       
	       
	    }
	    
	    }



