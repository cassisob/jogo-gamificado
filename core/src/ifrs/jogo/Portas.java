package ifrs.jogo;

public class Portas {
    float x, y;
    float width, height;

    public Portas(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void mover (float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean colidir (Portas rect) {
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }
}
