package ifrs.jogo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import java.util.ArrayList;


public class Personagem extends ApplicationAdapter {

    SpriteBatch batch;
    Sprite sprite;
    Texture img;
    Body body;
    Portas retangulo;

    public Personagem(Tela screen){
        create(screen);
    }



    public void create(Tela screen) {

        batch = new SpriteBatch();

        img = new Texture("bola.png");
        sprite = new Sprite(img);

        sprite.setPosition(624, 336);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(624, 336);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = screen.getMundo().createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(sprite.getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef).setUserData(this);


        retangulo = new Portas(body.getPosition().x - (sprite.getWidth() / 2), body.getPosition().y - (sprite.getHeight() / 2), sprite.getWidth(), sprite.getHeight());
    }

    public void update() {

        sprite.setPosition(body.getPosition().x - (sprite.getWidth() / 2), body.getPosition().y - (sprite.getHeight() / 2));
        retangulo.mover(body.getPosition().x - (sprite.getWidth() / 2), body.getPosition().y - (sprite.getHeight() / 2));

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            body.applyLinearImpulse(new Vector2(500f, body.getLinearVelocity().y), body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            body.applyLinearImpulse(new Vector2(-500f, 0), body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            body.applyLinearImpulse(new Vector2(body.getLinearVelocity().x, 500f), body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            body.applyLinearImpulse(new Vector2(body.getLinearVelocity().x, -500f), body.getWorldCenter(), true);
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            body.setLinearVelocity(new Vector2(0, 0));
        }
    }

    public void render(SpriteBatch batch) {

        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

    }


}
