/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.navigation;

import PU.game.GameController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import nodrawable.Ranking;
import nodrawable.Score;

/**
 *
 * @author davidsantiagobarrera
 */
public class NavigationController implements ActionListener, NavigationStateListener {

    private JApplet mainWindows;
    private JPanel wellcome = new JPanel();
    private JPanel gameMode = new JPanel();
    private JPanel story = new JPanel();
    private JPanel sPlayer = new JPanel();
    private JPanel lPanel = new JPanel();
    private GameController gameController;
    //private JTextField mensaje;
    private JTextField operacion;
    //private JButton botonGuardarBD,botonCargarBD,pausar;
    private JTextField userT;

    public JApplet getMainWindows() {
        return mainWindows;
    }

    public NavigationController(JApplet mainWindows) {
        this.mainWindows = mainWindows;
        initBackground();
        initLogin();
        initComponentWellcome();
        initComponentStory();
        initComponentGameMode();
        initSurvival();
    }

    private void initLogin() {
        userT = new JTextField(10);
        JTextField passT = new JTextField(10);
        JLabel user = new JLabel("user");
        JLabel pass = new JLabel("pass");
        JButton login = new JButton("Login");
        JButton register = new JButton("Register");
        login.addActionListener(this);
        register.addActionListener(this);
        lPanel.add(user);
        lPanel.add(userT);
        lPanel.add(pass);
        lPanel.add(passT);
        lPanel.add(login);
        lPanel.add(register);
        mainWindows.add("South", lPanel);
        lPanel.setVisible(true);
        //lPanel.setBackground(Color.white);
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

    private void initBackground() {
        ImageIcon fondo = new ImageIcon(getClass().getResource("/images/fondo.png"));
        Graphics g = mainWindows.getGraphics();
        g.drawImage(fondo.getImage(), 0, 0, null);
    }

//    private void initComponentGame() {
//        JLabel labelmensaje, labeloperacion;
//        JPanel fila = new JPanel();
//        fila.setLayout(new FlowLayout());
//        mensaje = new JTextField(5);
//        labelmensaje = new JLabel("User:");
//        //--------MIRAR
//        mensaje.setText(userT.getText());
//        labelmensaje.setLabelFor(mensaje);
//        botonGuardarBD = new JButton("GuardarBD");
//        operacion = new JTextField(10);
//        labeloperacion = new JLabel("Ope:");
//        labeloperacion.setLabelFor(operacion);
//        botonCargarBD = new JButton("CargarBD");
//        pausar = new JButton("Pause");
//        fila.setLayout(new FlowLayout());
//        fila.add(botonGuardarBD);
//        fila.add(labelmensaje);
//        fila.add(mensaje);
//        fila.add(labeloperacion);
//        fila.add(operacion);
//        fila.add(botonCargarBD);
//        fila.add(pausar);
//        fila.setBackground(Color.white);
//        mainWindows.add("South", fila);
//    }
    private void lauchGame(String gameMode) {
        Graphics g = mainWindows.getGraphics();
        //initComponentGame();
        gameController = new GameController(g, userT, operacion, mainWindows, gameMode, this);

        // --------------
        //botonGuardarBD.addActionListener(gameController);
        //botonCargarBD.addActionListener(gameController);
        //pausar.addActionListener(gameController);
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
                mainWindows.add("South", story);
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
                mainWindows.add("South", sPlayer);
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

    @Override
    public void viewRanking(Ranking ranking) {
        JFrame marco = new JFrame("Ranking");
        //JPanel marco = new JPanel();
        marco.setSize(300, 300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        marco.setLocation((width / 2) - 100, 0);
        JList nombre = new JList();
        JList tiempo = new JList();
        JList modo = new JList();
        DefaultListModel modeloName = new DefaultListModel();
        DefaultListModel modeloTime = new DefaultListModel();
        DefaultListModel modeloModo = new DefaultListModel();
        nombre.setModel(modeloName);
        tiempo.setModel(modeloTime);
        modo.setModel(modeloModo);
        //----------------------
        List<Score> scorescargados = ranking.getScores();
        modeloName.clear();
        modeloTime.clear();
        modeloModo.clear();
        for (Score sco : scorescargados) {
            modeloName.addElement(sco.getId());
            modeloTime.addElement(sco.getTime());
            modeloModo.addElement(sco.getModo());
        }
        //----------------------
        modeloName.addElement("Fin de Scores");
        marco.setLayout(new GridLayout(1, 3));
        marco.add(nombre);
        marco.add(tiempo);
        marco.add(modo);
        marco.setVisible(true);
    }
}
