import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {
    JFrame nicowindow;
    Container con; //wie creep in sc2 darauf kann man sachen platzieren
    JPanel titlepanel, startpanel, maintextpanel, choicebuttonpanel, playerpanel;
    JLabel displaylable, healthlabel, healthlabelnumber, weaponlabel, weaponlabelname, healpotlable, healpotnumber;
    Font titlefont = new Font("Times new Roman", Font.PLAIN, 100);
    Font normalfont = new Font("Times new Roman", Font.PLAIN, 22);
    Font buttonfont = new Font("Times new Roman", Font.PLAIN, 48);
    JButton startbutton, choice1, choice2, choice3, choice4;
    JTextArea maintextarea;

    //Game Variables
    String[] enemies = {"Knight", "Bandit"};
    int maxenemyhealth = 100;
    int maxenemyattackdmg = 25;

    //Player Variables
    int playerhealth = 100;
    int maxplayerattackdmg = 25;
    int numhealpots = 3;
    int healpotionhealamount = 30;
    int healpotiondropchance = 20; //Percent dropchance
    String weapon, position;

    //System Objects
    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    Random rand = new Random();


    public static void main(String[] args) {

        new Main();
    }

    public Main() {

        nicowindow = new JFrame();
        nicowindow.setSize(1600, 900);
        nicowindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nicowindow.getContentPane().setBackground(Color.black);
        nicowindow.setLayout(null); // so we can use our own layout
        con = nicowindow.getContentPane();

        titlepanel = new JPanel();
        titlepanel.setBounds(200, 200, 1200, 400);
        titlepanel.setBackground(Color.black);
        displaylable = new JLabel("Walasia");
        displaylable.setForeground(Color.orange);
        displaylable.setFont(titlefont);

        startpanel = new JPanel();
        startpanel.setBounds(600, 600, 400, 200);
        startpanel.setBackground(Color.black);

        startbutton = new JButton("Start");
        startbutton.setBackground(Color.black);
        startbutton.setForeground(Color.white);
        startbutton.setFont(buttonfont);
        startbutton.addActionListener(tsHandler); //tshandler gets called when you click button
        startbutton.setFocusPainted(false);

        titlepanel.add(displaylable);
        startpanel.add(startbutton);
        con.add(titlepanel);
        con.add(startpanel);
        nicowindow.setVisible(true);
    }

    public void createGameScreen() {

        titlepanel.setVisible(false);
        startpanel.setVisible(false);

        maintextpanel = new JPanel();
        maintextpanel.setBounds(200, 100, 1200, 350);
        maintextpanel.setBackground(Color.black);

        maintextarea = new JTextArea();
        maintextarea.setBounds(200, 100, 1200, 350);
        maintextarea.setBackground(Color.black);
        maintextarea.setForeground(Color.white);
        maintextarea.setFont(normalfont);
        maintextarea.setLineWrap(true); //helps so the text moves down in lines too
        maintextpanel.add(maintextarea);

        choicebuttonpanel = new JPanel();
        choicebuttonpanel.setBounds(500, 500, 600, 300);
        choicebuttonpanel.setBackground(Color.black);
        choicebuttonpanel.setLayout(new GridLayout(4, 1)); //Changes Layout

        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalfont);
        choicebuttonpanel.add(choice1);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1"); // so choicehandler can differenciate between button choices 1-4

        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalfont);
        choicebuttonpanel.add(choice2);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");

        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(normalfont);
        choicebuttonpanel.add(choice3);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");

        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(normalfont);
        choicebuttonpanel.add(choice4);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");

        playerpanel = new JPanel();
        playerpanel.setBounds(100, 15, 1400, 75);
        playerpanel.setBackground(Color.black);
        playerpanel.setLayout(new GridLayout(1, 4));

        healthlabel = new JLabel("HP:");
        healthlabel.setFont(buttonfont);
        healthlabel.setForeground(Color.red);
        playerpanel.add(healthlabel);

        healthlabelnumber = new JLabel(); //Variable for HP
        healthlabelnumber.setForeground(Color.white);
        healthlabelnumber.setFont(buttonfont);
        playerpanel.add(healthlabelnumber);

        weaponlabel = new JLabel("Weapon:");
        weaponlabel.setFont(buttonfont);
        weaponlabel.setForeground(Color.white);
        playerpanel.add(weaponlabel);

        weaponlabelname = new JLabel(); //Variable for weapon
        weaponlabelname.setFont(buttonfont);
        weaponlabelname.setForeground(Color.white);
        playerpanel.add(weaponlabelname);

        healpotlable = new JLabel("Potions:");
        healpotlable.setFont(buttonfont);
        healpotlable.setForeground(Color.magenta);
        playerpanel.add(healpotlable);

        healpotnumber = new JLabel(); //Variable for Healing Potions
        healpotnumber.setFont(buttonfont);
        healpotnumber.setForeground(Color.white);
        playerpanel.add(healpotnumber);

        con.add(playerpanel);
        con.add(choicebuttonpanel);
        con.add(maintextpanel);

        playersetup();

    }

    public void playersetup() {
        weapon = "Sword";
        weaponlabelname.setText(weapon);
        healthlabelnumber.setText("" + playerhealth);
        healpotnumber.setText("" + numhealpots);

        Area1();
    }

    public void Area1() {
        if (playerhealth <= 0) {
            playerhealth = 100;
            healthlabelnumber.setText("" + playerhealth);
        }
        weaponlabelname.setText("Sword");
        position = "Area1"; //Position helps the program to differenciate between choices. So they know ahhh this is choice one in this alea.
        maintextarea.setText("You are at the entrance to an Arena");

        choice1.setText("Go into the Arena to fight");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void Fight() {
        position = "Fight";
        maxenemyhealth = rand.nextInt(); // int between 0 and maxenemyhealth
        String enemy = enemies[rand.nextInt(enemies.length)];

        maintextarea.setText("" + enemy + " appeared");

        choice1.setText("Attack");
        choice2.setText("Drink Potion");
        choice3.setText("Run");
        choice4.setText("");

    }
    public class TitleScreenHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            createGameScreen();
        }

    }

    public class ChoiceHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String yourchoice = event.getActionCommand(); // c1, c2, c3, c4 choices

            switch (position) {
                case "Area1":
                    switch (yourchoice) {
                        case "c1":
                            Fight();
                            break;
                        case "c2":
                            ;
                            break;
                        case "c3":
                            ;
                            break;
                        case "c4":
                            ;
                            break;
                    }
                    break;
                case "Fight":
                    switch (yourchoice){
                        case "c1":
                            Area1();
                            break;
                        case "c2":
                            Area1();
                            break;
                        case "c3":
                            Area1();
                            break;
                        case "c4":
                            Area1();
                            break;
                    }
                    break;
            }
        }
    }
}