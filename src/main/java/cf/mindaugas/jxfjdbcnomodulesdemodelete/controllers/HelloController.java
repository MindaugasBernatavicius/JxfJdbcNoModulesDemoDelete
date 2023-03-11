package cf.mindaugas.jxfjdbcnomodulesdemodelete.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label textLabel;
    @FXML
    private TextField textInput;
    @FXML
    public Button displayButton;
    @FXML
    public Button replaceButton;

    private final Connection conn;
    public HelloController(Connection conn) {
       this.conn = conn;
    }

    public void initialize() {
        this.displayButton.setOnAction(e -> textLabel.setText(textInput.getText()));

        this.replaceButton.setOnAction(e -> {
            // // ... no prepared statement
            // try(var statement = conn.createStatement()){
            //     var sqlQ = "INSERT INTO message (id, text)"
            //             + "VALUES (1, '" + textInput.getText() + "') "
            //             + "ON DUPLICATE KEY UPDATE "
            //             + "text = '" + textInput.getText() + "';" ;
            //     System.out.println(sqlQ);
            //     statement.executeUpdate(sqlQ);
            // } catch (SQLException ex) {
            //     ex.printStackTrace();
            // }

            // ... with prepared statement
            var sqlQ = "INSERT INTO message (id, text) VALUES (1, ?) ON DUPLICATE KEY UPDATE text = ?;" ;
            try(var statement = conn.prepareStatement(sqlQ)){
                statement.setString(1, textInput.getText());
                statement.setString(2, textInput.getText());
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

    }
}