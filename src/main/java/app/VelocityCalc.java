package app;

import javafx.scene.chart.XYChart;

public class VelocityCalc {
    static Double x, y, s, vXnow, vYnow;

    public VelocityCalc(Controller controller, Manipulator manipulator) {
        x = Math.cos(manipulator.calculateAngle(controller) * Math.PI / 180) * controller.getL2();
        y = controller.getL1() + manipulator.shift + controller.getL2() * Math.sin(manipulator.calculateAngle(controller) * Math.PI / 180);
        s = 0.0;
    }

    public void calculateSpeed(double nX, double nY, Controller controller, XYChart.Series<Number, Number> vX, XYChart.Series<Number, Number> vY) {
        vXnow = Math.round((nX - x) * 100.0) / 100.0;
        vYnow = Math.round((nY - y) * 100.0) / 100.0;
        controller.setVxy(vXnow, vYnow);
        x = nX;
        y = nY;
        s++;
        System.out.println("nX: " + nX + " nY: " + nY + "s: " + s);
        vX.getData().add(new XYChart.Data<>(s, vXnow));
        vY.getData().add(new XYChart.Data<>(s, vYnow));
    }
}

