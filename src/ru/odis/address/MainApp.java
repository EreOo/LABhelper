package ru.odis.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.odis.address.model.Analyzer;
import ru.odis.address.view.AnalyzerOverviewController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    

    // �������������� �������� �����.
     
    public void initRootLayout() {
        try {
            // ��������� �������� ����� �� fxml �����.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // ���������� �����, ���������� �������� �����.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //���������� � �������� ������ ��������.
     
    public void showMaterialOverview() {
        try {
            // ��������� �������� �� ������������.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MaterialOverview.fxml"));
            AnchorPane materialOverview = (AnchorPane) loader.load();

            // �������� �������� �� ������������ � ����� ��������� ������.
            rootLayout.setCenter(materialOverview);
            
         // ��� ����������� ������ � �������� ����������.
            AnalyzerOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
     // ���������� ������� �����.
     
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    
    
    
    /**
     * ������, � ���� ������������ ������ ���������.
     */
    private ObservableList<Analyzer> personData = FXCollections.observableArrayList();

    /**
     * �����������
     */
    public MainApp() {
        // � �������� ������� ��������� ��������� ������
        personData.add(new Analyzer("WalkWay", "NBC41"));
        personData.add(new Analyzer("WalkWay", "PBC20"));
        personData.add(new Analyzer("SensiTitre", "GNID"));
        personData.add(new Analyzer("Crystal", "gr-"));
        
    }

    /**
     * ���������� ������ � ���� ������������ ������ ���������.
     * @return
     */
    public ObservableList<Analyzer> getPersonData() {
        return personData;
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