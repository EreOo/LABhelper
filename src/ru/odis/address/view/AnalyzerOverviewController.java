package ru.odis.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

	    // Ссылка на главное приложение.
		private MainApp mainApp;

	    /**
	     * Конструктор.
	     * Конструктор вызывается раньше метода initialize().
	     */
	    public AnalyzerOverviewController() {
	    }

	    /**
	     * Инициализация класса-контроллера. Этот метод вызывается автоматически
	     * после того, как fxml-файл будет загружен.
	     */
	    
	    @FXML
	    private void initialize() {
	        
	    	// Инициализация таблицы адресатов 
	        analyzerNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().analyzerNameProperty());
	        materialNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().materialNameProperty());

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
	             
	        } else {
	            // Если analyzer = null, то убираем весь текст.
	        	analyzerNameLable.setText("");
	            materialNameLabel.setText("");
	            idMAterialLabel.setText("");
	            countBoxLabel.setText("");
	            countINTOBoxLabel.setText("");
	            expLabel.setText("");
	            addDateLabel.setText("");
	        }
	    }
	}

