package org.egibide.lectorrss;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends Activity {

    private List<Noticia> noticias;
    private ListView lista;

    public final static String EXTRA_TITULO = "org.egibide.lectornoticias.TITULO";
    public final static String EXTRA_RESUMEN = "org.egibide.lectornoticias.RESUMEN";
    public final static String EXTRA_IMAGEN = "org.egibide.lectornoticias.IMAGEN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TareaRed() {

            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(MainActivity.this, "Cargando ...", Toast.LENGTH_SHORT).show();
            }

            protected void onPostExecute(List<Noticia> resultado) {

                if (resultado != null) {
                    noticias = resultado;
                    if (noticias.size() > 0) {
                        cargarLista();
                    }
                    Toast.makeText(MainActivity.this, "Actualizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "ERROR: No hay red ...", Toast.LENGTH_SHORT).show();
                }
            }

        }.execute();

    }

    private void cargarLista() {
        lista = (ListView) findViewById(R.id.lista);
        LazyAdapter adaptador = new LazyAdapter(this, noticias);
        lista.setAdapter(adaptador);

        /*
        // Asociar el evento al ListView
        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        detalleNoticia(noticias.get(position));
                    }
                });
        */
    }

    /*
    private void detalleNoticia(Noticia n) {
        Intent intent = new Intent(this, NoticiaCompleta.class);
        intent.putExtra(EXTRA_TITULO, n.getTitulo());
        intent.putExtra(EXTRA_RESUMEN, n.getResumen());
        intent.putExtra(EXTRA_IMAGEN, n.getImagen());
        startActivity(intent);
    }
    */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}