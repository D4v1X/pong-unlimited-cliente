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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author David
 */
public class AnimationController extends Thread implements KeyListener {

    private boolean stopping;
    private Drawable escena;
    private List<Ball> ballDrawable;
    private List<Brick> brickDrawable;
    private boolean p1FlagUp, p1FlagDown, p2FlagUp, p2FlagDown;
    private Chronometer crono;
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

    public AnimationController(Composite escena) {
        this.escena = escena;
        this.ballDrawable = Collections.synchronizedList(new ArrayList<Ball>());
        this.brickDrawable = Collections.synchronizedList(new ArrayList<Brick>());
        crono = new Chronometer();
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
                moveP1();
                moveP2();
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
        this.brickDrawable.add(brick);
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

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("wep");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("press");
        switch (e.getKeyCode()) {
            // Move 1
            case KeyEvent.VK_W:
                p1FlagUp = true;
                break;
            case KeyEvent.VK_S:
                p1FlagDown = true;
                break;
            // Move 2
            case KeyEvent.VK_UP:
                p2FlagUp = true;
                break;
            case KeyEvent.VK_DOWN:
                p2FlagDown = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("release");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                p1FlagUp = false;
                break;
            case KeyEvent.VK_S:
                p1FlagDown = false;
                break;
            case KeyEvent.VK_UP:
                p2FlagUp = false;
                break;
            case KeyEvent.VK_DOWN:
                p2FlagDown = false;
                break;
        }
    }

    public void moveP1() {
        Brick brick1 = brickDrawable.get(0);
        Boundary contorno = (Boundary) escena.getChild(0);
        if (p1FlagUp == true && brick1.getPosition().getY() >= contorno.getPosition().getY()) {
            brick1.moveDown();
        }
        if (p1FlagDown == true && brick1.getPosition().getY() + brick1.getHeight() <= contorno.getHeight()) {
            brick1.moveUp();
        }
    }

    public void moveP2() {
        Brick brick2 = brickDrawable.get(1);
        Boundary contorno = (Boundary) escena.getChild(0);
        if (p2FlagUp == true && brick2.getPosition().getY() >= contorno.getPosition().getY()) {
            brick2.moveDown();
        }
        if (p2FlagDown == true && brick2.getPosition().getY() + brick2.getHeight() <= contorno.getHeight()) {
            brick2.moveUp();
        }
    }
}
