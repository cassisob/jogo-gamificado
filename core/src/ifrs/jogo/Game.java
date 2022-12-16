package ifrs.jogo;

public class Game extends com.badlogic.gdx.Game {

	public static final float PROPORCAO = 1 / 2f;

	
	@Override
	public void create () {
		mostrarTelaPrincipal();
	}

	public void mostrarTelaPrincipal (){
		setScreen(new Tela(this));
	}
}
