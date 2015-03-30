package org.egibide.lectorrss;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by widemos on 30/3/15.
 */
public class DescargarImagen extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImage;
    Noticia n;

    public DescargarImagen(ImageView bmImage, Noticia n) {
        this.bmImage = bmImage;
        this.n = n;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;

        if (n.getBitmapImagen() == null) {
            try {
                URL url = new URL(urldisplay);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setInstanceFollowRedirects(true);

                InputStream is = conn.getInputStream();

                if (is != null) {
                    mIcon11 = BitmapFactory.decodeStream(is);
                    is.close();
                    conn.disconnect();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            mIcon11 = n.getBitmapImagen();

        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        if (n.getBitmapImagen() == null)
            n.setBitmapImagen(result);
        bmImage.setImageBitmap(result);
    }
}