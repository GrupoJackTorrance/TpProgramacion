import java.util.Scanner;

public class Tablero {

//  					 ------------	A T R I B U T O S    -------------
	
	String nombre;
	Casilla mapa[][] = new Casilla [4][4];
	
	
	
// 						 ------------	M E T O D O S    -------------
	
	//Constructor por defecto
	public Tablero() {
		this.nombre="Tablero partida";
		
		 /*
		 Se crea cada objeto Casilla para cada posición de la matriz. Aquellas posiciones en las que no se creó ningun 
		 objeto toman el valor null.
		 Se hardcodeó para poder hacer las pruebas con un tablero que conozcamos, con bifurcaciones.
		 */
	
		
		mapa[0][0] = new Casilla(0,0, "Azul");
		mapa[0][1] = new Casilla(0,1, "Blanco");
		mapa[1][1] = new Casilla(1,1, "Blanco");
		mapa[2][1] = new Casilla(2,1, "Blanco");
		mapa[3][1] = new Casilla(3,1, "Azul");
		mapa[4][1] = new Casilla(4,1, "Azul");
		mapa[2][2] = new Casilla(2,2, "Azul");
		mapa[4][2] = new Casilla(4,2, "Azul");
		mapa[2][3] = new Casilla(2,3, "Rojo");
		mapa[4][3] = new Casilla(4,3, "Verde");
		mapa[2][4] = new Casilla(2,4, "Blanco");
		mapa[3][4] = new Casilla(3,4, "Blanco");
		mapa[4][4] = new Casilla(4,4, "Blanco");
	}
	
	

	public int tirarDado(){
		return 3; // Hardcodeado para las pruebas
	}
	
	public boolean avanzarJugador(int posiciones, Jugador miJugador) {
		int posX=0, posY, i=0, j=0;;
		Scanner in;
		
		while(posiciones>0) {
			
			// Las variables i y j corresponden a las coordenadas de la posición del jugador (x e y)
			/* Comienzo evaluando si hay alguna bifurcación a tomar. Para esto, hay que chequear que no me salga de la matriz
			xq daría null pointer exception, y verificar alguno de los pares de casilleros que rodean al jugador, estén libres (instanciados)*/ 
			
			if((j<4 && i<4 && puedeAvanzar(i, j+1) && puedeAvanzar(i+1, j)) || 
					(i<4 && j>1 && puedeAvanzar(i+1, j) && puedeAvanzar(i, j-1)) ||
					(i>1 && j>1 && puedeAvanzar(i-1, j) && puedeAvanzar(i, j-1)) ||
					(i>1 && j<4 && puedeAvanzar(i-1, j) && puedeAvanzar(i, j+1))
					
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
