package fr.be2.gsbapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class FraisHorsForfait extends Menuprincipal {
    SQLhelper bdd;
    TextView date;
    EditText libelle;
    EditText montant;
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance();
    int aaaa = calendrier.get(Calendar.YEAR);
    int mm = calendrier.get(Calendar.MONTH);
    int jj = calendrier.get(Calendar.DAY_OF_MONTH);

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_hors_forfait);
        bdd = new SQLhelper(this);
        libelle = findViewById(R.id.Libelle);
        montant = findViewById(R.id.Montant);
        date = findViewById(R.id.Date);

    }

    public void save_DATA(View view) {

        if (libelle.getText().toString().trim().length() == 0) { // si rien dans Quantite
            afficherMessage("ERREUR", "aucun libelle n'a été renseigné");
            //return;


        } else if
        (montant.getText().toString().trim().length() == 0) { // si rien dans Quantite

            afficherMessage("ERREUR", "aucun montant n'a été renseigné");
            //return;

        } else if
        (date.getText().toString().trim().length() == 0) {
            afficherMessage("ERREUR", "aucune date n'a été saisie");
            //return;

        }else {
            String Libelle1 = libelle.getText().toString();
            Float Montant1 = Float.parseFloat("0"+montant.getText().toString());
            String Date1 = date.getText().toString();

            if (bdd.insertData(Libelle1, 0, Date1, Montant1, Libelle1)) {
                libelle.setText("");
                montant.setText("");
                date.setText("");
                Toast.makeText(FraisHorsForfait.this, "Frais enregistré", Toast.LENGTH_LONG).show();

            }
        }

    }


public void picker(View view)
    {
        picker = new DatePickerDialog(FraisHorsForfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                       date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                },aaaa,mm,jj);
        picker.show();
    }



    public void clique_retour(View view) {
        finish();

    }
}
