package inventory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InventoryModel {
	private final StringProperty id;
	private final StringProperty item;
	private final StringProperty stock;
	
	public InventoryModel(String id, String item, String stock) {
		this.id = new SimpleStringProperty(id);
		this.item = new SimpleStringProperty(item);
		this.stock = new SimpleStringProperty(stock);
	}

	public final StringProperty idProperty() {
		return this.id;
	}
	

	public final String getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final String id) {
		this.idProperty().set(id);
	}
	

	public final StringProperty itemProperty() {
		return this.item;
	}
	

	public final String getName() {
		return this.itemProperty().get();
	}
	

	public final void setName(final String item) {
		this.itemProperty().set(item);
	}
	

	public final StringProperty stockProperty() {
		return this.stock;
	}
	

	public final String getStock() {
		return this.stockProperty().get();
	}
	

	public final void setStock(final String stock) {
		this.stockProperty().set(stock);
	}
	
}
