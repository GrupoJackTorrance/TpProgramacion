package clienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;

import grafica.VentanaResultado;
import logica.Dado;
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
		 try {
			int contadorRonda=0;
			contadorTurno=1;
			int index;
			String msj;
			Gson gson= new Gson();
			while (corriendo && contadorRonda <sala.getPartida().getRondaMax()) {/*EMPIEZA WHILE*/
				System.out.println("TURNO "+contadorTurno);
				index=contadorTurno-1;
				turno=sala.getJugadores2().get(index);
				String datosTurno=turno.getNombre();
				PaqueteMensaje mensaje = new PaqueteMensaje("EmpiezaTurno",datosTurno,"EnSala"+sala.getNombreSala());
				ManejadorDeImputOutput.avisarCambioPartida(new Jugador("NULL","NULL"),mensaje,servidorCliente);
				entrada = new DataInputStream(clienteServidor.get(turno).getInputStream());
				salida = new DataOutputStream(servidorCliente.get(turno).getOutputStream());
				System.out.println("IPCLIENTE"+clienteServidor.get(turno).getInetAddress()+"IPSERVIDOR"+servidorCliente.get(turno).getInetAddress());
				mensaje.setAccion("muestraTiraDado");
				String mens=gson.toJson(mensaje);
				salida.writeUTF(mens);
				System.out.println("aca");
				//msj= gson.fromJson(entrada.readUTF(),String.class);
				Dado dado= new Dado();
				int num=dado.tirar();
				if(num==6) {
					num--;
				}
				String mensajeCliente =num+";"+Integer.toString(index);//espera recibir numero del dado tirado y enviamos el turno del jugador
				mensaje.setAccion("muestraDado");
				mensaje.setObj(mensajeCliente);
				System.out.println("aca");
				ManejadorDeImputOutput.avisarCambioPartida(new Jugador("NULL","NULL"),mensaje,servidorCliente);//replicar para simular tirada de dado
				Thread.sleep(3000);
				//msj= gson.fromJson(entrada.readUTF(),String.class);//FIJARSE NO LO RECIBE BIEN /***SEGUIR DESDE ACA****/
				mensaje.setAccion("muestraVentanaAtaca");
				mensaje.setObj(Integer.toString(index));
				mens=gson.toJson(mensaje);
				salida.writeUTF(mens);
				System.out.println("respuesta");
				//msj= gson.fromJson(entrada.readUTF(),String.class);
				msj="0";
				if(Integer.parseInt(msj)!=0) {	
					mensaje.setAccion("idObj");
					int jugadorAtacado= contadorTurno % sala.getPartida().getJugadores().size();
					mensaje.setObj(msj+";"+jugadorAtacado+";"+index);
					ManejadorDeImputOutput.avisarCambioPartida(new Jugador("NULL","NULL"),mensaje,servidorCliente);
					Thread.sleep(2000);
				}
				contadorTurno++;
				if(contadorTurno>sala.getPartida().getTurnos()) {
					contadorTurno=1;
					contadorRonda++;
				}
		}/*FIN WHILE*/
			
			System.out.println("TERMINO JUEGO");
			PaqueteMensaje mensaje = new PaqueteMensaje("termino",null,"EnSala"+sala.getNombreSala());
			ManejadorDeImputOutput.avisarCambioPartida(new Jugador("NULL","NULL"),mensaje,servidorCliente);
			
		} catch (IOException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
