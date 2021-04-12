package fr.be2.gsbapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SyntheseDuMois extends AppCompatActivity {

    //proptiétes
    private SQLhelper dbHelper;
    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthese_du_mois);
        setContentView(R.layout.activity_synthese_du_mois);

            dbHelper = new SQLhelper(this);
            dbHelper.open();


            //Générer le ListView a partir de SQLite Database
            displayListView();

        }

        private void displayListView () {
            Cursor cursor = dbHelper.fetchAllsynthese();

            // Les colonnes que l’on veut lier
            String[] columns = new String[]{
                    SQLhelper.ID,
                    SQLhelper.LIBELLE,
                    SQLhelper.QUANTITE,
                    SQLhelper.MONTANT,
                    SQLhelper.DATE,
                    SQLhelper.DATESAISIE,


            };


            // Les éléments defnis dans le XML auxquels les données sont liées
            int[] to = new int[]{
                    R.id.id,
                    R.id.libelle,
                    R.id.quantite,
                    R.id.montant,
                    R.id.date,
                    R.id.datesaisie,


            };

//On créer l'adaptateur à l'aide du curseur pointant sur les données souhaitées  ainsi que les informations de mise en page
            dataAdapter = new SimpleCursorAdapter(
                    this, R.layout.synthese_info,
                    cursor,
                    columns,
                    to,
                    0);


            ListView listView = (ListView) findViewById(R.id.listView1);
            // Attribuer l’adapter au ListView
            listView.setAdapter(dataAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> listView, View view,
                                        int position, long id) {
                    long boutonID = view.getId();
                    Toast.makeText(getApplicationContext(),
                            "" + boutonID, Toast.LENGTH_SHORT).show();
                    if (boutonID == R.id.delete) {
                        //On obtient le curseur, positionne sur la ligne correspondante dans le jeu de résultats
                        Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                        String myid =
                                cursor.getString(cursor.getColumnIndexOrThrow("ID"));
                        Toast.makeText(getApplicationContext(),
                                myid, Toast.LENGTH_SHORT).show();
                        Integer myid1 = Integer.parseInt(myid.toString());
                        dbHelper.deleteData(myid1);
                        displayListView();

                    }
                }

            });


        }


        public void clic_retour(View view) {
        finish();
    }


}