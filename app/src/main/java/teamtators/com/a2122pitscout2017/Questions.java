package teamtators.com.a2122pitscout2017;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class Questions extends AppCompatActivity {

    EditText scoutName, robotWeight, robotHeight, robotWidth, robotLength, numWheels, typeWheels, ballCap, startLoc, autoModes, humanPlayer;
    RadioButton dtTank, dtSwerve, dtMecanum, dtOmni, dtOther;
    CheckBox highShoot, lowShoot, gearPlace, hasAuto, cheesecake;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Button buttonNext = (Button) findViewById(R.id.buttonNext);
        scoutName = (EditText)findViewById(R.id.txtScoutName);
        robotWeight = (EditText)findViewById(R.id.txtWeight);
        robotHeight = (EditText)findViewById(R.id.txtHeight);
        robotWidth = (EditText)findViewById(R.id.txtWidth);
        robotLength = (EditText)findViewById(R.id.txtLength);
        numWheels = (EditText)findViewById(R.id.txtWheels);
        typeWheels = (EditText)findViewById(R.id.txtWheelType);
        ballCap = (EditText)findViewById(R.id.editCapacity);
        startLoc = (EditText)findViewById(R.id.editStartingLocation);
        autoModes = (EditText)findViewById(R.id.txtAutoMode);
        humanPlayer = (EditText)findViewById(R.id.editHumanPlayerPreference);
        dtTank = (RadioButton)findViewById(R.id.radioTank);
        dtSwerve = (RadioButton)findViewById(R.id.radioSwerve);
        dtMecanum = (RadioButton)findViewById(R.id.radioMecanum);
        dtOmni = (RadioButton)findViewById(R.id.radioOmni);
        dtOther = (RadioButton)findViewById(R.id.radioOther);
        highShoot = (CheckBox)findViewById(R.id.checkHigh);
        lowShoot = (CheckBox)findViewById(R.id.checkLow);
        gearPlace = (CheckBox)findViewById(R.id.checkGear);
        hasAuto = (CheckBox)findViewById(R.id.checkAuto);
        cheesecake = (CheckBox)findViewById(R.id.checkCheesecake);

        buttonNext.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v)  {
                        saveData();
                        startActivity (new Intent(Questions.this, Comments.class));

                    }
                }


        );


            }
    private void saveData(){
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ScoutName", scoutName.getText().toString());
        editor.putInt("Weight", Integer.parseInt(robotWeight.getText().toString()));
        editor.putInt("Height", Integer.parseInt(robotHeight.getText().toString()));
        editor.putInt("Width", Integer.parseInt(robotWidth.getText().toString()));
        editor.putInt("Length", Integer.parseInt(robotLength.getText().toString()));
        editor.putInt("NumWheels", Integer.parseInt(numWheels.getText().toString()));
        editor.putString("TypeWheels", numWheels.getText().toString());
        editor.putInt("BallCap", Integer.parseInt(ballCap.getText().toString()));
        editor.putString("StartLoc", startLoc.getText().toString());
        editor.putInt("AutoModes", Integer.parseInt(autoModes.getText().toString()));
        editor.putString("HumanPlayer", humanPlayer.getText().toString());
        editor.putString("DriveTrain", driveTrainType());
        editor.putBoolean("HighShoot", highShoot.isChecked());
        editor.putBoolean("LowShoot", lowShoot.isChecked());
        editor.putBoolean("PlaceGears", gearPlace.isChecked());
        editor.putBoolean("Cheesecake", cheesecake.isChecked());
        editor.commit();
    }
    private String driveTrainType(){
        if (dtTank.isChecked()){
            return "Tank";
        }
        if (dtMecanum.isChecked()){
            return "Mecanum";
        }
        if (dtOmni.isChecked()){
            return "Omni";
        }
        if (dtSwerve.isChecked()){
            return "Swerve";
        }
        if (dtOther.isChecked()){
            return "Other";
        }
        else{
            return "";
        }
    }
}




