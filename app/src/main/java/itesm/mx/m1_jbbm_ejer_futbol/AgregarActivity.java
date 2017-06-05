package itesm.mx.m1_jbbm_ejer_futbol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AgregarActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    Spinner spPosicion;
    Button btnAgregar;
    EditText etNombre;
    EditText etNacionalidad;

    int imageId;
    String posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        init();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id){
        posicion = spPosicion.getItemAtPosition(pos).toString();
        imageId = setImageId(posicion);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }

    @Override
    public void onClick(View v) {
        if(esInvalido()) {
            Toast.makeText(this.getApplicationContext(), "ERROR: Datos incompletos",
                    Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent();
            intent.putExtra("nombre", etNombre.getText().toString());
            intent.putExtra("nacionalidad", etNacionalidad.getText().toString());
            intent.putExtra("posicion", posicion);
            intent.putExtra("imagen", imageId);
            setResult(RESULT_OK, intent);
            finish();
        }
    }


    void init(){
        loadControls();
        setSpinner();
        setListeners();
    }

    void loadControls(){
        spPosicion = (Spinner)findViewById(R.id.spinner_posicion);
        btnAgregar = (Button)findViewById(R.id.button_agregar);
        etNacionalidad = (EditText)findViewById(R.id.edit_nacionalidad);
        etNombre = (EditText)findViewById(R.id.edit_nombre);
    }

    void setListeners(){
        spPosicion.setOnItemSelectedListener(this);
        btnAgregar.setOnClickListener(this);
    }

    private void setSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.posiciones_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spPosicion.setAdapter(adapter);
    }

    private boolean esInvalido(){
        return (etNombre.getText().toString().equals("") &&
                etNacionalidad.getText().toString().equals(""));
    }

    private int setImageId(String liga){
        int result = -1;
        switch(liga){
            case "Portero":
                result = R.drawable.portero;
                break;
            case "Defensa":
                result = R.drawable.defensa;
                break;
            case "Mediocampista":
                result  = R.drawable.mediocampista;
                break;
            case  "Volante":
                result = R.drawable.volante;
                break;
            case "Puntero":
                result = R.drawable.puntero;
                break;
            case "Extremo":
                result = R.drawable.extremo;
                break;
            case "Delantero":
                result = R.drawable.delantero;
                break;
            default:
                result = -1;
        }
        return result;
    }
}
