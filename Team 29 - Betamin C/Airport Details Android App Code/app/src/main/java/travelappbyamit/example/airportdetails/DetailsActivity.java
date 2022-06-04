package travelappbyamit.example.airportdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {



    TextView airport,location,country,state,phone,website,maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Intent intent = getIntent();
        String val1 = intent.getStringExtra("1");
        String val2 = intent.getStringExtra("2");
        String val3 = intent.getStringExtra("3");
        String val4 = intent.getStringExtra("4");
        String val5 = intent.getStringExtra("5");
        String val6 = intent.getStringExtra("6");
        String val7 = intent.getStringExtra("7");
        String val8 = intent.getStringExtra("8");

        airport = findViewById(R.id.airport_name);
        location = findViewById(R.id.location);
        country = findViewById(R.id.country);
        state = findViewById(R.id.state);
        phone = findViewById(R.id.phone_no);
        website = findViewById(R.id.website);
        maps = findViewById(R.id.location_maps);


        airport.setText(val1);
        location.setText(val2);
        country.setText(val3);
        state.setText(val4);
        phone.setText(val5);



        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(val8));
                startActivity(browserIntent);
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q="+val6+","+val7));
                startActivity(browserIntent);
            }
        });



    }
}