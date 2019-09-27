import java.util.List;
import java.util.Scanner;

public class Ronda {
	int turno;
	int cantTurnos;

	public Ronda(int cantTurnos) {
		super();
		this.cantTurnos = cantTurnos;
	}

	public boolean InicioRonda(List<Jugador> listaJugadores,Tablero tablero,int puntosObjetivo, Scanner reader ) throws Exception {
		this.turno=1;//Inicio el turno en 1
		Turno suTurno=null;
		int i=0;
		while(i<this.cantTurnos  && GanadorporObjetivo(puntosObjetivo,this.cantTurnos,listaJugadores)==false) {
			suTurno=new Turno(this.turno);//inicializo un turno 
			//Si esta ordenado por turno la lista
			this.turno=suTurno.turno(this.turno,listaJugadores.get(this.turno-1),tablero,reader,listaJugadores);//Inicio turno	
			
			//Si no estan ordenados en la lista los jugadores por turno 
			/*int j=0;
			while(!(listaJugadores.get(j).getNroTurno()==this.turno) && j<listaJugadores.size()) {
				//si no encuentro al jugador con el numero de turno que corresponde 
				j++;//sigo buscando
			}
			this.turno=suTurno.turno(this.turno,listaJugadores.get(j),tablero,reader);//Inicio turno
			*/
			i++;
			
		}
		
		return GanadorporObjetivo(puntosObjetivo,this.cantTurnos,listaJugadores);
	}

	public boolean terminaRonda(int cantJugadores) {
		if (this.turno <= cantJugadores)
			return false;
		return true;
	}

	public boolean GanadorporObjetivo(int puntosObjetivo, int cantJugadores, List<Jugador> listaJugadores){
		int i=0;
		while(i<cantJugadores) {
			if(listaJugadores.get(i).getPuntos()>=puntosObjetivo) {
				return true;
			}
			i++;
		}
		return false;
	}

}
