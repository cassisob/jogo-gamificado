package ifrs.jogo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.Arrays;

public class Interface {

    int[][] matrizMapa;
    int[][] matrizJogador;
    int[][] matrizMiniMapa;
    int[][] matrizDesenho;
    int jogadorX, jogadorY;
    Texture img;
    Sprite sprite0, sprite1, sprite2, sprite3, sprite4, sprite5, sprite6, spriteprincipal, spritefinal;

    public Interface(Tela tela) {
        matrizDesenho = new int[3][3];
        matrizMiniMapa = new int[13][13];

        img = new Texture("minimapa/mapa0.png");
        sprite0 = new Sprite(img);
        img = new Texture("minimapa/mapa1.png");
        sprite1 = new Sprite(img);
        img = new Texture("minimapa/mapa2.png");
        sprite2 = new Sprite(img);
        img = new Texture("minimapa/mapa3.png");
        sprite3 = new Sprite(img);
        img = new Texture("minimapa/mapa4.png");
        sprite4 = new Sprite(img);
        img = new Texture("minimapa/mapa5.png");
        sprite5 = new Sprite(img);
        img = new Texture("minimapa/mapa6.png");
        sprite6 = new Sprite(img);
        img = new Texture("minimapa/mapaprincipal.png");
        spriteprincipal = new Sprite(img);
        img = new Texture("minimapa/mapafinal.png");
        spritefinal = new Sprite(img);

    }

    public void criandoMiniMapa(int[][] matriz, int[][] matrizJ) {
        matrizMapa = matriz;
        this.matrizJogador = matrizJ;

        for (int acumX = 0; acumX < 12; acumX++) {
            for (int acumY = 0; acumY < 12; acumY++) {
                if (this.matrizJogador[acumX][acumY] != 0) {
                    matrizMiniMapa[acumX][acumY] = matrizMapa[acumX][acumY];
                }
            }
        }

    }

    public void escolher_mapa() {

        matrizDesenho[0][0] = matrizMiniMapa[jogadorX - 1][jogadorY - 1];
        matrizDesenho[0][1] = matrizMiniMapa[jogadorX - 1][jogadorY];
        matrizDesenho[0][2] = matrizMiniMapa[jogadorX - 1][jogadorY + 1];
        matrizDesenho[1][0] = matrizMiniMapa[jogadorX][jogadorY - 1];
        matrizDesenho[1][1] = matrizMiniMapa[jogadorX][jogadorY];
        matrizDesenho[1][2] = matrizMiniMapa[jogadorX][jogadorY + 1];
        matrizDesenho[2][0] = matrizMiniMapa[jogadorX + 1][jogadorY - 1];
        matrizDesenho[2][1] = matrizMiniMapa[jogadorX + 1][jogadorY];
        matrizDesenho[2][2] = matrizMiniMapa[jogadorX + 1][jogadorY + 1];


    }

    public void render(SpriteBatch batch, int x, int y, int[][] matrizM) {
        jogadorX = x;
        jogadorY = y;
        matrizMapa = matrizM;

        escolher_mapa();

        for (int acumX = 0; acumX < 3; acumX++) {
            for (int acumY = 0; acumY < 3; acumY++) {
                switch (matrizDesenho[acumX][acumY]) {
                    case 0:
                        batch.draw(sprite0, 20 + acumX * 64 / 2f, 20 + acumY * 36 / 2f, 64 / 2f, 36 / 2f);
                        break;

                    case 1:
                        batch.draw(sprite1, 20 + acumX * 64 / 2f, 20 + acumY * 36 / 2f, 64 / 2f, 36 / 2f);
                        break;

                    case 2:
                        batch.draw(sprite2, 20 + acumX * 64 / 2f, 20 + acumY * 36 / 2f, 64 / 2f, 36 / 2f);
                        break;

                    case 3:
                        batch.draw(sprite3, 20 + acumX * 64 / 2f, 20 + acumY * 36 / 2f, 64 / 2f, 36 / 2f);
                        break;

                    case 4:
                        batch.draw(sprite4, 20 + acumX * 64 / 2f, 20 + acumY * 36 / 2f, 64 / 2f, 36 / 2f);
                        break;

                    case 5:
                        batch.draw(sprite5, 20 + acumX * 64 / 2f, 20 + acumY * 36 / 2f, 64 / 2f, 36 / 2f);
                        break;

                    case 6:
                        batch.draw(sprite6, 20 + acumX * 64 / 2f, 20 + acumY * 36 / 2f, 64 / 2f, 36 / 2f);
                        break;

                    case 7:
                        batch.draw(spriteprincipal, 20 + acumX * 64 / 2f, 20 + acumY * 36 / 2f, 64 / 2f, 36 / 2f);
                        break;

                    case 8:
                        batch.draw(spritefinal, 20 + acumX * 64 / 2f, 20 + acumY * 36 / 2f, 64 / 2f, 36 / 2f);
                        break;
                }
            }
        }
    }
}
