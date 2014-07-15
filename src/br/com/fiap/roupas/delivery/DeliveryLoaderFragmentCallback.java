package br.com.fiap.roupas.delivery;

import java.util.List;

import br.com.fiap.roupas.model.DeliveryVO;

public interface DeliveryLoaderFragmentCallback
{
	void deliveriesLoaded(List<DeliveryVO> deliveries);
}
