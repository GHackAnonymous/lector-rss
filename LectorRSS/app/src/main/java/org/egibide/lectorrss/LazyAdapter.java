package org.egibide.lectorrss;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by widemos on 30/3/15.
 */
public class LazyAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater = null;

    private List<Noticia> noticias = null;

    public LazyAdapter(Activity a, List<Noticia> noticias) {
        activity = a;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.noticias = noticias;
    }

    public int getCount() {
        return noticias.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        Noticia n = noticias.get(position);

        if (convertView == null)
            if (n.getUrlImagen() != null)
                vi = inflater.inflate(R.layout.fila_con_imagen, null);
            else
                vi = inflater.inflate(R.layout.fila_sin_imagen, null);

        try {

            TextView titulo = (TextView) vi.findViewById(R.id.titulo);
            TextView resumen = (TextView) vi.findViewById(R.id.resumen);
            ImageView imagen = null;
            if (n.getUrlImagen() != null)
                imagen = (ImageView) vi.findViewById(R.id.imagen);

            titulo.setText(n.getTitulo());
            resumen.setText(n.getResumen());

            if (imagen != null)
                if (n.getBitmapImagen() != null)
                    imagen.setImageBitmap(n.getBitmapImagen());
                else
                    new DescargarImagen(imagen, n)
                            .execute(n.getUrlImagen());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vi;
    }

}