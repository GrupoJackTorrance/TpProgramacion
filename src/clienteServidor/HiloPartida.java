package clienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;

import logica.Jugador;
import logica.Sala;

public class HiloPartida extends Thread {
	private DataInputStream entrada;
	private DataOutputStream salida;
	boolean corriendo = true;
	private Sala sala;
	HashMap<Jugador,Socket> clienteServidor;
	HashMap<Jugador,Socket> servidorCliente;
	int contadorTurno;
	Jugador turno;

	public HiloPartida (Sala sala, HashMap clienteServidor, HashMap servidorCliente) throws IOException {
		this.sala = sala;
		this.clienteServidor=clienteServidor;
		this.servidorCliente=servidorCliente;
	}

	
	@Override
	public void run() {
		/*PROBABLE*/
		/****
		  try {
			int i=0;
			contadorTurno=1;
			Gson gson= new Gson();
			while (corriendo && i <sala.getPartida().getRondaMax()) {
				turno=sala.getJugadores2().get(contadorTurno-1);
				String datosTurno=turno.getNombre()+";";
				PaqueteMensaje mensaje = new PaqueteMensaje("EmpiezaTurno",datosTurno,"EnSala"+sala.getNombreSala());
				ManejadorDeImputOutput.avisarCambioPartida(new Jugador("NULL","NULL"),mensaje,servidorCliente);
				entrada = new DataInputStream(clienteServidor.get(turno).getInputStream());
				salida = new DataOutputStream(clienteServidor.get(turno).getOutputStream());
				salida.writeUTF("muestraTiraDado");
				String mensajeCliente = gson.fromJson(entrada.readUTF(),String.class);
				mensaje.setAccion("muestraDado");
				mensaje.setObj(mensajeCliente);
				ManejadorDeImputOutput.avisarCambioPartida(new Jugador("NULL","NULL"),mensaje,servidorCliente);
				
//				String mensajeCliente = entrada.readUTF();
//				String respuesta = hacerAccion(mensajeCliente);
//				salida.writeUTF(respuesta);
//				if (respuesta.equals("termino partida")) {
//					entrada.close();
//					salida.close();
//					corriendo = false;
				contadorTurno++;
				if(contadorTurno>sala.getPartida().getTurnos())
					contadorTurno=1;
			}	
		} catch (IOException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}****/
			

	}
}
