/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.game.animation.task;

import drawable.Drawable;
import drawable.Position;
import drawable.movable.Brick;
import java.util.Random;
import java.util.TimerTask;

/**
 *
 * @author davidsantiagobarrera
 */
public class TaskBricks extends TimerTask{
    private final Drawable escena;

    public TaskBricks(Drawable escena) {
        this.escena = escena;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        int width = (20 + rnd.nextInt(20));
        int heigth = (10 + rnd.nextInt(30));
        int x = 50 + rnd.nextInt(250);
        int y = 50 + rnd.nextInt(450);
        Brick brick1, brick2;
        brick1 = new Brick(2,
                new Position(x, y),
                width, heigth);
        brick2 = new Brick(2,
                new Position(600 - width - x, y),
                width, heigth);
        escena.add(brick1);
        escena.add(brick2);
    }
    
}
