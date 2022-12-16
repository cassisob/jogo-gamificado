package ifrs.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Arrays;
import java.util.Random;


public class Tela extends InputAdapter implements Screen {

    private World mundo;
    private TmxMapLoader carregarMapa;
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer carregador;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private SpriteBatch batch;
    private Personagem psg;
    private Random gerador;
    private int[][] matrizMapa;
    private int[][] matrizJogador;
    private int jogadorX, jogadorY;
    private boolean animacao;
    private Colisao colisao;
    private Portas portas;
    private Interface inter;


    public Tela(Game game) {

        batch = new SpriteBatch();

        mundo = new World(new Vector2(0, 0), true);
        psg = new Personagem(this, 312,168);
        inter = new Interface(this);

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, gamecam);

        gamecam.position.x = 312;
        gamecam.position.y = 168;

        carregarMapa = new TmxMapLoader();
        mapa = carregarMapa.load("salaprincipal.tmx");
        carregador = new OrthogonalTiledMapRenderer(mapa, Game.PROPORCAO);
        colisao = new Colisao(this);

        gerador = new Random();
        matrizMapa = new int[13][13];
        matrizJogador = new int[13][13];

        matrizMapa[6][6] = 7; // sala principal
        matrizJogador[6][6] = 1; // sala principal
        matrizMapa[6][5] = gerador.nextInt(5) + 1;
        matrizMapa[6][7] = gerador.nextInt(5) + 1;
        matrizMapa[5][6] = gerador.nextInt(5) + 1;
        matrizMapa[7][6] = gerador.nextInt(5) + 1;

        jogadorX = 6;
        jogadorY = 6;

        inter.criandoMiniMapa(matrizMapa, matrizJogador);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        batch.setProjectionMatrix(gamecam.combined);

        batch.begin();

        carregador.render();

        psg.render(batch);
        inter.render(batch, jogadorX, jogadorY, matrizMapa);

        batch.end();

    }

    public void update(float delta) {

        mundo.step(1 / 60f, 6, 2);

        psg.update();
        mapa();

        gamecam.update();
        carregador.setView(gamecam);
    }

    public void mapa() {
        for (Portas porta : Colisao.portas) {
            if (psg.retangulo.colidir(porta) && psg.retangulo.y >= 300) {

                if (jogadorY <= 11) {
                    jogadorY += 1;
                    matrizJogador[jogadorX][jogadorY] = 1;
                }

                if (matrizMapa[jogadorX][jogadorY + 1] == 0) {
                    matrizMapa[jogadorX][jogadorY + 1] = gerador.nextInt(5) + 1;
                }
                if (matrizMapa[jogadorX - 1][jogadorY] == 0) {
                    matrizMapa[jogadorX - 1][jogadorY] = gerador.nextInt(5) + 1;
                }
                if (matrizMapa[jogadorX + 1][jogadorY] == 0) {
                    matrizMapa[jogadorX + 1][jogadorY] = gerador.nextInt(5) + 1;
                }

                animacao = true;

            } else if (psg.retangulo.colidir(porta) && psg.retangulo.y <= 50) {

                if (jogadorY >= 1) {
                    jogadorY -= 1;
                    matrizJogador[jogadorX][jogadorY] = 1;
                }

                if (matrizMapa[jogadorX][jogadorY - 1] == 0) {
                    matrizMapa[jogadorX][jogadorY - 1] = gerador.nextInt(5) + 1;
                }
                if (matrizMapa[jogadorX - 1][jogadorY] == 0) {
                    matrizMapa[jogadorX - 1][jogadorY] = gerador.nextInt(5) + 1;
                }
                if (matrizMapa[jogadorX + 1][jogadorY] == 0) {
                    matrizMapa[jogadorX + 1][jogadorY] = gerador.nextInt(5) + 1;
                }

                animacao = true;

            } else if (psg.retangulo.colidir(porta) && psg.retangulo.x >= 500) {

                if (jogadorX <= 11) {
                    jogadorX += 1;
                    matrizJogador[jogadorX][jogadorY] = 1;
                }

                if (matrizMapa[jogadorX + 1][jogadorY] == 0) {
                    matrizMapa[jogadorX + 1][jogadorY] = gerador.nextInt(5) + 1;
                }
                if (matrizMapa[jogadorX][jogadorY + 1] == 0) {
                    matrizMapa[jogadorX][jogadorY + 1] = gerador.nextInt(5) + 1;
                }
                if (matrizMapa[jogadorX][jogadorY - 1] == 0) {
                    matrizMapa[jogadorX][jogadorY - 1] = gerador.nextInt(5) + 1;
                }

                animacao = true;

            } else if (psg.retangulo.colidir(porta) && psg.retangulo.x <= 50) {

                if (jogadorX >= 1) {
                    jogadorX -= 1;
                    matrizJogador[jogadorX][jogadorY] = 1;
                }

                if (matrizMapa[jogadorX - 1][jogadorY] == 0) {
                    matrizMapa[jogadorX - 1][jogadorY] = gerador.nextInt(5) + 1;
                }
                if (matrizMapa[jogadorX + 1][jogadorY] == 0) {
                    matrizMapa[jogadorX][jogadorY + 1] = gerador.nextInt(5) + 1;
                }
                if (matrizMapa[jogadorX][jogadorY - 1] == 0) {
                    matrizMapa[jogadorX][jogadorY - 1] = gerador.nextInt(5) + 1;
                }

                animacao = true;

            }
        }

        if (animacao) {
            psg.dispose();

            if (psg.body.getPosition().y >= 300) {
                psg = new Personagem(this, 312, 25);
            } else if (psg.body.getPosition().y <= 50) {
                psg = new Personagem(this, 312, 300);
            } else if (psg.body.getPosition().x >= 500) {
                psg = new Personagem(this, 50, 168);
            } else if (psg.body.getPosition().x <= 50) {
                psg = new Personagem(this, 550, 168);
            }

            escolher_mapa();

            inter.criandoMiniMapa(matrizMapa, matrizJogador);

            System.out.println(Arrays.deepToString(matrizMapa));

            animacao = false;
        }
    }

    public void escolher_mapa() {
        switch (matrizMapa[jogadorX][jogadorY]) {
            case 1:
                mapa = carregarMapa.load("sala1.tmx");
                carregador = new OrthogonalTiledMapRenderer(mapa, Game.PROPORCAO);
                break;

            case 2:
                mapa = carregarMapa.load("sala2.tmx");
                carregador = new OrthogonalTiledMapRenderer(mapa, Game.PROPORCAO);
                break;

            case 3:
                mapa = carregarMapa.load("sala3.tmx");
                carregador = new OrthogonalTiledMapRenderer(mapa, Game.PROPORCAO);
                break;

            case 4:
                mapa = carregarMapa.load("sala4.tmx");
                carregador = new OrthogonalTiledMapRenderer(mapa, Game.PROPORCAO);
                break;

            case 5:
                mapa = carregarMapa.load("sala5.tmx");
                carregador = new OrthogonalTiledMapRenderer(mapa, Game.PROPORCAO);
                break;

            case 6:
                mapa = carregarMapa.load("sala6.tmx");
                carregador = new OrthogonalTiledMapRenderer(mapa, Game.PROPORCAO);
                break;

            case 7:
                mapa = carregarMapa.load("salaprincipal.tmx");
                carregador = new OrthogonalTiledMapRenderer(mapa, Game.PROPORCAO);
                break;

            case 8:
                mapa = carregarMapa.load("salafinal.tmx");
                carregador = new OrthogonalTiledMapRenderer(mapa, Game.PROPORCAO);
                break;
        }
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

    public int[][] getMatrizMapa() {
        return matrizMapa;
    }
}
