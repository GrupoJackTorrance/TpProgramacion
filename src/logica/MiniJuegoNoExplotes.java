package logica;

import grafica.VentanaMiniJuegoNE;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MiniJuegoNoExplotes extends Minijuego {
	private String modalidad;
	private List<Jugador> jugadores;
	private List<Jugador> jugadoresvivos;
	private List<Jugador> jugadoresmuertos;
	private VentanaMiniJuegoNE ventana;

	private int i = 0;
	private int cantJugados;
	private int cantNumerosIngresados;
	private int tiemporeset = 7;

	public MiniJuegoNoExplotes(List<Jugador> jugadores) {
		this.jugadores = jugadores;
		this.jugadoresvivos = new ArrayList<Jugador>(jugadores);
		this.jugadoresmuertos = new ArrayList<Jugador>();
		this.cantJugados = jugadores.size();
		this.ventana = new VentanaMiniJuegoNE("minijuego", 100, 100, this);
		this.ventana.setVisible(true);
		this.ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.ventana.getPanel().setearNombreDeTurnoJugador(
				jugadores.get(0).getNombre());
	}

	public void cerrarMiniJuego() {
		this.ventana.dispose();
	}

	public String informarModalidad() {
		this.modalidad = "En este minijuego, la meta es no explotar!\n"
				+ "Cada jugador tomara turnos eligiendo un detonador\n"
				+ "-Cada detonador tiene una posibilidad de hacer explotar la dinamita que tiene abajo\n"
				+ "-Los ultimos jugadores vivos ganaran puntos. Los que explotan perderan puntos.\n"
				+ "-El jugador que no elige a tiempo ser� castigado con la quita de puntos";
		return modalidad;
	}

	public void recompensaCastigo() {

		for (Jugador jugador : jugadoresvivos) {
			System.out.println(jugador.getNombre() + " Gano!");
			jugador.sumarPuntos(10);
		}
		for (Jugador jugador : jugadoresmuertos) {
			jugador.restarPuntos(1);
		}
	}

	public int sortearNumero() { // lo puedo usar para los chances
		Dado dado = new Dado();
		return dado.tirar();
	}

	// Cuando elijo un detonador, desaparece y, con un 50% de probabilidad, sobrevive o muere el jugador.
	public void elegirDetonador(int numero, JButton boton) {
		System.out.println("Elegiste el detonador: " + numero);
		cantNumerosIngresados++;
		this.ventana.getPanel().interrumpirTimer();
		if (sortearNumero() > 3) {
			System.out.println("Explotaste!\n"+ jugadoresvivos.get(i).getNombre() + " ha muerto");
			//this.ventana.getPanel().explotaste("Explotaste",jugadoresvivos.get(i).getNombre());
			this.ventana.getPanel().setearTiempoExplosion(1); //Pone el timer para que la palabra BOOM aparezca por 1 segundo
			jugadoresmuertos.add(jugadoresvivos.remove(i));
			cantJugados--;
		} else {
			System.out.println("Te salvaste!");
			//this.ventana.getPanel().explotaste("no",jugadoresvivos.get(i).getNombre());
		}
		this.ventana.getPanel().detonadorActivado(boton);
		i = (i < cantJugados - 1) ? i + 1 : 0;

		if (this.cantJugados <= 1 || cantNumerosIngresados == 9) {
			terminaJuego();
		} else {
			this.ventana.getPanel().setearNombreDeTurnoJugador(this.jugadoresvivos.get(i).getNombre());
			this.ventana.getPanel().setearTiempo(tiemporeset);
		}

	}
	
	

	public void seAcaboElTiempo() { //El jugador que tenia el turno muere y continua con el proximo turno
		System.out
				.println("Se acabo el tiempo. "
						+ this.jugadoresvivos.get(i).getNombre()
						+ " se tiro de un precipicio de la verguenza y no participara mas de este minijuego.");
		jugadoresmuertos.add(jugadoresvivos.remove(i));
		cantJugados--;
		if (cantJugados <= 0)
			terminaJuego();
		else {
			i = (i < cantJugados - 1) ? i + 1 : 0;
			this.ventana.getPanel().setearNombreDeTurnoJugador(
					this.jugadoresvivos.get(i).getNombre());
			this.ventana.getPanel().setearTiempo(tiemporeset);

		}
	}

	public void terminaJuego() {
		this.ventana.getPanel().interrumpirTimer();
		recompensaCastigo();
		this.ventana.getPanel().mostrarResultados(this.jugadores,this.jugadoresvivos);
	}

}
