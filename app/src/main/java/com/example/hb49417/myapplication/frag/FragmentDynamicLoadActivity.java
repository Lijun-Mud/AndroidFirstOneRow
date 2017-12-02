package com.example.hb49417.myapplication.frag;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.hb49417.myapplication.R;

public class FragmentDynamicLoadActivity extends AppCompatActivity implements View.OnClickListener {

    //private FrameLayout frameLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, FragmentDynamicLoadActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_dynamic_load);
        Button replaceButton= (Button) findViewById(R.id.fragment_dynamic_replace_button);
        replaceButton.setOnClickListener(this);
        //frameLayout = (FrameLayout) findViewById(R.id.fragment_dynamic_right_frame_layout);
        replaceRightFragment(new RightFragment());
    }

    private void replaceRightFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_dynamic_right_frame_layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_dynamic_replace_button:
                replaceRightFragment(new RightSecondFragment());
                break;
            default:
                break;
        }
    }
}
