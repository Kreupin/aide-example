package com.projectvirus.aideexample;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.text.*;
import android.text.style.*;
import android.view.*;
import android.content.*;
import android.text.method.*;

public class JavLogin extends Activity
{
	DbAccount db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		db = new DbAccount(this);
		
		final EditText eUser = findViewById(R.id.edit_user);
		final EditText ePass = findViewById(R.id.edit_pass);
		CheckBox chkPass = findViewById(R.id.check_pass);
		Button bLogin = findViewById(R.id.button_login);
		
		bLogin.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				String user = eUser.getText().toString();
				String pass = ePass.getText().toString();
				
				if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass))
				{
					boolean b = db.CheckLogin(user,pass);
					if(b == true)
					{
						Intent i = new Intent(JavLogin.this,MainActivity.class);
						startActivity(i);
						JavLogin.this.finish();
					}
				}
			}
		});
		
		TextView tRegis = findViewById(R.id.text_regis);
		
		String regis = ">> Register NewAccount <<";
		SpannableStringBuilder span = new SpannableStringBuilder(regis);
		ClickableSpan clickSpan = new ClickableSpan()
		{
			@Override
			public void onClick(View view)
			{
				Intent i = new Intent(JavLogin.this,JavRegis.class);
				startActivity(i);
				JavLogin.this.finish();
			}
		};
		span.setSpan(clickSpan,regis.indexOf("Register NewAccount"),regis.indexOf("Register NewAccount") + String.valueOf("Register NewAccount").length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tRegis.setText(span);
		tRegis.setMovementMethod(LinkMovementMethod.getInstance());
	} 
}
