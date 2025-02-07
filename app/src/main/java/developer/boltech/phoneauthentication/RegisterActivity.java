package developer.boltech.phoneauthentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.example.phoneauthentication.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout phoneNumberRegister, passwordRegister;
    private Button registerButton;
    CardView cardViewMinimumEightCharacter, cardViewMinimumOneUpperCase, cardViewMinimumOneDigitNumber, cardViewMinimumOneSpecialCharacter;
    private boolean isEightCharacter = false, isOneUpperCase = false, isOneDigitNumber = false, isOneSpecialCharacter = false, isRegistrationButtonClikable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        phoneNumberRegister = findViewById(R.id.phoneNumberRegister);
        passwordRegister = findViewById(R.id.passwordRegister);

        cardViewMinimumEightCharacter = findViewById(R.id.cardViewMinimumEightCharacter);
        cardViewMinimumOneUpperCase = findViewById(R.id.cardViewMinimumOneUpperCase);
        cardViewMinimumOneDigitNumber = findViewById(R.id.cardViewMinimumOneDigitNumber);
        cardViewMinimumOneSpecialCharacter = findViewById(R.id.cardViewMinimumOneSpecialCharacter);

        phoneNumberRegister.getEditText().addTextChangedListener(logonTextWatcher);
        passwordRegister.getEditText().addTextChangedListener(logonTextWatcher);

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private TextWatcher logonTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String phoneNumber = phoneNumberRegister.getEditText().getText().toString().trim();
            String password = passwordRegister.getEditText().getText().toString().trim();

            registerButton.setEnabled(!phoneNumber.isEmpty() && !password.isEmpty());
            passwordCheck();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private void registerUser() {
        String phoneNumber = phoneNumberRegister.getEditText().getText().toString();
        String password = passwordRegister.getEditText().getText().toString();

        if (phoneNumber.isEmpty()) {
            phoneNumberRegister.setError("Please enter your phone number");
            phoneNumberRegister.requestFocus();
        } else if (password.isEmpty()) {
            passwordRegister.setError("Please enter your password");
            passwordRegister.requestFocus();
        } else if (password.length() < 6) {
            passwordRegister.setError("Password must be at least 6 characters");
            passwordRegister.requestFocus();
        } else {
            phoneNumberRegister.setError(null);
            passwordRegister.setError(null);
        }
    }

    private void passwordCheck(){
        String password = passwordRegister.getEditText().getText().toString();

        if (password.length() >= 8){
            cardViewMinimumEightCharacter.setCardBackgroundColor(getResources().getColor(R.color.cyan));
            isEightCharacter = true;
        } else {
            cardViewMinimumEightCharacter.setCardBackgroundColor(getResources().getColor(R.color.gray));
            isEightCharacter = false;
        }

        if (password.matches(".*[A-Z].*")){
            cardViewMinimumOneUpperCase.setCardBackgroundColor(getResources().getColor(R.color.cyan));
            isOneUpperCase = true;
        } else {
            cardViewMinimumOneUpperCase.setCardBackgroundColor(getResources().getColor(R.color.gray));
            isOneUpperCase = false;
        }

        if (password.matches(".*[0-9].*")){
            cardViewMinimumOneDigitNumber.setCardBackgroundColor(getResources().getColor(R.color.cyan));
            isOneDigitNumber = true;
        } else {
            cardViewMinimumOneDigitNumber.setCardBackgroundColor(getResources().getColor(R.color.gray));
            isOneDigitNumber = false;
        }

        if (password.matches(".*[@#$%^&+=].*")){
            cardViewMinimumOneSpecialCharacter.setCardBackgroundColor(getResources().getColor(R.color.cyan));
            isOneSpecialCharacter = true;
        } else {
            cardViewMinimumOneSpecialCharacter.setCardBackgroundColor(getResources().getColor(R.color.gray));
            isOneSpecialCharacter = false;
        }

    }

//    private void checkAllData(String passwordField){
//        String passwordField = passwordRegister.getEditText().getText().toString();
//
//        if (isEightCharacter && isOneUpperCase && isOneDigitNumber && isOneSpecialCharacter){
//            isRegistrationButtonClikable = true;
//            registerButton.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//       } else {
//            isRegistrationButtonClikable = false;
//            registerButton.setCardBackgroundColor(getResources().getColor(R.color.gray));
//        }
//    }
}