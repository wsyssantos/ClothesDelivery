package br.com.fiap.roupas.synchronize;

import br.com.fiap.roupas.util.FakeDatabase;
import br.com.fiap.roupas.util.Utilities;

public class SynchronizeService
{
	public void synchronize()
	{
		Utilities.simulateWork();
		//TODO: Guilherme e Diones sincronizem os objetos aqui, lembrem-se de sincronizar as fotos do Wesley tambem
		FakeDatabase.getInstance().init();
	}

	public void cleanDatabase()
	{
		Utilities.simulateWork();
		//TODO: Diones, limpar todos os dados
		FakeDatabase.getInstance().init();
	}
}
