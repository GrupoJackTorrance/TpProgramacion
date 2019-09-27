import java.util.LinkedList;
import java.util.List;

public class Tablero {

//  					 ------------	A T R I B U T O S    -------------
	
	String nombre;
	Casilla mapa[][] = new Casilla [8][12];
	List<Jugador> jugadores = new LinkedList<Jugador>();
	//        ----------  C O N S T R U C T O R---------------------
	public Tablero(List <Jugador> jugadores) {
		this.jugadores=jugadores;
	}
	
	
// 						 ------------	M E T O D O S    -------------
	
	//Constructor por defecto
	public Tablero() {
		this.nombre="Tablero partida";
		
		 /*
		 Se crea cada objeto Casilla para cada posición de la matriz. Aquellas posiciones en las que no se creó ningun 
		 objeto toman el valor null.
		 Se hardcodeó para poder hacer las pruebas con un tablero que conozcamos, con bifurcaciones.
		 */
	
		Efecto efectoSumarPuntos= new Efecto("sumarPuntos"); //verde
		Efecto sinEfecto= new Efecto("sinEfecto"); // blanco
		Efecto efectoRestarPuntos= new Efecto("restarPuntos"); // rojo
		Efecto efectoDarObjeto= new Efecto("objetoPoder"); // amarillo
		mapa[0][0] = new Casilla(0,0, "blanco",sinEfecto,true);
		mapa[0][1] = new Casilla(0,1, "blanco",sinEfecto,false);
		mapa[0][2] = new Casilla(0,2, "amarillo",efectoDarObjeto,false);
		mapa[0][3] = new Casilla(0,3, "verde",efectoSumarPuntos,false);
		mapa[0][4] = new Casilla(0,4, "verde",efectoSumarPuntos,false);
		mapa[0][5] = new Casilla(0,5, "rojo",efectoRestarPuntos,true);
		mapa[0][6] = new Casilla(0,6, "amarillo",efectoDarObjeto,false);
		mapa[0][7] = new Casilla(0,7, "rojo",efectoRestarPuntos,false);
		mapa[0][8] = new Casilla(0,8, "verde",efectoSumarPuntos,false);
		mapa[0][9] = new Casilla(0,9, "blanco",sinEfecto,false);
		mapa[0][10] = new Casilla(0,10, "verde",efectoSumarPuntos,false);
		
		mapa[1][0]= new Casilla(1,0,"blanco",sinEfecto,false);
		mapa[1][5] = new Casilla(1,5, "rojo",efectoRestarPuntos,false);
		mapa[1][10] = new Casilla(1,10, "rojo",efectoRestarPuntos,false);
		
		mapa[2][0] = new Casilla(2,0, "verde",efectoSumarPuntos,false);
		mapa[2][5] = new Casilla(2,5, "amarillo",efectoDarObjeto,false);
		mapa[2][8] = new Casilla(2,8, "verde",efectoSumarPuntos,false);
		mapa[2][9] = new Casilla(2,9, "blanco",sinEfecto,false);
		mapa[2][10] = new Casilla(2,10, "amarillo",efectoDarObjeto,false);
		
		
		mapa[3][0] = new Casilla(3,0, "rojo",efectoRestarPuntos,false);
		mapa[3][2] = new Casilla(3,2, "verde",efectoSumarPuntos,false);
		mapa[3][3] = new Casilla(3,3, "verde",efectoSumarPuntos,false);
		mapa[3][4] = new Casilla(3,4, "amarillo",efectoDarObjeto,false);
		mapa[3][5] = new Casilla(3,5, "rojo",efectoRestarPuntos,true);
		mapa[3][8] = new Casilla(3,8, "rojo",efectoRestarPuntos,false);
		
		mapa[4][0] = new Casilla(1,10, "blanco",sinEfecto,false);
		mapa[4][2] = new Casilla(4,2, "amarillo",efectoDarObjeto,false);
		mapa[4][5] = new Casilla(4,5, "blanco",sinEfecto,false);
		mapa[4][8]= new Casilla(4,8,"verde",efectoSumarPuntos,false);
		
		mapa[5][0] = new Casilla(5,0, "blanco",sinEfecto,true);
		mapa[5][1] = new Casilla(5,1, "blanco",sinEfecto,false);
		mapa[5][2] = new Casilla(5,2, "amarillo",efectoDarObjeto,false);
		mapa[5][5] = new Casilla(5,5, "rojo",efectoRestarPuntos,false);
		mapa[5][8] = new Casilla(5,8, "rojo",efectoRestarPuntos,false);
		mapa[5][9] = new Casilla(5,9, "verde",efectoSumarPuntos,false);
		mapa[5][10] = new Casilla(5,10, "blanco",sinEfecto,false);
	    mapa[5][11] = new Casilla(5,11, "blanco",sinEfecto,false);
	    
		mapa[6][0] = new Casilla(6,0, "verde",efectoSumarPuntos,false);
		mapa[6][5]= new Casilla(6,5, "amarillo",efectoDarObjeto,false);
		mapa[6][11]= new Casilla(6,11, "verde",efectoSumarPuntos,false);
		
		mapa[7][0] = new Casilla(7,0, "verde",efectoSumarPuntos,false);
		mapa[7][1] = new Casilla(7,1, "verde",efectoSumarPuntos,false);
		mapa[7][2] = new Casilla(7,2, "amarillo",efectoDarObjeto,false);
		mapa[7][3] = new Casilla(7,3, "rojo",efectoRestarPuntos,false);
		mapa[7][4] = new Casilla(7,4, "blanco",sinEfecto,false);
		mapa[7][5] = new Casilla(7,5, "blanco",sinEfecto,true);
		mapa[7][6] = new Casilla(7,6, "amarillo",efectoDarObjeto,false);
		mapa[7][7] = new Casilla(7,7, "rojo",efectoRestarPuntos,false);
		mapa[7][8] = new Casilla(7,8, "rojo",efectoRestarPuntos,false);
		mapa[7][9] = new Casilla(7,9, "blanco",sinEfecto,false);
		mapa[7][10] = new Casilla(7,10, "verde",efectoSumarPuntos,false);
		mapa[7][11] = new Casilla(7,11, "verde",efectoSumarPuntos,false);
		
	   
	   
	}
	
	/* COMENTO ESTO PARA VER SI PUEDO DESARROLAR ESOS METODOS DE OTRA FORMA
	public boolean avanzarJugador(int posiciones, Jugador miJugador) {
		int posX=0, posY, i=0, j=0;;
		Scanner in;
		
		while(posiciones>0) {
			
			// Las variables i y j corresponden a las coordenadas de la posición del jugador 
			//(x e y)
			// Comienzo evaluando si hay alguna bifurcación a tomar. 
			//Para esto, hay que chequear que no me salga de la matriz
		    //xq daría null pointer exception, y verificar alguno de los pares de 
			//casilleros que rodean al jugador, estén libres (instanciados)
			// 

			if((j<mapa.length && i<mapa.length && puedeAvanzar(i, j+1) && puedeAvanzar(i+1, j)) || 
					(i<mapa.length && j>1 && puedeAvanzar(i+1, j) && puedeAvanzar(i, j-1)) ||
					(i>1 && j>1 && puedeAvanzar(i-1, j) && puedeAvanzar(i, j-1)) ||
					(i>1 && j<mapa.length && puedeAvanzar(i-1, j) && puedeAvanzar(i, j+1))
					
					) {
				//le pido que ingrese las coordenadas a las que quiere ir
				
				System.out.println("Ingrese la posición en X");
				in = new Scanner(System.in);
		        posX = in.nextInt();
		        System.out.println("Ingresaste x "+posX);
		        System.out.println("Ingrese la posición en Y");
		        posY = in.nextInt();
		        System.out.println("Ingresaste x "+posY);
		        
		        //posicionar al jugadoren el par (x,y) ingresado
			}
				else {	
					if(j<4 && puedeAvanzar(i, j+1) && verificarPosAnterior(i, j+1)){ //Verifico que no salga de la matriz, que el casillero este libre, y que no haya pasado por ahí en el avance anterior
						//me muevo a la derecha: incremento en 1 la posicion en j del jugador
					}
					else if(i<4 && puedeAvanzar(i+1, j) && verificarPosAnterior(i+1, j)){
						//me muevo para abajo: incremento en 1 la posicion en i del jugador
					}
					else if(j>1 && puedeAvanzar(i, j-1) && verificarPosAnterior(i, j-1)){
						//me muevo a la izquierda: decremento en 1 la posicion en j del jugador
					}
					else if(i>1 && puedeAvanzar(i-1, j) && verificarPosAnterior(i-1, j)){
						//me muevo para arriba: decremento en 1 la posicion en i del jugador
					}
					
					posiciones--;
				}
			}
		
		return true;
	}
	
	public boolean puedeAvanzar(int x, int y) {
		if(this.mapa[x][y] == null)
			return false;
		return true;
	}
	
	// Este método sirve para que el algoritmo no te mande siempre para el mismo lado. Por defecto, está programado para mandar
	//al jugador a la derecha y abajo primero, pero si despues de una bifurcacion queres irte para la izquierda o para arriba, voy
	//a necesitar de este método que va a evitar que vuelva a la posicion anterior.
	//public boolean verificarPosAnterior(int x, int y) { 
		if(x == posicionAnteriorEnX && y == posicionAnteriorEnY)
			return false;
		return true;
	}
	*/

	public int avanzarJugador(Jugador jugador , int cantidad ) {
		int prox=0;
		
		while(cantidad>0) {
			if(mapa[jugador.getLugarTableroX()][jugador.getLugarTableroY()].getEsUnion()==false) {
				if(puedeAvanzar(jugador,"izquierda")) {
					jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
					jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
					jugador.setLugarTableroY(jugador.getLugarTableroY()-1);
					//System.out.println("Izq");
				}
				else if(puedeAvanzar(jugador,"derecha")) {
					jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
					jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
					jugador.setLugarTableroY(jugador.getLugarTableroY()+1);
					//System.out.println("Der");
				}
				else if(puedeAvanzar(jugador,"arriba")) {
					jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
					jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
					jugador.setLugarTableroX(jugador.getLugarTableroX()-1);
					//System.out.println("Arr");
				}
				else if(puedeAvanzar(jugador,"abajo")) {
					jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
					jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
					jugador.setLugarTableroX(jugador.getLugarTableroX()+1);
					//System.out.println("Ab");
				}
			}
			else { // Hay una unión
				System.out.println("Llegaste a una union de casilleros. Elegí el camino a seguir");
				if(puedeAvanzar(jugador,"izquierda")) {
					System.out.println("4-Ir hacia la izquierda");
					prox=4; // La variable prox sirve como reemplazo del input del jugador. Es una especie de hardcodeo para que se tome un camino determinado dentro de la union 
				}
					
				if(puedeAvanzar(jugador,"derecha")) {
					System.out.println("6-Ir hacia la derecha");
					prox=6;
				}
				if(puedeAvanzar(jugador,"arriba")) {
					System.out.println("8-Ir hacia arriba");
					prox=8;
				}
				if(puedeAvanzar(jugador,"abajo")) {
					System.out.println("2-Ir hacia abajo");
					prox=2;
				}
				
				switch(prox) {

				case 4:
					jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
					jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
					jugador.setLugarTableroY(jugador.getLugarTableroY()-1);
					System.out.println("Se movio a la izquierda");break;//. Quedaste en en: "+jugador.getLugarTableroX()+" "+jugador.getLugarTableroY());break;
				case 6:
					jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
					jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
					jugador.setLugarTableroY(jugador.getLugarTableroY()+1);
					System.out.println("Se movio a la derecha");break;// Quedaste en: "+jugador.getLugarTableroX()+" "+jugador.getLugarTableroY());break;
				case 8:
					jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
					jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
					jugador.setLugarTableroX(jugador.getLugarTableroX()-1);
					System.out.println("Se movio arriba");break;// Quedaste en: "+jugador.getLugarTableroX()+" "+jugador.getLugarTableroY());break;
				case 2:
					jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
					jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
					jugador.setLugarTableroX(jugador.getLugarTableroX()+1);
					System.out.println("Se movio abajo");break;// Quedaste en: "+jugador.getLugarTableroX()+" "+jugador.getLugarTableroY());break;
				}
				
			}
			cantidad--;
		}
		
	return cantidad;
     }

private boolean puedeAvanzar(Jugador jugador,String direccion) {
	int posicionX=jugador.getLugarTableroX();
	int posicionY=jugador.getLugarTableroY();
	int posicionAnteriorX=jugador.getPosicionAnteriorX();
	int posicionAnteriorY=jugador.getPosicionAnteriorY();
	int cantidadFilas=mapa.length;
	int cantidadColumnas=mapa[0].length;
	
	if(direccion.equals("arriba") &&  posicionX>0 
			&& posicionAnteriorX!=posicionX-1 && mapa[posicionX-1][posicionY]!=null) {
		return true;
		
	}
	else if(direccion.equals("abajo")&& posicionX+1<cantidadFilas
			&&posicionAnteriorX!=posicionX+1 && mapa[posicionX +1][posicionY]!=null ) {
		return true;
		
	}
   else if(direccion.equals("izquierda") &&  posicionY>0
		   &&posicionAnteriorY!=posicionY-1 && mapa[posicionX][posicionY-1]!=null) {
		return true;
	}
  else if(direccion.equals("derecha")&&  posicionY+1 <cantidadColumnas
		  &&posicionAnteriorY!=posicionY+1 && mapa[posicionX][posicionY+1]!=null) {
	return true;
   }
  
	return false;
	
}

}

//
//public class Tablero {
//String nombre;
//Casilla mapa[][];
//
//public void tirarDado() {}
//
//public boolean avanzarJugador() {
//	return true;
//}
//
//public boolean puedeAvanzar() {
//	return true;
//}
//
//public void crearMapa1(Casilla mapa[][]){ //mapa1 contiene 3 casilleros de 1 fila
//	
//	mapa[0][0] = new Casilla ();
//	mapa[0][0].color = "azul";
//	mapa[0][1] = new Casilla ();
//	mapa[0][2] = new Casilla ();
//	this.mapa = mapa;
//	
//}
//
//}
