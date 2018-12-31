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
 * This class is responsible for Registration page
 */
public class RegistrationActivity extends AppCompatActivity {

    /**
     * The account name that has already been checked
     */
    private String account;

    /**
     * The userManager
     */
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.userManager = FileManager.loadUserManager(this.getApplicationContext());
        setContentView(R.layout.activity_registration);
        addCheckButtonListener();
        addRegisterButtonListener();
    }


    /**
     * Activate the Check button.
     */
    @SuppressLint("SetTextI18n")
    private void addCheckButtonListener() {
        Button mCheckButton = findViewById(R.id.check_account_button);
        EditText mAccount = findViewById(R.id.new_account);
        TextView mAccountResult = findViewById(R.id.account_exist);

        mCheckButton.setOnClickListener((v) -> {
            String validity = this.userManager.checkUserNameValidity(mAccount.getText().toString());
            if (validity.equals("OK!")) {
                mAccountResult.setTextColor(Color.GREEN);
                account = mAccount.getText().toString();
            } else {
                mAccountResult.setTextColor(Color.RED);
            }
            mAccountResult.setText(validity);
        });
    }


    /**
     * Activate the Register button.
     */
    @SuppressLint("SetTextI18n")
    private void addRegisterButtonListener() {
        Button mRegisterButton = findViewById(R.id.register_button);
        EditText mPassword = findViewById(R.id.new_password);
        TextView mRegisterResult = findViewById(R.id.register_result);
        EditText mAccount = findViewById(R.id.new_account);
        TextView mAccountResult = findViewById(R.id.account_exist);
        mRegisterButton.setOnClickListener((v) -> {
            //If password is not valid, ask the user to change a longer password.
            if (!(this.userManager.isValidPassword(mPassword.getText().toString()))) {
                mRegisterResult.setTextColor(Color.RED);
                mRegisterResult.setText("Password too short");
            } else if (mAccountResult.getText().toString().equals("OK!") &&
                    mAccount.getText().toString().equals(account)) {

                //If the username does not exist and the password is long enough, register this account.
                this.userManager.signUp(mAccount.getText().toString(), mPassword.getText().toString());

                mRegisterResult.setTextColor(Color.GREEN);
                mRegisterResult.setText("Success");
                mAccountResult.setText("");

                FileManager.saveToFile(this.getApplicationContext(), this.userManager, "UM");
                Intent tmp = new Intent(this, LoginActivity.class);
                startActivity(tmp);
            } else if (!mAccount.getText().toString().equals(account)) {
                // If the user change the account after checking the availability, ask the user to check again.
                mRegisterResult.setTextColor(Color.RED);
                mRegisterResult.setText("Please check your username after change");
            } else {
                //If the user has not check the availability of the username, ask the user to check first.
                mRegisterResult.setTextColor(Color.RED);
                mRegisterResult.setText("Check your username first");
            }
        });
    }
}

