
public class Casilla {
int posicionX;
int posicionY;
String color; //color diferenciara el efecto? supongo que nos va a servir para la parte gráfica 
Efecto efecto;
public Casilla(int posicionX, int posicionY, String color,Efecto efecto) {
	this.posicionX=posicionX;
	this.posicionY=posicionY;
	this.color=color;
	this.efecto=efecto;
}
public void aplicarEfecto(Jugador jugador) {
	String tipo=efecto.getTipo();
	if(tipo.equals("sumarPuntos")){
	efecto.dar1pto(jugador);
	}
	else if(tipo.equals("restarPuntos")) {
		efecto.restarPuntos(jugador);
	}
	else {
		efecto.neutro(jugador);
	}
			
	
}

public String getColor(){
	return color;
}


}
