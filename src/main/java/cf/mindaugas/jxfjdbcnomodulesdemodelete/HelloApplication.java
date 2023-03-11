package cf.mindaugas.jxfjdbcnomodulesdemodelete;

import cf.mindaugas.jxfjdbcnomodulesdemodelete.controllers.HelloController;
import cf.mindaugas.jxfjdbcnomodulesdemodelete.services.DbUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class HelloApplication extends Application {
    private Connection connection;

    @Override
    public void start(Stage stage) throws IOException {
        this.connection = new DbUtil().getConnection();
        try(var stmt = connection.createStatement()) {
            var resultSet = stmt.executeQuery("SELECT VERSION();");
            if (resultSet.next())
                System.out.println("Mysql version: " + resultSet.getString("VERSION()"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        var fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        var hController = new HelloController(connection);
        fxmlLoader.setController(hController);

        var scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        this.connection.close();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}