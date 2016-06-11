package com.example.mylogin;

import android.app.Activity;
import com.example.mylogin.InformationOfUser;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mylogin.Signup;

import javax.security.auth.login.LoginException;

import com.example.mylogin.Signin;

public class SignupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
				
		Button btnSignup =  (Button) findViewById(R.id.btnSignup);
		
		btnSignup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				EditText eTextFirstname = (EditText) findViewById(R.id.etxtFirstName);
				
				EditText eTextLastname = (EditText) findViewById(R.id.etxtLastName);
				
				EditText eTextEmail = (EditText) findViewById(R.id.etxtEmail);
				
				EditText eTextPassword = (EditText) findViewById(R.id.etxtPassword);
				
				//New a object have properties  InformationOfUser
				InformationOfUser informationOfUser = new InformationOfUser(eTextFirstname.getText().toString(), eTextLastname.getText().toString(), eTextEmail.getText().toString(), eTextPassword.getText().toString());
				
				CheckBox checkAgree = (CheckBox) findViewById(R.id.checkAcept);
				
				boolean i = Signup.createAccount(informationOfUser);
				
				if(i && checkAgree.isChecked()){
					Toast.makeText(null, "Signup successfull!", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(SignupActivity.this, MainActivity.class);
					startActivity(intent);
				}
				else
				{
					Toast.makeText(null, "Signup fail!", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
}
