package inventory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.MariaDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class InventoryController implements Initializable{

    @FXML
    private TextField newItemId;
    
    @FXML
    private TextField newItemName;

    @FXML
    private TextField newItemQuantity;

    @FXML
    private Button newItemButton;
    
    @FXML
    private TableView<InventoryModel> tableitems;

    @FXML
    private TableColumn<InventoryModel, String> iditem;
    
    @FXML
    private TableColumn<InventoryModel, String> item;

    @FXML
    private TableColumn<InventoryModel, String> itemsstock;

    @FXML
    public ObservableList<InventoryModel> list = FXCollections.observableArrayList();
    
    void clearFields() {
    	this.newItemId.setText("");
    	this.newItemName.setText("");
    	this.newItemQuantity.setText("");
    }
    
    @FXML
    void addItem(ActionEvent event) throws SQLException {
    	try {
	    	Connection conn = MariaDBConnection.Connector();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO item (item, stock) VALUES(?,?)");
			ps.setString(1, this.newItemName.getText());
			ps.setString(2, this.newItemQuantity.getText());
			ps.executeQuery();
			conn.close();
			this.clearFields();
			this.showData();
    	}catch(Exception error) {
			System.out.println(error);
		}
    }
    
    public void showData() {
    	try{
    		Connection conn = MariaDBConnection.Connector();
    		this.list = FXCollections.observableArrayList();
    		ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM item");
    		while(rs.next()) {
    			this.list.add(new InventoryModel(rs.getString(1),rs.getString(2),rs.getString(3)));
    		}
    	}catch(Exception error) {
			System.out.println(error);
		}
    	iditem.setCellValueFactory(new PropertyValueFactory<InventoryModel, String>("id"));
		item.setCellValueFactory(new PropertyValueFactory<InventoryModel, String>("item"));
		itemsstock.setCellValueFactory(new PropertyValueFactory<InventoryModel, String>("stock"));
		this.tableitems.setItems(null);
		this.tableitems.setItems(this.list);
    }

    @FXML
    void deleteData(ActionEvent event) {
    	try {
	    	Connection conn = MariaDBConnection.Connector();
			PreparedStatement ps = conn.prepareStatement("DELETE FROM item WHERE id = (?)");
			ps.setString(1, this.newItemId.getText());
			ps.executeQuery();
			conn.close();
			this.clearFields();
			this.showData();
    	}catch(Exception error) {
			System.out.println(error);
		}
    }

    @FXML
    public void updateData(ActionEvent event) {
    	try {
	    	Connection conn = MariaDBConnection.Connector();
			PreparedStatement ps = conn.prepareStatement("UPDATE item SET item = ? ,stock = ?  WHERE id = (?)");
			ps.setString(1, this.newItemName.getText());
			ps.setString(2, this.newItemQuantity.getText());
			ps.setString(3, this.newItemId.getText());
			ps.executeQuery();
			conn.close();
			this.clearFields();
			this.showData();
    	}catch(Exception error) {
			System.out.println(error);
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
}
