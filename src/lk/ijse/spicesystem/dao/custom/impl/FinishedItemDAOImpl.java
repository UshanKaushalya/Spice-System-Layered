package lk.ijse.spicesystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.spicesystem.dao.CrudUtil;
import lk.ijse.spicesystem.dao.custom.FinishedItemDAO;
import lk.ijse.spicesystem.entity.FinishedItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FinishedItemDAOImpl implements FinishedItemDAO {
    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(FinishedItem finishedItem) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public FinishedItem search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(FinishedItem finishedItem) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ObservableList getFinishedItem(String item) throws SQLException, ClassNotFoundException {
        String sql = "SELECT FinishedItem FROM finisheditem WHERE Production = ?";

        ResultSet result = CrudUtil.execute(sql, item);

        ObservableList list = FXCollections.observableArrayList();

        while(result.next()){
            list.add(result.getString("FinishedItem"));
        }

        return list;
    }

    @Override
    public String getBarcode(String finishedItem) throws SQLException, ClassNotFoundException {
        String sql = "SELECT BarcodeNO FROM finisheditem WHERE FinishedItem = ?";

        ResultSet resultSet = CrudUtil.execute(sql, finishedItem);

        if(resultSet.next()){
            return resultSet.getString("BarcodeNO");
        }

        return null;
    }

    @Override
    public boolean updateFinishedItemTable(String barcodeNo, int amount) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE finishedItem SET AmountInStock = AmountInStock + ? WHERE BarcodeNO = ?";

        return CrudUtil.execute(sql, amount, barcodeNo);
    }

    @Override
    public String getQtyOnHand(String finishedItem) throws SQLException, ClassNotFoundException {
        String sql = "SELECT AmountInStock FROM finisheditem WHERE FinishedItem = ?";

        ResultSet result = CrudUtil.execute(sql, finishedItem);

        if(result.next()){
            return result.getString("AmountInStock");
        }

        return null;
    }

    @Override
    public boolean updateFinishedItem(String item, int amount) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE finisheditem SET AmountInStock = AmountInStock - ? WHERE FinishedItem = ?";

        return CrudUtil.execute(sql, amount, item);
    }
}
