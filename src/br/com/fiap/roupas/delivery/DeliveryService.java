package br.com.fiap.roupas.delivery;

import java.util.List;

import br.com.fiap.roupas.model.DeliveryVO;
import br.com.fiap.roupas.util.FakeDatabase;
import br.com.fiap.roupas.util.Utilities;

public class DeliveryService
{
	public List<DeliveryVO> getPendentDeliveries()
	{
		//TODO: Diones, listar os deliveries pendentes
		Utilities.simulateWork();
		return FakeDatabase.getInstance().getPendentDeliveries();
	}
	
	public List<DeliveryVO> getFinishedDeliveries()
	{
		//TODO: Diones, listar os deliveries finalizados
		Utilities.simulateWork();
		return FakeDatabase.getInstance().getFinishedDeliveries();
	}
}
