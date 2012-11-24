/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.game.animation.task;

import drawable.Drawable;
import drawable.Position;
import drawable.movable.Ball;
import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

/**
 *
 * @author davidsantiagobarrera
 */
public class TaskBalls extends TimerTask{
    private final Drawable escena;
    private final List<Ball> ballDrawable;

    public TaskBalls(Drawable escena, List<Ball> ballDrawable) {
        this.escena = escena;
        this.ballDrawable = ballDrawable;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        Ball ball;
        int dx = rnd.nextInt(3) - 1;
        int dy = rnd.nextInt(3) - 1;
        if (dx == 0 && dy == 0) {
            dx++;
            dy++;
        }
        if (dx == 0) {
            dx--;
        }
        if (dy == 0) {
            dy--;
        }
        ball = new Ball((dx), (dy), (2 + rnd.nextInt(8)),
                new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)),
                new Position(300, 300));
        escena.add(ball);
        ballDrawable.add(ball);
    }
    
}
