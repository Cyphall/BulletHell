package fr.cyphall.bullethell;

import fr.cyphall.cyphengine.Game;
import fr.cyphall.cyphengine.ToolBox;
import fr.cyphall.cyphengine.Window;
import org.joml.Vector2i;

public class Main extends Game
{
	@Override
	public Window initWindow()
	{
		return new Window(600, 800, "Bullet Hell");
	}
	
	@Override
	public void init()
	{
		ToolBox.setCurrentScene(
				new MainScene(
						new Vector2i(ToolBox.window().getSize().x/2, ToolBox.window().getSize().y/2)
				)
		);
	}
	
	public static void main(String[] args)
	{
		Main main = new Main();
		main.run();
	}
}