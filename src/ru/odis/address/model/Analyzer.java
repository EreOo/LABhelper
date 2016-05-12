package ru.odis.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Analyzer {
	private final StringProperty analyzerName;
    private final StringProperty materialName;
    private final StringProperty idMAterial;
    private final IntegerProperty countBox;
    private final IntegerProperty countINTOBox;
    private final ObjectProperty<LocalDate> exp;
    private final ObjectProperty<LocalDate> dateAdd;

    //����������� �� ���������
	public Analyzer() {
		this(null,null);
	}
	
	/**�������� ����������� tset**/
	public Analyzer(String analyzer, String material)
	{
		this.analyzerName = new SimpleStringProperty(analyzer);
		this.materialName = new SimpleStringProperty(material);
		
		//���� ��� ������������
		
		this.idMAterial = new SimpleStringProperty("B1111-94");
		this.countBox = new SimpleIntegerProperty(30);
		this.countINTOBox = new SimpleIntegerProperty(20);
		this.exp = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
		this.dateAdd = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999 , 2, 21));
        
	}

	/**�������� ����������� ���\���**/
	public String getAnalyzerName() {
        return analyzerName.get();
    }

    public void setAnalyzerName(String analyzerName) {
        this.analyzerName.set(analyzerName);
    }

    public StringProperty analyzerNameProperty() {
        return analyzerName;
    }
	
    /**�������� �������� ���\���**/
    public String getMaterialName() {
        return materialName.get();
    }

    public void setMaterialName(String materialName) {
        this.materialName.set(materialName);
    }

    public StringProperty materialNameProperty() {
        return materialName;
    }
    
    /**id ��������**/
    public String getIdMAterial() {
        return idMAterial.get();
    }

    public void setIdMAterial(String idMAterial) {
        this.idMAterial.set(idMAterial);
    }

    public StringProperty idMAterialProperty() {
        return idMAterial;
    }
    
    /**���-�� ������� **/
    public int get�ountBox() {
        return countBox.get();
    }

    public void set�ountBox(int countBox) {
        this.countBox.set(countBox);
    }

    public IntegerProperty countBoxProperty() {
        return countBox;
    }
    
    /**���-�� � ����� �������**/
    public int get�ountINTOBox() {
        return countINTOBox.get();
    }

    public void set�ountINTOBox(int countINTOBox) {
        this.countINTOBox.set(countINTOBox);
    }

    public IntegerProperty countINTOBoxProperty() {
        return countINTOBox;
    }
    
    /**���� ��������**/
    public LocalDate getExp() {
        return exp.get();
    }

    public void setExp(LocalDate exp) {
        this.exp.set(exp);
    }

    public ObjectProperty<LocalDate> expProperty() {
        return exp;
    }
    
    /**���� �����������**/
    public LocalDate getDateAdd() {
        return dateAdd.get();
    }

    public void setDateAdd(LocalDate dateAdd) {
        this.dateAdd.set(dateAdd);
    }

    public ObjectProperty<LocalDate> dateAddProperty() {
        return dateAdd;
    }
	
}
