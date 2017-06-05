package itesm.mx.m1_jbbm_ejer_futbol;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by benji on 13/02/17.
 */

public class JugadorAdapter extends ArrayAdapter<Jugador>{

    //region Constructor
    public JugadorAdapter(Context context, ArrayList<Jugador> jugadores){
        super(context, 0, jugadores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Jugador jugador = getItem(position);

        convertView = (convertView == null) ?
                LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false) :
                convertView;

        TextView tvNombre  = (TextView)convertView.findViewById(R.id.text_nombre);
        TextView tvNacionalidad  = (TextView)convertView.findViewById(R.id.text_nacionalidad);
        TextView tvPosicion  = (TextView)convertView.findViewById(R.id.text_posicion);
        ImageView ivPosicion =(ImageView)convertView.findViewById(R.id.iamge_jugador);

        tvNombre.setText(jugador.getNombre());
        tvNacionalidad.setText(jugador.getNacionalidad());
        tvPosicion.setText(jugador.getPosicion());
        //ivPosicion.setImageResource(jugador.getImagenId());
        ivPosicion.setImageDrawable(getImage(jugador.getImagenId()));

        return convertView;
    }

    BitmapDrawable getImage(int imageId){
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getContext().getResources(), imageId), 750,
                750, true);
        return  new BitmapDrawable(bmp);
    }
    //endregion
}
