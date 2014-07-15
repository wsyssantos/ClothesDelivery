package br.com.fiap.roupas.model;

public class SharedState
{
	private static SharedState instance;
	
	private UserVO userVO;
	
	public UserVO getUserVO()
	{
		return userVO;
	}

	public void setUserVO(UserVO userVO)
	{
		this.userVO = userVO;
	}

	public static SharedState getInstance()
	{
		synchronized (SharedState.class)
		{
			if(instance == null)
			{
				instance = new SharedState();
			}
			
			return instance;
		}
	}
	
	private SharedState()
	{
	}
}
