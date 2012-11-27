/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.game.animation;

import PU.game.GameStateListener;
import PU.game.animation.task.TaskBalls;
import PU.game.animation.task.TaskBricks;
import drawable.Boundary;
import drawable.Chronometer;
import drawable.Composite;
import drawable.Drawable;
import drawable.Marcador;
import drawable.movable.Ball;
import drawable.movable.Brick;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

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
    private final KeyboardController keyboard;
    private Timer tasks = new Timer();
    private final Chronometer crono;
    private final Marcador marcador;
    private final Boundary contorno;
    private final GameStateListener GameState;
    

    public AnimationController(Composite escena, KeyboardController keyboardController, Chronometer crono, Marcador marcador, Boundary contorno, GameStateListener GameState) {
        this.escena = escena;
        this.ballDrawable = Collections.synchronizedList(new ArrayList<Ball>());
        this.brickJugadores = Collections.synchronizedList(new ArrayList<Brick>());
        this.keyboard = keyboardController;
        this.crono = crono;
        this.marcador = marcador;
        this.contorno = contorno;
        this.GameState = GameState;
    }

    @Override
    public void run() {
        Drawable obj = null;
        Ball ballC = null;
        tasks.schedule(new TaskBricks(escena), 0, 10000);
        tasks.schedule(new TaskBalls(escena, ballDrawable, contorno), 0, 5000);
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
                //pruebas:
                //System.out.println(crono.getTime());
                if (marcador.acabado()) {
                    tasks.cancel();
                    tasks.cancel();
                    crono.stop();
                    setStopping(true);
                    GameState.EndGame();
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

    public Drawable getEscena() {
        return escena;
    }

    public void setBallDrawable(List<Ball> ballDrawable) {
        this.ballDrawable = ballDrawable;
    }

}
