/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.navigation;

import PU.game.GameController;
import PU.navigation.panel.ControlsPanel;
import PU.navigation.panel.CreditsPanel;
import PU.navigation.panel.GameModePanel;
import PU.navigation.panel.GamePanel;
import PU.navigation.panel.LoginPanel;
import PU.navigation.panel.RankingPanel;
import PU.navigation.panel.StoryPanel;
import PU.navigation.panel.WellcomePanel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;
import nodrawable.Ranking;

/**
 *
 * @author davidsantiagobarrera
 */
public class NavigationController implements ActionListener, NavigationStateListener {

    private JApplet mainWindows;
    private GameController gameController;
    private Graphics g;
    private ImageIcon fondo;
    private LoginPanel loginPanel;
    private WellcomePanel wellcomePanel;
    private StoryPanel storyPanel;
    private GameModePanel gameModePanel;
    private GamePanel gamePanel;
    private CreditsPanel creditsPanel;
    private ControlsPanel controlsPanel;

    public NavigationController(JApplet mainWindows) {
        this.mainWindows = mainWindows;
        initBackground();
        initComponentLogin();
        initComponentWellcome();
        initComponentGameMode();
    }

    private void initComponentLogin() {
        loginPanel = new LoginPanel(fondo, this);
        mainWindows.add(loginPanel);
    }

    private void initComponentWellcome() {
        wellcomePanel = new WellcomePanel(fondo, this);
        storyPanel = new StoryPanel(fondo, this);
        creditsPanel = new CreditsPanel(fondo, this);
        controlsPanel = new ControlsPanel(fondo, this);
    }

    private void initComponentGameMode() {
        gameModePanel = new GameModePanel(fondo, this);
    }

    private void initBackground() {
        System.out.println("wep: " + getClass().getResource("/images/fondo.png"));
        fondo = new ImageIcon(getClass().getResource("/images/fondo.png"));
    }

    private void lauchGame(String gameMode) {
        g = mainWindows.getGraphics();
        gameController = new GameController(g, loginPanel.getUser(), mainWindows, gameMode, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Login":
                mainWindows.add(wellcomePanel);
                setInvisiblePanel();
                wellcomePanel.setVisible(true);
                break;
            case "Game mode":
                mainWindows.add(gameModePanel);
                setInvisiblePanel();
                gameModePanel.setVisible(true);
                break;
            case "Story":
                mainWindows.add(storyPanel);
               setInvisiblePanel();
                storyPanel.setVisible(true);

                break;
            case "Controls":
                mainWindows.add(controlsPanel);
                setInvisiblePanel();
                controlsPanel.setVisible(true);
                break;
            case "Credits":
                mainWindows.add(creditsPanel);
                setInvisiblePanel();
                creditsPanel.setVisible(true);
                break;
            case "Back":
                setInvisiblePanel();
                wellcomePanel.setVisible(true);
                break;
            case "Survival":
                setInvisiblePanel();
                lauchGame("Survival");
                break;
            case "Duel":
                setInvisiblePanel();
                lauchGame("Duel");
                break;
            case "Online":
                break;
        }
    }

    private void setInvisiblePanel() {
        loginPanel.setVisible(false);
        wellcomePanel.setVisible(false);
        storyPanel.setVisible(false);
        gameModePanel.setVisible(false);
        //gamePanel.setVisible(false);
        creditsPanel.setVisible(false);
        controlsPanel.setVisible(false);
    }

    @Override
    public void viewRanking(Ranking ranking) {
        JFrame marco = new JFrame("Ranking");
        marco.setSize(300, 300);
        RankingPanel rankingPanel = new RankingPanel(ranking);
        marco.add(rankingPanel, BorderLayout.CENTER);
        marco.setVisible(true);
    }
}
