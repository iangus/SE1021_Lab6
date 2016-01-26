/**
 * SE1021 - 032
 * Winter 2016
 * Lab 6
 * Name: Ian Guswiler
 * Created: 1/21/2016
 */

import edu.msoe.se1021.Lab6.WebsiteTester;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Class built to utilize the WebsiteTester class to connect to a website and download the text contents of the page.
 *
 * @author Ian Guswiler
 * @version 1/26/2016
 */
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
    private WebsiteTester webTester = new WebsiteTester();


    /**
     * Creates labels and formats the components that are then added to the frame.
     */
    public void buildGUI(){
        JLabel urlLabel = new JLabel("URL", JLabel.LEFT);
        JLabel sizeLabel = new JLabel("Size", JLabel.LEFT);
        JLabel portLabel = new JLabel("Port", JLabel.LEFT);
        JLabel timeoutLabel = new JLabel("Timeout", JLabel.LEFT);
        JLabel downloadLabel = new JLabel("Download Time", JLabel.LEFT);
        JLabel hostLabel = new JLabel("Host", JLabel.LEFT);

        JScrollPane textFrame = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);




        c.anchor = GridBagConstraints.FIRST_LINE_START;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.ipadx = 10;
        add(urlLabel,c);

        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 0;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.BOTH;
        add(urlTextBox,c);

        c.gridx = 5;
        c.gridy = 0;
        c.gridwidth = 2;
        add(analyzeButton,c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(sizeLabel,c);

        c.gridx = 1;
        c.gridy = 1;
        add(resourceSize,c);

        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 2;
        add(downloadLabel,c);

        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 3;
        add(downloadTime,c);


        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(portLabel,c);

        c.gridx = 1;
        c.gridy = 2;
        add(port,c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 2;
        add(hostLabel,c);

        c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 3;
        add(host,c);


        c.gridx = 0;
        c.gridy = 3;
        add(timeoutLabel,c);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 4;
        add(timeout,c);

        c.gridx = 5;
        c.gridy = 3;
        c.gridwidth = 2;
        add(setTimeoutButton,c);


        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 7;
        c.gridheight = 4;
        c.weighty = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        add(textFrame,c);
    }

    /**
     * main program function that creates a new website tester and sets its visibility
     *
     * @param args Ignored
     */
    public static void main(String[] args) {
        WebsiteTesterUI test = new WebsiteTesterUI();
        test.setVisible(true);
    }

    /**
     * Initializes the program components and creates the anonymous action listeners for buttons and text fields
     */
    public WebsiteTesterUI(){
        setTitle("Website Downloader and Tester");
        setLayout(new GridBagLayout());
        setSize(500, 400);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        analyzeButton = new JButton("Analyze");
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(webTester.isUrlSet()){
                    try {
                        webTester.openConnection();
                        webTester.downloadText();
                    } catch (SocketTimeoutException e1){
                        int n = JOptionPane.showConfirmDialog(null, "There has been a timeout reaching the site. Would " +
                                "you like to extend the timeout period?", "Connection Timeout",
                                JOptionPane.YES_NO_OPTION);
                        if(n == 0){
                            String input = JOptionPane.showInputDialog(null, "Please enter the timeout" +
                                    " that you would like.");
                            webTester.setTimeout(input);
                        }
                    } catch (UnknownHostException e2){
                        JOptionPane.showMessageDialog(null, "Error: Unable to reach the host " + urlTextBox.getText(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException e3) {
                        JOptionPane.showMessageDialog(null, "Error: File was not found on the server. " + urlTextBox.getText());
                    }
                    resourceSize.setText("" +webTester.getSize());
                    String downloadedText = webTester.getContent();
                    String existingText = textArea.getText();
                    textArea.setText(existingText + "\n" + downloadedText);
                    downloadTime.setText("" + webTester.getDownloadTime() + " ms");
                    host.setText(webTester.getHostname());
                    port.setText("" + webTester.getPort());

                }
            }
        });

        setTimeoutButton = new JButton("Set");
        setTimeoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String timeSet = timeout.getText();
                    webTester.setTimeout(timeSet);
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Timeout must be greater that or equal to 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    timeout.setText(webTester.getTimeout());
                    logger.warning(e1.getMessage());
                    logger.info("NumberFormatException: Timeout entry must be a positive integer entered as a string");
                }
            }
        });

        urlTextBox = new JTextField(20);
        urlTextBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String url =  urlTextBox.getText();
                    webTester.openURL(url);
                } catch (MalformedURLException e1) {
                    urlTextBox.setText("");
                    JOptionPane.showMessageDialog(null, "The URL entered in the text box is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                    logger.warning(e1.getMessage());
                    logger.info("MalformedURLException: A valid url must be entered.");
                }
            }
        });

        resourceSize = new JTextField(5);
        resourceSize.setEditable(false);

        port = new JTextField(5);
        port.setEditable(false);

        downloadTime = new JTextField(15);
        downloadTime.setEditable(false);

        host = new JTextField(15);
        host.setEditable(false);

        timeout = new JTextField(20);
        timeout.setText(webTester.getTimeout());

        textArea = new JTextArea();
        textArea.setSize(600,300);

        buildGUI();
    }
}
