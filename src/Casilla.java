
public class Casilla {
int posicionX;
int posicionY;
String color; //color diferenciara el efecto? SI
public Casilla(int posicionX, int posicionY, String color) {
	this.posicionX=posicionX;
	this.posicionY=posicionY;
	this.color=color;
}
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
