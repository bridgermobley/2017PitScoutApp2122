package teamtators.com.a2122pitscout2017;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Questions extends AppCompatActivity {

    EditText scoutName, robotWeight, robotHeight, robotWidth, robotLength, numWheels, typeWheels, ballCap, startLoc, autoModes, humanPlayer;
    RadioButton dtTank, dtSwerve, dtMecanum, dtOmni, dtOther, dtJump;
    CheckBox highShoot, lowShoot, gearPlace, hasAuto, cheesecake, climber, defense;
    String[] spinnerAry;
    Context context;
    Spinner spnTeamSpinner;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        verifyStoragePermissions(this);
        context = this;
        Button buttonNext = (Button) findViewById(R.id.buttonFinish);
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
        dtJump= (RadioButton)findViewById(R.id.radioJump);
        cheesecake = (CheckBox)findViewById(R.id.checkCheesecake);
        spnTeamSpinner = (Spinner)findViewById(R.id.spinnerChoseTeam);
        climber = (CheckBox)findViewById(R.id.checkClimber);
        defense = (CheckBox)findViewById(R.id.Defense);

        spinnerAry = new String[7];
        //DummyData
        spinnerAry[0] = "Pick A Team";
        spinnerAry[1] = "254,CHEZYPOFS";
        spinnerAry[2] = "976,URMOM.com";
        spinnerAry[3] = "987,DONTKNOWTHISTEANM";
        spinnerAry[4] = "1114,NINTENDOSWITCHSUCKS";
        spinnerAry[5] = "2056,KINDAGOOD";
        spinnerAry[6] = "2122,BESTTEAMINTHEWOROLD";
        //ActualData
        System.out.println("Hello FakeData");
        readFile();
        SharedPreferences preferences = this.getSharedPreferences("preferences", MODE_PRIVATE);
        scoutName.setText(preferences.getString("ScoutName", ""));

        ArrayAdapter<String> teamSpnAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerAry);
        teamSpnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTeamSpinner.setAdapter(teamSpnAdapter);
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
        editor.putString("TypeWheels", getWheels());
        editor.putInt("BallCap", Integer.parseInt(ballCap.getText().toString()));
        editor.putString("StartLoc", startLoc.getText().toString());
        editor.putInt("AutoModes", Integer.parseInt(autoModes.getText().toString()));
        editor.putString("HumanPlayer", humanPlayer.getText().toString());
        editor.putString("DriveTrain", driveTrainType());
        editor.putBoolean("HighShoot", highShoot.isChecked());
        editor.putBoolean("LowShoot", lowShoot.isChecked());
        editor.putBoolean("PlaceGears", gearPlace.isChecked());
        editor.putBoolean("Cheesecake", cheesecake.isChecked());
        editor.putString("Team", spnTeamSpinner.getSelectedItem().toString());
        editor.putBoolean("Climber" ,climber.isChecked());
        editor.putBoolean("Defense", defense.isChecked());
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
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static void verifyStoragePermissions(Activity activity) {
        //Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        System.out.println("Hello ReachedVSP");
        if (permission != PackageManager.PERMISSION_GRANTED){
            System.out.println("Hello No Permission");
            //We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    private void readFile() {
        try {
            FileReader fr = new FileReader(Environment.getExternalStorageDirectory() + "/team_names.csv");
            Scanner scanner = new Scanner(fr);
            scanner.useDelimiter("\n");
            ArrayList<String> teams = new ArrayList<>();
            teams.add("Pick A Team");
            while (scanner.hasNext()){
                String team = scanner.next().trim();
                teams.add(team);
            }
            scanner.close();
            spinnerAry = teams.toArray(new String[teams.size()]);
        }
        catch (FileNotFoundException e){
            Toast t = Toast.makeText(context,
                    "File not found!", Toast.LENGTH_LONG);
            t.show();
        }
    }
    private boolean checkEverything(){
        if(scoutName.getText().toString().equals("")){
            Toast.makeText(context, "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(robotWeight.getText().toString().equals("")){
            Toast.makeText(context, "Please enter a weight", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(robotHeight.getText().toString().equals("")){
            Toast.makeText(context, "Please enter a height", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(robotWidth.getText().toString().equals("")){
            Toast.makeText(context, "Please enter a width", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(robotLength.getText().toString().equals("")){
            Toast.makeText(context, "Please enter a length", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(numWheels.getText().toString().equals("")){
            Toast.makeText(context, "Please enter the number of wheels", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(typeWheels.getText().toString().equals("")){
            Toast.makeText(context, "Please enter a wheel type", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(ballCap.getText().toString().equals("")){
            Toast.makeText(context, "Please enter a ball capacity", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(startLoc.getText().toString().equals("")){
            Toast.makeText(context, "Please enter a Starting Location", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(autoModes.getText().toString().equals("")){
            Toast.makeText(context, "Please enter the number of auto modes", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(humanPlayer.getText().toString().equals("")){
            Toast.makeText(context, "Please enter a human player preference", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!dtTank.isChecked() && !dtSwerve.isChecked() && !dtOmni.isChecked() && !dtMecanum.isChecked() && !dtOther.isChecked()){
            Toast.makeText(context, "Please enter a drive train type", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (spnTeamSpinner.getSelectedItem().toString().equals("Pick A Team")){
            Toast.makeText(context, "Please select a team", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void nextButton(View view){
        //if (!checkEverything()){
        //    return;
        //}
        saveData();

        startActivity (new Intent(Questions.this, Comments.class));
    }
    public String getWheels(){
            if(dtJump.isChecked()){
                return "Jump";
            }else if (dtMecanum.isChecked()) {
                return "Mecanum";
            }else if (dtOmni.isChecked()) {
                return "Omni";
            }else if (dtOther.isChecked()) {
                return "Other";
            }else if (dtSwerve.isChecked()) {
                return "Swerve";
            }else if (dtTank.isChecked()) {
                return "Swerve";
            }else
                return"none";
        }
    }




