package clienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import logica.Dado;
import logica.Jugador;
import logica.Partida;

public class VentanaPartida implements Runnable {
	private static Socket socketClienteServidor;
	private static Socket socketServidorCliente;
	private static Partida partida;
	private Dado dado;
	
	public VentanaPartida(Socket socketClienteServidor, Socket socketServidorCliente,Partida partida,Dado dado) {
		this.socketClienteServidor = socketClienteServidor;
		this.socketServidorCliente=socketServidorCliente;
		this.partida=partida;
		this.dado=dado;
		Thread hiloCliente = new Thread(this);
		hiloCliente.start();
	}

	public static Socket getSocketClienteServidor() {
		return socketClienteServidor;
	}

	public static void setSocketClienteServidor(Socket socketClienteServidor) {
		VentanaPartida.socketClienteServidor = socketClienteServidor;
	}

	public static Socket getSocketServidorCliente() {
		return socketServidorCliente;
	}

	public static void setSocketServidorCliente(Socket socketServidorCliente) {
		VentanaPartida.socketServidorCliente = socketServidorCliente;
	}

	public static Partida getPartida() {
		return partida;
	}

	public static void setPartida(Partida partida) {
		VentanaPartida.partida = partida;
	}

	public Dado getDado() {
		return dado;
	}
	
	@Override
	public void run() {
		Gson gson= new Gson();
		try {
			DataInputStream flujoEntrada = new DataInputStream(socketServidorCliente.getInputStream());
			while (true) {
				String mensaje = flujoEntrada.readUTF();
				System.out.println("se recibio un mensaje desde el servidor a la partida"+partida.getNombre());
				PaqueteMensaje mensajeRecibido=gson.fromJson(mensaje,PaqueteMensaje.class);
				rePintar(mensajeRecibido);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	private void rePintar(PaqueteMensaje mensajeRecibido) throws IOException  {
		if(mensajeRecibido.accion.equals("EmpiezaTurno")){
			empiezaTurno((String) mensajeRecibido.getObj());
		}
		if(mensajeRecibido.accion.equals("muestraTiraDado")) {
			muestraTiraDado();
		}
		if(mensajeRecibido.accion.equals("muestraDado")) {
			muestraDado((String)mensajeRecibido.getObj());
		}
		if(mensajeRecibido.accion.equals("muestraVentanaAtaca")) {
			muestraAtaca((String)mensajeRecibido.getObj());
		}
		if(mensajeRecibido.accion.equals("idObj")) {
			Ataque((String)mensajeRecibido.getObj());
		}

		
	}

	private void Ataque(String obj) {
		int idObj=Integer.parseInt(obj.split(";")[0]);
		int indexAtacado= Integer.parseInt(obj.split(";")[1]);
		int indexturno= Integer.parseInt(obj.split(";")[2]);
		System.out.println("Antes: "+partida.getJugadores().get(indexAtacado).getPuntos());
		partida.getJugadores().get(indexturno).usarObjeto(partida.getJugadores().get(indexAtacado),partida.getJugadores(), idObj);
		System.out.println("Despues "+partida.getJugadores().get(indexAtacado).getPuntos());
		partida.getTablero().getVentanaTablero().getPanelTablero().repaint();
	}

	private void muestraAtaca(String turno)  {
		int index=Integer.parseInt(turno);
		int respuesta;
			try {
				System.out.println("muestraAtaque");
				respuesta=partida.getTablero().deseaAtacar(partida.getJugadores().get(index));
				
				DataOutputStream flujoSalida = new DataOutputStream(socketClienteServidor.getOutputStream());
				flujoSalida.writeUTF(Integer.toString(respuesta));
				System.out.println("muestraAtaquefin");
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
	}

	private void muestraDado(String mensaje) throws IOException {
		int cant=Integer.parseInt(mensaje.split(";")[0]);
		int index=Integer.parseInt(mensaje.split(";")[1]);
		int indexMiJugador=Integer.parseInt(mensaje.split(";")[2]);
		Jugador jugador=partida.getJugadores().get(index);
		try {
			partida.getTablero().getVentanaTablero().getPanelTablero().mostrarTiraDado(cant);
			int cantObjetosAnteriores= jugador.getCantidadObjetos();//OBJETOS ANTES DE MOVERSE
			int puntosAnteriores = jugador.getPuntos(); //PUNTOS DEL JUGADOR ANTES DE TIRAR EL DADO
			partida.getTablero().avanzarJugador(jugador, cant);
			partida.getTablero().getVentanaTablero().getPanelTablero().setearObjetos(partida.getJugadores().get(indexMiJugador));
			if(puntosAnteriores != jugador.getPuntos()) 
				partida.getTablero().getVentanaTablero().getPanelTablero().mostrarModificacionPts(jugador.getPuntos() - puntosAnteriores,jugador);
			if(cantObjetosAnteriores != jugador.getCantidadObjetos())
				partida.getTablero().getVentanaTablero().getPanelTablero().mostrarModificacionObjt(jugador.getCantidadObjetos()-cantObjetosAnteriores, jugador);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void muestraTiraDado() throws IOException {
		try {
			int cant=partida.getTablero().getVentanaTablero().getPanelTablero().tirodado(new Jugador("NULL","NULL"));
			DataOutputStream flujoSalida = new DataOutputStream(socketClienteServidor.getOutputStream());
			flujoSalida.writeUTF(Integer.toString(cant));
			System.out.println("cantidad enviada");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void empiezaTurno(String infoTurno) {		
		try {
			String turno=infoTurno.split(";")[0];
			int indexMiJugador=Integer.parseInt(infoTurno.split(";")[1]);
			partida.getTablero().getVentanaTablero().getPanelTablero().empiezaTurno(turno);
			partida.getTablero().getVentanaTablero().getPanelTablero().setearTurnoJugador(turno);
			partida.getTablero().getVentanaTablero().getPanelTablero().setearObjetos(partida.getJugadores().get(indexMiJugador));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
