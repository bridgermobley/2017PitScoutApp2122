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

public class Comments extends AppCompatActivity {

    CheckBox cbxCrossLine, cbxDelayAuto, cbxPlaceGear, cbxShootFuel, cbxHopper, cbxPickBalls, cbxStartKey, cbxStartNextKey, cbxStartMiddle, cbxStartLoad;
    RadioButton rad1Year, rad2Year, rad3Year, rad4Year, rad5Year;
    EditText txtComments;
    Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        cbxCrossLine=(CheckBox)findViewById(R.id.checkCross);
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
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
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
}
