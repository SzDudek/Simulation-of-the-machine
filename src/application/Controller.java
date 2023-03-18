package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Controller {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label notifications;
    @FXML
    private Label enterParam;
    @FXML
    private Label LabelD;
    @FXML
    private Label LabelH;
    @FXML
    private Label LabelS;
    @FXML
    private Label LabelL1;
    @FXML
    private Label LabelL2;
    @FXML
    private Label Vy;
    @FXML
    private Label Vx;
    @FXML
    private Label valueX;
    @FXML
    private Label valueY;


    @FXML
    private TextField inputD;
    @FXML
    private TextField inputH;
    @FXML
    private TextField inputS;
    @FXML
    private TextField inputL1;
    @FXML
    private TextField inputL2;


    @FXML
    private Button start;
    @FXML
    private Button acceptD;
    @FXML
    private Button acceptH;
    @FXML
    private Button acceptS;
    @FXML
    private Button acceptL1;
    @FXML
    private Button acceptL2;


    private int d = 160, h = 200, s = 60, l1 = 140, l2 = 250;

    public void startApp() {
        if (s <= l1 / 2) {
            Main.animate();
            notifications.setTextFill(Color.LAWNGREEN);
            notifications.setText("Correct Data");
        } else {
            notifications.setTextFill(Color.RED);
            notifications.setText("Incorrect Data has been entered");
        }
    }

    public void acceptD() {
        try {
            d = Integer.parseInt(inputD.getText());
            System.out.println("d=" + d);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void acceptH() {
        try {
            h = Integer.parseInt(inputH.getText());
            System.out.println("h=" + h);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void acceptS() {
        try {
            s = Integer.parseInt(inputS.getText());
            System.out.println("s=" + s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void acceptL1() {
        try {
            l1 = Integer.parseInt(inputL1.getText());
            System.out.println("l1=" + l1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void acceptL2() {
        try {
            l2 = Integer.parseInt(inputL2.getText());
            System.out.println("l2=" + l2);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getD() {
        return d;
    }

    public int getH() {
        return h;
    }

    public int getS() {
        return s;
    }

    public int getL1() {
        return l1;
    }

    public int getL2() {
        return l2;
    }

    public void setVxy(double Vx, double Vy) {
        valueX.setText("" + Vx);
        valueY.setText("" + Vy);
    }

    @Override
    public String toString() {
        return d + " " + h + " " + s + " " + l1 + " " + l2;
    }
}

