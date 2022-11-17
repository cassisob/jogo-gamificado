package ifrs.jogo;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.lang.management.ThreadMXBean;


public class Tela extends InputAdapter implements Screen {

    private World mundo;
    private TmxMapLoader carregarMapa;
    private TiledMap mapa;

    public Tela(Game game) {

        mundo = new World(new Vector2(0, -100f), true);

        carregarMapa = new TmxMapLoader();
        mapa = carregarMapa.load("mapa.tmx");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public World getMundo() {
        return mundo;
    }

    public TiledMap getMapa() {
        return mapa;
    }
}
