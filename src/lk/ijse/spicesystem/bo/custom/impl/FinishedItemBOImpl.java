package lk.ijse.spicesystem.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.spicesystem.bo.custom.FinishedItemBO;
import lk.ijse.spicesystem.dao.DAOFactory;
import lk.ijse.spicesystem.dao.custom.FinishedItemDAO;

import java.sql.SQLException;

public class FinishedItemBOImpl implements FinishedItemBO {

    FinishedItemDAO finishedItemDAO = (FinishedItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FINISHEDITEM);

    @Override
    public ObservableList<String> getFinishedItem(String item) throws SQLException, ClassNotFoundException {
        ObservableList<String> finishedItems = finishedItemDAO.getFinishedItem(item);
        ObservableList<String> finishedItemDTOS = FXCollections.observableArrayList();

        for (String f : finishedItems) {
            finishedItemDTOS.add(f);
        }

        return finishedItemDTOS;
    }

    @Override
    public String getBarcode(String finishedItem) throws SQLException, ClassNotFoundException {
        return finishedItemDAO.getBarcode(finishedItem);
    }

    @Override
    public boolean updateFinishedItemTable(String barcodeNo, int amount) throws SQLException, ClassNotFoundException {
        return finishedItemDAO.updateFinishedItemTable(barcodeNo, amount);
    }

    @Override
    public String getQtyOnHand(String finishedItem) throws SQLException, ClassNotFoundException {
        return finishedItemDAO.getQtyOnHand(finishedItem);
    }

    @Override
    public boolean updateFinishedItem(String item, int amount) throws SQLException, ClassNotFoundException {
        return finishedItemDAO.updateFinishedItem(item, amount);
    }


}
