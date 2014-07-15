package br.com.fiap.roupas.tablemenu;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import br.com.fiap.roupas.delivery.execute.ExecuteDeliveryActivity;
import br.com.fiap.roupas.model.SharedState;

public class TableMenuActivity extends Activity implements TableMenuCallback
{
	private TableMenuFragment tableMenuFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		getActionBar().setTitle(SharedState.getInstance().getUserVO().getName());
		
		tableMenuFragment = new TableMenuFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.add(android.R.id.content, tableMenuFragment).commit();
		
	}

	@Override
	public void goToExecuteDeliveries()
	{
		Intent intent = new Intent(getApplicationContext(), ExecuteDeliveryActivity.class);
		startActivity(intent);
	}

	@Override
	public void goToFinalizedDeliveries()
	{
	}

	@Override
	public void goToDeliveriesForTomorrow()
	{
	}

	@Override
	public void cancelDeliveries()
	{
	}

	@Override
	public void registerReason()
	{
	}

	@Override
	public void deliveriesSynchronized()
	{
	}

	@Override
	public void databaseCleaned()
	{
	}

	@Override
	public void changePassword()
	{
	}

	@Override
	public void logout()
	{
		finish();
	}
}
