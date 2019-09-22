
public class Casilla {
int posicionX;
int posicionY;
String color; //color diferenciara el efecto?

public void aplicarEfecto(Jugador j) {
	if(color.contentEquals("azul"))
		{
		Efecto.dar1pto(j);
		}
}
	
public String getColor(){
	return color;
}

}
