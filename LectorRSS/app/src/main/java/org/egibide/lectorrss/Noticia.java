package org.egibide.lectorrss;

import android.graphics.Bitmap;

/**
 * Created by widemos on 28/3/15.
 */
public class Noticia {

    // Esta clase contiene los datos que procesamos de una noticia contenida en un RSS

    private String titulo = null;
    private String resumen = null;

    private String urlImagen = null;    // Dirección de la foto
    private Bitmap bitmapImagen = null; // Contenido de la foto, una vez descargada

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Bitmap getBitmapImagen() {
        return bitmapImagen;
    }

    public void setBitmapImagen(Bitmap bitmapImagen) {
        this.bitmapImagen = bitmapImagen;
    }
}
