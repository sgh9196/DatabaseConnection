package fx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.mybatis.domain.User;

import driving.main.JDBCBind;
import driving.main.SQLMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MyController implements Initializable {

	@FXML private Button btnSign_In;
	@FXML private Button btnSign_Up;
	@FXML private Button btnCancel;

	@FXML private StackPane stackPane;

	@FXML private BorderPane loginPane;
	@FXML private TextField L_txtUser;
	@FXML private TextField L_txtPassWD;
	@FXML private Button L_btnConnect;
	@FXML private Button L_btnReset;

	@FXML private BorderPane signupPane;
	@FXML private TextField S_txtUser;
	@FXML private TextField S_txtPassWD;
	@FXML private TextField S_txtDB;
	@FXML private Button S_btnSave;
	@FXML private Button S_btnReset;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loginPane.setVisible(false);

		btnSign_In.setOnAction(event -> handleBtnSignInAction(event));
		btnSign_Up.setOnAction(event -> handleBtnSignUpAction(event));
		btnCancel.setOnAction(event -> System.exit(0));
		
		S_btnSave.setOnAction(event -> handleBtnSaveAction(event));
		
	}
	
	
	/* DB Save Event */
	public void handleBtnSaveAction(ActionEvent event) {
		
		User user = new User(S_txtUser.getText(), S_txtPassWD.getText(), S_txtDB.getText());
		
		new JDBCBind().createDatabase(user.getDbName());
		
		System.out.println(user);
		
		new SQLMapper().insertDatabase(user);
		
	}
	
	/* Sign Up Panel Show Event */
	public void handleBtnSignInAction(ActionEvent event) {
		signupPane.setVisible(false);
		loginPane.setVisible(true);
	}
	
	/* Sign In Panel Show Event */
	public void handleBtnSignUpAction(ActionEvent event) {
		loginPane.setVisible(false);
		signupPane.setVisible(true);
	}

}
