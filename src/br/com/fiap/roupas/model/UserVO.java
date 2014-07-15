package br.com.fiap.roupas.model;

public class UserVO
{
	private final Long id;
	private final String name;
	private final String token;

	public UserVO(Long id, String name, String token)
	{
		super();
		this.id = id;
		this.name = name;
		this.token = token;
	}

	public Long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getToken()
	{
		return token;
	}
}
