package com.vishal.samplewebcallstructure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vishal.samplewebcallstructure.controller.LoginController;
import com.vishal.samplewebcallstructure.helper.Util;
import com.vishal.samplewebcallstructure.helper.api.interfaces.OnWebAPIResponseListener;
import com.vishal.samplewebcallstructure.helper.api.response.ErrorResponse;
import com.vishal.samplewebcallstructure.model.UserModel;
import com.vishal.samplewebcallstructure.model.basemodel.BaseResponse;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements OnWebAPIResponseListener {

    private static final int RC_LOGIN = 222;
    @BindView(R.id.login_email_et)
    TextInputEditText etEmail;
    @BindView(R.id.login_email_til)
    TextInputLayout tilEmail;
    @BindView(R.id.login_password_et)
    TextInputEditText etPassword;
    @BindView(R.id.login_password_til)
    TextInputLayout tilPassword;
    @BindView(R.id.login_forgot_pwd_tv)
    TextView tvForgotPwd;
    @BindView(R.id.reg_card_view)
    CardView regCardView;
    @BindView(R.id.login_done_fab)
    Button btnDone;
    private AppCompatActivity context;
    private LoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        controller = new LoginController(this, context);
        etEmail.addTextChangedListener(new MyTextWatcher(tilEmail));
        etPassword.addTextChangedListener(new MyTextWatcher(tilPassword));

    }

    @Override
    public void onCallComplete(BaseResponse baseResponse, int requestCode) {
        switch (requestCode) {
            case RC_LOGIN:
                if (baseResponse != null) {
                    if (baseResponse.getData() != null) {

//                        hideProgress();
//
//                        showSnackBar(baseResponse.getMessage(), false);

                        if (baseResponse.getData() instanceof UserModel) {
                            UserModel userModel = (UserModel) baseResponse.getData();

                            if (userModel != null) {

                                Toast.makeText(this,"Login Successfull",Toast.LENGTH_SHORT).show();


//                                if (TextUtils.isEmpty(PrefManager.getPregDate(context))) {
//                                    Intent intent = new Intent(context, SetDueDateActivity.class);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    startActivity(intent);
//                                    context.finish();
//                                } else {
//                                Intent intent = new Intent(context, MainActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                                context.finish();
//                                }


                            }
                        }

//


                    }
                }
                break;
        }
    }

    @Override
    public void onCallError(ErrorResponse errorModel, int requestCode) {
        switch (requestCode) {
            case RC_LOGIN:
                if (errorModel != null) {
                    if (errorModel.getMessage() != null) {
                        Toast.makeText(this,errorModel.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @OnClick({R.id.login_forgot_pwd_tv, R.id.login_done_fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_forgot_pwd_tv:

                // do for forgot password
                break;
            case R.id.login_done_fab:
                login();
                break;
        }
    }
    //Load verification fragment to Verify User
    private void login() {


        boolean isValid = checkValidation();


        if (isValid) {
            if (Util.isInternetOn(context)) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                // showProgress();
                controller.loginUser(RC_LOGIN, email, password);

            } else {
                //hideProgress();
                //showSnackBar(getString(R.string.error_no_internet_connection), true);
            }


        } else {

            //hideProgress();

        }


    }


    // check all validations
    private boolean checkValidation() {
        if (TextUtils.isEmpty(etEmail.getText().toString().trim())) {
            tilEmail.setError(context.getResources().getString(R.string.empty_email));
            return false;
        } else if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
            tilPassword.setError(context.getResources().getString(R.string.empty_password));
            return false;
        } else if (!com.vishal.samplewebcallstructure.helper.Util.isValidEmail(etEmail.getText().toString().trim())) {

            tilEmail.setError(context.getResources().getString(R.string.error_incorrect_email));

            return false;
        } else if (!Util.isValidPassword(etPassword.getText().toString().trim())) {
            tilPassword.setError(context.getResources().getString(R.string.error_incorrect_password));

            return false;
        }
        return true;
    }


    // Handle edittext listener
    class MyTextWatcher implements TextWatcher {
        View inputLayout;


        public MyTextWatcher(TextInputLayout inputLayout) {
            this.inputLayout = inputLayout;

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            switch (inputLayout.getId()) {
                case R.id.login_email_til:
                    if (!TextUtils.isEmpty(text))
                        tilEmail.setError(null);
                    break;
                case R.id.login_password_til:
                    if (!TextUtils.isEmpty(text))
                        tilPassword.setError(null);
                    break;

            }


        }


    }
}
