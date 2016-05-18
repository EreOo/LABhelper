package ru.odis.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.odis.address.model.Analyzer;
import ru.odis.address.view.AddDialogController;
import ru.odis.address.view.AnalyzerOverviewController;
import ru.odis.address.view.EditDialogController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    /**
     * Список расходников.
     */
    private static ObservableList<Analyzer> aData = FXCollections.observableArrayList();
    
    public static ObservableList<Analyzer> getData(){
    	return aData;
    }

    

    // Инициализирует корневой макет.
     
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Показывает в корневом макете сведения.
     
    public void showMaterialOverview() {
        try {
            // Загружаем сведения об анализатарах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MaterialOverview.fxml"));
            AnchorPane materialOverview = (AnchorPane) loader.load();

            // Помещаем сведения об анализатарах в центр корневого макета.
            rootLayout.setCenter(materialOverview);
            
         // Даём контроллеру доступ к главному приложению.
            AnalyzerOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
     // Возвращает главную сцену.
     
    public Stage getPrimaryStage() {
        return primaryStage;
    }


    /**
     * Конструктор
     */
    public MainApp() {
        // В качестве образца добавляем некоторые данные
        aData.add(new Analyzer("WalkWay", "NBC41"));
        aData.add(new Analyzer("WalkWay", "PBC20"));
        aData.add(new Analyzer("SensiTitre", "GNID"));
        aData.add(new Analyzer("Crystal", "NH"));
        aData.add(new Analyzer("WalkWay", "PC29"));
        aData.add(new Analyzer("SensiTitre", "GPID"));
        aData.add(new Analyzer("SensiTitre", "GP4D"));
        aData.add(new Analyzer("Crystal", "NG"));
        aData.add(new Analyzer("Crystal", "PG"));
        aData.add(new Analyzer("WalkWay", "RYID"));
        aData.add(new Analyzer("SensiTitre", "GP4D"));
        aData.add(new Analyzer("SensiTitre", "GNID"));
        
    }

    /**
     * Возвращает данные в виде наблюдаемого списка.
     * @return
     */
    public ObservableList<Analyzer> getPersonData() {
        return aData;
    }
    
    
    //кнопка НОВЫЙ
    public  boolean showAddDialog() {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AddDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Новые материалы");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём  в контроллер.
            AddDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // кнопка "изменить"
    public boolean showEditDialog(Analyzer a) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Израсходовано");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём анализатор
            EditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAnalyzerE(a);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LABHelper v0.1");

        initRootLayout();

        showMaterialOverview();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}