package br.com.fiap.roupas.login;

import br.com.fiap.roupas.model.UserVO;
import br.com.fiap.roupas.util.Utilities;

public class LoginService
{

	public UserVO login(String username, String password)
	{
		//TODO: FAKE Guilherme implementa seu servico aqui
		Utilities.simulateWork();
		
		return new UserVO(1l, "User Fake", "XPTOTOKEN");
	}

}
