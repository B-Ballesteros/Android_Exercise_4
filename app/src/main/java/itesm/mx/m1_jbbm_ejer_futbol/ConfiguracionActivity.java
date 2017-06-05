package itesm.mx.m1_jbbm_ejer_futbol;

import android.content.Intent;
import android.support.v4.view.KeyEventCompat;
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

public class ConfiguracionActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    Spinner spLigas;
    Button btnGuardar;
    EditText etEquipo;
    EditText etDirector;

    int imageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        init();
        spLigas.setSelection(0);
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
        imageId = setImageId(spLigas.getItemAtPosition(pos).toString());
        System.out.println(imageId + "");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }

    @Override
    public void onClick(View v){
        if(esInvalido()){
            Toast.makeText(this.getApplicationContext(), "ERROR: Datos incompletos",
                    Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent();
            intent.putExtra("director", etDirector.getText().toString());
            intent.putExtra("equipo", etEquipo.getText().toString());
            intent.putExtra("imagen", imageId);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private void init(){
        loadControls();
        setSpinner();
        setListeners();
    }

    private void loadControls(){
        spLigas = (Spinner)findViewById(R.id.spinner_liga);
        btnGuardar = (Button)findViewById(R.id.button_guardar);
        etDirector = (EditText)findViewById(R.id.edit_nombreDT);
        etEquipo = (EditText)findViewById(R.id.edit_nombreEquipo);

    }

    private void setListeners(){
        spLigas.setOnItemSelectedListener(this);
        btnGuardar.setOnClickListener(this);
    }

    private void setSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ligas_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spLigas.setAdapter(adapter);
    }


    private int setImageId(String liga){
        int result = -1;
        switch(liga){
            case "Premier League":
                result = R.drawable.premier_league;
                break;
            case "LaLiga":
                result = R.drawable.laliga;
                break;
            case "Bundesliga":
                result  = R.drawable.bundesliga;
                break;
            case  "Liga Mx":
                result = R.drawable.ligamx;
                break;
            case "Serie A":
                result = R.drawable.seriea;
                break;
            default:
                result = -1;
        }
        return result;
    }

    private boolean esInvalido(){
        return (etEquipo.getText().toString().equals("") &&
                etDirector.getText().toString().equals(""));
    }
}
