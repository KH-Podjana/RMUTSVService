package app.rmutsv.kh_podjana.rmutsvservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.rmutsv.kh_podjana.rmutsvservice.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  //      Add Fragment to Activity
        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFragmentMain, new MainFragment()).commit(); //การ add fragment

        }

    } // Main Method

}//Main Class
