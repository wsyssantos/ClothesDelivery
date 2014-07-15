package br.com.fiap.roupas.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import br.com.fiap.roupas.R;

public class DialogUtil
{
	private static ProgressDialog loadingDialog;
	
	public synchronized static void showLoading(Context context, String title, String message)
	{
		if(loadingDialog == null)
		{
			loadingDialog = ProgressDialog.show(context, title, message);
		}
	}
	
	public synchronized static void showDefaultLoading(Context context)
	{
		String title = context.getResources().getString(R.string.dialog_loading_title);
		String message = context.getResources().getString(R.string.dialog_loading_message);
		showLoading(context, title, message);
	}
	
	public synchronized static void showDefaultLoading(Context context, String message)
	{
		String title = context.getResources().getString(R.string.dialog_loading_title);
		showLoading(context, title, message);
	}
	
	public synchronized static void hideLoading()
	{
		if(loadingDialog != null)
		{
			loadingDialog.hide();
			loadingDialog = null;
		}
	}
	
	public synchronized static void showAlert(Context context, String title, String message)
	{
		hideLoading();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(android.R.string.ok, new OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
			}
		});
		
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public synchronized static void showErrorAlert(Context context, String message)
	{
		String title = context.getResources().getString(R.string.dialog_error_title);
		showAlert(context, title, message);
	}
	
	public synchronized static void showErrorAlert(Context context)
	{
		String message = context.getResources().getString(R.string.dialog_error_message);
		showErrorAlert(context, message);
	}
}
