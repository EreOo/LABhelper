package ru.odis.address.view;

import java.time.LocalDate;
import java.time.Month;



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
	        
           //название материала
	        materialNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().materialNameProperty());
	        
	        
	        //кол-во коробок
	        countBoxColumn.setCellValueFactory(
	                cellData -> cellData.getValue().countBoxProperty());
	        
	        
	        //срок годности
	        expColumn.setCellValueFactory(
	                cellData -> cellData.getValue().expProperty());
	        
	        
	        //Выделение цветом строк      
	        //если просроченно или мало коробок
	        analyzerTable.setRowFactory(tv -> new TableRow<Analyzer>() {
	            @Override
	            public void updateItem(Analyzer item, boolean empty) {
	                super.updateItem(item, empty) ;
	                if (item == null) {
	                    setStyle("");
	                } else if (item.getExp().isBefore(LocalDate.now())) {
	                    setStyle("-fx-background-color: tomato;");
	                } else if (item.getСountBox() <= 5) {
	                    setStyle("-fx-background-color: yellow;");
	                } else {
	                    setStyle("");
	                }
	            }
	        });
	        
	        
	        
	    
	        //ФИЛЬТР
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

	        // 5. Добавление информации в таблицу.
	        analyzerTable.setItems(sortedData);
	        
	        
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
	    private  void  deleteAnalyzer() {
	    	
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
	            	//MainApp.getData().remove(analyzerTable.getSelectionModel().getFocusedIndex());
	            	MainApp.getData().remove(analyzerTable.getSelectionModel().getSelectedItem());
	        	//analyzerTable.getItems().remove(analyzerTable.getSelectionModel());
	            	}
	            
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



