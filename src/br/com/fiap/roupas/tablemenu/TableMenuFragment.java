package br.com.fiap.roupas.tablemenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.com.fiap.roupas.R;
import br.com.fiap.roupas.synchronize.SynchronizeService;
import br.com.fiap.roupas.util.DialogUtil;

public class TableMenuFragment extends Fragment
{
	private TableMenuCallback callback;
	private SynchronizeService synchronizeService = new SynchronizeService();
	
	private List<Integer> items = new ArrayList<Integer>();
	
	public TableMenuFragment()
	{
		items.add(R.string.tablemenu_item_execute_deliver);
		items.add(R.string.tablemenu_item_deliveries_made);
		items.add(R.string.tablemenu_item_deliveries_for_tomorrow);
		items.add(R.string.tablemenu_item_cancel_deliver);
		items.add(R.string.tablemenu_item_register_reason);
		items.add(R.string.tablemenu_synchronize_delivers);
		items.add(R.string.tablemenu_clean_database);
		items.add(R.string.tablemenu_alter_password);
		items.add(R.string.tablemenu_logout);
	}
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		callback = (TableMenuCallback) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View result = inflater.inflate(R.layout.tablemenu, container, false);
		ListView listView = (ListView) result.findViewById(R.id.table);
		listView.setAdapter(new TableMenuAdapter(inflater));
		listView.setOnItemClickListener(new TableMenuSelectListener());
		
		return result;
	}
	
	private class TableMenuSelectListener implements AdapterView.OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position, long id)
		{
			if(R.string.tablemenu_item_execute_deliver == id)
			{
				callback.goToExecuteDeliveries();
			}
			else if(R.string.tablemenu_item_deliveries_made == id)
			{
				callback.goToFinalizedDeliveries();
			}
			else if(R.string.tablemenu_item_deliveries_for_tomorrow == id)
			{
				callback.goToDeliveriesForTomorrow();
			}
			else if(R.string.tablemenu_item_cancel_deliver == id)
			{
				callback.cancelDeliveries();
			}
			else if(R.string.tablemenu_item_register_reason == id)
			{
				callback.registerReason();
			}
			else if(R.string.tablemenu_synchronize_delivers == id)
			{
				synchronize();
			}
			else if(R.string.tablemenu_clean_database == id)
			{
				cleanDatabase();
			}
			else if(R.string.tablemenu_logout == id)
			{
				callback.logout();
			}
		}
	}
	
	private void synchronize()
	{
		DialogUtil.showDefaultLoading(getActivity(), getActivity().getResources().getString(R.string.dialog_synchronizing_message));
		AsyncTask<Void, Void, Object> asyncTask = new AsyncTask<Void, Void, Object>()
		{
			
			@Override
			protected Object doInBackground(Void... params)
			{
				try
				{
					synchronizeService.synchronize();
					return null;
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
				
				if(result instanceof Exception)
				{
					DialogUtil.showErrorAlert(getActivity(), ((Exception)result).getMessage());
				}
				else
				{
					DialogUtil.showAlert(getActivity(), null, getActivity().getResources().getString(R.string.message_deliveries_synchronized_successfully));
					callback.deliveriesSynchronized();
				}
			}
		};
		asyncTask.execute();
	}
	
	private void cleanDatabase()
	{
		DialogUtil.showDefaultLoading(getActivity(), getActivity().getResources().getString(R.string.dialog_cleaning_datase));
		AsyncTask<Void, Void, Object> asyncTask = new AsyncTask<Void, Void, Object>()
		{
			
			@Override
			protected Object doInBackground(Void... params)
			{
				try
				{
					synchronizeService.cleanDatabase();
					return null;
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
				
				if(result instanceof Exception)
				{
					DialogUtil.showErrorAlert(getActivity(), ((Exception)result).getMessage());
				}
				else
				{
					DialogUtil.showAlert(getActivity(), null, getActivity().getResources().getString(R.string.message_database_cleaned_successfully));
					callback.databaseCleaned();
				}
			}
		};
		asyncTask.execute();
	}
	
	private class TableMenuAdapter extends BaseAdapter
	{
		private LayoutInflater inflater;

		public TableMenuAdapter(LayoutInflater inflater)
		{
			this.inflater = inflater;
		}
		
		@Override
		public int getCount()
		{
			return items.size();
		}

		@Override
		public Object getItem(int position)
		{
			return items.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return items.get(position);
		}

		@Override
		public View getView(int position, View view, ViewGroup parent)
		{
			if(view == null)
			{
				view = inflater.inflate(R.layout.tablemenu_item, null);
			}
			
			TextView lbl = (TextView) view.findViewById(R.id.lbl);
			lbl.setText(getResources().getString(items.get(position)));
			
			return view;
		}
		
	}
}
