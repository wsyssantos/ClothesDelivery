package br.com.fiap.roupas.map;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ToggleButton;
import br.com.fiap.roupas.model.DeliveryVO;
import br.com.fiap.roupas.util.FakeDatabase;
import br.com.fiap.roupas.util.MapUtil;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapService 
{
	public MapService() 
	{
		FakeDatabase.getInstance().init();
	}
	
	public List<DeliveryVO> configureMapType( String mapType, ToggleButton btnFinished, ToggleButton btnPending )
	{
		
		if(mapType.equals("FINISHED"))
		{
			btnFinished.setVisibility(View.INVISIBLE);
			btnPending.setVisibility(View.INVISIBLE);
			
			return getFinalizedDeliveries();
		}
		else if(mapType.equals("PENDING"))
		{
			btnFinished.setVisibility(View.INVISIBLE);
			btnPending.setVisibility(View.INVISIBLE);
			
			return getPendingDeliveries();
		}
		else 
		{
			return getPendingDeliveries();
		}
	}
	
	public void addMapMarkers( GoogleMap map, List<DeliveryVO> deliveries, Context context ) throws IOException
	{
		map.clear();
		
		for(DeliveryVO delivery : deliveries)
		{
			map.addMarker(new MarkerOptions().position(MapUtil.getAddressPosition(context, delivery)));
		}
	}
	
	public void addMarker( GoogleMap map, DeliveryVO delivery, Context context ) throws IOException
	{
		map.addMarker(new MarkerOptions().position(MapUtil.getAddressPosition(context, delivery)));
	}
	
	public List<DeliveryVO> getPendingDeliveries( )
	{
		List<DeliveryVO> result = null;
		
		FakeDatabase database = FakeDatabase.getInstance();
		
		result = database.getPendentDeliveries();
		
		return result;
	}
	
	public List<DeliveryVO> getFinalizedDeliveries( )
	{
		List<DeliveryVO> result = null;
		
		FakeDatabase database = FakeDatabase.getInstance();
		
		result = database.getFinishedDeliveries();
		
		return result;
	}
}
