package calorietracker.scenes;

import calorietracker.controllers.UsersController;
import calorietracker.models.User;
import calorietracker.util.SessionUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScene {
    private Stage stage;

    public LoginScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        Image image = new Image(getClass().getResourceAsStream("/images/starting_scene.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(750);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(false);

        Label labelCalorie = new Label("Calorie");
        labelCalorie.getStyleClass().add("label-calorie");

        Label labelTracker = new Label("Tracker");
        labelTracker.getStyleClass().add("label-tracker");

        VBox boxTitle = new VBox(labelCalorie, labelTracker);
        boxTitle.setAlignment(Pos.CENTER);
        VBox.setMargin(boxTitle, new Insets(0, 0, 30, 0));

        TextField textFieldUsername = new TextField();
        textFieldUsername.setPromptText("Username");
        textFieldUsername.getStyleClass().add("tf-username");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().add("tf-password");
                
        Label labelStatus = new Label();
        labelStatus.getStyleClass().add("red-label");

        Button buttonLogin = new Button("LOG IN");
        buttonLogin.getStyleClass().add("starting-button");

        Label labelRegister = new Label("Belum punya akun? Daftar di sini!");
        labelRegister.getStyleClass().add("red-label");

        VBox mainLayout = new VBox(11, boxTitle, textFieldUsername, passwordField, labelStatus, buttonLogin, labelRegister);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(0, 0, 40, 325));
        
        StackPane stackPane = new StackPane(imageView, mainLayout);

        VBox root = new VBox(stackPane);
        Scene scene = new Scene(root, 750, 500);
        scene.getStylesheets().add(getClass().getResource("/styles/starting-styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        buttonLogin.setOnAction(e -> {
            String username = textFieldUsername.getText();
            String password = passwordField.getText();
            if (username.isEmpty() || password.isEmpty()){
                labelStatus.setText("Email dan password harus diisi!");
                return;
            }
            User user = UsersController.login(username, password);
            if (user != null) {
                SessionUtil.setUsername(username);
                int user_id = UsersController.getUserIdByUsername(username);
                if(user_id != -1){
                    ProfileScene profileScene = new ProfileScene(stage);
                    profileScene.show(user_id);
                }
            } else { 
                labelStatus.setText("Email atau password salah!");
            }
        });

        labelRegister.setOnMouseClicked(e -> {
            RegisterScene registerScene = new RegisterScene(stage);
            registerScene.show();
        });
    }
}