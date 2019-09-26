
import java.util.List;
import java.util.Timer;



public class Turno{
		int numeroTurno;
		int delay;

		public Turno(int numeroTurno, int delay) {
			super();
			this.numeroTurno = numeroTurno;
			this.delay = delay;//Tiempo de turno
		}


		public int turno (int turno, Jugador jugador,Tablero tablero) {
	        
			switch (turno) {
	            case 1: 
	            case 2: 
	            case 3:
	            case 4:
	            	System.out.println("Empieza turno");
	            	System.out.println("Juega jugador: "+turno);
	            	int cantidad=jugador.tirarDado();
	            	tablero.avanzarJugador(jugador, cantidad);
	            	//luego de tirar el dado y avanzar en casillero
	            	Contador contador=new Contador(turno);//cronometro de turno
	            	Timer timer = new Timer();
	   		     	timer.schedule(contador, 0, 1000);//Inicio cronometro
	   		     	/*
	   		     	//Lograr esta parte como una tarea en segundo plano
	   		     	//la cual comprueba el contador para saber cuando termina turno
	            	*/
	            	while(contador.getSeconds()>=0) {//comprueba contador
	            		System.out.println("Comprueba");
	            	}
	            	
	            	if(contador.getSeconds()<0) {//contador en 0
	            		turno++;//pasa al proximo turno
	            		timer.cancel();
	            	}
	            	 break;    
	            	
	        }
	        
	        if(turno==5) {
            	System.out.println("termino partida");
                System.out.println("Temporizador apagado.");
            	turno=1;
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