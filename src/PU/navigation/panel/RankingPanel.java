/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.navigation.panel;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import nodrawable.Ranking;
import nodrawable.Score;

/**
 *
 * @author davidsantiagobarrera
 */
public class RankingPanel extends JPanel {

    public RankingPanel(Ranking ranking) {
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
        setLayout(new GridLayout(1, 3));
        add(nombre);
        add(tiempo);
        add(modo);
    }
}
