package ifrs.jogo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Interface {

    int[][] matrizMapa;
    int[][] matrizJogador;
    int[][] matrizMiniMapa;
    int[][] matrizDesenho;
    int jogadorX, jogadorY;
    Texture img;
    Sprite sprite1, sprite2, sprite3, sprite4, sprite5, sprite6, spriteprincipal, spritefinal;

    public Interface(Tela tela) {
        matrizDesenho = new int[3][3];

        img = new Texture("sala1.png");
        sprite1 = new Sprite(img);
        img = new Texture("sala2.png");
        sprite2 = new Sprite(img);
        img = new Texture("sala3.png");
        sprite3 = new Sprite(img);
        img = new Texture("sala4.png");
        sprite4 = new Sprite(img);
        img = new Texture("sala5.png");
        sprite5 = new Sprite(img);
        img = new Texture("sala6.png");
        sprite6 = new Sprite(img);
        img = new Texture("salaprincipal.png");
        spriteprincipal = new Sprite(img);
        img = new Texture("salafinal.png");
        spritefinal = new Sprite(img);

    }

    public void criandoMiniMapa(int[][] matriz, int[][] matrizJ) {
        matrizMapa = matriz;
        matrizJogador = matrizJ;

        for (int acumX = 0; acumX < 12; acumX++) {
            for (int acumY = 0; acumY < 12; acumY++) {
                if (matrizMapa[acumX][acumY] != 0 || matrizJogador[acumX][acumY] != 0) {
                    matrizMiniMapa[acumX][acumY] = 1;
                }
            }
        }

    }

    public void escolher_mapa() {

        for (int acumX = 0; acumX < 3; acumX++) {
            for (int acumY = 0; acumY < 3; acumY++) {
                matrizDesenho[acumX][acumY] = matrizMapa[jogadorX -1][jogadorY + 1];
                matrizDesenho[acumX][acumY] = matrizMapa[jogadorX][jogadorY + 1];
                matrizDesenho[acumX][acumY] = matrizMapa[jogadorX + 1][jogadorY + 1];
                matrizDesenho[acumX][acumY] = matrizMapa[jogadorX - 1][jogadorY];
                matrizDesenho[acumX][acumY] = matrizMapa[jogadorX + 1][jogadorY];
                matrizDesenho[acumX][acumY] = matrizMapa[jogadorX - 1][jogadorY - 1];
                matrizDesenho[acumX][acumY] = matrizMapa[jogadorX][jogadorY - 1];
                matrizDesenho[acumX][acumY] = matrizMapa[jogadorX + 1][jogadorY + 1];
            }
        }

        int acumX1 = 0;
        int acumY1 = 0;

        for (int acumX = jogadorX - 1; acumX < jogadorX + 2; acumX++) {
            for (int acumY = jogadorY - 1; acumY < jogadorY + 2; acumY++) {
                if (matrizMiniMapa[acumX][acumY] == 0) {

                    matrizDesenho[acumX1][acumY1] = 0;
                }
            }
        }
    }

    public void render(SpriteBatch batch, int x, int y, int[][] matrizM) {
        jogadorX = x;
        jogadorY = y;
        matrizMapa = matrizM;

            switch (matrizMapa[jogadorX][jogadorY]) {
                case 1:
                    batch.draw(sprite1, 20, 20, 192, 108);
                    break;

                case 2:
                    batch.draw(sprite2, 20, 20, 192, 108);
                    break;

                case 3:
                    batch.draw(sprite3, 20, 20, 192, 108);
                    break;

                case 4:
                    batch.draw(sprite4, 20, 20, 192, 108);
                    break;

                case 5:
                    batch.draw(sprite5, 20, 20, 192, 108);
                    break;

                case 6:
                    batch.draw(sprite6, 20, 20, 192, 108);
                    break;

                case 7:
                    batch.draw(spriteprincipal, 20, 20, 192, 108);
                    break;

                case 8:
                    batch.draw(spritefinal, 20, 20, 192, 108);
                    break;
            }

    }
}
