package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddDetails extends AppCompatActivity {
    EditText fname, lname, cname, dob, coname, num, email, address;
    AppCompatButton subbt, gobackbt;
    String apiurl="https://courseapplogix.onrender.com/addstudents";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_details);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        cname = findViewById(R.id.cname);
        dob = findViewById(R.id.dob);
        coname = findViewById(R.id.coname);
        num = findViewById(R.id.num);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        subbt = findViewById(R.id.subbt);
        gobackbt = findViewById(R.id.gobackbt);
        subbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = fname.getText().toString();
                String last_name = lname.getText().toString();
                String college_name = cname.getText().toString();
                String date_of_birth = dob.getText().toString();
                String course_name = coname.getText().toString();
                String mobile_number = num.getText().toString();
                String email_address = email.getText().toString();
                String address_details = address.getText().toString();

                JSONObject student = new JSONObject();
                try {
                    student.put("firstname", first_name);
                    student.put("lastname", last_name);
                    student.put("college", college_name);
                    student.put("dob", date_of_birth);
                    student.put("course", course_name);
                    student.put("mobile", mobile_number);
                    student.put("email", email_address);
                    student.put("address", address_details);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST, apiurl, student,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "added successfully", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });

            gobackbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });

    }
}