package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OrderManager extends JFrame {
    public static final String GET_TOTAL = "Get Total";
    public static final String CREATE_ORDER = "Create Order";
    public static final String EXIT = "Exit";
    public static final String CA_ORDER = "California Order";
    public static final String NON_CA_ORDER = "Non-California Order";
    public static final String OVERSEAS_ORDER = "Overseas Order";

    public JComboBox<String> cmbOrderType;
    private JPanel dynamicPanel;
    private UIDirector director;
    OrderVisitor objVisitor;

    public OrderManager() {
        super("Visitor Pattern - Example");
        setLayout(new BorderLayout());

        // Crear el visitor (se asume que existe esta clase)
        objVisitor = new OrderVisitor();

        // Panel superior con el JComboBox
        JPanel topPanel = new JPanel();
        cmbOrderType = new JComboBox<>(new String[]{CA_ORDER, NON_CA_ORDER, OVERSEAS_ORDER});
        topPanel.add(new JLabel("Order Type:"));
        topPanel.add(cmbOrderType);
        add(topPanel, BorderLayout.NORTH);

        // Panel dinámico para los campos
        dynamicPanel = new JPanel();
        add(dynamicPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        JButton getTotalButton = new JButton(GET_TOTAL);
        JButton createOrderButton = new JButton(CREATE_ORDER);
        JButton exitButton = new JButton(EXIT);
        buttonPanel.add(getTotalButton);
        buttonPanel.add(createOrderButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Inicializar el director
        director = new UIDirector();

        // Listener para el JComboBox
        cmbOrderType.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateUI();
            }
        });

        // Action listeners para los botones
        exitButton.addActionListener(e -> System.exit(0));
        createOrderButton.addActionListener(new ButtonHandler(this));
        getTotalButton.addActionListener(new ButtonHandler(this));

        // Inicializar la UI con el primer tipo de orden
        updateUI();
    }

    private void updateUI() {
        String selected = (String) cmbOrderType.getSelectedItem();
        UIBuilder builder;
        //Uso de fabrica
        BuilderFactory factory = new BuilderFactory();
        builder = factory.getUIBuilder(selected);

        //Seleccion de builder
        director.setBuilder(builder);
        director.constructUI();

        // Limpiar y actualizar el panel dinámico
        dynamicPanel.removeAll();
        dynamicPanel.add(director.getPanel());
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }

    // Métodos para acceder a los campos dinámicos
    public String getOrderAmount() {
        JTextField txtOrderAmount = (JTextField) getComponentByName("orderAmount");
        return txtOrderAmount != null ? txtOrderAmount.getText() : "";
    }

    public String getTax() {
        JTextField txtAdditionalTax = (JTextField) getComponentByName("additionalTax");
        return txtAdditionalTax != null ? txtAdditionalTax.getText() : "";
    }

    public String getSH() {
        JTextField txtAdditionalSH = (JTextField) getComponentByName("additionalSH");
        return txtAdditionalSH != null ? txtAdditionalSH.getText() : "";
    }

    private Component getComponentByName(String name) {
        for (Component comp : dynamicPanel.getComponents()) {
            if (comp instanceof JPanel) {
                for (Component innerComp : ((JPanel) comp).getComponents()) {
                    if (innerComp instanceof JTextField && name.equals(innerComp.getName())) {
                        return innerComp;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        OrderManager frame = new OrderManager();
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class ButtonHandler implements ActionListener {
    private OrderManager objOrderManager;

    public ButtonHandler(OrderManager objOrderManager) {
        this.objOrderManager = objOrderManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(OrderManager.EXIT)) {
            System.exit(0);
        } else if (command.equals(OrderManager.CREATE_ORDER)) {
            String orderType = (String) objOrderManager.cmbOrderType.getSelectedItem();
            String strOrderAmount = objOrderManager.getOrderAmount();
            String strTax = objOrderManager.getTax();
            String strSH = objOrderManager.getSH();

            double dblOrderAmount = strOrderAmount.isEmpty() ? 0.0 : Double.parseDouble(strOrderAmount);
            double dblTax = strTax.isEmpty() ? 0.0 : Double.parseDouble(strTax);
            double dblSH = strSH.isEmpty() ? 0.0 : Double.parseDouble(strSH);

            Order order = createOrder(orderType, dblOrderAmount, dblTax, dblSH);
            if (order != null) {
                order.accept(objOrderManager.objVisitor);
            }
        } else if (command.equals(OrderManager.GET_TOTAL)) {
            OrderIterator iterator = objOrderManager.objVisitor.getIterator();
            double total = getOrderTotal(iterator);
            JOptionPane.showMessageDialog(objOrderManager, "Total: " + total);
        }
    }

    private Order createOrder(String orderType, double orderAmount, double tax, double SH) {
        if (orderType.equals(OrderManager.CA_ORDER)) {
            return new CaliforniaOrder(orderAmount, tax);
        } else if (orderType.equals(OrderManager.NON_CA_ORDER)) {
            return new NonCaliforniaOrder(orderAmount);
        } else if (orderType.equals(OrderManager.OVERSEAS_ORDER)) {
            return new OverseasOrder(orderAmount, SH);
        }
        return null;
    }

    private double getOrderTotal(OrderIterator orders){
        double total = 0.0;
        orders.reset();
        while (orders.hasNext()) {
            Order o = orders.next();

            if (o instanceof CaliforniaOrder) {
                CaliforniaOrder co = (CaliforniaOrder) o;
                total += co.getOrderAmount() + co.getAdditionalTax();
            } else if (o instanceof OverseasOrder) {
                OverseasOrder oo = (OverseasOrder) o;
                total += oo.getOrderAmount() + oo.getAdditionalSH();
            } else if (o instanceof NonCaliforniaOrder) {
                NonCaliforniaOrder nco = (NonCaliforniaOrder) o;
                total += nco.getOrderAmount();
            }
        }

        return total;
    }
}

class BuilderFactory {
  public UIBuilder getUIBuilder(String str) {
      UIBuilder builder = null;
      if (str.equals(OrderManager.CA_ORDER)) {
          builder = new CaliforniaOrderUIBuilder();
      } else if (str.equals(OrderManager.NON_CA_ORDER)) {
          builder = new NonCaliforniaOrderUIBuilder();
      } else if (str.equals(OrderManager.OVERSEAS_ORDER)) {
          builder = new OverseasOrderUIBuilder();
      }
      return builder;
  }
}