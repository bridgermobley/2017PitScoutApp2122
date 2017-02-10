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
    RadioButton dtTank, dtSwerve, dtMecanum, dtOmni, dtOther;
    CheckBox highShoot, lowShoot, gearPlace, hasAuto, cheesecake;
    String[] spinnerAry;
    Context context;
    Spinner spnTeamSpinner;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        verifyStoragePermissions(this);
        context = this;
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
        spnTeamSpinner = (Spinner)findViewById(R.id.spinnerChoseTeam);

        spinnerAry = new String[7];
        //DummyData
        spinnerAry[0] = "Pick A Team";
        spinnerAry[1] = "254";
        spinnerAry[2] = "976";
        spinnerAry[3] = "987";
        spinnerAry[4] = "1114";
        spinnerAry[5] = "2056";
        spinnerAry[6] = "2122";
        //ActualData
        System.out.println("Hello FakeData");
        readFile();

        ArrayAdapter<String> teamSpnAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerAry);
        teamSpnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTeamSpinner.setAdapter(teamSpnAdapter);

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
}




