/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.navigation;

import PU.game.GameController;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author davidsantiagobarrera
 */
public class NavigationController implements ActionListener {

    private JApplet mainWindows;
    private JPanel wellcome = new JPanel();
    private JPanel gameMode = new JPanel();
    private JPanel story = new JPanel();
    private JPanel sPlayer = new JPanel();
    private JPanel lPanel = new JPanel();
    private GameController gameController;
    private JTextField mensaje;
    private JTextField operacion;

    public JApplet getMainWindows() {
        return mainWindows;
    }

    
    
    public NavigationController(JApplet mainWindows) {
        this.mainWindows = mainWindows;
        initLogin();
        initComponentWellcome();
        initComponentStory();
        initComponentGameMode();
        initSurvival();
    }

    private void initLogin()
    {
        JTextField userT  = new JTextField(10);
        JTextField passT  = new JTextField(10);
        JLabel user = new JLabel("user");
        JLabel pass = new JLabel("pass");
        JButton login = new JButton("Login");
        JButton register = new JButton("Register");
        login.addActionListener(this);
        register.addActionListener(this);
        lPanel.add(user);lPanel.add(userT);
        lPanel.add(pass);lPanel.add(passT);
        lPanel.add(login);lPanel.add(register);
        mainWindows.add("South", lPanel);
        lPanel.setVisible(true);
        lPanel.setBackground(Color.white);
    }
    
    
    private void initComponentWellcome() {
        JButton start = new JButton("Start");
        JButton storyB = new JButton("Story");
        start.addActionListener(this);
        storyB.addActionListener(this);
        wellcome.add(start);
        wellcome.add(storyB);
        wellcome.setBackground(Color.white);
    }

    private void initComponentStory() {
        JButton back = new JButton("Back");
        JTextPane tp = new JTextPane();
        tp.setText("Once upon a time in a forbidden\n"
                + "galaxy, two lords of void, one evil,\n"
                + "one psyco, created an overwhelming\n"
                + "battle to death just to amuse\n"
                + "theirseft. I was call PONGUnlimited.");
        back.addActionListener(this);
        story.add(tp);
        story.add(back);
        story.setBackground(Color.white);
    }

    private void initComponentGameMode() {
        JButton pve = new JButton("Survival");
        JButton pvp = new JButton("Duel");
        JButton online = new JButton("Online");
        JButton back = new JButton("Back");
        pve.addActionListener(this);
        pvp.addActionListener(this);
        online.addActionListener(this);
        back.addActionListener(this);
        gameMode.add(pve);
        gameMode.add(pvp);
        gameMode.add(online);
        gameMode.add(back);
        gameMode.setBackground(Color.white);
    }
    
    private void initSurvival() {
        JButton nGame = new JButton("New Game");
        JButton lGame = new JButton("Load Game");
        JButton back = new JButton("Back");
        nGame.addActionListener(this);
        lGame.addActionListener(this);
        back.addActionListener(this);
        sPlayer.add(nGame);
        sPlayer.add(lGame);
        sPlayer.add(back);
        sPlayer.setBackground(Color.white);

    }

    private void initComponentGame() {
        JLabel labelmensaje, labeloperacion;
        JPanel fila = new JPanel();
        fila.setLayout(new FlowLayout());
        mensaje = new JTextField(5);
        labelmensaje = new JLabel("User:");
        labelmensaje.setLabelFor(mensaje);
        JButton botonGuardarBD = new JButton("GuardarBD");
        operacion = new JTextField(10);
        labeloperacion = new JLabel("Ope:");
        labeloperacion.setLabelFor(operacion);
        JButton botonCargarBD = new JButton("CargarBD");
        JButton pausar = new JButton("Pause");
        fila.setLayout(new FlowLayout());
        fila.add(botonGuardarBD);
        fila.add(labelmensaje);
        fila.add(mensaje);
        fila.add(labeloperacion);
        fila.add(operacion);
        fila.add(botonCargarBD);
        fila.add(pausar);
        fila.setBackground(Color.white);
        mainWindows.add("South", fila);
        // --------------
        botonGuardarBD.addActionListener(gameController);
        botonCargarBD.addActionListener(gameController);
        pausar.addActionListener(gameController);
    }

    private void lauchGame(String gameMode) {
        Graphics g = mainWindows.getGraphics();
        initComponentGame();
        gameController = new GameController(g, mensaje, operacion, mainWindows, gameMode);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Login":
                mainWindows.add("South", wellcome);
                lPanel.setVisible(false);   
                wellcome.setVisible(true);   
                break;
            case "Start":
                mainWindows.add("South", gameMode);
                wellcome.setVisible(false);
                gameMode.setVisible(true);
                break;
            case "Story":
                mainWindows.add("South",story);
                wellcome.setVisible(false);
                story.setVisible(true);
                break;
            case "Back":
                sPlayer.setVisible(false);
                story.setVisible(false);
                gameMode.setVisible(false);
                wellcome.setVisible(true);
                break;
            case "Survival":
                mainWindows.add("South",sPlayer);
                gameMode.setVisible(false);
                sPlayer.setVisible(true);
                break;
            case "New Game":
                sPlayer.setVisible(false);
                lauchGame("Survival");
                break;
            case "Load Game":
                break;
            case "Duel":
                gameMode.setVisible(false);
                lauchGame("Duel");
                break;
            case "Online":
               break;
        }
    }
}
