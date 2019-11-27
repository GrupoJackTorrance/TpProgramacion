package logica;
import java.util.InputMismatchException;
import java.util.List;



import javax.swing.JOptionPane;

public class Turno {
	private int numeroTurno;
	private int delay;
	private int entrada = 2;
	private int puntosAnteriores;
	private int cantObjetos;

	public Turno(int numeroTurno, int delay) {
		this.numeroTurno = numeroTurno;
		this.delay = delay;// Tiempo de turno
	}

	public int turno(int turno, Jugador jugador, Tablero tablero, List<Jugador> listaJugadores)
			throws Exception {
		boolean termino = false;
		switch (turno) {
		case 1:
		case 2:
		case 3:
		case 4:
			System.out.println("Empieza turno");
			System.out.println("Juega jugador: " + turno);
			tablero.getVentanaTablero().getPanelTablero().empiezaTurno(jugador);
			tablero.getVentanaTablero().getPanelTablero().setearTurnoJugador(jugador);
			tablero.getVentanaTablero().getPanelTablero().setearObjetos(jugador);
			cantObjetos= jugador.getCantidadObjetos();//OBJETOS ANTES DE MOVERSE
			puntosAnteriores = jugador.getPuntos(); //PUNTOS DEL JUGADOR ANTES DE TIRAR EL DADO
			
			int cantidad = tablero.getVentanaTablero().getPanelTablero().tirodado(jugador);
			//int cantidad = jugador.tirarDado();
			//tablero.getVentanaTablero().getPanelTablero().mostrardado(cantidad);
			tablero.avanzarJugador(jugador, cantidad);
			if(puntosAnteriores != jugador.getPuntos()) 
				tablero.getVentanaTablero().getPanelTablero().mostrarModificacionPts(jugador.getPuntos() - puntosAnteriores,jugador);
			tablero.getVentanaTablero().getPanelTablero().setearObjetos(jugador);
			if(cantObjetos != jugador.getCantidadObjetos())
				tablero.getVentanaTablero().getPanelTablero().mostrarModificacionObjt(jugador.getCantidadObjetos()-cantObjetos, jugador);
			// luego de tirar el dado y avanzar en casillero
			int idObj;
			if(jugador.getObjEfectos().getIdObjeto()!=0)
				idObj = tablero.deseaAtacar(jugador);//Para preguntar si el jugador quiere atacar o no
			else
				idObj = 0;
			while (termino == false) {
				do {
					try {

						while (termino == false) {
							if (idObj == 999 || idObj == 0)
								termino = true;
							else  {
									int jugadorAtacado = (turno) % listaJugadores.size();
									if (!jugador.usarObjeto(listaJugadores.get(jugadorAtacado), listaJugadores, idObj)) {
										JOptionPane.showMessageDialog(null, "No se pudo atacar a "+ listaJugadores.get(jugadorAtacado).getNombre() +" porque no tiene objetos");
										termino = true;
									} else {
										termino = true;
										//tablero.getVentanaTablero().getPanelTablero().mostrarModificacionPts(listaJugadores.get(jugadorAtacado).getPuntos() - puntosAnteriores);
									}
							}
						}
						System.out.println("*********************************************");
						System.out.println("****************PROXIMO TURNO****************");
						System.out.println("*********************************************");
					} catch (InputMismatchException ime) {
						System.out.println("Cuidado! Solo puedes insertar numeros. ");
						ime.printStackTrace();
					}
				} while (termino == false);

			}

			if (termino == true) {
				turno++;
			}
			break;
		}

		if (turno == 5) {
			turno = 1;
		}
		
		return turno;
	}

	public Turno(int numeroTurno) {
		this.numeroTurno = numeroTurno;
		this.delay = 60;
	}

	public int getNumeroTurno() {
		return numeroTurno;
	}

	public void setNumeroTurno(int numeroTurno) {
		this.numeroTurno = numeroTurno;
	}

}
