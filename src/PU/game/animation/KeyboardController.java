/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.game.animation;

import drawable.Boundary;
import drawable.movable.Brick;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author davidsantiagobarrera
 */
public class KeyboardController implements KeyListener {

    private boolean p1FlagUp, p1FlagDown, p2FlagUp, p2FlagDown;
    private final Brick barraJ1, barraJ2;
    private final Boundary contorno;

    public KeyboardController(Brick barraJ1, Brick barraJ2, Boundary contorno) {
        this.barraJ1 = barraJ1;
        this.barraJ2 = barraJ2;
        this.contorno = contorno;
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
        if (p1FlagUp == true && barraJ1.getPosition().getY() >= contorno.getPosition().getY()) {
            barraJ1.moveDown();
        }
        if (p1FlagDown == true && barraJ1.getPosition().getY() + barraJ1.getHeight() <= contorno.getHeight()) {
            barraJ1.moveUp();
        }
    }

    public void moveP2() {
        if (p2FlagUp == true && barraJ2.getPosition().getY() >= contorno.getPosition().getY()) {
            barraJ2.moveDown();
        }
        if (p2FlagDown == true && barraJ2.getPosition().getY() + barraJ2.getHeight() <= contorno.getHeight()) {
            barraJ2.moveUp();
        }
    }
}
