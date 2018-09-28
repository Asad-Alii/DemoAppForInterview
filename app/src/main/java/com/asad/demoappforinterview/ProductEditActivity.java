package com.asad.demoappforinterview;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.InputStream;

public class ProductEditActivity extends AppCompatActivity {

    EditText etId, etName, etFatherName, etAddress, etDateOfBirth, etDateOFAdmission, etGrade;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_edit_layout);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etFatherName = findViewById(R.id.etFatherName);
        etAddress = findViewById(R.id.etAddress);
        etDateOfBirth = findViewById(R.id.etDateOfBirth);
        etDateOFAdmission = findViewById(R.id.etDateOfAdmission);
        etGrade = findViewById(R.id.etgrade);

        btn = findViewById(R.id.commit_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickbuttonRecieve(v);
            }
        });
    }

    public void clickbuttonRecieve(View v) {
        try {
            JSONObject json = new JSONObject();
            json.put("id", etId.getText().toString());
            json.put("name", etName.getText().toString());
            json.put("fatherName", etFatherName.getText().toString());
            json.put("address", etAddress.getText().toString());
            json.put("dateOfBirth", etDateOfBirth.getText().toString());
            json.put("dateOfAdmission", etDateOFAdmission.getText().toString());
            json.put("grade", etGrade.getText().toString());
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams,
                    10000);
            HttpConnectionParams.setSoTimeout(httpParams, 10000);
            HttpClient client = new DefaultHttpClient(httpParams);
            //
            //String url = "http://10.0.2.2:8080/sample1/webservice2.php?" +
            //             "json={\"UserName\":1,\"FullName\":2}";
            String url = "http://192.168.1.103:8080/update.php";

            HttpPost request = new HttpPost(url);
            request.setEntity(new ByteArrayEntity(json.toString().getBytes(
                    "UTF8")));
            request.setHeader("json", json.toString());
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            // If the response does not enclose an entity, there is no need
            if (entity != null) {
                InputStream instream = entity.getContent();

                String result = RestClient.convertStreamToString(instream);
                Log.i("Read from server", result);
                Toast.makeText(this,  result,
                        Toast.LENGTH_LONG).show();
            }
        } catch (Throwable t) {
            Toast.makeText(this, "Request failed: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
