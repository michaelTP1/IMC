package dad.javafx.imc;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class IMCModel {
	
	private DoubleProperty peso=new SimpleDoubleProperty();
	private IntegerProperty altura=new SimpleIntegerProperty();
	
	private ReadOnlyDoubleWrapper imc=new ReadOnlyDoubleWrapper();
	
	
	
	public IMCModel() {
		
		
		imc.bind(peso.divide(Bindings
				.createDoubleBinding(()->Math.pow(altura.doubleValue()/100,2),altura)));
//				Math.pow(altura.doubleValue(),2)));
	

	}
	public final DoubleProperty pesoProperty() {
		return this.peso;
	}
	



	public final double getPeso() {
		return this.pesoProperty().get();
	}
	



	public final void setPeso(final double peso) {
		this.pesoProperty().set(peso);
	}
	



	public final IntegerProperty alturaProperty() {
		return this.altura;
	}
	



	public final int getAltura() {
		return this.alturaProperty().get();
	}
	



	public final void setAltura(final int altura) {
		this.alturaProperty().set(altura);
	}
	



	public final javafx.beans.property.ReadOnlyDoubleProperty imcProperty() {
		return this.imc.getReadOnlyProperty();
	}
	



	public final double getImc() {
		return this.imcProperty().get();
	}
	

}
