package fr.be2.gsbapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EnvoiDesDonnees extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoi_des_donnees);
    }


    public void clique_retour (View view){
        finish();
    }

}



