package fr.cyphall.bullethell;

import fr.cyphall.cyphengine.component.Hitbox;
import fr.cyphall.cyphengine.component.Script;
import fr.cyphall.cyphengine.component.SpriteRenderer;
import fr.cyphall.cyphengine.core.Entity;
import fr.cyphall.cyphengine.core.ToolBox;
import org.joml.Vector2f;

import java.util.ArrayList;

public class PlayerScript extends Script
{
	private int fireCD = 0;
	
	private Entity gun;
	private ArrayList<Entity> moteurs = new ArrayList<>();
	
	@Override
	public void init()
	{
		getEntity().setRelativePos(new Vector2f(150, 350));
		getEntity().addComponent(new SpriteRenderer("cockpit"));
		getEntity().addComponent(new Hitbox(-8, -8, 8, 8));
		
		gun = instantiate(new Entity());
		getEntity().addChild(gun);
		gun.setType("ally");
		gun.setRelativePos(new Vector2f(0, -9));
		gun.addComponent(new SpriteRenderer("canon", 2));
		gun.addComponent(new Hitbox(-4, -8, 4, 1));
		
		Entity arriere = instantiate(new Entity());
		getEntity().addChild(arriere);
		arriere.setType("ally");
		arriere.setRelativePos(new Vector2f(0, 12));
		arriere.addComponent(new SpriteRenderer("arriere"));
		arriere.addComponent(new Hitbox(-8, -4, 8, 4));
		
		Entity moteur1 = instantiate(new Entity());
		arriere.addChild(moteur1);
		moteur1.setType("ally");
		moteur1.setRelativePos(new Vector2f(-12, 0));
		moteur1.addComponent(new SpriteRenderer("moteur1"));
		moteur1.addComponent(new Hitbox(-4, -4, 4, 4));
		moteurs.add(moteur1);
		
		Entity moteur2 = instantiate(new Entity());
		arriere.addChild(moteur2);
		moteur2.setType("ally");
		moteur2.setRelativePos(new Vector2f(12, 0));
		moteur2.addComponent(new SpriteRenderer("moteur1"));
		moteur2.addComponent(new Hitbox(-4, -4, 4, 4));
		moteurs.add(moteur2);
		
		Entity moteur3 = instantiate(new Entity());
		arriere.addChild(moteur3);
		moteur3.setType("ally");
		moteur3.setRelativePos(new Vector2f(-4, 6));
		moteur3.addComponent(new SpriteRenderer("moteur2"));
		moteur3.addComponent(new Hitbox(-3, -2, 3, 2));
		moteurs.add(moteur3);
		
		Entity moteur4 = instantiate(new Entity());
		arriere.addChild(moteur4);
		moteur4.setType("ally");
		moteur4.setRelativePos(new Vector2f(4, 6));
		moteur4.addComponent(new SpriteRenderer("moteur2"));
		moteur4.addComponent(new Hitbox(-3, -2, 3, 2));
		moteurs.add(moteur4);
	}
	
	@Override
	public void update()
	{
		if (fireCD > 0) fireCD--;
		
		moteurs.removeIf(m -> !m.exists());
		
		Vector2f pos = getEntity().getAbsolutePos();
		
		float speed = 2.5f;
		float realSpeed = speed * moteurs.size() / 4;
		if (ToolBox.inputs().isActionPerformed("up") && pos.y > 20) pos.y -= realSpeed;
		if (ToolBox.inputs().isActionPerformed("down") && pos.y < getEntity().getScene().getSize().y - 30) pos.y += realSpeed;
		if (ToolBox.inputs().isActionPerformed("left") && pos.x > 20) pos.x -= realSpeed;
		if (ToolBox.inputs().isActionPerformed("right") && pos.x < getEntity().getScene().getSize().x - 20) pos.x += realSpeed;
		
		getEntity().setRelativePos(pos);
		
		if (ToolBox.inputs().isActionPerformed("fire"))
		{
			fire(gun);
		}
	}
	
	private void fire(Entity entity)
	{
		if (!entity.exists()) return;
		
		if (fireCD > 0) return;
		fireCD = 10;
		
		Entity bullet = instantiate(new Entity());
		bullet.setType("allyBullet");
		bullet.setRelativePos(entity.getAbsolutePos());
		bullet.addComponent(new SpriteRenderer("bullet1", 10));
		bullet.addComponent(new Hitbox(-1, -7, 1, 7));
		
		BulletScript script = bullet.addComponent(new BulletScript());
		script.setDirection(new Vector2f(0, -1), 6);
		script.setTarget("enemy");
		
	}
}
