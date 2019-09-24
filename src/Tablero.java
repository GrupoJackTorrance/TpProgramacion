import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Tablero {

//  					 ------------	A T R I B U T O S    -------------
	
	String nombre;
	Casilla mapa[][] = new Casilla [7][11];
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
		mapa[0][0] = new Casilla(0,0, "blanco",sinEfecto);
		mapa[0][1] = new Casilla(0,1, "blanco",sinEfecto);
		mapa[0][2] = new Casilla(0,2, "blanco",sinEfecto);
		mapa[0][3] = new Casilla(0,3, "verde",efectoSumarPuntos);
		mapa[0][4] = new Casilla(0,4, "verde",efectoSumarPuntos);
		mapa[0][5] = new Casilla(0,5, "rojo",efectoRestarPuntos);
		mapa[0][6] = new Casilla(0,6, "verde",efectoSumarPuntos);
		mapa[0][7] = new Casilla(0,7, "rojo",efectoRestarPuntos);
		mapa[0][8] = new Casilla(0,8, "verde",efectoSumarPuntos);
		mapa[0][9] = new Casilla(0,9, "blanco",sinEfecto);
		mapa[0][10] = new Casilla(0,10, "verde",efectoSumarPuntos);
		
		mapa[1][5] = new Casilla(1,5, "rojo",efectoRestarPuntos);
		mapa[1][10] = new Casilla(1,10, "rojo",efectoRestarPuntos);
		
		mapa[2][5] = new Casilla(2,5, "verde",efectoSumarPuntos);
		mapa[2][8] = new Casilla(2,8, "verde",efectoSumarPuntos);
		mapa[2][9] = new Casilla(2,9, "blanco",sinEfecto);
		mapa[2][10] = new Casilla(2,10, "blanco",sinEfecto);
		mapa[2][11] = new Casilla(2,11, "rojo",efectoRestarPuntos);
		
		mapa[3][2] = new Casilla(3,2, "verde",efectoSumarPuntos);
		mapa[3][3] = new Casilla(3,3, "verde",efectoSumarPuntos);
		mapa[3][4] = new Casilla(3,4, "rojo",efectoRestarPuntos);
		mapa[3][5] = new Casilla(3,5, "rojo",efectoRestarPuntos);
		mapa[3][8] = new Casilla(3,8, "rojo",efectoRestarPuntos);
		
		mapa[4][2] = new Casilla(4,2, "blanco",sinEfecto);
		mapa[4][5] = new Casilla(4,5, "blanco",sinEfecto);
		mapa[4][8]= new Casilla(4,8,"verde",efectoSumarPuntos);
		
		mapa[5][0] = new Casilla(5,0, "blanco",sinEfecto);
		mapa[5][1] = new Casilla(5,1, "blanco",sinEfecto);
		mapa[5][2] = new Casilla(5,2, "blanco",sinEfecto);
		mapa[5][5] = new Casilla(5,5, "rojo",efectoRestarPuntos);
		mapa[5][8] = new Casilla(5,8, "rojo",efectoRestarPuntos);
		mapa[5][9] = new Casilla(5,9, "verde",efectoSumarPuntos);
		mapa[5][10] = new Casilla(5,10, "blanco",sinEfecto);
	    mapa[5][11] = new Casilla(5,11, "blanco",sinEfecto);
	    
		mapa[6][0] = new Casilla(6,0, "verde",efectoSumarPuntos);
		mapa[6][5]= new Casilla(6,5, "verde",efectoSumarPuntos);
		mapa[6][11]= new Casilla(6,11, "verde",efectoSumarPuntos);
		
		mapa[7][0] = new Casilla(7,0, "verde",efectoSumarPuntos);
		mapa[7][1] = new Casilla(7,1, "verde",efectoSumarPuntos);
		mapa[7][2] = new Casilla(7,2, "verde",efectoSumarPuntos);
		mapa[7][3] = new Casilla(7,3, "rojo",efectoRestarPuntos);
		mapa[7][4] = new Casilla(7,4, "blanco",sinEfecto);
		mapa[7][5] = new Casilla(7,5, "blanco",sinEfecto);
		mapa[7][6] = new Casilla(7,6, "blanco",sinEfecto);
		mapa[7][7] = new Casilla(7,7, "rojo",efectoRestarPuntos);
		mapa[7][8] = new Casilla(7,8, "rojo",efectoRestarPuntos);
		mapa[7][9] = new Casilla(7,9, "blanco",sinEfecto);
		mapa[7][10] = new Casilla(7,10, "verde",efectoSumarPuntos);
		mapa[7][11] = new Casilla(7,11, "verde",efectoSumarPuntos);
	   
	   
	}
	

	public int tirarDado(){
		return 3; // Hardcodeado para las pruebas
	}
	
	public boolean avanzarJugador(int posiciones, Jugador miJugador) {
		int posX=0, posY, i=0, j=0;;
		Scanner in;
		
		while(posiciones>0) {
			
			/** Las variables i y j corresponden a las coordenadas de la posición del jugador 
			*(x e y)
			* Comienzo evaluando si hay alguna bifurcación a tomar. 
			* Para esto, hay que chequear que no me salga de la matriz
			*xq daría null pointer exception, y verificar alguno de los pares de 
			*casilleros que rodean al jugador, estén libres (instanciados)
			*/ 

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
	
	/* Este método sirve para que el algoritmo no te mande siempre para el mismo lado. Por defecto, está programado para mandar
	al jugador a la derecha y abajo primero, pero si despues de una bifurcacion queres irte para la izquierda o para arriba, voy
	a necesitar de este método que va a evitar que vuelva a la posicion anterior.*/
	public boolean verificarPosAnterior(int x, int y) { 
		if(x == posicionAnteriorEnX && y == posicionAnteriorEnY)
			return false;
		return true;
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
