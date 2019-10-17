import java.util.List;

public abstract class Minijuego {
	protected String modalidad;
	private String[] resultados;
	protected List <Jugador> jugadores;
	
	public abstract String informarModalidad();
	public abstract void recompensaCastigo();
	
}
