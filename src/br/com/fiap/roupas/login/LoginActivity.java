package br.com.fiap.roupas.login;

import br.com.fiap.roupas.model.SharedState;
import br.com.fiap.roupas.model.UserVO;
import br.com.fiap.roupas.tablemenu.TableMenuActivity;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class LoginActivity extends Activity implements LoginFragmentCallback
{
	private LoginFragment loginFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		loginFragment = new LoginFragment();
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.add(android.R.id.content, loginFragment).commit();
	}

	@Override
	public void login(UserVO userVO)
	{
		SharedState sharedState = SharedState.getInstance();
		sharedState.setUserVO(userVO);
		
		Intent intent = new Intent(this, TableMenuActivity.class);
		startActivity(intent);
	}
}
