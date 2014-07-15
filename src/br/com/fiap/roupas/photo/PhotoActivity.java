package br.com.fiap.roupas.photo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import br.com.fiap.roupas.R;

public class PhotoActivity extends Activity 
{
	private final static String TAG = "fiap_roupas_photo_activity";
	private ImageView imageView;
	private Bitmap bmp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_layout);
		
		imageView = (ImageView)findViewById(R.id.imageCamera);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK)
		{
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			imageView.setImageBitmap(bmp);
		}
	}
	
	public void clickCamera(View view)
	{
		//camera.takePicture(null, null, new PhotoHandler(getApplicationContext()));
		Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(i, 0);
	}
	
	@Override
	public void finish() 
	{
		String bitmapPath = "";
		if(bmp != null)
		{
			bitmapPath = saveBitmapToSdCard();
		}
		
		Intent returnIntent = new Intent();
		returnIntent.putExtra("result", bitmapPath);
		setResult(RESULT_OK, returnIntent);
		super.finish();
	}
	
	@Override
	protected void onPause() 
	{
		super.onPause();
	}
	
	private String saveBitmapToSdCard( )
	{
		String imagePath = "";
		
		String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String date = sdf.format(new Date());
		
		OutputStream fOut = null;
		File file = new File(path, "fiap_roupas_picture_" + date + ".png");
		try 
		{
			fOut = new FileOutputStream(file);
			bmp.compress(Bitmap.CompressFormat.PNG, 85, fOut);
			fOut.flush();
			fOut.close();
			
			MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
			
			imagePath = file.getAbsolutePath();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		return imagePath;
	}
}
