package teamtators.com.a2122pitscout2017;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class Comments extends AppCompatActivity {

    CheckBox cbxCrossLine, cbxDelayAuto, cbxPlaceGear, cbxShootFuel, cbxHopper, cbxPickBalls, cbxStartKey, cbxStartNextKey, cbxStartMiddle, cbxStartLoad;
    RadioButton rad1Year, rad2Year, rad3Year, rad4Year, rad5Year;
    EditText txtComments;

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
        
    }
}
