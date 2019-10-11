import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;


public class Minijuego {
	private String modalidad;
	private String[] resultados;
	private List <Jugador> jugadores;
	VentanaInicioMiniJuego ventana;
 int numeroSorteado;
	
	int i=0;
	int cantJugados;
	int [] numerosIngresados=new int [4];
	public Minijuego(List<Jugador> jugadores) {
		this.jugadores=jugadores;
		this.cantJugados=jugadores.size();
	   this. ventana=new VentanaInicioMiniJuego("minijuego", 100, 100,this);
		this.ventana.setVisible(true);
		this.ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		   this.numeroSorteado=sortearNumero();
		   this.ventana.getPanel().setearNombreDeTurnoJugador(jugadores.get(0).getNombre());
	
	}
	
	public String informarModalidad() {
		this.modalidad = "En este minijuego se va a sortear un numero del 1 al 6."
				+ "Cada jugador debe ingresar un número:\n"
				+ "-los jugadores que acierten van a recirbir puntos, los que no aciertan van a perder puntos.\n"
				+ "-si nadie acierta, no reciben castigo.\n"
				+ "-El jugador que no ingrese un número va a ser castigado con la quita de puntos";
		return modalidad;
	}



	public void recompensaCastigo() {
		for (int i=0; i<numerosIngresados.length;i++) {
			if(this.numerosIngresados[i]==this.numeroSorteado)
				this.jugadores.get(i).sumarPuntos(20);
			else
				this.jugadores.get(i).restarPuntos(20);
				
		}
	}
	public int sortearNumero() {	
		Dado dado=new Dado();
		return this.numeroSorteado=dado.tirar();
	}

	// para agregar seleccionado a la lista de numeros ingresados
	public void agregarNumero(int numero) {
		this.numerosIngresados[this.i]=numero;
		System.out.println("llego el numero: "+ numero);
		this.i++;
		this.cantJugados--;
		if(this.cantJugados==0) {
			recompensaCastigo();		
			this.ventana.getPanel().mostrarResultados(this.jugadores);
			this.ventana.getPanel().mostrarNumeroSorteado(numeroSorteado);
			
		}
	if(this.cantJugados!=0)
			this.ventana.getPanel().setearNombreDeTurnoJugador(this.jugadores.get(i).getNombre());

	}

	
	
}

