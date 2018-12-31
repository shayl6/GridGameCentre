package Users;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Basic.FileManager;
import fall2018.csc2017.slidingtiles.R;

/**
 * This class manage the reset password page
 */
@SuppressLint("Registered")
public class ResetPasswordActivity extends AppCompatActivity {

    /**
     * The userManager
     */
    private UserManager userManager;
    private String checked_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        this.userManager = FileManager.loadUserManager(this.getApplicationContext());
        addCheckButtonListener();
        addResetPasswordButtonListener();
    }


    /**
     * Activate the Check button.
     */
    @SuppressLint("SetTextI18n")
    private void addCheckButtonListener() {
        Button CheckButton = findViewById(R.id.checkAccount);
        EditText account = findViewById(R.id.old_account);
        EditText oldPassword = findViewById(R.id.old_password);
        TextView message = findViewById(R.id.textView);

        CheckButton.setOnClickListener((v) -> {
            String name = account.getText().toString();
            String password = oldPassword.getText().toString();
            if ("".equals(this.userManager.login(name, password))) {
                message.setTextColor(Color.RED);
                if (this.userManager.checkUserNameValidity(name).equals("E-mail exist")) {
                    message.setText("Password Incorrect");
                } else {
                    message.setText("Username does not exist(valid)");
                }
            } else {
                message.setTextColor(Color.GREEN);
                message.setText("OK!");
                this.checked_account = name;
            }
        });
    }


    /**
     * Activate the ResetPassword button.
     */
    @SuppressLint("SetTextI18n")
    private void addResetPasswordButtonListener() {
        Button ResetButton = findViewById(R.id.resetButton);
        EditText Password = findViewById(R.id.resetPassword);
        @SuppressLint("CutPasteId") TextView ResetResult = findViewById(R.id.textView);
        EditText Account = findViewById(R.id.old_account);
        @SuppressLint("CutPasteId") TextView message = findViewById(R.id.textView);

        ResetButton.setOnClickListener((v) -> {
            //If password is too short, ask the user to change a longer password.
            if (Password.getText().toString().length() < 5) {
                ResetResult.setTextColor(Color.RED);
                ResetResult.setText("Password too short");
                //if user input his name and password correctly, then he can reset a new password now.
            } else if (message.getText().toString().equals("OK!") &&
                    checked_account.equals(Account.getText().toString())) {
                this.userManager.getUser(Account.getText().toString()).setPassword(Password.getText().toString());
                message.setTextColor(Color.GREEN);
                message.setText("Reset Success");
                FileManager.saveToFile(this.getApplicationContext(), this.userManager, "UM");
                Intent tmp = new Intent(this, LoginActivity.class);
                startActivity(tmp);
            } else {
                ResetResult.setTextColor(Color.RED);
                ResetResult.setText("Please check your username and password again");
            }
        });
    }
}
