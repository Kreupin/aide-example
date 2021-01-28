package com.projectvirus.aideexample;
import android.app.*;
import android.os.*;
import android.database.sqlite.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import android.text.*;

public class JavRegis extends Activity
{
	DbAccount db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regis);
		
		db = new DbAccount(this);
		
		final EditText eUser = findViewById(R.id.edit_newUser);
		final EditText ePass = findViewById(R.id.edit_newPass);
		final EditText eConfirm = findViewById(R.id.edit_confirm);
		CheckBox chkPass = findViewById(R.id.check_newPass);
		CheckBox chkConfirm = findViewById(R.id.check_confirm);
		Button bRegis = findViewById(R.id.button_regis);
		
		bRegis.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				String newUser = eUser.getText().toString();
				String newPass = ePass.getText().toString();
				String confirm = eConfirm.getText().toString();
				
				if(TextUtils.isEmpty(newUser) && !TextUtils.isEmpty(newPass) || (TextUtils.isEmpty(confirm) && !TextUtils.isEmpty(confirm)))
				{
					eUser.setError("No empty this field");
					eUser.requestFocus();
					return;
				}
				
				if(!TextUtils.isEmpty(newUser) && TextUtils.isEmpty(newPass) || (TextUtils.isEmpty(confirm) && !TextUtils.isEmpty(confirm)))
				{
					ePass.setError("No empty this field");
					ePass.requestFocus();
					return;
				}
				
				if(!TextUtils.isEmpty(newUser) && !TextUtils.isEmpty(newPass) && !confirm.equals(newPass))
				{
					eConfirm.setError("Password don't match");
					eConfirm.requestFocus();
					return;
				}
				else
				{
					boolean chkUser = db.CheckUsername(newUser);
					if(chkUser == true)
					{
						boolean b = db.Insert(newUser,newPass);
						if(b == true)
						{
							Toast.makeText(JavRegis.this,"SUCCESS !!",Toast.LENGTH_SHORT).show();
							eUser.setText("");
							ePass.setText("");
							eConfirm.setText("");
						}
					}
					else
					{
						eUser.setError("Username is already");
						eUser.requestFocus();
						return;
					}
				}
			}		
		});
	}

	@Override
	public void onBackPressed()
	{
		Intent i = new Intent(this,JavLogin.class);
		startActivity(i);
		this.finish();
	} 
}
