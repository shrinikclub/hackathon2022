package travelappbyamit.example.airportdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {



    EditText editText;
    Button button;
    ProgressBar progressDialog;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editext);
        progressDialog   = findViewById(R.id.progress_bar);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.qr_code_scan);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    try {
                        fetchData(editText.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,QRCodeScan.class);
                startActivity(intent);
            }
        });


    }

    private void fetchData(String toString) throws IOException {

        progressDialog.setVisibility(View.VISIBLE);

        String url = "https://airport-info.p.rapidapi.com/airport?iata="+toString;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Host", "airport-info.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "8215e9e382msh411551c64217c12p1fe5adjsn276704f44ec2")
                .build();

        Response response = client.newCall(request).execute();


        if (response.isSuccessful()){
            String resp = response.body().string();


            JSONObject jsonObject = null;
            try {

                jsonObject = new JSONObject(resp);
                String val1 =  jsonObject.getString("name");
                String val2 =  jsonObject.getString("location");
                String val3 =  jsonObject.getString("country");
                String val4 =  jsonObject.getString("state");
                String val5 =  jsonObject.getString("phone");
                String val6 =  jsonObject.getString("latitude");
                String val7 =  jsonObject.getString("longitude");
                String val8 =  jsonObject.getString("website");

                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("1",val1);
                intent.putExtra("2",val2);
                intent.putExtra("3",val3);
                intent.putExtra("4",val4);
                intent.putExtra("5",val5);
                intent.putExtra("6",val6);
                intent.putExtra("7",val7);
                intent.putExtra("8",val8);
                startActivity(intent);

                progressDialog.setVisibility(View.GONE);

            } catch (JSONException e) {
                e.printStackTrace();
            }



        }


    }



}