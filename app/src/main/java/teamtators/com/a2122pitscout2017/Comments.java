package teamtators.com.a2122pitscout2017;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class Comments extends AppCompatActivity {

    CheckBox cbxCrossLine, cbxDelayAuto, cbxPlaceGear, cbxShootFuel, cbxHopper, cbxPickBalls, cbxStartKey, cbxStartNextKey, cbxStartMiddle, cbxStartLoad;
    RadioButton rad1Year, rad2Year, rad3Year, rad4Year, rad5Year;
    EditText txtComments, driverTimeThisYear;
    Button buttonNext;
    SharedPreferences preferences;
    Context context;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        context=this;
        cbxCrossLine=(CheckBox)findViewById(R.id.checkCross);
        driverTimeThisYear =(EditText)findViewById(R.id.txtDriverThisYear);
        cbxDelayAuto=(CheckBox)findViewById(R.id.checkDelay);
        cbxPlaceGear=(CheckBox)findViewById(R.id.checkGear);
        cbxShootFuel=(CheckBox)findViewById(R.id.checkShoot);
        cbxHopper=(CheckBox)findViewById(R.id.checkHopper);
        cbxPickBalls=(CheckBox)findViewById(R.id.checkPick);
        cbxStartKey=(CheckBox)findViewById(R.id.radioStartInKey);
        cbxStartNextKey=(CheckBox)findViewById(R.id.radioStartNextKey);
        cbxStartMiddle=(CheckBox)findViewById(R.id.radioStartMiddle);
        cbxStartLoad=(CheckBox)findViewById(R.id.radioStartLoadingStation);
        rad1Year=(RadioButton)findViewById(R.id.radio1Year);
        rad2Year=(RadioButton)findViewById(R.id.radio2Year);
        rad3Year=(RadioButton)findViewById(R.id.radio3Year);
        rad4Year=(RadioButton)findViewById(R.id.radio4Year);
        rad5Year=(RadioButton)findViewById(R.id.radio5Year);
        txtComments=(EditText)findViewById(R.id.txtComments);
        buttonNext=(Button)findViewById(R.id.buttonFinish);
        radioGroup=(RadioGroup)findViewById(R.id.radGroupDriverCurrent);
        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        buttonNext.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v)  {
                        saveData();
                        startActivity (new Intent(Comments.this, Questions.class));
                    }
                }
        );
    }
    private void saveData(){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("comments", txtComments.getText().toString());
        editor.putBoolean("crossLine", cbxCrossLine.isChecked());
        editor.putBoolean("delayAuto", cbxDelayAuto.isChecked());
        editor.putBoolean("placeGear", cbxPlaceGear.isChecked());
        editor.putBoolean("shootFuel", cbxShootFuel.isChecked());
        editor.putBoolean("hopper", cbxHopper.isChecked());
        editor.putBoolean("pickBalls", cbxPickBalls.isChecked());
        editor.putBoolean("startKey", cbxStartKey.isChecked());
        editor.putBoolean("startNextKey", cbxStartNextKey.isChecked());
        editor.putBoolean("startMiddle", cbxStartMiddle.isChecked());
        editor.putBoolean("startLoad", cbxStartLoad.isChecked());
        editor.putInt("YearsDriving", DriverExperience());
        editor.commit();
    }
    private int DriverExperience(){
        if (rad1Year.isChecked()){
            return 1;
        }
        if (rad2Year.isChecked()){
            return 2;
        }
        if (rad3Year.isChecked()){
            return 3;
        }
        if (rad4Year.isChecked()){
            return 4;
        }
        if (rad5Year.isChecked()){
            return 5;
        }
        else{
            return 0;
        }
    }
    private void writeFile(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("filename", "StandScout_Team_" + preferences.getString("Team", "TEAM#"));
        editor.commit();


        File file = new File(Environment.getExternalStorageDirectory() + "/" + preferences.getString("filename", "BROKEN"));
        try{
            FileWriter writer;
                writer = new FileWriter(file, true);
            writer.append("ScoutName,Team,Weight,Height,Width,Length,NumWheels,TypeWheels,BallCap,StartLoc,AutoModes,HumanPlayer," +
                    "DriveTrain,HighShoot,LowShoot,PlaceGears,Cheesecake,YearsDriving,crossLine,delayAuto,placeGear,shootFuel,hopper," +
                    "pickBalls,startKey,startNextKey,startMiddle,startLoad,comments,");
            writer.append(preferences.getString("ScoutName", ""));
            writer.append(preferences.getString("Team", ""));
            writer.append(preferences.getInt("Weight", 0)+"");
            writer.append(preferences.getInt("Height", 0)+"");
            writer.append(preferences.getInt("Width", 0)+"");
            writer.append(preferences.getInt("Length", 0)+"");
            writer.append(preferences.getInt("NumWheels", 0)+"");
            writer.append(preferences.getString("TypeWheels", ""));
            writer.append(preferences.getInt("BallCap", 1)+"");
            writer.append(preferences.getString("StartLoc", ""));
            writer.append(preferences.getInt("AutoModes", 0)+"");
            writer.append(preferences.getString("HumanPlayer", ""));
            writer.append(preferences.getString("DriveTrain", ""));
            writer.append(preferences.getBoolean("HighShoot", false)+"");
            writer.append(preferences.getBoolean("LowShoot", false)+"");
            writer.append(preferences.getBoolean("PlaceGears", false)+"");
            writer.append(preferences.getBoolean("Cheesecake", false)+"");
            writer.append(preferences.getInt("YearsDriving", 0)+"");
            writer.append(preferences.getBoolean("crossLine", false)+"");
            writer.append(preferences.getBoolean("delayAuto", false)+"");
            writer.append(preferences.getBoolean("placeGear", false)+"");
            writer.append(preferences.getBoolean("shootFuel", false)+"");
            writer.append(preferences.getBoolean("hopper", false)+"");
            writer.append(preferences.getBoolean("pickBalls", false)+"");
            writer.append(preferences.getBoolean("startKey", false)+"");
            writer.append(preferences.getBoolean("startNextKey", false)+"");
            writer.append(preferences.getBoolean("startMiddle", false)+"");
            writer.append(preferences.getBoolean("startLoad", false)+"");
            writer.append(preferences.getString("comments", ""));
        }
        catch(Exception e){
            Toast.makeText(context, "File Writer Failed", Toast.LENGTH_SHORT).show();
        }
        SharedPreferences.Editor editorTwo = preferences.edit();
        editorTwo.putInt("match end",1);
        editorTwo.commit();

        Intent intent  = new Intent(context,Questions.class);
        startActivity(intent);

        }
    public void finishButton(){
        if (CheckEverything()){
            return;
        }
        saveData();
        startActivity (new Intent(Comments.this, Questions.class));

    }
    private boolean CheckEverything() {
        if (driverTimeThisYear.getText().toString().equals("")) {
            Toast.makeText(context, "Please tell us how much time the driver has spent practicing", Toast.LENGTH_SHORT).show();
            return false;
        }
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.radio1Year:
                break;
            case R.id.radio2Year:
                break;
            case R.id.radio3Year:
                break;
            case R.id.radio4Year:
                break;
            case R.id.radio5Year:
                break;
            default:
                return false;
        }
        return true;
    }

}
