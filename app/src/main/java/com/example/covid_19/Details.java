package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    TextView mCity,mIndian,mForeigner,mDischarged,mDeaths;
    Button mbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mCity=findViewById(R.id.city);
        mIndian=findViewById(R.id.m_indian);
        mForeigner=findViewById(R.id.m_foreign);
        mDischarged=findViewById(R.id.m_discharged);
        mDeaths=findViewById(R.id.m_deaths);
        mbtn=findViewById(R.id.knowbtn);

        Intent i =getIntent();
        String city =i.getStringExtra("city");
        Integer indian =i.getIntExtra("c_indian",0);
        Integer foreign =i.getIntExtra("c_foreign",0);
        Integer discharged =i.getIntExtra("c_discharged",0);
        Integer deaths =i.getIntExtra("c_deaths",0);

        mCity.setText(city);
        mIndian.setText(indian+"");
        mForeigner.setText(foreign+"");
        mDischarged.setText(discharged+"");
        mDeaths.setText(deaths+"");

        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KnowledgeAboutCovid();
            }
        });


    }

    private void KnowledgeAboutCovid() {
        Intent i = new Intent(Details.this,Social.class);
        i.putExtra("url","https://www.who.int/health-topics/coronavirus#tab=tab_1");
        startActivity(i);
    }
}
