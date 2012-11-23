/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.navigation;

import PU.game.GameController;
import java.awt.BorderLayout;
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
    //private JPanel game = new JPanel(true);
    private JPanel story = new JPanel();
    private GameController gameController;
    private JTextField mensaje;
    private JTextField operacion;

    public NavigationController(JApplet mainWindows) {
        this.mainWindows = mainWindows;
        initComponentWellcome();
        initComponentStory();
        initComponentGameMode();
    }

    private void initComponentWellcome() {
        JButton start = new JButton("Start");
        JButton story = new JButton("Story");
        start.addActionListener(this);
        story.addActionListener(this);
        wellcome.add(start);
        wellcome.add(story);
        wellcome.setBackground(Color.red);
        //mainWindows.add(wellcome);
        mainWindows.add("South", wellcome);
        //wellcome.setVisible(true);
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
        story.setBackground(Color.red);
        //mainWindows.add(story);
        mainWindows.add("South",story);
        //story.setVisible(false);
    }

    private void initComponentGameMode() {
        JButton pve = new JButton("PvE");
        JButton pvp = new JButton("PvP");
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
        gameMode.setBackground(Color.red);
        //mainWindows.add(gameMode);
        mainWindows.add("South", gameMode);
        //gameMode.setVisible(false);
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
        fila.setBackground(Color.red);
        mainWindows.setLayout(new BorderLayout());
        mainWindows.add("South", fila);
        // --------------
        botonGuardarBD.addActionListener(gameController);
        botonCargarBD.addActionListener(gameController);
        pausar.addActionListener(gameController);
    }

    private void lauchGame(String gameMode) {
        Graphics g = mainWindows.getGraphics();
        //gameController = new GameController(mainWindows, g, gameMode);
        initComponentGame();
        gameController = new GameController(g, mensaje, operacion, mainWindows, gameMode);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Start":
                wellcome.setVisible(false);
                story.setVisible(false);
                gameMode.setVisible(true);
                break;
            case "Story":
                wellcome.setVisible(false);
                gameMode.setVisible(false);
                story.setVisible(true);
                break;
            case "Back":
                story.setVisible(false);
                gameMode.setVisible(false);
                wellcome.setVisible(true);
                break;
            case "PvE":
                gameMode.setVisible(false);
                //game.setVisible(true);
                lauchGame("PvE");
                break;
            case "PvP":
                gameMode.setVisible(false);
                //game.setVisible(true);
                lauchGame("PvP");
                break;
            case "Online":
                //gameMode.setVisible(false);
                //game.setVisible(true);
                //lauchGame("Online");
                break;
        }
    }
}
