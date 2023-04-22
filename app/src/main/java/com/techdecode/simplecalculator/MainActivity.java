package com.techdecode.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText et_bill;
    TextView tv_tip_output,tv_total_output,tv_tib,tv_people;
    Button b_calculate,b_tib_minus,b_tib_plus,b_people_plus,b_people_minus;
    int tipPercent=0;
    int peopleCount=1;
    double billinit=100.00;
    double tipOutput=0;
    double totalOutput=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_bill=findViewById(R.id.et_bill);
        tv_people=findViewById(R.id.tv_people);
        tv_tib=findViewById(R.id.tv_tip);
        tv_total_output=findViewById(R.id.tv_total_output);
        tv_tip_output=findViewById(R.id.tv_tip_output);
        b_tib_plus=findViewById(R.id.b_tib_plus);
        b_people_plus=findViewById(R.id.b_people_plus);
        b_people_minus=findViewById(R.id.b_people_minus);
        b_tib_minus=findViewById(R.id.b_Tib_minus);
       b_calculate=findViewById(R.id.b_calculate);

       b_calculate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String billString=et_bill.getText().toString();
               if(!billString.equals("")){
                   //the initiall bill
                   billinit=Double.valueOf(billString);
                   //format thre number
                   billinit=billinit * 100;
                   billinit=Math.round(billinit);
                   billinit=billinit / 100;
                   //show the formated bill
                   et_bill.setText(String.format(Locale.getDefault(),"%.2f",billinit));

                   //calc the tip
                   tipOutput=(billinit * tipPercent)/100;
                   //format the number
                   tipOutput=tipOutput*100;
                   tipOutput=Math.round(tipOutput);
                   tipOutput=tipOutput/100;
                   if(peopleCount==1) {
                       //show the total tip
                       tv_tip_output.setText(String.format(Locale.getDefault(), "%.2f", tipOutput));
                       //Show the total bill including the tip
                       totalOutput = billinit + tipOutput;
                       tv_total_output.setText(String.format(Locale.getDefault(), "%.2f", totalOutput));
                   }else{

                       //Show the total bill including the tip
                       totalOutput = billinit + tipOutput;
                       totalOutput=totalOutput/peopleCount;
                       tv_total_output.setText(String.format(Locale.getDefault(), "%.2f", totalOutput) +" "+ "per person");
                       //show the total tip
                       tipOutput=tipOutput/peopleCount;
                       tv_tip_output.setText(String.format(Locale.getDefault(), "%.2f", tipOutput) +" "+ "per person");

                   }
               }
           }
       });
        b_tib_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tipPercent >0){
                    tipPercent--;
                    tv_tib.setText(tipPercent+"%");
                }

            }
        });
        b_tib_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipPercent++;
                tv_tib.setText(tipPercent+"%");
            }
        });
        b_people_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(peopleCount >1){
                    peopleCount--;
                    tv_people.setText(peopleCount+" ");
                }

            }
        });
        b_people_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peopleCount++;
                tv_people.setText(peopleCount+" ");

            }
        });

    }
}