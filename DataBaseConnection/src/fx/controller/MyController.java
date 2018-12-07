package fx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
	@FXML private Button S_btnIdCheck;
	@FXML private Button S_btnSave;
	@FXML private Button S_btnReset;

	private boolean idCheck = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
	
			btnSign_In.setOnAction(event -> handle_S_BtnSignInAction(event));
			btnSign_Up.setOnAction(event -> handle_S_BtnSignUpAction(event));
			btnCancel.setOnAction(event -> System.exit(0));
			
			L_btnConnect.setOnAction(event -> handle_L_BtnConnectAction(event));
			L_btnReset.setOnAction(event -> handle_L_BtnResetAction(event));
			
			S_btnIdCheck.setOnAction(event -> handle_S_BtnIdCheckAction(event));
			S_btnSave.setOnAction(event -> handle_S_BtnSaveAction(event));
			S_btnReset.setOnAction(event -> handle_S_BtnResetAction(event));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/* Login Connection Event */
	public void handle_L_BtnConnectAction(ActionEvent event) {
		
		try {
			
			SQLMapper sqlMapper = new SQLMapper();
			User user = new User(L_txtUser.getText(), L_txtPassWD.getText(), "");
			String db = sqlMapper.selectLogin(user);
			
			if(db.equals("")) {  
				MSG("잘못된 정보를 입력하셨습니다. 다시 입력해주세요.", "경고"); 
			}
			else {
				user.setDbName(db);
				 MSG("[" + db + "] 접속 ..", "확인");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/* DB Save Event */
	public void handle_S_BtnSaveAction(ActionEvent event) {
		
		User user = new User(S_txtUser.getText(), S_txtPassWD.getText(), S_txtDB.getText());
		
		SQLMapper sqlMapper = new SQLMapper();
		
		String name = user.getUserName();
		String pw = user.getPassWD();
		String db = user.getDbName();
		
		if(name.equals("") || pw.equals("") || db.equals("")) {
			MSG("모든 정보를 입력해주세요.", "경고");
		}
		else if(idCheck) {
			new JDBCBind().createDatabase(user.getDbName());
			sqlMapper.insertDatabase(user);
			MSG("Database 생성 완료", "확인");
		}
		
	}
	
	/* User Overlap Check */
	public void handle_S_BtnIdCheckAction(ActionEvent event) {
		
		int result = new SQLMapper().selectIdCheck(S_txtUser.getText()); 
		
		if(result>0) { 
			S_txtUser.setText(""); 
			idCheck = false;
			MSG("이미 등록 된 [User]입니다.", "경고");
		}
		else { 
			S_txtUser.setDisable(true);
			idCheck = true; 
			MSG("사용 가능한 [User]입니다.", "확인"); 
		}
		
	}
	
	/* Sign Up Panel Show Event */
	public void handle_S_BtnSignInAction(ActionEvent event) {
		signupPane.setVisible(false);
		loginPane.setVisible(true);
	}
	
	/* Sign In Panel Show Event */
	public void handle_S_BtnSignUpAction(ActionEvent event) {
		loginPane.setVisible(false);
		signupPane.setVisible(true);
	}

	public void handle_L_BtnResetAction(ActionEvent event) {
		L_txtUser.setText("");
		L_txtPassWD.setText("");
	}
	
	public void handle_S_BtnResetAction(ActionEvent event) {
		S_txtUser.setText("");
		S_txtPassWD.setText("");
		S_txtDB.setText("");
	}
	
	public void MSG(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
}
