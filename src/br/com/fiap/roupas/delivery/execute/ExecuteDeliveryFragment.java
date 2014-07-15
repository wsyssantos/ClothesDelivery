package br.com.fiap.roupas.delivery.execute;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.roupas.R;
import br.com.fiap.roupas.delivery.DeliveryLoaderFragmentCallback;
import br.com.fiap.roupas.delivery.DeliveryService;
import br.com.fiap.roupas.model.DeliveryVO;
import br.com.fiap.roupas.util.DialogUtil;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class ExecuteDeliveryFragment extends Fragment
{
	private enum Mode {PENDENT, FINISHED};
	
	private DeliveryLoaderFragmentCallback callback;
	private ToggleButton btnListPendentDeliveries;
	private ToggleButton btnListFinishedDeliveries;
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		callback = (DeliveryLoaderFragmentCallback) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View result = inflater.inflate(R.layout.execute_delivery, container, false);
		btnListPendentDeliveries = (ToggleButton) result.findViewById(R.id.btnListPendentDeliveries);
		btnListFinishedDeliveries = (ToggleButton) result.findViewById(R.id.btnListFinishedDeliveries);
		
		btnListPendentDeliveries.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if(isChecked)
				onBtnListPendentDeliveriesClick();
			}
		});
		
		btnListFinishedDeliveries.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if(isChecked)
				onBtnListFinishedDeliveriesClick();
			}
		});
		
		return result;
	}
	
	private void onBtnListPendentDeliveriesClick()
	{
		btnListFinishedDeliveries.setChecked(false);
		loadDeliveries(Mode.PENDENT);
	}
	
	private void onBtnListFinishedDeliveriesClick()
	{
		btnListPendentDeliveries.setChecked(false);
		loadDeliveries(Mode.FINISHED);
	}
	
	public void loadDeliveries(final Mode mode)
	{
		new AsyncTask<Void, Void, Object>()
		{

			@Override
			protected Object doInBackground(Void... params)
			{
				DeliveryService service = new DeliveryService();
				
				List<DeliveryVO> result = new ArrayList<DeliveryVO>();
				
				if(mode == Mode.PENDENT)
				{
					result = service.getPendentDeliveries();
					
				}
				else if(mode == Mode.FINISHED)
				{
					result = service.getFinishedDeliveries();
				}
				
				return result;
			}
			
			@SuppressWarnings("unchecked")
			protected void onPostExecute(Object result)
			{
				if(result instanceof List<?>)
				{
					List<DeliveryVO> deliveries = (List<DeliveryVO>) result;
					callback.deliveriesLoaded(deliveries);
				}
				else if(result instanceof Exception)
				{
					DialogUtil.showErrorAlert(getActivity(), ((Exception)result).getMessage());
				}
			};
			
		}.execute();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		if(!btnListFinishedDeliveries.isChecked() && !btnListPendentDeliveries.isChecked())
		{
			btnListPendentDeliveries.setChecked(true);
		}
	}
}
