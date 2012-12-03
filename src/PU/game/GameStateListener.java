/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.game;

/**
 *
 * @author davidsantiagobarrera
 */
public interface GameStateListener {
    void endGame();
    void pauseGame();
    void retryGame();
    void resumeGame();
}
