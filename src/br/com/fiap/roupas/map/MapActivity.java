package br.com.fiap.roupas.map;

import java.io.IOException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import br.com.fiap.roupas.R;
import br.com.fiap.roupas.model.DeliveryVO;
import br.com.fiap.roupas.util.Constants;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends FragmentActivity implements OnClickListener
{
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;
//	private String mapType = "";
//	private ToggleButton btnPendingDeliveries;
//	private ToggleButton btnFinishedDeliveries;
//	private List<DeliveryVO> deliveryList;

	private MapService mapService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_deliveries);
//		btnPendingDeliveries = (ToggleButton) findViewById(R.id.btnPendingDel);
//		btnFinishedDeliveries = (ToggleButton) findViewById(R.id.btnFinishedDel);
		mapService = new MapService();
		
		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		
		Intent intent = getIntent();
		
//		mapType = intent.getStringExtra("mapType");
//
//		if(mapType != null) 
//		{
//			deliveryList = mapService.configureMapType(mapType, btnFinishedDeliveries, btnPendingDeliveries);
//		}
		
		DeliveryVO delivery = (DeliveryVO)intent.getSerializableExtra("delivery");
		
		if(delivery != null)
		{
			try 
			{
				mapService.addMarker(map, delivery, getApplicationContext());
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
//		btnFinishedDeliveries.setOnClickListener(this);
//		btnPendingDeliveries.setOnClickListener(this);
//		
//		try 
//		{
//			mapService.addMapMarkers(map, deliveryList, getApplicationContext());
//		}
//		catch(Exception e)
//		{
//			Log.e("MAP", e.getMessage());
//		}

		try 
		{
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.getStandartAddress(getApplicationContext()), 15));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 1000, null);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View v) 
	{
//		try 
//		{
//			if(v.getId() == R.id.btnFinishedDel)
//			{
//				deliveryList = mapService.getFinalizedDeliveries();
//				mapService.addMapMarkers(map, deliveryList, getApplicationContext());
//			}
//			else if( v.getId() == R.id.btnPendingDel )
//			{
//				deliveryList = mapService.getPendingDeliveries();
//				mapService.addMapMarkers(map, deliveryList, getApplicationContext());
//			}
//		}
//		catch(Exception e)
//		{
//			Log.e("MAP", e.getMessage());
//		}
	}
}
