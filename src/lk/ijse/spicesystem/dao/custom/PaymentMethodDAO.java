package lk.ijse.spicesystem.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.spicesystem.dao.CrudDAO;
import lk.ijse.spicesystem.entity.PaymentMethod;

import java.sql.SQLException;

public interface PaymentMethodDAO extends CrudDAO<PaymentMethod> {
    public ObservableList paymentmethod() throws SQLException, ClassNotFoundException;
    public boolean updatePaymentMethod(String payament, Double price) throws SQLException, ClassNotFoundException;
    public String getPaymentId(String Paymnt) throws SQLException, ClassNotFoundException;
    public boolean updatePaymentMethodMinus(int cost, String paymentMethod) throws SQLException, ClassNotFoundException;
}
