/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.game;

import PU.game.animation.AnimationController;
import PU.game.animation.KeyboardController;
import PU.game.view.Renderer;
import connection.ConnectionController;
import connection.ConnectionManager;
import drawable.Boundary;
import drawable.Chronometer;
import drawable.Composite;
import drawable.Drawable;
import drawable.Marcador;
import drawable.Position;
import drawable.movable.Ball;
import drawable.movable.Brick;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JApplet;
import javax.swing.JTextField;

/**
 *
 * @author David
 */
public class GameController implements ActionListener {

    private Composite escena;
    private Boundary contorno;
    private Marcador marcador;
    private Chronometer crono;
    private Brick barraJ1;
    private Brick barraJ2;
    private AnimationController animationcontroller;
    private ConnectionManager conexion;
    private JTextField mensajeU;
    private JTextField mensajeO;
    private boolean pausado = false;
    private JApplet vp;
    private Graphics g;
    private KeyboardController keyboardController;

    public GameController(Graphics g, JTextField mensajeU, JTextField mensajeO, JApplet vp, String gameMode) {
        this.g = g;
        this.mensajeU = mensajeU;
        this.mensajeO = mensajeO;
        this.vp = vp;
        initScene(gameMode);
        initControl();
        initMotorGraphics();
        vp.addKeyListener(keyboardController);//Control de Teclado
        initConnection();

    }

    private void initScene(String gameMode) {
        escena = new Composite();
        contorno = new Boundary(new Position(0, 0), vp.getWidth(), vp.getHeight() - 40);
        marcador = new Marcador();
        crono = new Chronometer();
        switch (gameMode) {
            case "Survival":
                barraJ1 = new Brick(1, new Position(0, 0), 10, contorno.getHeight() + 20);
                barraJ2 = new Brick(1, new Position(contorno.getWidth() - 40, contorno.getHeight() / 2 - 20), 10, 40);
                break;
            case "Duel":
                barraJ1 = new Brick(1, new Position(20, contorno.getHeight() / 2 - 20), 10, 40);
                barraJ2 = new Brick(1, new Position(contorno.getWidth() - 30, contorno.getHeight() / 2 - 40), 10, 40);
                break;
            case "Online":
                barraJ1 = new Brick(1, new Position(20, contorno.getHeight() / 2 - 20), 10, 40);
                barraJ2 = new Brick(1, new Position(contorno.getWidth() - 30, contorno.getHeight() / 2 - 40), 10, 40);
                break;
        }
        escena.add(contorno);
        escena.add(marcador);
        escena.add(crono);
        escena.add(barraJ1);
        escena.add(barraJ2);
    }

    private void initMotorGraphics() {
        Renderer renderer;
        renderer = new Renderer(escena, g);
        animationcontroller = new AnimationController(escena, keyboardController, crono, marcador, contorno);
        animationcontroller.addBrick(barraJ1);
        animationcontroller.addBrick(barraJ2);
        renderer.start();
        animationcontroller.start();
    }

    private void initControl() {
        keyboardController = new KeyboardController(barraJ1, barraJ2, contorno);
    }

    private void initConnection() {
        conexion = new ConnectionController();
    }

    public String saveScene(Drawable scene, String name, String operacion) {
        return conexion.sendScene(scene, name, operacion);
    }

    public String loadScene(String name, String operacion) {
        Composite escenaCargada;
        List<Drawable> childDrawableCargado = Collections.synchronizedList(new ArrayList<Drawable>());
        List<Ball> ballDrawableCargada = Collections.synchronizedList(new ArrayList<Ball>());
        escenaCargada = (Composite) conexion.receiveScene(name, operacion);
        for (Drawable draw : escenaCargada.getChildDrawable()) {
            childDrawableCargado.add(draw);
            if (draw instanceof Ball) {
                ballDrawableCargada.add((Ball) draw);
            }
        }
        escena.setChildDrawable(childDrawableCargado);
        animationcontroller.setBallDrawable(ballDrawableCargada);
        return "Cargado de BD";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String usuario;
        System.out.println(e.getActionCommand());

        //Guardamos la escema de una BD
        if (e.getActionCommand().equals("GuardarBD")) {
            usuario = mensajeU.getText();
            mensajeO.setText(this.saveScene(animationcontroller.getEscena(), usuario, "GUARDARBD"));
        }
        //Cargamos la Escena de una BD
        if (e.getActionCommand().equals("CargarBD")) {
            usuario = mensajeU.getText();
            mensajeO.setText(this.loadScene(usuario, "CARGARBD"));
        }
    }
}
