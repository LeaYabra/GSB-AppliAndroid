package fr.be2.gsbapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;

import java.util.Calendar;


public class FraisAuForfait extends Menuprincipal {
    //declaration des variables
    SQLhelper bdd;
    Spinner typefrais;
    EditText quantite;
    TextView dateFrais;
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance();
    int aaaa = calendrier.get(Calendar.YEAR);
    int mm = calendrier.get(Calendar.MONTH);
    int jj = calendrier.get(Calendar.DAY_OF_MONTH);

    //Tableau montant des frais au forfait
    Double MontantFrais[] = new Double[]{0.0, 110.00, 0.62, 80.00, 25.00};
    //declaration d'un tableau avec les libelles des frais forfaits
    //String[] tabType = new String[]{" ", "Forfait etape", "Frais KM", "Nuitee hotel", "Repas restaurant"};

    //constructeur
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_au_forfait);
        bdd = new SQLhelper(this);
        typefrais = findViewById(R.id.TypeFrais);
        quantite = findViewById(R.id.Quantite);
        dateFrais = findViewById(R.id.date);
       
    }

    /**
     * Ajoute les valeurs saisies à la base de donnée;
     * La fonction verifie dans un premier temps si le visiteur a bien rempli le champ quantité
     * Elle calcule le montant du frais (produit de la quantité par le montant fixé)
     * Si la fonction a bien enregistré les frais dans la base de donées, elle affiche un message de succés
     *
     * @param
     *
     * @return null
     */
    public void save_DATA(View view) {

        if
        (typefrais.getSelectedItem().toString().trim().length() == 0) { // si rien dans Quantite
            afficherMessage("ERREUR", "aucun type n'a été saisie");

        } else if
            (quantite.getText().toString().trim().length() == 0) { // si rien dans Quantite
             afficherMessage("ERREUR", "aucune quantité n'a été saisie");
            //return;


            } else if
            (dateFrais.getText().toString().trim().length() == 0) {

                afficherMessage("ERREUR", "aucune date n'a été saisie");
                //return;

        } else{
            String Date1 = dateFrais.getText().toString();
            String TypeFrais1 = typefrais.getSelectedItem().toString();
            Integer Quantite1 = Integer.parseInt("0"+quantite.getText().toString());
            int position = typefrais.getSelectedItemPosition();

            Double Tarif = Quantite1 * MontantFrais[position];
            if (bdd.insertData(TypeFrais1, Quantite1, Date1, Tarif, TypeFrais1)) {
                dateFrais.setText("");
                quantite.setText("");
                typefrais.setSelection(0);
                Toast.makeText(FraisAuForfait.this, "Frais enregistré avec succes", Toast.LENGTH_LONG).show();

            }


        }

    }

    /**
     *permet d'avoir un tableau de date pour saisir une date , mise a jour a la date du jour
     *
     * @param view
     *
     * @return null
     */
    public void picker(View view)
    {
        picker = new DatePickerDialog(FraisAuForfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateFrais.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                },aaaa,mm,jj);
        picker.show();
    }

    /**
     * Effectue un retour en arrière soit arrête l'activité en cours
     *
     * @param view
     *
     * @return null
     */

    public void clique_retour (View view){
            finish();
        }


}