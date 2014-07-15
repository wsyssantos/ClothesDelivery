package br.com.fiap.roupas.util;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

public class Constants
{
	public static final String FRAGMENT_TAG = "fragment";
	
	public static LatLng getStandartAddress( Context context ) throws IOException
	{
		List<Address> adresses = new Geocoder(context).getFromLocationName("Av. Lins de Vasconcelos, 1222, Aclimação São Paulo - SP", 1);
		
		double latitude = 0.0;
		double longitude = 0.0;
		
		Address homeSweetHome = adresses.get(0);
		latitude = homeSweetHome.getLatitude();
		longitude = homeSweetHome.getLongitude();
		
		LatLng result = new LatLng(latitude, longitude);
		
		return result;
	}
}
