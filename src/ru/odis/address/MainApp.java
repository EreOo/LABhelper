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
     * ������ �����������.
     */
    private static ObservableList<Analyzer> aData = FXCollections.observableArrayList();
    
    public static ObservableList<Analyzer> getData(){
    	return aData;
    }

    

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
     * �����������
     */
    public MainApp() {
        // � �������� ������� ��������� ��������� ������
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
     * ���������� ������ � ���� ������������ ������.
     * @return
     */
    public ObservableList<Analyzer> getPersonData() {
        return aData;
    }
    
    
    //������ �����
    public  boolean showAddDialog() {
        try {
            // ��������� fxml-���� � ������ ����� �����
            // ��� ������������ ����������� ����.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AddDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // ������ ���������� ���� Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("����� ���������");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // �������  � ����������.
            AddDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            

            // ���������� ���������� ���� � ���, ���� ������������ ��� �� �������
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // ������ "��������"
    public boolean showEditDialog(Analyzer a) {
        try {
            // ��������� fxml-���� � ������ ����� �����
            // ��� ������������ ����������� ����.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // ������ ���������� ���� Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("�������������");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // ������� ����������
            EditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAnalyzerE(a);

            // ���������� ���������� ���� � ���, ���� ������������ ��� �� �������
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