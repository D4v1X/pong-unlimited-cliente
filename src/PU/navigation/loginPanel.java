/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.navigation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author AitorC
 */
public class loginPanel extends JPanel {
    private JPanel lPanel;

    public loginPanel(NavigationController nController) {
        JPanel lPanel = new JPanel();
        JLabel user = new JLabel("user");
        JLabel pass = new JLabel("pass");
        JButton login = new JButton("Login");
        login.addActionListener(nController);
        lPanel.add(user);lPanel.add(pass);lPanel.add(login);
       
    }
    
    
}
