package br.com.fiap.roupas.util;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import br.com.fiap.roupas.model.DeliveryVO;

import com.google.android.gms.maps.model.LatLng;

public class MapUtil 
{
	public static LatLng getAddressPosition( Context context, DeliveryVO delivery ) throws IOException
	{
		StringBuilder addressStr = new StringBuilder();
		addressStr.append(delivery.getClient().getAddress())
				.append(" ")
				.append(delivery.getClient().getCity())
				.append(" - ")
				.append(delivery.getClient().getState())
				.append(" ")
				.append(delivery.getClient().getZipCode());
		
		List<Address> adresses = new Geocoder(context).getFromLocationName(addressStr.toString(), 1);

		LatLng result = null;
		
		if(adresses != null && !adresses.isEmpty())
		{
			double latitude = 0.0;
			double longitude = 0.0;
	
			Address homeSweetHome = adresses.get(0);
			latitude = homeSweetHome.getLatitude();
			longitude = homeSweetHome.getLongitude();
			
			result = new LatLng(latitude, longitude);
		}
		else
		{
			result = Constants.getStandartAddress(context);
		}
		return result;	
	}
}
