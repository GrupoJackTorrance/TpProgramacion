package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MiServidor implements Runnable {

	public static void main(String[] args) throws Exception {
		MiServidor log = new MiServidor();
		
	}
	public MiServidor() throws Exception {
		
//		Jugador jugador1 = new Jugador("pepe","Pedro");
//		Jugador jugador2 = new Jugador("Dragon", "Maria");
//		Jugador jugador3 = new Jugador("Rana", "Julian");
//		Jugador jugador4 = new Jugador("Mono", "Marlen");
		
//		EfectoDarObjeto robaPuntos = new ObjRobarPuntos();
//		EfectoDarObjeto duplicaPuntaje = new ObjDuplicarPuntaje();
//		EfectoDarObjeto descuentaPuntos = new ObjDescuentaPuntos();
//		jugador1.setObjEfectos(descuentaPuntos);
//		jugador2.setObjEfectos(duplicaPuntaje);
//		jugador3.setObjEfectos(robaPuntos);
//		jugador4.setObjEfectos(robaPuntos);
//		jugador1.setObjEfectos(duplicaPuntaje);
//	
//		int puntosobj = 40;
//		Sala sala = jugador1.crearSala(puntosobj, 20);

//		sala.addJugadorSala(jugador2);
//		sala.addJugadorSala(jugador3);
//		sala.addJugadorSala(jugador4);
//		Partida partida1 = sala.crearPartida();
//		partida1.InicioPartida();
		
		Thread hilo = new Thread(this);
		
		hilo.start();
	}
	
	
	@Override
	public void run() {
		try {
			ServerSocket servidor = new ServerSocket(10001);
			
			Socket miSocket = servidor.accept();
			
			DataInputStream entrada = new DataInputStream(miSocket.getInputStream());
			
			String nombrePersonaje = entrada.readUTF();
			
			//System.out.println(nombrePersonaje);
			
			/* Acá habría que plasmar la lógica y los métodos que se ejecutan en el main? */
			Jugador jugador1 = new Jugador("pepe", nombrePersonaje);

			EfectoDarObjeto robaPuntos = new ObjRobarPuntos();
			EfectoDarObjeto duplicaPuntaje = new ObjDuplicarPuntaje();
			EfectoDarObjeto descuentaPuntos = new ObjDescuentaPuntos();
			jugador1.setObjEfectos(descuentaPuntos);
//			jugador2.setObjEfectos(duplicaPuntaje);
//			jugador3.setObjEfectos(robaPuntos);
//			jugador4.setObjEfectos(robaPuntos);
			jugador1.setObjEfectos(duplicaPuntaje);
		
			int puntosobj = 40;
			Sala sala = jugador1.crearSala(puntosobj, 20);

//			sala.addJugadorSala(jugador2);
//			sala.addJugadorSala(jugador3);
//			sala.addJugadorSala(jugador4);
			Partida partida1 = sala.crearPartida();
			partida1.InicioPartida();
			
			
			miSocket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
