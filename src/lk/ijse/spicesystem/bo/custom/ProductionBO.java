package lk.ijse.spicesystem.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.spicesystem.bo.SuperBO;
import lk.ijse.spicesystem.dto.ProductionDTO;

import java.sql.SQLException;

public interface ProductionBO extends SuperBO {
    public ObservableList<String> getProductionItem() throws SQLException, ClassNotFoundException;
    public String getProductionId(String item) throws SQLException, ClassNotFoundException;
    public boolean updateProductionTable(String productionId, int amount) throws SQLException, ClassNotFoundException;
    public ObservableList<String> getProduction(String id) throws SQLException, ClassNotFoundException;
    public boolean productionTale(int amount, String productionItem) throws SQLException, ClassNotFoundException;
}
