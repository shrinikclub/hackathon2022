package travelappbyamit.example.airportdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import eu.livotov.labs.android.camview.ScannerLiveView;
import eu.livotov.labs.android.camview.scanner.decoder.zxing.ZXDecoder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.VIBRATE;

public class QRCodeScan extends AppCompatActivity {


    private ScannerLiveView camera;
    private TextView scannedTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_code_scan);
        // check permission method is to check that the
        // camera permission is granted by user or not.
        // request permission method is to request the
        // camera permission if not given.


        // initialize scannerLiveview and textview.
        scannedTV = findViewById(R.id.idTVscanned);
        camera = (ScannerLiveView) findViewById(R.id.camview);


        if (checkPermission()) {
            // if permission is already granted display a toast message
            Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        camera.setScannerViewEventListener(new ScannerLiveView.ScannerViewEventListener() {
            @Override
            public void onScannerStarted(ScannerLiveView scanner) {
                // method is called when scanner is started
                Toast.makeText(QRCodeScan.this, "Scanner Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScannerStopped(ScannerLiveView scanner) {
                // method is called when scanner is stopped.
                Toast.makeText(QRCodeScan.this, "Scanner Stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScannerError(Throwable err) {
                // method is called when scanner gives some error.
                Toast.makeText(QRCodeScan.this, "Scanner Error: " + err.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeScanned(String data) {

                Toast.makeText(QRCodeScan.this, data, Toast.LENGTH_SHORT).show();

                // method is called when camera scans the
                // qr code and the data from qr code is
                // stored in data in string format.
                scannedTV.setText(data);



                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    try {
                        fetchData(data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }
        });
    }


    private void fetchData(String toString) throws IOException {


        String url = "https://airport-info.p.rapidapi.com/airport?iata=" + toString;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Host", "airport-info.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "8215e9e382msh411551c64217c12p1fe5adjsn276704f44ec2")
                .build();

        Response response = client.newCall(request).execute();


        if (response.isSuccessful()) {
            String resp = response.body().string();


            JSONObject jsonObject = null;
            try {

                jsonObject = new JSONObject(resp);
                String val1 = jsonObject.getString("name");
                String val2 = jsonObject.getString("location");
                String val3 = jsonObject.getString("country");
                String val4 = jsonObject.getString("state");
                String val5 = jsonObject.getString("phone");
                String val6 = jsonObject.getString("latitude");
                String val7 = jsonObject.getString("longitude");
                String val8 = jsonObject.getString("website");

                Intent intent = new Intent(QRCodeScan.this, DetailsActivity.class);
                intent.putExtra("1", val1);
                intent.putExtra("2", val2);
                intent.putExtra("3", val3);
                intent.putExtra("4", val4);
                intent.putExtra("5", val5);
                intent.putExtra("6", val6);
                intent.putExtra("7", val7);
                intent.putExtra("8", val8);
                startActivity(intent);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        ZXDecoder decoder = new ZXDecoder();
        // 0.5 is the area where we have
        // to place red marker for scanning.
        decoder.setScanAreaPercent(0.8);
        // below method will set secoder to camera.
        camera.setDecoder(decoder);
        camera.startScanner();
    }

    @Override
    protected void onPause() {
        // on app pause the
        // camera will stop scanning.
        camera.stopScanner();
        super.onPause();
    }

    private boolean checkPermission() {
        // here we are checking two permission that is vibrate
        // and camera which is granted by user and not.
        // if permission is granted then we are returning
        // true otherwise false.
        int camera_permission = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int vibrate_permission = ContextCompat.checkSelfPermission(getApplicationContext(), VIBRATE);
        return camera_permission == PackageManager.PERMISSION_GRANTED && vibrate_permission == PackageManager.PERMISSION_GRANTED;
    }


    private void requestPermission() {
        // this method is to request
        // the runtime permission.
        int PERMISSION_REQUEST_CODE = 200;
        ActivityCompat.requestPermissions(this, new String[]{CAMERA, VIBRATE}, PERMISSION_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // this method is called when user
        // allows the permission to use camera.
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            boolean cameraaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            boolean vibrateaccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
            if (cameraaccepted && vibrateaccepted) {
                Toast.makeText(this, "Permission granted..", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denined \n You cannot use app without providing permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

}