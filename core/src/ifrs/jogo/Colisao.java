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

        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
        }

        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            portas.add(new Portas(rect.getX() * Game.PROPORCAO, rect.getY() * Game.PROPORCAO, rect.getWidth() * Game.PROPORCAO, rect.getHeight() * Game.PROPORCAO));
        }

        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) * Game.PROPORCAO, (rect.getY() + rect.getHeight() / 2) * Game.PROPORCAO);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 * Game.PROPORCAO, rect.getHeight() / 2 * Game.PROPORCAO);
            fdef.shape = shape;
            fdef.filter.categoryBits = 4;
            body.createFixture(fdef);
        }

    }
}