package lk.ijse.spicesystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lk.ijse.spicesystem.bo.BOFactory;
import lk.ijse.spicesystem.bo.custom.*;
import lk.ijse.spicesystem.db.DBConnection;
import lk.ijse.spicesystem.dto.OrderDTO;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;

public class PlaceOrderFormController {
    public JFXComboBox cmbShopId;
    public Label lblShop;
    public JFXComboBox cmbProductionItem;
    public JFXComboBox cmbFinishedItem;
    public Label lblItemOnStock;
    public JFXTextField txtAmount;
    public JFXTextField txtPrice;
    public Label lblOrderId;
    public JFXComboBox cmbPaymeneMethod;


    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACEORDER);

    public void initialize(){

        try {
            cmbShopId.setItems(placeOrderBO.getAllId());
            lblOrderId.setText(placeOrderBO.nextId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cmbShopIdOnAction(ActionEvent actionEvent) {

        try {
            lblShop.setText(String.valueOf(placeOrderBO.getShopName(String.valueOf(cmbShopId.getValue()))));
            cmbProductionItem.setItems(placeOrderBO.getProductionItem());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cmbProductionItemOnAction(ActionEvent actionEvent) {

        try {
            cmbFinishedItem.setItems(placeOrderBO.getFinishedItem(String.valueOf(cmbProductionItem.getValue())));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cmbFinishedItemOnAction(ActionEvent actionEvent) {

        try {
            lblItemOnStock.setText(placeOrderBO.getQtyOnHand(String.valueOf(cmbFinishedItem.getValue())));
            cmbPaymeneMethod.setItems(placeOrderBO.paymentmethod());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }

    public void cmbPaymentMethodOnAction(ActionEvent actionEvent) {
    }


    public void btnSubmitOnAction(ActionEvent actionEvent) {

        OrderDTO order = new OrderDTO(lblOrderId.getText(), String.valueOf(cmbShopId.getValue()), String.valueOf(cmbFinishedItem.getValue()), Integer.valueOf(txtAmount.getText()), Double.valueOf(txtPrice.getText()));

        try {

            DBConnection.getInstance().getConnection().setAutoCommit(false);

            boolean isUpdateFinished = placeOrderBO.updateFinishedItem(String.valueOf(cmbFinishedItem.getValue()), Integer.valueOf(txtAmount.getText()));

            if(isUpdateFinished) {

                boolean isUpdateOrder = placeOrderBO.add(order);

                if(isUpdateOrder){

                    boolean isUpdateFinance = placeOrderBO.updateFinance(String.valueOf(cmbPaymeneMethod.getValue()), Integer.valueOf(txtPrice.getText()));

                    if(isUpdateFinance){

                        boolean isUpdatePaymentMethod = placeOrderBO.updatePaymentMethod(String.valueOf(cmbPaymeneMethod.getValue()), Double.valueOf(txtPrice.getText()));

                        if(isUpdatePaymentMethod){

                            DBConnection.getInstance().getConnection().commit();

                            Image image = new Image("/lk/ijse/spicesystem/asset/correct.png");
                            Notifications notifications = Notifications.create();
                            notifications.graphic(new ImageView(image));
                            notifications.text("Place Succesful");
                            notifications.title("Spice System");
                            notifications.hideAfter(Duration.seconds(3));
                            notifications.show();

                            ObservableList list = FXCollections.observableArrayList();

                            lblOrderId.setText("");
                            lblShop.setText("");
                            lblItemOnStock.setText("");

                            cmbPaymeneMethod.setItems(list);
                            cmbProductionItem.setItems(list);
                            cmbFinishedItem.setItems(list);

                            txtAmount.clear();
                            txtPrice.clear();

                            initialize();

                        }

                    }

                }

            }

            DBConnection.getInstance().getConnection().rollback();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
