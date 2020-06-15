package com.corona.covid_19explorer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.corona.covid_19explorer.R;

public class CoughTest extends AppCompatActivity {

    private Button btnSubmit, btnReset;
    private TextView finalScore;
    private RadioButton cough, sourThroat, fever, tiredness, breathing;

    private  int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cough_test);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnReset = (Button) findViewById(R.id.btnReset);
        finalScore = (TextView) findViewById(R.id.textFinalScore);

        cough = (RadioButton) findViewById(R.id.cough);
        sourThroat = (RadioButton) findViewById(R.id.sour_throat);
        fever = (RadioButton) findViewById(R.id.fever);
        tiredness = (RadioButton) findViewById(R.id.tiredness);
        breathing = (RadioButton) findViewById(R.id.breathingDifficulty);

        getSupportActionBar().setTitle("Take a Quick Test");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cough.isChecked() && sourThroat.isChecked() && fever.isChecked() && tiredness.isChecked() && breathing.isChecked()) {
                    finalScore.setText("High risk. Please visit the nearby COVID - 19 Test centers.");
                }
                else  if (cough.isChecked() && sourThroat.isChecked() && fever.isChecked() && tiredness.isChecked()) {
                    finalScore.setText("Moderate risk. Call for home assistance.");
                }
                else if (cough.isChecked() && sourThroat.isChecked() && fever.isChecked()){
                    finalScore.setText("You have tonsillitis. An inflammation of the two oval-shaped pads of tissue at the back of the throat. Treatment can range from home-care remedies to surgical removal.");
                }
                else if (cough.isChecked() && fever.isChecked()){
                    finalScore.setText("Dont Panic. Its Common cold. Have medics.");
                }
                else if (breathing.isChecked() && fever.isChecked()){
                    finalScore.setText("Its a common cold. A common viral infection of the nose and throat.");
                }
                else if (breathing.isChecked()){
                    finalScore.setText("It can be Pneumonia. Please check the doctor.");
                }
                else if (sourThroat.isChecked() && fever.isChecked()){
                    finalScore.setText("You have Strep throat. Its a bacterial infection that may cause a sore, scratchy throat.\n" +
                            "The infection is generally transmitted by direct contact with the mucus or sores of someone else with strep." + "Treatment is important to reduce complications. Oral antibiotics like penicillin, amoxicillin, cephalexin or azithromycin are commonly used. ");
                }
                else if (cough.isChecked() || sourThroat.isChecked() || fever.isChecked() || tiredness.isChecked()){
                    finalScore.setText("Nothing serious.");
                }
                else if (!cough.isChecked() || !sourThroat.isChecked() || !fever.isChecked() || !tiredness.isChecked() || !breathing.isChecked()){
                    Toast.makeText(CoughTest.this, "Please select the options.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Resetting everything
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cough.isChecked() && !sourThroat.isChecked() && !fever.isChecked() && !tiredness.isChecked() && !breathing.isChecked()){
                    Toast.makeText(CoughTest.this, "Nothing to reset.", Toast.LENGTH_SHORT).show();
                }
                else {
                    score = 0;
                    cough.setChecked(false);
                    fever.setChecked(false);
                    breathing.setChecked(false);
                    tiredness.setChecked(false);
                    sourThroat.setChecked(false);
                    finalScore.setText("");
                }
            }
        });
    }
}