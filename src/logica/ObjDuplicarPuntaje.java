package logica;

import java.util.List;

public class ObjDuplicarPuntaje extends EfectoDarObjeto{

	public ObjDuplicarPuntaje() {
		super(3, "Duplica tu puntaje");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean aplicarObj(Jugador jugadorAtacado, List<Jugador> listaJugadores) {
		int puntajeActual;
		int indice = listaJugadores.indexOf(jugadorAtacado);
		indice = (indice==0)?listaJugadores.size()-1:indice-1;
		puntajeActual = listaJugadores.get(indice).getPuntos();
		listaJugadores.get(indice).setPuntos(puntajeActual*2);
		return true;
	}

	@Override
	public void accion(Jugador jugador) {
		jugador.setObjEfectos(this);
		
	}
}
