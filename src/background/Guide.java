package background;

import taiko.*;
import background.Interval.Position;
import javafx.application.Platform;
import javafx.scene.shape.Circle;

public class Guide 
{
	private Game game;
	private Circle circle;
	private boolean active;
	private boolean checked;
	private Position position;

	public Guide(Game game,FXMLDocumentController controller,Position position) 
	{
		this.game=game;
		this.position=position;
		circle=new Circle();
		circle.setVisible(true);
		if(position==Position.left)
			circle.setLayoutX(220.0);
		else
			circle.setLayoutX(360.0);
		circle.setLayoutY(0.0);
		circle.setRadius(25.0);
		circle.setVisible(true);
		active=true;
		Platform.runLater(new Runnable() 
		{
			@Override public void run() 
			{
				controller.panelPlay.getChildren().add(circle);
			}
        });
	}
	public boolean isActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
	public void setChecked(boolean checked) 
	{
		this.checked=checked;
	}
	public void isFail()
	{
		if(!checked)
		{
			game.incrementFails();
			Platform.runLater(new Runnable() 
			{
				@Override public void run() 
				{
					if(position==Position.left)
						game.getController().blueBad();
					else
						game.getController().redBad();
				}
	        });
		}
		else
			Platform.runLater(new Runnable() 
			{
				@Override public void run() 
				{
					if(position==Position.left)
						game.getController().blueGood();
					else
						game.getController().redGood();
				}
	        });
	}
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return circle.isVisible();
	}
	public void destroy()
	{
		circle.setVisible(false);
	}
	public void move() 
	{
		// TODO Auto-generated method stub
		circle.setLayoutY(circle.getLayoutY()+8);
		if(active&&circle.getLayoutY()>350.0)
		{
			setActive(false);
			isFail();
			destroy();
		}
	}

}
