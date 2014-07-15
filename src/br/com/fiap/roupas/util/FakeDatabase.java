package br.com.fiap.roupas.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import br.com.fiap.roupas.model.ClientVO;
import br.com.fiap.roupas.model.DeliveryReceiptVO;
import br.com.fiap.roupas.model.DeliveryStatus;
import br.com.fiap.roupas.model.DeliveryVO;

public class FakeDatabase
{
	private static FakeDatabase instance;
	private static Map<Long, DeliveryVO> deliveries;
	
	public synchronized static FakeDatabase getInstance()
	{
		if(instance == null)
		{
			instance = new FakeDatabase();
		}
		
		return instance;
	}
	
	@SuppressLint("UseSparseArrays")
	private FakeDatabase()
	{
		deliveries = new HashMap<Long, DeliveryVO>();
	}
	
	public void init()
	{
		ClientVO client = new ClientVO("Fiap", "cliente@teste.com", "SP", "Sao Paulo", "Cambuci", "Rua Linz de Vasconcelos 1222", "00000000", "(11)11111-1111");
		
		Calendar calendar = new GregorianCalendar();
		
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 2);
		Date tomorrow = calendar.getTime();
		
		DeliveryReceiptVO receipt = new DeliveryReceiptVO(111111111l, "Porteiro", "11.111.111-1", "https://qqcoisa.com/image.jpg", yesterday);
		DeliveryVO deliverFinalized = new DeliveryVO(111111111l, "5 camisas polo", yesterday, yesterday, client, receipt, DeliveryStatus.FINALIZED);
		DeliveryVO deliverPendent = new DeliveryVO(222222222l, "10 camisas polo", today, today, client, null, DeliveryStatus.PENDENT);
		DeliveryVO deliverPendent2 = new DeliveryVO(333333333l, "10 camisas polo", today, today,  client, null, DeliveryStatus.PENDENT);
		DeliveryVO deliverForTomorrow = new DeliveryVO(444444444l, "10 camisas do Corinthians", tomorrow, tomorrow, client, null, DeliveryStatus.PENDENT);
		
		deliveries.put(deliverFinalized.getDeliveryId(), deliverFinalized);
		deliveries.put(deliverPendent.getDeliveryId(), deliverPendent);
		deliveries.put(deliverPendent2.getDeliveryId(), deliverPendent);
		deliveries.put(deliverForTomorrow.getDeliveryId(), deliverForTomorrow);
	}
	
	@SuppressLint("UseSparseArrays")
	public void clean()
	{
		deliveries = new HashMap<Long, DeliveryVO>();
	}
	
	public List<DeliveryVO> getPendentDeliveries()
	{
		List<DeliveryVO> result = new ArrayList<DeliveryVO>();
		Collection<DeliveryVO> values = deliveries.values();
		for (DeliveryVO delivery : values)
		{
			if(delivery.getDeliveryStatus() == DeliveryStatus.PENDENT)
			{
				result.add(delivery);
			}
		}
		
		return result;
	}
	
	public List<DeliveryVO> getFinishedDeliveries()
	{
		List<DeliveryVO> result = new ArrayList<DeliveryVO>();
		Collection<DeliveryVO> values = deliveries.values();
		for (DeliveryVO delivery : values)
		{
			if(delivery.getDeliveryStatus() == DeliveryStatus.FINALIZED)
			{
				result.add(delivery);
			}
		}
		
		return result;
	}
	
	public List<DeliveryVO> getDeliveriesForTomorrow()
	{
		List<DeliveryVO> result = new ArrayList<DeliveryVO>();
		Collection<DeliveryVO> values = deliveries.values();
		for (DeliveryVO delivery : values)
		{
			Calendar now = new GregorianCalendar();
			Calendar deliveryCalendar = new GregorianCalendar();
			deliveryCalendar.setTime(delivery.getStartDate());
			
			if(deliveryCalendar.get(Calendar.DAY_OF_YEAR) + 1 == now.get(Calendar.DAY_OF_YEAR))
			{
				result.add(delivery);
			}
		}
		
		return result;
	}
	
	public List<DeliveryVO> getAll()
	{
		List<DeliveryVO> result = new ArrayList<DeliveryVO>();
		Collection<DeliveryVO> values = deliveries.values();
		for (DeliveryVO delivery : values)
		{
			result.add(delivery);
		}
		
		return result;
	}
}
