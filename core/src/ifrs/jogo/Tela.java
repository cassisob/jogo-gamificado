package ifrs.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.lang.management.ThreadMXBean;


public class Tela extends InputAdapter implements Screen {

    private World mundo;
    private TmxMapLoader carregarMapa;
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer carregador;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private SpriteBatch batch;


    public Tela(Game game) {

        batch = new SpriteBatch();

        mundo = new World(new Vector2(0, -100f), true);

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);

        carregarMapa = new TmxMapLoader();
        mapa = carregarMapa.load("mapa.tmx");
        carregador = new OrthogonalTiledMapRenderer(mapa);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        batch.setProjectionMatrix(gamecam.combined);

        batch.begin();

        carregador.render();

        batch.end();

    }

    public void update(float delta) {
        mundo.step(1 / 60f, 6, 2);

        gamecam.position.x = Gdx.input.getX();

        gamecam.update();
        carregador.setView(gamecam);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
