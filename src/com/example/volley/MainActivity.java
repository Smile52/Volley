package com.example.volley;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;




import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView imageview;
	private NetworkImageView mNetworkImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//mNetworkImageView = (NetworkImageView) findViewById(R.id.networkImageView);
		//loadImageviewByVolley();
		//showImageByNetworkImageView();
		
	}

	private void loadImageviewByVolley() {
		String url = "http://192.168.191.1:8080/Demo/01.jpg";
		imageview = (ImageView) findViewById(R.id.image_img);
		/*
		 * RequestQueue requestQueue=Volley.newRequestQueue(this); final
		 * LruCache<String,Bitmap> lruCache=new LruCache<String, Bitmap>(20);
		 * ImageCache imageCache=new ImageCache() {
		 * 
		 * @Override public void putBitmap(String url, Bitmap bitmap) { // TODO
		 * Auto-generated method stub lruCache.put(url, bitmap); }
		 * 
		 * @Override public Bitmap getBitmap(String url) {
		 * 
		 * return lruCache.get(url); } }; ImageLoader imageLoader=new
		 * ImageLoader(requestQueue, imageCache); ImageListener
		 * listener=ImageLoader.getImageListener(imageview,
		 * R.drawable.ic_launcher, R.drawable.ic_launcher); imageLoader.get(url,
		 * listener);
		 */

		RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
		ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
		ImageListener listener = ImageLoader.getImageListener(imageview,R.drawable.default_image, R.drawable.default_image);
		imageLoader.get("http://192.168.191.1:8080/Demo/01.jpg", listener);

	}

	private void showImageByNetworkImageView() {
		String imageUrl = "http://192.168.191.1:8080/Demo/01.jpg";
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(
				20);
		ImageCache imageCache = new ImageCache() {
			@Override
			public void putBitmap(String key, Bitmap value) {
				lruCache.put(key, value);
			}

			@Override
			public Bitmap getBitmap(String key) {
				return lruCache.get(key);
			}
		};
		ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
		mNetworkImageView.setTag("url");
		mNetworkImageView.setImageUrl(imageUrl, imageLoader);
	}
	public void displayImg(View view){
		ImageView imageView = (ImageView)this.findViewById(R.id.image_img);
		RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext()); 
		
		ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
//		ImageLoader imageLoader = new ImageLoader(mQueue, new ImageCache() {
//			@Override
//			public void putBitmap(String url, Bitmap bitmap) {
//			}
//
//			@Override
//			public Bitmap getBitmap(String url) {
//				return null;
//			}
//		});

		ImageListener listener = ImageLoader.getImageListener(imageView,R.drawable.default_image, R.drawable.default_image);
		imageLoader.get("http://192.168.191.1:8080/Demo/01.jpg", listener);
		//指定图片允许的最大宽度和高度
		//imageLoader.get("http://developer.android.com/images/home/aw_dac.png",listener, 200, 200);
	}


}
