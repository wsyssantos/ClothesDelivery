package br.com.fiap.roupas.delivery.list;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import br.com.fiap.roupas.R;
import br.com.fiap.roupas.model.DeliveryVO;

public class DeliveryListItem extends FrameLayout
{
	private final SimpleDateFormat sdfHour;
	private final SimpleDateFormat sdfDay;
	
	private DeliveryVO delivery;
	private boolean open = false;
	private ViewGroup viewDetail;

	private TextView lblDistrict;
	private TextView lblDeliveryId;
	private TextView lblDeliveryDay;
	private TextView lblDeliveryHour;
	private TextView lblClientName;
	private TextView lblDeliveryAddress;
	private TextView lblCity;
	private TextView lblEmail;
	private TextView lblPhone;
	private Button btnDetail;
	private Button btnMap;
	private ToggleButton btnSelect;
	private DeliveryListItemCallback callback;
	
	public DeliveryListItem(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		
		Locale locale = getContext().getResources().getConfiguration().locale;
		sdfHour = new SimpleDateFormat("hh", locale);
		sdfDay = new SimpleDateFormat("dd/MM/yy", locale);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.addView(inflater.inflate(R.layout.delivery_list_item, null));

		viewDetail = (ViewGroup) findViewById(R.id.viewDetail);

		lblDistrict = (TextView) findViewById(R.id.lblDistrict);
		lblDeliveryId = (TextView) findViewById(R.id.lblDeliveryId);
		lblDeliveryDay = (TextView) findViewById(R.id.lblDeliveryDay);
		lblDeliveryHour = (TextView) findViewById(R.id.lblDeliveryHour);
		lblClientName = (TextView) findViewById(R.id.lblClientName);
		lblDeliveryAddress = (TextView) findViewById(R.id.lblDeliveryAddress);
		lblCity = (TextView) findViewById(R.id.lblCity);
		lblEmail = (TextView) findViewById(R.id.lblEmail);
		lblPhone = (TextView) findViewById(R.id.lblPhone);
		btnDetail = (Button)findViewById(R.id.btnDetail);
		btnDetail = (Button)findViewById(R.id.btnDetail);
		btnSelect = (ToggleButton) findViewById(R.id.btnSelect);
		btnMap = (Button) findViewById(R.id.btnMap);

		Button btnDetail = (Button) findViewById(R.id.btnDetail);
		btnDetail.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!open)
				{
					open();
				}
				else
				{
					close();
				}
			}
		});
		
		btnSelect.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				ToggleButton toggleButton = (ToggleButton) v;
				onBtnSelectCheck(toggleButton.isChecked());
			}
		});
		
		btnMap.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				callback.callMap(delivery, getContext());
			}
		});
	}
	
	public DeliveryListItem(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public DeliveryListItem(Context context)
	{
		this(context, null);
	}

	public DeliveryVO getDelivery()
	{
		return delivery;
	}

	public void setDelivery(DeliveryVO delivery)
	{
		this.delivery = delivery;
		
		lblCity.setText(delivery.getClient().getCity());
		lblClientName.setText(delivery.getClient().getName());
		lblDeliveryAddress.setText(delivery.getClient().getAddress());
		lblDeliveryDay.setText(sdfDay.format(delivery.getStartDate()));
		
		String strDay = sdfDay.format(delivery.getStartDate());
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(delivery.getStartDate());
		Calendar tomorrowCalendar = new GregorianCalendar();
		tomorrowCalendar.add(Calendar.DAY_OF_YEAR, 1);
		
		if(tomorrowCalendar.get(Calendar.DAY_OF_YEAR) - startCalendar.get(Calendar.DAY_OF_YEAR) == 1)
		{
			strDay = getResources().getString(R.string.tomorrow);
		}
		lblDeliveryDay.setText(strDay);
		
		String strHour = String.format("%sh - %sh", sdfHour.format(delivery.getStartDate()), sdfHour.format(delivery.getEndDate()));
		lblDeliveryHour.setText(strHour);
		
		lblDeliveryId.setText(delivery.getDeliveryId().toString());
		lblDistrict.setText(delivery.getClient().getDistrict());
		lblEmail.setText(delivery.getClient().getEmail());
		lblPhone.setText(delivery.getClient().getPhone());
	}
	
	public void onBtnSelectCheck(boolean checked)
	{
		if(callback != null && checked)
		{
			callback.select(delivery);
		}
		else if(callback != null && !checked)
		{
			callback.deselect(delivery);
		}
	}
	
	public void check()
	{
		btnSelect.setChecked(true);
	}
	
	public void uncheck()
	{
		btnSelect.setChecked(false);
	}
	
	public void reset()
	{
		btnSelect.setChecked(false);
		close();
	}
	
	public void close()
	{
		open = false;
		ViewGroup.LayoutParams params = viewDetail.getLayoutParams();
		params.height = 0;
		viewDetail.setLayoutParams(params);
		btnDetail.setBackground(getContext().getResources().getDrawable(R.drawable.expander_open_holo_light));
	}

	public void open()
	{
		open = true;
		ViewGroup.LayoutParams params = viewDetail.getLayoutParams();
		params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		viewDetail.setLayoutParams(params);
		btnDetail.setBackground(getContext().getResources().getDrawable(R.drawable.expander_close_holo_light));
	}

	public void setCallback(DeliveryListItemCallback callback)
	{
		this.callback = callback;
	}
}
