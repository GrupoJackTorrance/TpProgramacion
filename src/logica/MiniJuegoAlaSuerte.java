package logica;

import java.util.List;

import javax.swing.JFrame;

import grafica.VentanaMiniJuego;


public class MiniJuegoAlaSuerte extends Minijuego {
	private String modalidad;
	private String[] resultados;
	private List <Jugador> jugadores;
	private VentanaMiniJuego ventana;
	private int numeroSorteado;
	
	private int i=0;
	private int cantJugados;
	private int [] numerosIngresados=new int [4];
	public MiniJuegoAlaSuerte(List<Jugador> jugadores) {
		this.jugadores=jugadores;
		this.cantJugados=jugadores.size();
		this. ventana=new VentanaMiniJuego("minijuego", 100, 100,this);
		this.ventana.setVisible(true);
		this.ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.numeroSorteado=sortearNumero();
		
		this.ventana.getPanel().setearNombreDeTurnoJugador(jugadores.get(0).getNombre());
	}
	
	public void cerrarMiniJuego() {
		this.ventana.dispose();
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
				this.jugadores.get(i).sumarPuntos(10);
			else
				this.jugadores.get(i).restarPuntos(1);
				
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
			Dado dado=new Dado();
			this.cantJugados--;
		while(cantJugados>0){
			this.i++;
			int num=dado.tirar();
			this.ventana.getPanel().mostrarElegido(num);
			System.out.println("llego el numero: "+ num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.numerosIngresados[this.i]=num;
			this.cantJugados--;
		}
		if(this.cantJugados==0) {
			recompensaCastigo();
			this.ventana.getPanel().mostrarResultados(this.jugadores);
			this.ventana.getPanel().mostrarNumero(numeroSorteado);
			this.ventana.getPanel().mostrarNumeroSorteado(numeroSorteado);
		}
	if(this.cantJugados!=0)
			this.ventana.getPanel().setearNombreDeTurnoJugador(this.jugadores.get(i).getNombre());

	}

}