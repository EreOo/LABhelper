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
	private final StringProperty typeMaterial;
    private final StringProperty materialName;
    private final StringProperty idMAterial;
    private final IntegerProperty countBox;
    private final IntegerProperty countINTOBox;
    private final ObjectProperty<LocalDate> dateAdd;
    private final ObjectProperty<LocalDate> exp;
   
   

    //конструктор по умолчанию
	public Analyzer() {
		this(null,null);
	}
	
	/**неполный конструктор tset**/
	public Analyzer(String analyzer, String material)
	{
		this.analyzerName = new SimpleStringProperty(analyzer);
		this.materialName = new SimpleStringProperty(material);
		
		//инфо для тестирования
		
		this.idMAterial = new SimpleStringProperty("B1111-94");
		this.countBox = new SimpleIntegerProperty(30);
		this.countINTOBox = new SimpleIntegerProperty(20);
		this.exp = new SimpleObjectProperty<LocalDate>(LocalDate.of(2016, 5, 16));
		this.dateAdd = new SimpleObjectProperty<LocalDate>(LocalDate.of(2016, 5, 20));
		this.typeMaterial = new SimpleStringProperty("Тест система");
        
	}
	
	/**неполный конструктор tset2**/
	public Analyzer(String analyzer,String type,String material,String id, Integer countBox, Integer countintoobx)
	{
		this.analyzerName = new SimpleStringProperty(analyzer);
		this.materialName = new SimpleStringProperty(material);
		this.idMAterial = new SimpleStringProperty(id);
		this.countBox = new SimpleIntegerProperty(countBox);
		this.countINTOBox = new SimpleIntegerProperty(countintoobx);
		this.typeMaterial = new SimpleStringProperty(type);
		
		//инфо для тестирования
		
	
		this.exp = new SimpleObjectProperty<LocalDate>(LocalDate.of(2016, 5, 16));
		this.dateAdd = new SimpleObjectProperty<LocalDate>(LocalDate.of(2016, 5, 20));
		
        
	}
	
	
	
	/**Реальный конструктор**/
	public Analyzer(String analyzer,String type,String material,String id, Integer countBox, Integer countintoobx, LocalDate exp, LocalDate add)
	{
		this.analyzerName = new SimpleStringProperty(analyzer);
		this.materialName = new SimpleStringProperty(material);
		this.idMAterial = new SimpleStringProperty(id);
		this.countBox = new SimpleIntegerProperty(countBox);
		this.countINTOBox = new SimpleIntegerProperty(countintoobx);
		this.exp = new SimpleObjectProperty<LocalDate>(exp);
		this.dateAdd = new SimpleObjectProperty<LocalDate>(add);
		this.typeMaterial = new SimpleStringProperty(type);
        
	}
	

	/**название анализатора гет\сет**/
	public String getAnalyzerName() {
        return analyzerName.get();
    }

    public void setAnalyzerName(String analyzerName) {
        this.analyzerName.set(analyzerName);
    }

    public StringProperty analyzerNameProperty() {
        return analyzerName;
    }
	
    /**название расходки гет\сет**/
    public String getMaterialName() {
        return materialName.get();
    }

    public void setMaterialName(String materialName) {
        this.materialName.set(materialName);
    }

    public StringProperty materialNameProperty() {
        return materialName;
    }
    
    /**id расходки**/
    public String getIdMAterial() {
        return idMAterial.get();
    }

    public void setIdMAterial(String idMAterial) {
        this.idMAterial.set(idMAterial);
    }

    public StringProperty idMAterialProperty() {
        return idMAterial;
    }
    
    /**кол-во коробок **/
    public int getСountBox() {
        return countBox.get();
    }

    public void setСountBox(int countBox) {
        this.countBox.set(countBox);
    }

    public IntegerProperty countBoxProperty() {
        return countBox;
    }
    
    /**кол-во в одной коробке**/
    public int getСountINTOBox() {
        return countINTOBox.get();
    }

    public void setСountINTOBox(int countINTOBox) {
        this.countINTOBox.set(countINTOBox);
    }

    public IntegerProperty countINTOBoxProperty() {
        return countINTOBox;
    }
    
    /**срок годности**/
    public LocalDate getExp() {
        return exp.get();
    }

    public void setExp(LocalDate exp) {
        this.exp.set(exp);
    }

    public ObjectProperty<LocalDate> expProperty() {
        return exp;
    }
    
    /**дата поступления**/
    public LocalDate getDateAdd() {
        return dateAdd.get();
    }

    public void setDateAdd(LocalDate dateAdd) {
        this.dateAdd.set(dateAdd);
    }

    public ObjectProperty<LocalDate> dateAddProperty() {
        return dateAdd;
    }
    
    /**тип расходки**/
    public String getTypeMaterial() {
        return typeMaterial.get();
    }

    public void setTypeMaterial(String typeMaterial) {
        this.typeMaterial.set(typeMaterial);
    }

    public StringProperty typeMaterialProperty() {
        return typeMaterial;
    }
	
}
