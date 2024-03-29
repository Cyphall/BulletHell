package fr.cyphall.bullethell;

import fr.cyphall.cyphengine.Entity;
import fr.cyphall.cyphengine.Hitbox;
import fr.cyphall.cyphengine.Script;
import fr.cyphall.cyphengine.SpriteRenderer;
import org.joml.Vector2f;

public class EnemyScript extends Script
{
	private int fireCD = 0;
	private float speed = 1.5f;
	
	@Override
	public void init()
	{
		getEntity().addComponent(new SpriteRenderer("enemy1", 20));
		getEntity().addComponent(new Hitbox(-12, -10, 12, 10));
	}
	
	@Override
	public void update()
	{
		if (fireCD > 0) fireCD--;
		
		Vector2f pos = getEntity().getAbsolutePos();
		
		pos.x += speed;
		
		getEntity().setRelativePos(pos);
		
		if (pos.x < 50 || pos.x > 250) speed *= -1;
		
		fire(getEntity());
	}
	
	private void fire(Entity entity)
	{
		if (!entity.exists()) return;
		
		if (fireCD > 0) return;
		fireCD = 60;
		
		Entity bullet = instantiate(new Entity(), entity.getAbsolutePos().add(0, 15));
		bullet.setType("enemyBullet");
		bullet.addComponent(new SpriteRenderer("bullet2", 10));
		bullet.addComponent(new Hitbox(-1, -7, 1, 7));

		BulletScript script = bullet.addComponent(new BulletScript());
		script.setDirection(new Vector2f(0, 1), 2);
		script.setTarget("ally");
		
	}
}
