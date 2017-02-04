package teamtators.com.a2122pitscout2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Questions extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Button buttonNext = (Button) findViewById(R.id.buttonNext);

        buttonNext.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View View)  {
                        startActivity (new Intent(Questions.this, Comments.class));

                    }
                }


        );


            }


    }




