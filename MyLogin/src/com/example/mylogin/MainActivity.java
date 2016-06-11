package com.example.mylogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

import com.example.mylogin.Signin;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView txtSignup = (TextView) findViewById(R.id.txtCreateAccount);
		txtSignup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,SignupActivity.class);
				startActivity(intent);
			}
		});
		
		Button btnSignin = (Button) findViewById(R.id.btnSignin);
		btnSignin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText editTextEmail = (EditText) findViewById(R.id.etxtUsername);
				
				EditText editTextPassword = (EditText)findViewById(R.id.etxtPassword);
				boolean i = Signin.Login(editTextEmail.getText().toString(), editTextPassword.getText().toString());
				if(i){
					Toast.makeText(MainActivity.this, "Login successfull!", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainActivity.this, "Login fail!", Toast.LENGTH_SHORT).show();
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
