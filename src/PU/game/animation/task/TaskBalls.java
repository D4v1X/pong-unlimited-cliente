/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.game.animation.task;

import drawable.Boundary;
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
    private final Boundary contorno;

    public TaskBalls(Drawable escena, List<Ball> ballDrawable, Boundary contorno) {
        this.escena = escena;
        this.ballDrawable = ballDrawable;
        this.contorno = contorno;
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
                new Position(contorno.getWidth()/2, contorno.getHeight()/2));
        escena.add(ball);
        ballDrawable.add(ball);
    }
    
}
