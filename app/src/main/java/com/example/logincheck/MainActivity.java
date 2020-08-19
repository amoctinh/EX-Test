package com.example.logincheck;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.Toast;


import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainActivity extends Activity implements View.OnClickListener {

    private String username,password;
    private Button ok;
    private EditText editTextUsername,editTextPassword;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ok = (Button)findViewById(R.id.buttonLogin);
        ok.setOnClickListener(this);
        editTextUsername = (EditText)findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        saveLoginCheckBox = (CheckBox)findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            editTextUsername.setText(loginPreferences.getString("username", ""));
            editTextPassword.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
    }

    public void onClick(View view) {
        if (view == ok) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);

            username = editTextUsername.getText().toString();
            password = editTextPassword.getText().toString();

            if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", username);
                loginPrefsEditor.putString("password", password);
                loginPrefsEditor.commit();

            }



            else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }

            doSomethingElse();
        }
    }




    public void doSomethingElse() {
        if (username.length() > 0) {

            if (password.length() > 6) {


                Pattern numberPat = Pattern.compile("\\d+");
                Matcher matcher1 = numberPat.matcher(password);

                Pattern stringPat = Pattern.compile("", Pattern.CASE_INSENSITIVE);
                Matcher matcher2 = stringPat.matcher(password);

                if (matcher1.find() && matcher2.find()) {


                    Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher(password);
                    boolean b = m.find();
                    if (b) {
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();

                        /* liên kết sang class DangXuat mở layout bên logout*/

                        Intent it = new Intent(MainActivity.this, DangXuat.class);
                        startActivity(it);
                    } else {
                        Toast.makeText(MainActivity.this, "vui lòng nhập mật khẩu có ít nhất 1  ký tự đặc biệt ", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "vui lòng nhập mật khẩu có ít nhất 1 chữ số ", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "vui lòng nhập mật khẩu  >6 ký tự  ", Toast.LENGTH_LONG).show();
            }
        }
        if (username.length() == 0) {
            Toast.makeText(MainActivity.this, "vui lòng nhập tài khoản", Toast.LENGTH_LONG).show();
        }
    }
}
