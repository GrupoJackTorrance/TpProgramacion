import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Minijuego {
	private String modalidad;
	private String[] resultados;
	private List <Jugador> jugadores;
 int numeroSorteado;
	private Dado dado=new Dado();
	int i=0;
	int cantJugados;
	int [] numerosIngresados=new int [4];
	public Minijuego(List<Jugador> jugadores) {
		this.jugadores=jugadores;
		this.cantJugados=jugadores.size();
	}
	
	public String informarModalidad() {
		this.modalidad = "En este minijuego se va a sortear un numero del 1 al 6."
				+ "Cada jugador debe ingresar un número:\n"
				+ "-los jugadores que acierten van a recirbir puntos, los que no aciertan van a perder puntos.\n"
				+ "-si nadie acierta, no reciben castigo.\n"
				+ "-El jugador que no ingrese un número va a ser castigado con la quita de puntos";
		return modalidad;
	}

	public void iniciarMinijuego() throws InterruptedException {
	//	this.numeroSorteado=sortearNumero();	
		// una vez que todos hayan ingresado su numero veo a quien recompensar y a quien castigar
		// deberia haber un tiempo para el ingreso de cada jugador			
	
		//System.out.println("sorteado:" +this.numeroSorteado);
		
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
		return this.numeroSorteado=dado.tirar();
	}
	// para agregar el numero ingresado en el textfield  a la lista de numeros ingresados
	public boolean agregarNumero(int numero) {
		this.numerosIngresados[this.i]=numero;
		this.i++;
		this.cantJugados--;
		if(this.cantJugados==0) {
			System.out.println("ennnnnasda");
			recompensaCastigo();
			return false;
			
		}
		
		return true;
	}
	
	
}
