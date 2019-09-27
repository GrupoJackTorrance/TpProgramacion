
import java.util.InputMismatchException;
import java.util.Scanner;




public class Turno{
		int numeroTurno;
		int delay;
		int entrada=2;
		
		public Turno(int numeroTurno, int delay) {
			super();
			this.numeroTurno = numeroTurno;
			this.delay = delay;//Tiempo de turno
		}



		public int turno (int turno, Jugador jugador,Tablero tablero,Scanner reader) throws Exception {
			    boolean termino=false;
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
		            	while(termino==false) {
		            		int advertencia=0;
		            		System.out.println("DESEA REALIZAR USAR UN OBJETO:");
		            		System.out.println("1-Utilizar Poder");
		            		System.out.println("0-No gracias,Termina turno");
		            		do {			
		            			  try {
		            				  
		            				  while(termino==false) {
		            					  		Thread.sleep(1000);
		            					  	//Entrada de teclado por archivo de texto (por el momento) 
		            					  		this.entrada = reader.nextInt();
		            							advertencia++;
		            							if(advertencia==50)
		            								System.out.println("¡APURATE QUEDA POCO TIEMPO!");
		            							else if(advertencia==60) {
		            									System.out.println("Se termino el tiempo de tu turno");
		            									termino=true;
		            							}
		            						    //this.entrada=reader.nextInt();
		      	            				  	if(entrada!=2) {
		      	            					  if(entrada==1 && jugador.getObjEfectos()==1) 
		      	            						  System.out.println("¡Utilizo poder!");
		      	            					  else	if(entrada==0) {
		      	            						  		System.out.println("Elegiste NO, termino tu turno");
		      		            			    			termino=true;
		      	            					  		}else {
		      	            				  				System.out.println("Elegiste Si pero No tienes objetos para usar \nSe termino tu turno");
		      	            				  		}
		      	            					termino=true;
		      	            				  }
		            						}	
		            				  if(entrada==2)
		            					  termino=true;  
		            			  } catch (InputMismatchException ime){
		            			    System.out.println("¡Cuidado! Solo puedes insertar números. ");
		            			    //reader.next();
		            			  }
		            			} while (termino==false);
		            		
		            	}
		            	
		            	if(termino=true) {
		            		turno++;
		            	}
		            	 break;      
		        }
		        
		        if(turno==5) {
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
