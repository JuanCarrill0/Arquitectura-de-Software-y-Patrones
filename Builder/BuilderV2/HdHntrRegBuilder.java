import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HdHntrRegBuilder extends UIBuilder {

    private JTextField txtAddress = new JTextField(20);
    private JTextField txtEIN = new JTextField(15);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtURL = new JTextField(20);
    private JTextField txtPhone = new JTextField(15);

    public void addUIControls() {
        searchUI = new JPanel();
        JLabel lblAddress = new JLabel("Address:");
        JLabel lblEIN = new JLabel("EIN:");
        JLabel lblEmail = new JLabel("E-mail:");
        JLabel lblURL = new JLabel("URL of web site:");
        JLabel lblPhone = new JLabel("Phone number:");
        
        GridBagLayout gridbag = new GridBagLayout();
        searchUI.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        searchUI.add(lblAddress);
        searchUI.add(txtAddress);
        searchUI.add(lblEIN);
        searchUI.add(txtEIN);
        searchUI.add(lblEmail);
        searchUI.add(txtEmail);
        searchUI.add(lblURL);
        searchUI.add(txtURL);
        searchUI.add(lblPhone);
        searchUI.add(txtPhone);

        gbc.insets.top = 5;
        gbc.insets.bottom = 5;
        gbc.insets.left = 5;
        gbc.insets.right = 5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gridbag.setConstraints(lblAddress, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gridbag.setConstraints(txtAddress, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gridbag.setConstraints(lblEIN, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gridbag.setConstraints(txtEIN, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gridbag.setConstraints(lblEmail, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gridbag.setConstraints(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gridbag.setConstraints(lblURL, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gridbag.setConstraints(txtURL, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gridbag.setConstraints(lblPhone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gridbag.setConstraints(txtPhone, gbc);
    }

    public void initialize() {
        txtAddress.setText("Enter company address");
        txtEIN.setText("Enter EIN");
        txtEmail.setText("example@domain.com");
        txtURL.setText("http://www.example.com");
        txtPhone.setText("(000) 000-0000");
    }

    public String getSQL() {
        return "INSERT INTO HeadhunterEnterprise (Address, EIN, Email, URL, PhoneNumber) VALUES ('" +
           txtAddress.getText() + "', '" +
           txtEIN.getText() + "', '" +
           txtEmail.getText() + "', '" +
           txtURL.getText() + "', '" +
           txtPhone.getText() + "')";
    }

}
