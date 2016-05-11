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
	        // Инициализация таблицы адресатов с двумя столбцами.
	        analyzerNameColumn.setCellValueFactory(cellData -> cellData.getValue().analyzerNameProperty());
	        materialNameColumn.setCellValueFactory(cellData -> cellData.getValue().materialNameProperty());
	    }

	    
	     //Вызывается главным приложением, которое даёт на себя ссылку.
	    
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	        // Добавление в таблицу данных из наблюдаемого списка
	        analyzerTable.setItems(mainApp.getPersonData());
	    }
	}

