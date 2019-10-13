
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Turno {
	int numeroTurno;
	int delay;
	int entrada = 2;

	public Turno(int numeroTurno, int delay) {
		this.numeroTurno = numeroTurno;
		this.delay = delay;// Tiempo de turno
	}

	public int turno(int turno, Jugador jugador, Tablero tablero, Scanner reader, List<Jugador> listaJugadores)
			throws Exception {
		boolean termino = false;
		switch (turno) {
		case 1:
		case 2:
		case 3:
		case 4:
			System.out.println("Empieza turno");
			System.out.println("Juega jugador: " + turno);
			int cantidad = jugador.tirarDado();
			tablero.avanzarJugador(jugador, cantidad);
			// luego de tirar el dado y avanzar en casillero
			int entrada = tablero.deseaAtacar();//Para preguntar si el jugador quiere atacar o no
			while (termino == false) {
				int advertencia = 0;
				System.out.println("DESEA REALIZAR USAR UN OBJETO:");
				System.out.println("1-Utilizar Poder");
				System.out.println("0-No gracias,Termina turno");
				do {
					try {

						while (termino == false) {
							Thread.sleep(1000);
							// Entrada de teclado por archivo de texto (por el momento)
							this.entrada = reader.nextInt();

							if (entrada == 2)
								termino = false;
							// this.entrada=reader.nextInt();
							else if (entrada != 2) {
								if (entrada == 1) {
									int jugadorAtacado = (turno + 1) % 4;
									if (!jugador.usarObjeto(listaJugadores.get(jugadorAtacado))) {
										System.out.println("No se pudo atacar porque no tiene objetos");
										termino = true;
									} else {
										System.out.println(
												"A atacado a:" + listaJugadores.get(jugadorAtacado).getNombre());
										termino = true;
									}
								}
								if (entrada == 0) {
									System.out.println("Elegiste NO, termino tu turno");
									termino = true;
								}

							}
							advertencia++;
							if (advertencia == 50)
								System.out.println("APURATE QUEDA POCO TIEMPO!");
							else if (advertencia == 60) {
								System.out.println("Se termino el tiempo de tu turno");
								termino = true;
							}

						}
					} catch (InputMismatchException ime) {
						System.out.println("Cuidado! Solo puedes insertar numeros. ");
						// reader.next();
					}
				} while (termino == false);

			}

			if (termino = true) {
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
