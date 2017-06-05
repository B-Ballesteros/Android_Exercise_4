package itesm.mx.m1_jbbm_ejer_futbol;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ListActivity implements View.OnClickListener {

    static final int REQUEST_CODE_CONFIGURAR = 1;
    static final int REQUEST_CODE_AGREGAR = 2;
    Button btnConfigurar;
    Button btnAgregar;
    TextView tvEquipo;
    ImageView imgLogo;


    ArrayList<Jugador> arrayListJugador;
    JugadorAdapter adapterJugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        arrayListJugador = new ArrayList<>();
        adapterJugadores = new JugadorAdapter(this, arrayListJugador);
        setListAdapter(adapterJugadores);
    }

    @Override
    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.button_configurar:
                startActivityForResult(new Intent(MainActivity.this,
                        ConfiguracionActivity.class), REQUEST_CODE_CONFIGURAR);
                break;
            case R.id.button_agregar:
                if(validate()){
                    Toast.makeText(this.getApplicationContext(),
                            "ERROR: El equipo no ha sido configurado", Toast.LENGTH_SHORT).show();
                }else{
                    startActivityForResult(new Intent(MainActivity.this,
                            AgregarActivity.class), REQUEST_CODE_AGREGAR);
                }
                break;
        }
    }

    @Override
    protected  void  onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if( resultCode == RESULT_OK){
            switch(requestCode){
                case REQUEST_CODE_CONFIGURAR:
                    if(data.getExtras() != null) {
                        tvEquipo.setText(data.getStringExtra("equipo"));
                        imgLogo.setImageDrawable(getImage(data.getIntExtra("imagen",
                                R.mipmap.ic_launcher)));
                    }
                    break;
                case REQUEST_CODE_AGREGAR:
                    agregarJugador(data);
                    break;
            }
        }
    }

    void init(){
        loadControls();
        setListeners();
        tvEquipo.setText("");
        imgLogo.setImageDrawable(null);
    }

    void loadControls(){
        btnConfigurar = (Button)findViewById(R.id.button_configurar);
        btnAgregar = (Button)findViewById(R.id.button_agregar);
        tvEquipo  = (TextView)findViewById(R.id.text_equipo);
        imgLogo = (ImageView)findViewById(R.id.image_liga);
    }

    void setListeners(){
        btnConfigurar.setOnClickListener(this);
        btnAgregar.setOnClickListener(this);
    }

    BitmapDrawable getImage(int imageId){
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(), imageId), 750,
                750, true);
        return  new BitmapDrawable(bmp);
    }

    boolean validate(){
        return tvEquipo.getText().toString().equals("");

    }

    void agregarJugador(Intent data){
        if(data.getExtras() != null){
            Jugador jugador = new Jugador(data.getStringExtra("nombre"),
                    data.getStringExtra("nacionalidad"), data.getStringExtra("posicion"),
                    data.getIntExtra("imagen", R.mipmap.ic_launcher));
            arrayListJugador.add(jugador);
            adapterJugadores.notifyDataSetChanged();
        }
    }
}
