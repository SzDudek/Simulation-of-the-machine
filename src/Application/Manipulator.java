package Application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Manipulator extends SymObj {
    public double shift = 0;
    public final double toDegrees = 180 / Math.PI;
    public final double[] polygonx = {0.0, -8.0, 8.0};
    public final double[] polygony = {0.0, -14.0, -14.0};

    @Override
    public void draw(GraphicsContext gc, Controller controller) {
        System.out.println("draw, shift= " + shift);
        double dx0 = gc.getCanvas().getWidth() / 2 - 100;
        double dy0 = gc.getCanvas().getHeight() / 2 + 100;
        gc.save();
        gc.translate(dx0, dy0);
        gc.scale(1, -1);
        gc.setLineWidth(1);
        gc.strokeRect(-10, 15, 20, 30);
        gc.translate(controller.getD(), controller.getH());
        gc.strokePolygon(polygonx, polygony, 3);
        gc.fillPolygon(polygonx, polygony, 3);
        gc.translate(-controller.getD(), -controller.getH());
        gc.translate(0, shift);
        gc.strokeLine(0, controller.getL1(), 0, 0);
        gc.strokeOval(-4, -4, 8, 8);
        gc.setFill(Color.WHITE);
        gc.fillOval(-4, -4, 8, 8);
        gc.translate(0, controller.getL1());
        gc.rotate(calculateAngle(controller));
        gc.strokeLine(0, 0, controller.getL2(), 0);
        gc.strokeOval(-4, -4, 8, 8);
        gc.fillOval(-4, -4, 8, 8);
        gc.translate(calculatePsRect(controller), 0);
        gc.strokeRect(-15, -10, 30, 20);
        gc.strokeOval(-4, -4, 8, 8);
        gc.fillOval(-4, -4, 8, 8);
        gc.translate(controller.getL2() - calculatePsRect(controller), 0);
        gc.strokeOval(-4, -4, 8, 8);
        gc.setFill(Color.BLACK);
        gc.fillOval(-4, -4, 8, 8);
        //TO DO
        gc.restore();
    }

    @Override
    public void moveUp() {
        shift++;
    }

    @Override
    public void moveDown() {
        shift--;
    }

    public double calculateAngle(Controller controller) {
        return  java.lang.Math.atan2(controller.getH() - controller.getL1() - shift, controller.getD()) * toDegrees;
    }

    public double calculatePsRect(Controller controller) {
        return Math.sqrt(Math.pow(controller.getH() - controller.getL1() - shift, 2) + Math.pow(controller.getD(), 2));
    }

}
