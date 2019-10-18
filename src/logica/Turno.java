package logica;
import java.util.InputMismatchException;
import java.util.List;



import javax.swing.JOptionPane;

public class Turno {
	int numeroTurno;
	int delay;
	int entrada = 2;
	

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
			tablero.getVentanaTablero().getPanelTablero().setearTurnoJugador(jugador);
			int cantidad = jugador.tirarDado();
			tablero.getVentanaTablero().getPanelTablero().mostrardado(cantidad);
			tablero.getVentanaTablero().getPanelTablero().setearObjetos(jugador);
			tablero.avanzarJugador(jugador, cantidad);
			tablero.getVentanaTablero().getPanelTablero().setearObjetos(jugador);
			// luego de tirar el dado y avanzar en casillero
			if(jugador.getObjEfectos()==1) {
				 entrada = tablero.deseaAtacar(jugador);//Para preguntar si el jugador quiere atacar o no
			}else {
				termino=true;
			}
			while (termino == false) {
				do {
					try {

						while (termino == false) {
							if (entrada == 2)
								termino = true;
							else if (entrada != 2) {
								if (entrada == 1) {
									int jugadorAtacado = (turno) % 4;
									if (!jugador.usarObjeto(listaJugadores.get(jugadorAtacado))) {
										JOptionPane.showMessageDialog(null, "No se pudo atacar a "+ listaJugadores.get(jugadorAtacado).getNombre() +" porque no tiene objetos");
										termino = true;
									} else {
										//JOptionPane.showMessageDialog(null, "Ha atacado a: " + listaJugadores.get(jugadorAtacado).getNombre());
										termino = true;
									}
								}
								if (entrada == 0) {
									termino = true;
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
