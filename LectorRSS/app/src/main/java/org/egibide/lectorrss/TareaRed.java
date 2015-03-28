package org.egibide.lectorrss;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by widemos on 28/3/15.
 */
public class TareaRed extends AsyncTask<Void, Void, List<Noticia>> {

    // Leer el feed RSS de elpais.com y devolver una lista de objetos Noticia, usando el parser

    @Override
    protected List<Noticia> doInBackground(Void... params) {

        List<Noticia> lista = null;

        try {
            URL url = new URL("http://ep00.epimg.net/rss/tags/ultimas_noticias.xml");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setInstanceFollowRedirects(true);

            InputStream is = conn.getInputStream();

            if (is != null) {
                ParserSAX p = new ParserSAX();
                lista = p.parseDocument(is);
                is.close();
                conn.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
