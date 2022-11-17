package ifrs.jogo;

public class Game extends com.badlogic.gdx.Game {

	public static final int PROPORCAO = 3;

	
	@Override
	public void create () {
		mostrarTelaPrincipal();
	}

	public void mostrarTelaPrincipal (){
		setScreen(new Tela(this));
	}
}
