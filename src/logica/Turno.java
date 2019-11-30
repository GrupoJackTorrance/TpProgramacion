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
			tablero.getVentanaTablero().getPanelTablero().empiezaTurno(jugador.getNombre());
			tablero.getVentanaTablero().getPanelTablero().setearTurnoJugador(jugador.getNombre());
			tablero.getVentanaTablero().getPanelTablero().setearObjetos(jugador);
			cantObjetos= jugador.getCantidadObjetos();//OBJETOS ANTES DE MOVERSE
			puntosAnteriores = jugador.getPuntos(); //PUNTOS DEL JUGADOR ANTES DE TIRAR EL DADO
			int cantidad;
			if(turno==1)
				cantidad = tablero.getVentanaTablero().getPanelTablero().tirodado(jugador);
			else
				cantidad=tablero.getVentanaTablero().getPanelTablero().tiraDado2(jugador);
			
			tablero.getVentanaTablero().getPanelTablero().mostrarTiraDado(cantidad);
			
			//int cantidad = jugador.tirarDado();
			//tablero.getVentanaTablero().getPanelTablero().mostrardado(cantidad);
			
				tablero.avanzarJugador(jugador, cantidad);
			
		
				
			if(puntosAnteriores != jugador.getPuntos()) 
				tablero.getVentanaTablero().getPanelTablero().mostrarModificacionPts(jugador.getPuntos() - puntosAnteriores,jugador);
			tablero.getVentanaTablero().getPanelTablero().setearObjetos(jugador);
			if(cantObjetos != jugador.getCantidadObjetos())
				tablero.getVentanaTablero().getPanelTablero().mostrarModificacionObjt(jugador.getCantidadObjetos()-cantObjetos, jugador);
			// luego de tirar el dado y avanzar en casillero
			int idObj=0;
			String nombreObj="";
			if(jugador.getTipo().equals("normal")) {
			if(jugador.getObjEfectos().getIdObjeto()!=0 )
				idObj = tablero.deseaAtacar(jugador);//Para preguntar si el jugador quiere atacar o no
			else
				idObj = 0;
			}else {
				Dado dado= new Dado();
				int cant=dado.tirarObj();
				if(jugador.getObj1().getIdObjeto()!=0 && cant==1){
					idObj=jugador.getObj1().getIdObjeto();
				
				}else if (jugador.getObj2().getIdObjeto()!=0 && cant==2) {
					idObj=jugador.getObj2().getIdObjeto();
				}else if (jugador.getObj3().getIdObjeto()!=0 && cant==3) {
					idObj=jugador.getObj3().getIdObjeto();
				}
			}
			
			if(idObj==1)
				nombreObj="Descontar 5 puntos";
			else if(idObj==2)
				nombreObj="robar 8 puntos";
			else if(idObj==3)
				nombreObj="Duplica Puntaje";
				
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
										
										tablero.getVentanaTablero().getPanelTablero().mostrarAtaque(listaJugadores.get(jugadorAtacado),nombreObj, jugador);
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
