/**
 * SE1021 - 032
 * Winter 2016
 * Lab
 * Name: Ian Guswiler
 * Created: 1/21/2016
 */

import edu.msoe.se1021.Lab6.WebsiteTester;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class WebsiteTesterUI extends JFrame {
    private JButton analyzeButton;
    private JTextField downloadTime;
    private JTextField host;
    private Logger logger;
    private JTextField port;
    private JTextField resourceSize;
    private static final long serialVersionUID = 1L;
    private JButton setTimeoutButton;
    private JTextArea textArea;
    private JTextField timeout;
    private JTextField urlTextBox;
    private WebsiteTester webTester;

    public void buildGUI(){
        setTitle("Website Downloader and Tester");
        setSize(600, 500);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        JLabel urlLabel = new JLabel("URL");
        JLabel sizeLabel = new JLabel("Size");
        JLabel portLabel = new JLabel("Port");
        JLabel timeoutLabel = new JLabel("Timeout");
        JLabel downloadLabel = new JLabel("Download Time");
        JLabel hostLabel = new JLabel("Host");

        analyzeButton = new JButton("Analyze");

        setTimeoutButton = new JButton("Set");

        urlTextBox = new JTextField(20);

        resourceSize = new JTextField(5);
        resourceSize.setEditable(false);

        port = new JTextField(5);
        port.setEditable(false);

        downloadTime = new JTextField(15);
        downloadTime.setEditable(false);

        host = new JTextField(15);
        host.setEditable(false);

        timeout = new JTextField(20);

        textArea = new JTextArea();
        textArea.setSize(600,300);

        c.gridx = 0;
        c.gridy = 0;
        add(urlLabel,c);

        c.gridx = 1;
        c.gridy = 0;
        add(urlTextBox,c);

        c.gridx = 2;
        c.gridy = 0;
        add(analyzeButton,c);
    }

    public static void main(String[] args) {
        WebsiteTesterUI test = new WebsiteTesterUI();
        test.setVisible(true);
    }

    public WebsiteTesterUI(){
        buildGUI();
    }
}
