package br.com.fiap.roupas.delivery.list;

import android.content.Context;
import br.com.fiap.roupas.model.DeliveryVO;

public interface DeliveryListItemCallback
{
	public void select(DeliveryVO delivery);
	public void deselect(DeliveryVO delivery);
	public void callMap(DeliveryVO delivery, Context context);
}
