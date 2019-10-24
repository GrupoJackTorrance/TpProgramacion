package logica;

import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import grafica.VentanaMiniJuego;
import grafica.VentanaMniJP;

public class MiniJuegoPalabras extends Minijuego{

	private String modalidad;
	
	private List <Jugador> jugadores;
	private VentanaMniJP ventana;
	
	private int i=0;
	private int cantJugados;
	private int [] CantDePalabras=new int [4];
	
	private int tiemporeset=20;
	private int mayor=0;
	private String [] vec= {
			"Anticonstitucionalmente",
			"Antigubernamentalisticamente",
			"Constitucionalisticamente",
			"Desconsoladamente",
			"Desproporcionadisimamente",
			"Electroencefalograficamente",
			"Electroencefalografistas",
			"Esternocleidomastoideisticamente",
			"Esternocleidooccipitomastoideo",
			"Hiperesternocleidomastoideitis",
			"Inconstitucionalmente",
			"Otorrinolaringologicalisimamente",
			"Otorrinolaringologicamente",
			"Sobresaturacion",
			"Lactovegetarianismo",
			"Magnetoencefalografia",
			"Electrooculograma",
			"Electrorrecepcion",
			"Aminotransferasa",
			"Antitauromaquia",
			"Ciclopentanoperhidrofenantreno",
			"Esfigmomanometro",
			"Faringoamigdalitis",
			"Hiperprolactinemia"
	
	};
	Random radm=new Random();
	public MiniJuegoPalabras(List<Jugador> jugadores) {
		this.jugadores=jugadores;
		this.cantJugados=jugadores.size();
		this. ventana=new VentanaMniJP("minijuego", 100, 100,this);
		this.ventana.setVisible(true);
		this.ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		for (int j = 0; j < CantDePalabras.length; j++) {
			CantDePalabras[j]=0;
		}
		
		this.ventana.getPanel().setearNombreDeTurnoJugador(jugadores.get(0).getNombre());
		this.ventana.getPanel().setearPalabra(vec[(int)(radm.nextDouble()*vec.length)]);
		this.ventana.getPanel().mostrarNumero(mayor);
		this.ventana.getPanel().setearCantXJ(CantDePalabras[0]);
		
	}
	
	public void cerrarMiniJuego() {
		this.ventana.dispose();
	}
	
	@Override
	public String informarModalidad() {
		this.modalidad = "Este minijuego consiste en escribir exactamente la palabra indicada\n "
				+ "en un tiempo dado:\n"
				+ "-los jugadores que escriban correctamente en tiempo y forma van sumando palabras correctas,\n"
				+ "si no acierta o se termina el tiempo pasa al siguiente jugador.\n"
				+ "-Gana puntos el que mas palabras correctas escribe.\n"
				+ "-Pierden 1 punto los jugadores que no llegan a escribir la mayoria de palabras correctas.\n";
		return modalidad;
	}

	@Override
	public void recompensaCastigo() {
		// TODO Auto-generated method stub
		for (int i=0; i<CantDePalabras.length;i++) {
			if(mayor>0 && this.CantDePalabras[i]==mayor)
				this.jugadores.get(i).sumarPuntos(10);
			else
				if(mayor!=0)
				this.jugadores.get(i).restarPuntos(1);
				
		}
		
	}

	public void incrementarContador() {
		this.CantDePalabras[this.i]+=1;
		System.out.println("Cantidad de palabras: "+ this.CantDePalabras[this.i]);
		if(mayor<this.CantDePalabras[this.i])
			mayor=this.CantDePalabras[this.i];
		this.ventana.getPanel().setearPalabra(vec[(int)(radm.nextDouble()*vec.length)]);
		this.ventana.getPanel().setearCantXJ(CantDePalabras[this.i]);
		this.ventana.getPanel().mostrarNumero(mayor);
	}
	
	
	public void Falso() {
		this.i++;
		this.cantJugados--;
		if(this.cantJugados==0) {
			recompensaCastigo();
			this.ventana.getPanel().mostrarResultados(this.jugadores);
			this.ventana.getPanel().mostrarNumero(mayor);
			this.ventana.getPanel().mostrarNumeroMayor(mayor);
			
		}
		if(this.cantJugados!=0)
			{
			this.ventana.getPanel().setearNombreDeTurnoJugador(this.jugadores.get(i).getNombre());
			this.ventana.getPanel().setearPalabra(vec[(int)(radm.nextDouble()*vec.length)]);
			this.ventana.getPanel().setearCantXJ(CantDePalabras[this.i]);
			this.ventana.getPanel().setearTiempo(tiemporeset);
			}
			
	}
	
	
}
