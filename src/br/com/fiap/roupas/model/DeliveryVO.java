package br.com.fiap.roupas.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import br.com.fiap.roupas.util.Constants;

import com.google.android.gms.maps.model.LatLng;

public class DeliveryVO implements Serializable
{
	private static final long serialVersionUID = 1L;
	private final Long deliveryId;
	private final String description;
	private final Date startDate;
	private final Date endDate;
	private final ClientVO client;
	private final DeliveryReceiptVO receipt;
	private final DeliveryStatus deliveryStatus;

	public DeliveryVO(Long deliveryId, String description, Date startDate, Date endDate, ClientVO client, DeliveryReceiptVO receipt, DeliveryStatus deliveryStatus)
	{
		super();
		this.deliveryId = deliveryId;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.client = client;
		this.receipt = receipt;
		this.deliveryStatus = deliveryStatus;
	}

	public Long getDeliveryId()
	{
		return deliveryId;
	}

	public String getDescription()
	{
		return description;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public ClientVO getClient()
	{
		return client;
	}

	public DeliveryReceiptVO getReceipt()
	{
		return receipt;
	}

	public DeliveryStatus getDeliveryStatus()
	{
		return deliveryStatus;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof DeliveryVO))
		{
			return false;
		}
		DeliveryVO otherDelivery = (DeliveryVO) o;
		
		return this.deliveryId.equals(otherDelivery.deliveryId);
	}
}
