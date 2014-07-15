package br.com.fiap.roupas;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;
import br.com.fiap.roupas.delivery.list.DeliveryListItem;
import br.com.fiap.roupas.tablemenu.TableMenuFragment;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.change_password);
		DeliveryListItem deliverListItem = new DeliveryListItem(this);
		setContentView(deliverListItem);
		
		FrameLayout frame = new FrameLayout(this);
		frame.setId(R.id.mainView);
        setContentView(frame, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		TableMenuFragment tableMenuFragment = new TableMenuFragment();
		transaction.add(R.id.mainView, tableMenuFragment).commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
