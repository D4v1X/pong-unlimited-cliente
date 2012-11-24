/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.game.animation;

import drawable.Boundary;
import drawable.Chronometer;
import drawable.Composite;
import drawable.Drawable;
import drawable.Marcador;
import drawable.Position;
import drawable.movable.Ball;
import drawable.movable.Brick;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author David
 */
//public class AnimationController extends Thread implements KeyListener {
public class AnimationController extends Thread {

    private boolean stopping;
    private Drawable escena;
    private List<Ball> ballDrawable;
    private List<Brick> brickJugadores;
    private Chronometer crono;
    private final KeyboardController keyboard;
    private Timer createBalls = new Timer(5000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
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
    });
    private Timer createBricks = new Timer(10000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
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
    });
    

    public AnimationController(Composite escena, KeyboardController keyboardController) {
        this.escena = escena;
        this.ballDrawable = Collections.synchronizedList(new ArrayList<Ball>());
        this.brickJugadores = Collections.synchronizedList(new ArrayList<Brick>());
        crono = new Chronometer();
        this.keyboard = keyboardController;
    }

    @Override
    public void run() {
        Drawable obj = null;
        Ball ballC = null;
        Marcador marcador = (Marcador) escena.getChild(1);
        Boundary contorno = (Boundary) escena.getChild(0);
        escena.add(crono);
        createBalls.start();
        createBricks.start();
        crono.start();
        stopping = false;
        while (!stopping) {
            synchronized (ballDrawable) {// Monitor para controlar la concurrencia
                for (Ball ball : ballDrawable) {
                    //mover bolas
                    ball.move();
                    obj = escena.colision(ball);//detectar colisiones
                    if (obj != null && obj instanceof Ball) {
                        ballC = (Ball) obj;
                        if (ballC.getPosition().getX() == 0) {
                            marcador.addPuntoDcha();
                        }
                        if (ballC.getPosition().getX() == contorno.getWidth()) {
                            marcador.addPuntoIzda();
                        }

                    }
                }
                //mover barras
                keyboard.moveP1();
                keyboard.moveP2();
                /*
                 if (obj != null && obj instanceof Ball && ballDrawable.size() > 1) {
                 escena.remove(obj);
                 ballDrawable.remove(obj);
                 }*/
                if (marcador.acabado()) {
                    createBalls.stop();
                    createBricks.stop();
                    crono.stop();
                    setStopping(true);
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    public void addBall(Ball ball) {
        this.ballDrawable.add(ball);
    }

    public void addBrick(Brick brick) {
        this.brickJugadores.add(brick);
    }

    public boolean isStopping() {
        return stopping;
    }

    public void setStopping(boolean stopping) {
        this.stopping = stopping;
    }

    public void setStoppingballs(boolean stopping) {
        if (stopping) {
            createBalls.stop();
        } else {
            createBalls.start();
        }
    }

    public void setStoppingbricks(boolean stopping) {
        if (stopping) {
            createBricks.stop();
        } else {
            createBricks.start();
        }
    }

    public Drawable getEscena() {
        return escena;
    }

    public void setEscena(Drawable escena) {
        this.escena = escena;
    }

    public List<Ball> getBallDrawable() {
        return ballDrawable;
    }

    public void setBallDrawable(List<Ball> ballDrawable) {
        this.ballDrawable = ballDrawable;
    }

}
