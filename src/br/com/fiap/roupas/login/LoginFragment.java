package br.com.fiap.roupas.login;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import br.com.fiap.roupas.R;
import br.com.fiap.roupas.model.UserVO;
import br.com.fiap.roupas.util.DialogUtil;

public class LoginFragment extends Fragment
{
	private LoginFragmentCallback callback;
	private LoginService service = new LoginService();
	
	private EditText txtUsername;
	private EditText txtPassword;
	private Button btnLogin;
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		callback = (LoginFragmentCallback) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View result = inflater.inflate(R.layout.login, container, false); 
		
		txtUsername = (EditText) result.findViewById(R.id.txtUsername);
		txtPassword = (EditText) result.findViewById(R.id.txtPassword);
		btnLogin = (Button) result.findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onBtnLoginClick(v);
			}
		});
		
		return result; 
	}
	
	public void onBtnLoginClick(View view)
	{
		final String username = txtUsername.getText().toString();
		final String password = txtPassword.getText().toString();
		
		DialogUtil.showDefaultLoading(getActivity());
		AsyncTask<Void, Void, Object> asyncTask = new AsyncTask<Void, Void, Object>()
		{
			
			@Override
			protected Object doInBackground(Void... params)
			{
				try
				{
					UserVO result = service.login(username, password);
					return result;
				}
				catch(Exception e)
				{
					return e;
				}
			}
			
			@Override
			protected void onPostExecute(Object result)
			{
				super.onPostExecute(result);
				
				if(result instanceof UserVO)
				{
					UserVO user = (UserVO) result;
					DialogUtil.hideLoading();
					callback.login(user);
				}
				else if(result instanceof Exception)
				{
					DialogUtil.showErrorAlert(getActivity(), ((Exception)result).getMessage());
				}
			}
		};
		asyncTask.execute();
	}
}
