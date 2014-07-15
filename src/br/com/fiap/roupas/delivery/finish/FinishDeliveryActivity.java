package br.com.fiap.roupas.delivery.finish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.com.fiap.roupas.R;
import br.com.fiap.roupas.model.DeliveryVO;
import br.com.fiap.roupas.photo.PhotoActivity;

public class FinishDeliveryActivity extends Activity implements FinishDeliveryFragmentCallback
{
	public static final String INTENT_EXTRA_DELIVERY = "delivery";
	private DeliveryVO delivery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finish_delivery);
		delivery = (DeliveryVO) getIntent().getExtras().getSerializable(INTENT_EXTRA_DELIVERY);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK)
		{
			Bundle extras = data.getExtras();
			String imagePath = extras.getString("result");
			
			if(imagePath != null && imagePath.trim().length() > 0)
			{
				if(delivery != null && delivery.getReceipt() != null)
				{
					delivery.getReceipt().setPhotoUrl(imagePath);
				}
			}
		}
	}
	
	public void finishDelivery(View v) 
	{
		Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
		startActivityForResult(intent, 0);
	}
}
