import java.util.List;

public class Ronda {
	int turno;
	int cantTurnos;
	
	
	
	public Ronda(int cantTurnos) {
		super();
		this.cantTurnos = cantTurnos;
	}

	public void InicioRonda(List<Jugador> listaJugadores,int cantJugadores,Tablero tablero) {
		this.turno=1;//Inicio el turno en 1
		int j;//cantidad de jugadores, es para recorrer la lista de jugadores
		Turno suTurno=null; 
		while(this.turno<cantJugadores) {
			suTurno=new Turno(this.turno);//inicializo un turno 
			j=0;
			while(!(listaJugadores.get(j).getNroTurno()==this.turno) && j<listaJugadores.size()) {//Si no estan ordenados en la lista los jugadores por turno
				//si no encuentro al jugador con el numero de turno que corresponde 
				j++;//sigo buscando
			}
			
			this.turno=suTurno.turno(this.turno,listaJugadores.get(j),tablero);//Inicio turno	
		}
	}
	
	
	public boolean terminaRonda(int cantJugadores) {
		if(this.turno<=cantJugadores)
			return false;
	return true;
	}

	public Jugador hayGanador(int puntosObjetivo, int cantJugadores, List<Jugador> listaJugadores){
		int i=0;
		while(i<cantJugadores)
			if(listaJugadores.get(i).getPuntos()==puntosObjetivo) {
				System.out.println("GANO JUGADOR"+listaJugadores.get(i).getNombre());
				return listaJugadores.get(i);
			}
		return null;
	}
     
	
}	

