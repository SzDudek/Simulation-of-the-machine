package Application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public abstract class SymObj {
	public Affine m;
	abstract public void draw(GraphicsContext gc, Controller controller);
	abstract public void moveUp();
	abstract public void moveDown();
}
