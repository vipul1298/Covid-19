package com.example.covid_19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19.adapter.LocationAdapter;
import com.example.covid_19.models.Regional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<Regional> regionalList =new ArrayList<>();
    TextView total_case,indian_case,foreigner_case,discharged,total_deaths,title;
    String URL_Data;
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    LocationAdapter locationAdapter;
    ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        locationAdapter = new LocationAdapter(regionalList);
//        recyclerView.setAdapter(locationAdapter);
        mProgress= new ProgressDialog(MainActivity.this);
        mProgress.setMessage("Please wait for a moment...");
        mProgress.setCancelable(false);
        mProgress.show();

        total_case=findViewById(R.id.total_cases);
        indian_case=findViewById(R.id.total_indian);
        foreigner_case=findViewById(R.id.total_foreigner);
        discharged=findViewById(R.id.dischared);
        total_deaths=findViewById(R.id.deaths);
        title=findViewById(R.id.mTitle);

        loadurl();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Alert();
    }

    public void loadurl(){
        URL_Data="https://api.rootnet.in/covid19-in/stats/latest";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL_Data, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                             JSONObject jsonObject = response.getJSONObject("data");
                             JSONObject jsonObject1 =jsonObject.getJSONObject("summary");
                            Integer total = jsonObject1.getInt("total");
                            Integer c_indian = jsonObject1.getInt("confirmedCasesIndian");
                            Integer c_foreigner = jsonObject1.getInt("confirmedCasesForeign");
                            Integer c_dischared = jsonObject1.getInt("discharged");
                            Integer c_deaths = jsonObject1.getInt("deaths");

                            total_case.setText(total+"");
                            indian_case.setText(c_indian+"");
                            foreigner_case.setText(c_foreigner+"");
                            discharged.setText(c_dischared+"");
                            total_deaths.setText(c_deaths+"");

                            JSONArray jsonArray =jsonObject.getJSONArray("regional");
                            getvalue(jsonArray);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    public void getvalue(JSONArray array){
        for(int i=0;i<array.length();i++){
            Regional regional = new Regional();
            JSONObject json =null;
            try{
                json=array.getJSONObject(i);
                String loc =json.getString("loc");
                regional.setLocation(loc);
                Log.d("hsakj",loc);
                Integer c_indian = json.getInt("confirmedCasesIndian");
                regional.setConfirmed_indian_case(c_indian);
                Integer c_foreign = json.getInt("confirmedCasesForeign");
                regional.setConfirmed_foreigner_case(c_foreign);
                Integer discharged =json.getInt("discharged");
                regional.setDischared(discharged);
                Integer deaths =json.getInt("deaths");
                regional.setDeaths(deaths);

            }catch(JSONException e){
                e.printStackTrace();
            }
            regionalList.add(regional);

        }
        locationAdapter =new LocationAdapter(regionalList,this);
        recyclerView.setAdapter(locationAdapter);
        mProgress.dismiss();

    }

    public void Alert(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        dialog.show();
    }
}
