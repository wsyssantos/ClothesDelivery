package br.com.fiap.roupas.delivery.list;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import br.com.fiap.roupas.R;
import br.com.fiap.roupas.map.MapActivity;
import br.com.fiap.roupas.model.DeliveryVO;

public class DeliveryListFragment extends Fragment implements DeliveryListItemCallback
{
	private ListView list;
	private List<DeliveryListItem> listItems = new ArrayList<DeliveryListItem>();
	private DeliveryListFragmentCallback callback;
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		
		if(activity instanceof DeliveryListFragmentCallback)
		{
			callback = (DeliveryListFragmentCallback) activity;
		}
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View result = inflater.inflate(R.layout.deliveries_list, container, false);
		list = (ListView) result.findViewById(R.id.list);
		
		list.setAdapter(new DeliveriesListAdapter(getActivity()));
		
		return result;
	}
	
	public void setDeliveries(List<DeliveryVO> deliveries)
	{
		setSelectedDelivery(null);
		DeliveriesListAdapter adapter = (DeliveriesListAdapter) list.getAdapter();
		adapter.setDeliveries(deliveries);
		adapter.notifyDataSetChanged();
	}
	
	private class DeliveriesListAdapter extends BaseAdapter
	{
		private final Context context;
		private List<DeliveryVO> deliveries = new ArrayList<DeliveryVO>();
		
		public DeliveriesListAdapter(Context context)
		{
			super();
			this.context = context;
		}

		@Override
		public int getCount()
		{
			return deliveries.size();
		}

		@Override
		public Object getItem(int arg0)
		{
			return deliveries.get(arg0);
		}

		@Override
		public long getItemId(int arg0)
		{
			return deliveries.get(arg0).getDeliveryId();
		}

		@Override
		public View getView(int position, View view, ViewGroup parent)
		{
			if(view == null)
			{
				view = new DeliveryListItem(context);
				((DeliveryListItem)view).setCallback(DeliveryListFragment.this);
				listItems.add(((DeliveryListItem)view));
			}
			DeliveryListItem deliveryListItem = (DeliveryListItem) view;
			deliveryListItem.reset();
			deliveryListItem.setDelivery(deliveries.get(position));
			
			return view;
		}

		public void setDeliveries(List<DeliveryVO> deliveries)
		{
			this.deliveries = deliveries;
		}
	}

	@Override
	public void select(DeliveryVO delivery)
	{
		for(DeliveryListItem item : listItems)
		{
			if(!item.getDelivery().equals(delivery) )
			{
				item.uncheck();
			}
		}
		setSelectedDelivery(delivery);
	}

	@Override
	public void deselect(DeliveryVO delivery)
	{
		setSelectedDelivery(null);
	}

	private void setSelectedDelivery(DeliveryVO selectedDelivery)
	{
		callback.setSelectedDelivery(selectedDelivery);
	}


	@Override
	public void callMap(DeliveryVO delivery, Context context) 
	{
		Intent intent = new Intent(context, MapActivity.class);
		intent.putExtra("mapType", "FINISHED");
		intent.putExtra("delivery", delivery);
		startActivity(intent);
	}
}
