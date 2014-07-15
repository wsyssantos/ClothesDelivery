package br.com.fiap.roupas.delivery.finish;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import br.com.fiap.roupas.R;

public class FinishDeliveryFragment extends Fragment
{
	private FinishDeliveryFragmentCallback callback;
	
	private Button btnFinishDelivery;
	private Button btnDetail;
	private View viewDetail;
	private EditText txtName;
	private EditText txtRg;
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		
		if(activity instanceof FinishDeliveryFragmentCallback)
		{
			callback = (FinishDeliveryFragmentCallback) activity;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View result = inflater.inflate(R.layout.finish_delivery, container, false);
		
		btnFinishDelivery = (Button) result.findViewById(R.id.btnFinishDelivery);
		btnDetail = (Button) result.findViewById(R.id.btnDetail);
		viewDetail = result.findViewById(R.id.viewDetail);
		txtName = (EditText) result.findViewById(R.id.txtName);
		txtRg = (EditText) result.findViewById(R.id.txtRg);
		
		return result;
	}
}
