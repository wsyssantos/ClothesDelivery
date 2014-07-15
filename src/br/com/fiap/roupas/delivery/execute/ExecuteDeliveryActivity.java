package br.com.fiap.roupas.delivery.execute;

import java.util.List;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import br.com.fiap.roupas.R;
import br.com.fiap.roupas.delivery.DeliveryLoaderFragmentCallback;
import br.com.fiap.roupas.delivery.finish.FinishDeliveryActivity;
import br.com.fiap.roupas.delivery.list.DeliveryListFragment;
import br.com.fiap.roupas.delivery.list.DeliveryListFragmentCallback;
import br.com.fiap.roupas.model.DeliveryVO;
import br.com.fiap.roupas.model.SharedState;

public class ExecuteDeliveryActivity extends Activity implements DeliveryLoaderFragmentCallback, DeliveryListFragmentCallback
{
	private DeliveryListFragment listFragment;
	private ExecuteDeliveryFragment executeDeliveryFragment;
	private DeliveryVO selectedDelivery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_execute_delivery);
		
		getActionBar().setTitle(SharedState.getInstance().getUserVO().getName());
		
		listFragment = new DeliveryListFragment();
		executeDeliveryFragment = new ExecuteDeliveryFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.add(R.id.containerExecuteDeliveryFragment, executeDeliveryFragment);
		transaction.add(R.id.containerDeliveryListFragment, listFragment);
		transaction.commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.clear();
		
		if(selectedDelivery != null)
		{
			MenuItem item = menu.add(getResources().getString(R.string.menu_item_delivery));
			item.setOnMenuItemClickListener(new OnMenuItemClickListener()
			{
				@Override
				public boolean onMenuItemClick(MenuItem item)
				{
					finishDeliver(selectedDelivery);
					return false;
				}
			});
		}
		
		return true;
	}
	
	public void finishDeliver(DeliveryVO delivery)
	{
		Intent intent = new Intent(this, FinishDeliveryActivity.class);
		intent.putExtra(FinishDeliveryActivity.INTENT_EXTRA_DELIVERY, delivery);
		startActivity(intent);
	}

	@Override
	public void deliveriesLoaded(List<DeliveryVO> deliveries)
	{
		listFragment.setDeliveries(deliveries);
	}

	@Override
	public void setSelectedDelivery(DeliveryVO delivery)
	{
		selectedDelivery = delivery;
		invalidateOptionsMenu();
	}
}
