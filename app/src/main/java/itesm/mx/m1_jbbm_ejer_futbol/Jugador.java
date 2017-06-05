package itesm.mx.m1_jbbm_ejer_futbol;

/**
 * Created by benji on 12/02/17.
 */

public class Jugador {
    private String nombre;
    private String nacionalidad;
    private String  posicion;
    private int imagenId;


    //region Constructor
    public Jugador(String nombre, String nacionalidad, String posicion, int imagenId){
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
        this.imagenId = imagenId;
    }
    //endregion

    //region getters & setters
    public void setNombre(String nombre){ this.nombre = nombre; }
    public String getNombre(){ return nombre; }

    public void setNacionalidad(String nacionalidad){ this.nacionalidad = nacionalidad; }
    public String getNacionalidad(){ return nacionalidad; }

    public void setPosicion(String posicion){ this.posicion = posicion; }
    public String getPosicion(){ return posicion; }

    public void setImagenId( int imagenId){ this.imagenId = imagenId; }
    public int getImagenId(){ return imagenId; }
    //endregion

}
