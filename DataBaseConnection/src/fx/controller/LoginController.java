package fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.mybatis.domain.User;

import driving.main.JDBCBind;
import driving.main.SQLMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML private Button btnSign_Up;
	@FXML private Button btnCancel;
	@FXML private StackPane stackPane;


	@FXML private BorderPane signupPane;
	@FXML private TextField S_txtUser;
	@FXML private TextField S_txtPassWD;
	@FXML private TextField S_txtDB;
	@FXML private Button S_btnIdCheck;
	@FXML private Button S_btnSave;
	@FXML private Button S_btnReset;

	private boolean idCheck = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {


			btnSign_Up.setOnAction(event -> handle_S_BtnSignUpAction(event));
			btnCancel.setOnAction(event -> System.exit(0));

			S_btnIdCheck.setOnAction(event -> handle_S_BtnIdCheckAction(event));
			S_btnSave.setOnAction(event -> handle_S_BtnSaveAction(event));
			S_btnReset.setOnAction(event -> handle_S_BtnResetAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 새로운 fxml Load */
	public void showStage() throws IOException {
		
		Parent p = FXMLLoader.load(getClass().getResource("../../formDialog.fxml"));
		
		Stage stage = new Stage();
		Scene scene = new Scene(p);
		
		
		stage.setTitle("Create");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		
	}
	
	/* DB Save Event */
	public void handle_S_BtnSaveAction(ActionEvent event) {

		User user = new User(S_txtUser.getText(), S_txtPassWD.getText(), S_txtDB.getText());

		SQLMapper sqlMapper = new SQLMapper();

		String name = user.getUserName();
		String pw = user.getPassWD();
		String db = user.getDbName();

		if (name.equals("") || pw.equals("") || db.equals("")) {
			MSG("모든 정보를 입력해주세요.", "경고");
		} else if (idCheck) {
			new JDBCBind().createDatabase(user.getDbName());
			sqlMapper.insertDatabase(user);
			MSG("Database 생성 완료", "확인");
		}

	}

	/* User Overlap Check */
	public void handle_S_BtnIdCheckAction(ActionEvent event) {

		int result = new SQLMapper().selectIdCheck(S_txtUser.getText());

		if (result > 0) {
			S_txtUser.setText("");
			idCheck = false;
			MSG("이미 등록 된 [User]입니다.", "경고");
		} else {
			S_txtUser.setDisable(true);
			idCheck = true;
			MSG("사용 가능한 [User]입니다.", "확인");
		}

	}

	/* Sign Up Panel Show Event */
	public void handle_S_BtnSignInAction(ActionEvent event) {
		signupPane.setVisible(false);
	}

	/* Sign In Panel Show Event */
	public void handle_S_BtnSignUpAction(ActionEvent event) {
		signupPane.setVisible(true);
	}

	public void handle_S_BtnResetAction(ActionEvent event) {
		S_txtUser.setDisable(false);
		S_txtUser.setText("");
		S_txtPassWD.setText("");
		S_txtDB.setText("");
	}

	public void MSG(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

}
