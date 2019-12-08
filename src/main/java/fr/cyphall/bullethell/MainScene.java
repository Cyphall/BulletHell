package fr.cyphall.bullethell;

import fr.cyphall.cyphengine.core.Entity;
import fr.cyphall.cyphengine.core.Scene;
import org.joml.Vector2i;

public class MainScene extends Scene
{
	public MainScene(Vector2i size)
	{
		super(size);
	}
	
	@Override
	protected void init()
	{
		Entity player = instantiate(new Entity());
		player.setType("ally");
		player.setID("player");
		player.addComponent(new PlayerScript());
		
		Entity enemy = instantiate(new Entity());
		enemy.setType("enemy");
		enemy.addComponent(new EnemyScript());
	}
}
