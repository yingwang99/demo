package com.example.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button button;
	private ImageView img;
	final int TAKE_PICTURE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	public void initView(){
		button = (Button)findViewById(R.id.button1);
		img = (ImageView)findViewById(R.id.picture);
		
		button.setOnClickListener(this);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PICTURE) {
            if (resultCode == RESULT_OK) {
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                img.setImageBitmap(bm); 
                //在这边调用isMacth方法
                File myCaptureFile = new File("sdcard/demo.jpg");
                try {
	                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
	
	                bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
		           
		            bos.flush();
		           
		            bos.close();
			    } catch (FileNotFoundException e) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
			     
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    }
	            }
	        }
		}
	
	public void forward(boolean isMatch){
		if(isMatch == true){
			startActivity(new Intent(MainActivity.this, SuccessActivity.class));
			this.finish();
		}else{
			
		}
	}
	
	public boolean macth(Bitmap b1, Bitmap b2){
	    if(b1.getWidth()==b2.getWidth()
			        &&b1.getHeight()==b2.getHeight()){
		        int xCount = b1.getWidth();
		        int yCount = b1.getHeight();
		        for(int x=0; x<xCount; x++){
		            for(int y=0; y<yCount; y++){
			                if(b1.getPixel(x, y)!=b2.getPixel(x, y)){
				                    return false;
			                }
		            }            
		        }    
		        return true;
	    }else{
		        return false;
	    }
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), TAKE_PICTURE);
			break;

		default:
			break;
		}
	}

	
	

}
