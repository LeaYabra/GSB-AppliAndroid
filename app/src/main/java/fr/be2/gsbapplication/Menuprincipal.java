package fr.be2.gsbapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;


public class Menuprincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);
    }
    /**
     * affiche un message contenant un titre et un message passés en paramètres
     *
     * @param titre
     * @param message
     *
     * @return null
     */

    public void afficherMessage(String titre ,String message){
        AlertDialog.Builder Builder = new AlertDialog.Builder(this) ;
        Builder.setCancelable(true) ;
        Builder.setTitle(titre) ;
        Builder.setMessage(message) ;
        Builder.show() ;
    }

    /**
     * Méthode clic bouton menu 1
     * appliquée sur l'objet "frais hors forfait"
     * lance la classe Menuprincipal
     */

    public void unclique(View view) {

        if (view.getId()==R.id.btnfraisforfait) {
            Intent intent = new Intent(getApplicationContext(),FraisAuForfait.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.btnfraishorsforfait) {
            Intent intent  = new Intent(getApplicationContext(),FraisHorsForfait.class);
            startActivity(intent);
        }

        if (view.getId()==R.id.btnsynthesedumois) {
            Intent intent = new Intent(getApplicationContext(),SyntheseDuMois.class);
            startActivity(intent);
        }
        if (view.getId()==R.id.btnenvoi) {
            Intent intent = new Intent(getApplicationContext(),EnvoiDesDonnees.class);
            startActivity(intent);
        }

            if (view.getId() == R.id.btnparametres) {
                Intent intent = new Intent(getApplicationContext(),Parametres.class);
                startActivity(intent);
            }


    }
    }

