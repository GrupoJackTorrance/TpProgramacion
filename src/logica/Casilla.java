package logica;
import java.awt.Color;
import java.awt.Image;

import javax.swing.*;

public class Casilla {


	private Color color; // color diferenciara el efecto? supongo que nos va a servir para la parte
							// gráfica

	private Image imagen;
	private Efecto efecto;
	private boolean esUnion;

	public Casilla(Image imagen,Color color, Efecto efecto, boolean esUnion) {
		this.imagen=imagen; 
		this.color = color;
		this.efecto = efecto;
		this.esUnion = esUnion;
	}

	
	public Casilla(Color color, Efecto efecto, boolean esUnion) { 
		this.color = color;
		this.efecto = efecto;
		this.esUnion = esUnion;
	}
	public void aplicarEfecto(Jugador jugador) {
	efecto.accion(jugador);
	}

	public Color getColor() {
		return color;
	}

	public boolean getEsUnion() {
		return esUnion;
	}

	public Image getImagen() {
		return imagen;
	}	

}
