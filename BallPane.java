//U10416020

package ball;

import java.security.SecureRandom;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BallPane extends Pane {
    //DataFields
    public final double radius = 20;  
    private double initX = radius,initY = radius;  
    private int count = 0;  
    private Timeline animation;
    private ArrayList<Circle> circle = new ArrayList<>();
    private ArrayList<Double> initDx = new ArrayList<>();
    private ArrayList<Double> initDy = new ArrayList<>();
    
    //Contructor
    public BallPane() {
        addBall();      
    }
    
    //Add balls
    public void addBall(){
        circle.add(new Circle(initX, initY, radius));
        SecureRandom random = new SecureRandom();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        circle.get(count).setFill(Color.rgb(r, g, b));
        getChildren().add(circle.get(count));
        initDx.add(1.0);
        initDy.add(1.0);
        timeLine(count);      
        count++;      
    }
  
    //Set timeLine
    public void timeLine(int currentCount){
        animation = new Timeline(
        new KeyFrame(Duration.millis(50), e -> moveBall(currentCount)));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
    }
    
    //Play animation
    public void play() {
        animation.play();
    }

    //Pause animation
    public void pause() {
        animation.pause();
    }

    //Increase speed
    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }

    //Decrease speed
    public void decreaseSpeed() {
        animation.setRate(
        animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    //Return rate property
    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }
  
    //Set ball center
    protected void moveBall(int currentCount) {    
        for(int i = 0;i<=currentCount;i++){
            double x = circle.get(i).getCenterX();
            double y = circle.get(i).getCenterY();
            double dx = initDx.get(i);
            double dy = initDy.get(i);
            if (x < radius || x > getWidth() - radius) {
                dx *= -1; // Change ball move direction
                initDx.set(i, dx);
            }
            if (y < radius || y > getHeight() - radius) {
                dy *= -1; // Change ball move direction
                initDy.set(i, dy);
            }

            // Adjust ball position
            x += dx;
            y += dy;
            circle.get(i).setCenterX(x);
            circle.get(i).setCenterY(y);
        }
    }
}