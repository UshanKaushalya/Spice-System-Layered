package lk.ijse.spicesystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.spicesystem.bo.BOFactory;
import lk.ijse.spicesystem.bo.custom.ShopBO;
import lk.ijse.spicesystem.bo.custom.SupplierBO;
import lk.ijse.spicesystem.dto.SupplierDTO;
import lk.ijse.spicesystem.entity.Supplier;
import lk.ijse.spicesystem.util.Navigation;
import lk.ijse.spicesystem.util.Routes;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;

public class AddSupplierFormController {
    public ImageView imgDotOne;
    public ImageView imgDotTwo;
    public Label lblSupplierId;
    public JFXTextField txtSupplierName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXTextField txtTelephone;
    public AnchorPane dashboardPane;
    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    public void initialize(){
        try {
            lblSupplierId.setText(supplierBO.nextShopId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnSubmitOnAction(ActionEvent actionEvent) {
        SupplierDTO supplierDTO = new SupplierDTO(lblSupplierId.getText(), txtSupplierName.getText(), txtAddress.getText(), txtEmail.getText(), Integer.valueOf(txtTelephone.getText()));

        try {
            boolean isAdded = supplierBO.add(supplierDTO);

            if(isAdded){

                Image image = new Image("/lk/ijse/spicesystem/asset/correct.png");
                Notifications notifications = Notifications.create();
                notifications.graphic(new ImageView(image));
                notifications.text("Added Suuccesful");
                notifications.title("Spice System");
                notifications.hideAfter(Duration.seconds(3));
                notifications.show();

                lblSupplierId.setText("");
                txtAddress.clear();
                txtEmail.clear();
                txtSupplierName.clear();
                txtTelephone.clear();

                initialize();

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIERDASHBOARD, dashboardPane);
    }
}
