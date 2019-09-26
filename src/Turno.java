
import java.util.Timer;



public class Turno{
		int numeroTurno;
		int delay;
		int cantTurnos;

		public Turno(int numeroTurno, int delay, int cantTurnos) {
			super();
			this.numeroTurno = numeroTurno;
			this.delay = delay;//Tiempo de turno
			this.cantTurnos = cantTurnos;
		}


		public int turno (int turno, int n) {
			 
	        switch (turno) {
	            case 1: 
	            case 2: 
	            case 3:
	            case 4:
	            	System.out.println("Empieza turno");
	            	System.out.println("Juega jugador: "+turno);
	            	Contador contador=new Contador(turno);//cronometro de turno
	            	Timer timer = new Timer();
	   		     	timer.schedule(contador, 0, 1000);
	   		     	/*
	   		     	//Lograr esta parte como una tarea en segundo plano
	   		     	//la cual comprueba el contador para saber cuando termina turno
	            	*/
	            	while(contador.getSeconds()>=0) {//comprueba contador
	            		System.out.println("acaaa");
	            	}
	            	
	            	if(contador.getSeconds()<0) {//contador en 0
	            		turno++;//pasa al proximo turno
	            		timer.cancel();
	            	}
	            	 break;    
	            	
	        }
	        
	        n++;
	        if(turno==5) {
            	System.out.println("termino partida");
                System.out.println("Temporizador apagado.");
            	n=0;
            	turno=1;
            }
	        return turno;
	  }
	
	
     public Turno(int numeroTurno, int cantTurnos) {
    	this.cantTurnos=cantTurnos;
		this.numeroTurno = numeroTurno;
		this.delay = 60;
	}
     
 	public int getCantTurnos() {
		return cantTurnos;
	}

	public void setCantTurnos(int cantTurnos) {
		this.cantTurnos = cantTurnos;
	}

	public int getNumeroTurno() {
 		return numeroTurno;
 	}
 	public void setNumeroTurno(int numeroTurno) {
 		this.numeroTurno = numeroTurno;
 	}

}