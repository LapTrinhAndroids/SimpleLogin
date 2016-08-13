package com.example.simpleweather;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	String apikey = "4e555db5dc87833dfcca65d43b97a23e";
	String strJson;
	String _strJson;
	String keyword = "null";

	ArrayList<ImageWeather> arr_Image_Black_Weather = new ArrayList<ImageWeather>();
	ArrayList<ImageWeather> arr_Image_White_Weather = new ArrayList<ImageWeather>();

	EditText edtSearch;
	TextView txtCountry;
	TextView txtWeatherDetail;
	TextView txtTemp;
	TextView txtClouds;
	TextView txtWind;
	TextView txtHumidity;
	TextView txtPressure;
	TextView txtSunrise;
	TextView txtSunset;
	Button	 btnRefresh;
	Button	 btnSearch;
	ImageView imgWeather;
	TextView txtWeatherforthenextfewdays;
	ImageView imgNextListDay;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set background color
		LinearLayout linearBackground = (LinearLayout) findViewById(R.id.linearBackground);
		linearBackground.setBackgroundColor(Color.parseColor("#F9FBE7"));

		// Check connect network
		if (isNetworkConnected() == false) {
			Intent intent1 = new Intent(Intent.ACTION_MAIN);
			intent1.addCategory(Intent.CATEGORY_HOME);
			intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent1);
			Toast.makeText(getApplicationContext(), "No network connection", Toast.LENGTH_LONG).show();
			;
		}

		txtCountry = (TextView) findViewById(R.id.txtCountry);
		txtWeatherDetail = (TextView) findViewById(R.id.txtWeatherDetail);
		txtTemp = (TextView) findViewById(R.id.txtTemp);
		txtClouds = (TextView) findViewById(R.id.txtCloud);
		txtWind = (TextView) findViewById(R.id.txtWind);
		txtHumidity = (TextView) findViewById(R.id.txtHumidity);
		txtPressure = (TextView) findViewById(R.id.txtPressure);
		txtSunrise = (TextView) findViewById(R.id.txtSunRise);
		txtSunset = (TextView) findViewById(R.id.txtSunSet);
		btnRefresh = (Button) findViewById(R.id.btnRefresh);
		imgWeather = (ImageView) findViewById(R.id.imgWeather);
		txtWeatherforthenextfewdays = (TextView) findViewById(R.id.txtWeatherforthenextfewdays);
		imgNextListDay = (ImageView) findViewById(R.id.imgNextListDay);
		edtSearch = (EditText) findViewById(R.id.edtSearch);
		btnSearch = (Button) findViewById(R.id.btnSearch);

		if (keyword == null) {
			keyword = "HoChiMinh";
			keyword =readData();
		}

		// Set value for array image black weather
		arr_Image_Black_Weather.add(new ImageWeather("b_01d", R.drawable.b_01d));
		arr_Image_Black_Weather.add(new ImageWeather("b_01n", R.drawable.b_01n));
		arr_Image_Black_Weather.add(new ImageWeather("b_02d", R.drawable.b_02d));
		arr_Image_Black_Weather.add(new ImageWeather("b_02n", R.drawable.b_02n));
		arr_Image_Black_Weather.add(new ImageWeather("b_03d", R.drawable.b_03d));
		arr_Image_Black_Weather.add(new ImageWeather("b_03n", R.drawable.b_03n));
		arr_Image_Black_Weather.add(new ImageWeather("b_04d", R.drawable.b_04d));
		arr_Image_Black_Weather.add(new ImageWeather("b_04n", R.drawable.b_04n));
		arr_Image_Black_Weather.add(new ImageWeather("b_09d", R.drawable.b_09d));
		arr_Image_Black_Weather.add(new ImageWeather("b_09n", R.drawable.b_09n));
		arr_Image_Black_Weather.add(new ImageWeather("b_10d", R.drawable.b_10d));
		arr_Image_Black_Weather.add(new ImageWeather("b_10n", R.drawable.b_10n));
		arr_Image_Black_Weather.add(new ImageWeather("b_11d", R.drawable.b_11d));
		arr_Image_Black_Weather.add(new ImageWeather("b_11n", R.drawable.b_11n));
		arr_Image_Black_Weather.add(new ImageWeather("b_13d", R.drawable.b_13d));
		arr_Image_Black_Weather.add(new ImageWeather("b_13n", R.drawable.b_13n));
		arr_Image_Black_Weather.add(new ImageWeather("b_50d", R.drawable.b_50d));
		arr_Image_Black_Weather.add(new ImageWeather("b_50n", R.drawable.b_50n));

		new WeatherTask().execute();
		btnRefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				new WeatherTask().execute();
				writeData(keyword);
			}
		});
		txtWeatherforthenextfewdays.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, ListDayAcitivity.class);
				intent.putExtra("keyword", keyword);
				// intent.putExtra("JSon", _strJson);
				startActivity(intent);
			}
		});
		imgNextListDay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, ListDayAcitivity.class);
				intent.putExtra("keyword", keyword);
				// intent.putExtra("JSon", _strJson);
				startActivity(intent);
			}
		});
		btnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				keyword = edtSearch.getText().toString();
				writeData(keyword);
				keyword = keyword.replaceAll("\\s+","");
				Log.e("word", readData());
				new WeatherTask().execute();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private String capitalize(String line) {
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}

	protected void showUpData() {
		try {
			JSONObject response = new JSONObject(strJson);

			JSONObject weather = response.getJSONArray("weather").getJSONObject(0);
			String icon = weather.getString("icon");
			txtWeatherDetail.setText(capitalize(weather.getString("description")));

			JSONObject main = response.getJSONObject("main");
			txtTemp.setText(String.valueOf(main.getInt("temp")) + "°");

			txtPressure.setText(String.valueOf(main.getInt("pressure") + " hPa"));
			txtHumidity.setText(main.getString("humidity") + " %");

			JSONObject wind = response.getJSONObject("wind");
			txtWind.setText(wind.getString("speed") + " m/s");

			JSONObject clouds = response.getJSONObject("clouds");
			txtClouds.setText(clouds.getString("all") + " %");

			JSONObject system = response.getJSONObject("sys");
			String country = system.getString("country");
			Long sunrise = system.getLong("sunrise");
			Long sunset = system.getLong("sunset");

			txtSunrise.setText(getDateTime(sunrise).split("-")[1]);
			txtSunset.setText(getDateTime(sunset).split("-")[1]);

			txtCountry.setText(response.getString("name") + ", " + country);

			// Set imageWeather for application
			for (int i = 0; i < arr_Image_Black_Weather.size(); i++) {
				if (arr_Image_Black_Weather.get(i).imgName.contains(icon)) {
					imgWeather.setImageResource(arr_Image_Black_Weather.get(i).imgValue);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	class WeatherTask extends AsyncTask {

		@SuppressWarnings("unchecked")
		@Override
		protected void onProgressUpdate(Object... values) {
			showUpData();
			super.onProgressUpdate(values);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected Object doInBackground(Object... params) {
			try {
				if (keyword == null) {
					readData();
					keyword = "HoChiMinh";
				}
				URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + keyword + "&units=metric&appid="
						+ apikey);
				URLConnection connection = url.openConnection();
				connection.connect();
				int responseSize = connection.getContentLength();
				InputStream ins = connection.getInputStream();
				strJson = new MyStringParser(ins, responseSize).toString();
				publishProgress();
			} catch (MalformedURLException e) {
				Log.e("Error", "WeatherTask->MalformedURLException");
			} catch (IOException e) {
				Log.e("Error", "WeatherTask->IOException");
			}
			return null;
		}
	}

	@SuppressLint("SimpleDateFormat")
	protected String getDateTime(long sec) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
		return formatter.format(new Date(sec * 1000L));
	}

	private boolean isNetworkConnected() {
		@SuppressWarnings("static-access")
		ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);

		return cm.getActiveNetworkInfo() != null;
	}

	public String readData() {
		String str = null;
		try {
			
			FileInputStream in = openFileInput("location.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String data = "";
			StringBuilder builder = new StringBuilder();
			while ((data = reader.readLine()) != null) {
				builder.append(data);
				builder.append("\n");
			}
			in.close();
			str = builder.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public void writeData(String str) {
		try {
			FileOutputStream out = openFileOutput("location.txt", 0);
			OutputStreamWriter writer = new OutputStreamWriter(out);
			writer.write(str);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
