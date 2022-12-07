package ifrs.jogo;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class Colisao {

    static ArrayList<Portas> portas;

    public Colisao(Tela screen) {
        World world = screen.getMundo();
        TiledMap map = screen.getMapa();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        portas = new ArrayList<>();

        portas = new ArrayList<>();

        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            portas.add(new Portas(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight()));
        }
    }
}