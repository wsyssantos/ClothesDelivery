package br.com.fiap.roupas.model;

import java.io.Serializable;
import java.util.Date;

public class DeliveryReceiptVO implements Serializable
{
	private static final long serialVersionUID = 1L;
	private final Long internalId;
	private final String name;
	private final String rg;
	private String photoUrl;
	private final Date deliveryDate;

	public DeliveryReceiptVO(Long internalId, String name, String rg, String photoUrl, Date deliveryDate)
	{
		super();
		this.internalId = internalId;
		this.name = name;
		this.rg = rg;
		this.photoUrl = photoUrl;
		this.deliveryDate = deliveryDate;
	}

	public Long getInternalId()
	{
		return internalId;
	}

	public String getName()
	{
		return name;
	}

	public String getRg()
	{
		return rg;
	}

	public String getPhotoUrl()
	{
		return photoUrl;
	}

	public Date getDeliveryDate()
	{
		return deliveryDate;
	}
	
	public void setPhotoUrl(String photoUrl)
	{
		this.photoUrl = photoUrl;
	}

}
