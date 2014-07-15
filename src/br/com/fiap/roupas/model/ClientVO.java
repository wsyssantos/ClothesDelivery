package br.com.fiap.roupas.model;

import java.io.Serializable;

public class ClientVO implements Serializable
{
	private static final long serialVersionUID = 1L;
	private final String name;
	private final String email;
	private final String state;
	private final String city;
	private final String district;
	private final String address;
	private final String zipCode;
	private final String phone;

	public ClientVO(String name, String email, String state, String city, String district, String address, String zipCode, String phone)
	{
		super();
		this.name = name;
		this.email = email;
		this.state = state;
		this.city = city;
		this.district = district;
		this.address = address;
		this.zipCode = zipCode;
		this.phone = phone;
	}

	public String getName()
	{
		return name;
	}

	public String getEmail()
	{
		return email;
	}

	public String getState()
	{
		return state;
	}

	public String getCity()
	{
		return city;
	}

	public String getDistrict()
	{
		return district;
	}

	public String getAddress()
	{
		return address;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public String getPhone()
	{
		return phone;
	}

}
