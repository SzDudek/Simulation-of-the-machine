/**
 * @author Szymon Dudek 10.12.2022
 */
package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
    private static Canvas canvas;
    private static Manipulator sym;
    private static Controller controll;
    private static XYChart.Series<Number, Number> vX;
    private static XYChart.Series<Number, Number> vY;
    private static VelocityCalc speed;

    @Override
    public void start(Stage primaryStage) {
        try {

            sym = new Manipulator();
            canvas = new Canvas(440, 365);
            canvas.setLayoutX(14);
            canvas.setLayoutY(14);
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("Main.fxml"));
            AnchorPane root = fxml.load();
            controll = fxml.getController();
            root.getChildren().add(canvas);
            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Time");
            yAxis.setLabel("Velocity");
            xAxis.setLowerBound(0);
            xAxis.setUpperBound(125);
            yAxis.setLowerBound(-2);
            yAxis.setUpperBound(2);
            final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
            vX = new XYChart.Series<Number, Number>();
            vX.setName("Vx");
            vY = new XYChart.Series<Number, Number>();
            vY.setName("Vy");
            lineChart.getData().add(vY);
            lineChart.getData().add(vX);
            lineChart.setLayoutX(454);
            lineChart.setLayoutY(14);
            lineChart.setPrefWidth(326);
            lineChart.setPrefHeight(300);
            lineChart.getXAxis().setAutoRanging(false);
            lineChart.getYAxis().setAutoRanging(false);
            lineChart.setCreateSymbols(false);
            root.getChildren().add(lineChart);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Simulation");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void animate() {
        speed = new VelocityCalc(controll, sym);
        System.out.println(controll);
        Thread t = new Thread(new Runnable() {
            public void run() {
                int x;
                x = controll.getS();
                while (x > 0) {
                    canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    sym.draw(canvas.getGraphicsContext2D(), controll);
                    sym.moveDown();
                    x--;
                    double newX = Math.cos(sym.calculateAngle(controll) * Math.PI / 180) * controll.getL2();
                    double newY = controll.getL1() + sym.shift + controll.getL2() * Math.sin(sym.calculateAngle(controll) * Math.PI / 180);
                    Platform.runLater(new Runnable() {
                        public void run() {
                            speed.calculateSpeed(newX, newY, controll, vX, vY);
                        }
                    });

                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                }
                while (x < controll.getS()) {

                    canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    sym.draw(canvas.getGraphicsContext2D(), controll);
                    sym.moveUp();
                    x++;
                    double noweX = Math.cos(sym.calculateAngle(controll) * Math.PI / 180) * controll.getL2();
                    double noweY = controll.getL1() + sym.shift + controll.getL2() * Math.sin(sym.calculateAngle(controll) * Math.PI / 180);

                    Platform.runLater(new Runnable() {
                        public void run() {
                            speed.calculateSpeed(noweX, noweY, controll, vX, vY);
                        }
                    });

                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                }

            }
        });
        t.start();
    }
}

