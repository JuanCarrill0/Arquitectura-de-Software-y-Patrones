package com.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;

public class OrderManager extends JFrame {
    public static final String GET_TOTAL = "Get Total";
    public static final String CREATE_ORDER = "Create Order";
    public static final String EXIT = "Exit";
    public static final String DELETE = "Delete Order";
    public static final String CA_ORDER = "California Order";
    public static final String NON_CA_ORDER = "Non-California Order";
    public static final String OVERSEAS_ORDER = "Overseas Order";
    public static final String CAN_ORDER = "Canadian Order";

    private JComboBox<String> cmbOrderType;
    private JPanel dynamicPanel;
    private UIDirector director;
    private OrderVisitor objVisitor;

    // Variables lista de ordenes
    private DefaultListModel<String> orderListModel;
    private JList<String> orderList;

    public OrderManager() {
        super("Visitor Pattern - Example");
        setLayout(new BorderLayout());

        // Crear el visitor (se asume que existe esta clase)
        objVisitor = new OrderVisitor();

        // Panel superior con el JComboBox
        JPanel topPanel = new JPanel();
        cmbOrderType = new JComboBox<>(new String[] { CA_ORDER, NON_CA_ORDER, OVERSEAS_ORDER, CAN_ORDER });
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
        JButton deleteButton = new JButton(DELETE);
        buttonPanel.add(getTotalButton);
        buttonPanel.add(createOrderButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Lista de ordenes
        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(orderList);
        add(scrollPane, BorderLayout.EAST);

        // Inicializar el director
        director = new UIDirector();

        ButtonHandler vf = new ButtonHandler(this);
        

        // Action listeners para los botones
        exitButton.addActionListener(vf);
        createOrderButton.addActionListener(vf);
        getTotalButton.addActionListener(vf);
        deleteButton.addActionListener(vf);
        cmbOrderType.addActionListener(vf);

        ListHandler lh = new ListHandler(this);
        // Listener a la lista de órdenes
        orderList.addListSelectionListener(lh);

        // Inicializar la UI con el primer tipo de orden
        updateUI();
    }

    public void updateUI() {
        String selected = (String) cmbOrderType.getSelectedItem();
        // Uso de fabrica
        BuilderFactory factory = new BuilderFactory();
        UIBuilder builder = factory.getUIBuilder(selected);

        // Seleccion de builder
        director.setBuilder(builder);
        director.constructUI();

        // Limpiar y actualizar el panel dinámico
        dynamicPanel.removeAll();
        dynamicPanel.add(director.getPanel());
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }

    // Actualizar el JComboBox según la selección de la lista de órdenes
    public void updateOrderTypeComboFromSelection() {
        int selectedIndex = orderList.getSelectedIndex();
        if (selectedIndex >= 0) {

            OrderIterator iterator = objVisitor.getIterator();
            int currentIndex = 0;
            Order selectedOrder = null;
            iterator.reset();
            while (iterator.hasNext()) {
                Order current = iterator.next();
                if (currentIndex == selectedIndex) {
                    selectedOrder = current;
                    break;
                }
                currentIndex++;
            }
            if (selectedOrder instanceof CaliforniaOrder) {
                cmbOrderType.setSelectedItem(CA_ORDER);
            } else if (selectedOrder instanceof NonCaliforniaOrder) {
                cmbOrderType.setSelectedItem(NON_CA_ORDER);
            } else if (selectedOrder instanceof OverseasOrder) {
                cmbOrderType.setSelectedItem(OVERSEAS_ORDER);
            } else if (selectedOrder instanceof CanadianOrder) {
                cmbOrderType.setSelectedItem(CAN_ORDER);
            }
        }
    }

    // Actualizar la lista de órdenes
    public void refreshOrderList() {
        orderListModel.clear();
        OrderIterator orders = objVisitor.getIterator();
        orders.reset();
        while (orders.hasNext()) {
            Order order = orders.next();
            orderListModel.addElement(order.toString());
        }
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

    public JComboBox getOrderTypeCtrl() {
        return cmbOrderType;
    }

    public JList<String> getOrderList() {
        return orderList;
    }

    public OrderVisitor getVisitor() {
        return objVisitor;
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

class ListHandler implements ListSelectionListener {
    private OrderManager objOrderManager;

    public ListHandler(OrderManager objOrderManager) {
        this.objOrderManager = objOrderManager;
    }

    public void valueChanged(ListSelectionEvent e) {
      objOrderManager.updateOrderTypeComboFromSelection();
    }

  }
class ButtonHandler implements ActionListener {
    private OrderManager objOrderManager;

    public ButtonHandler(OrderManager objOrderManager) {
        this.objOrderManager = objOrderManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(OrderManager.EXIT)) {
            System.exit(0);
        }
        if (e.getActionCommand().equals(OrderManager.CREATE_ORDER)) {
            String orderType = (String) objOrderManager.getOrderTypeCtrl().getSelectedItem();
            String strOrderAmount = objOrderManager.getOrderAmount();
            String strTax = objOrderManager.getTax();
            String strSH = objOrderManager.getSH();

            double dblOrderAmount = strOrderAmount.isEmpty() ? 0.0 : Double.parseDouble(strOrderAmount);
            double dblTax = strTax.isEmpty() ? 0.0 : Double.parseDouble(strTax);
            double dblSH = strSH.isEmpty() ? 0.0 : Double.parseDouble(strSH);

            Order order = createOrder(orderType, dblOrderAmount, dblTax, dblSH);
            if (order != null) {
                order.accept(objOrderManager.getVisitor());
                objOrderManager.refreshOrderList();
            }
        }
        if (e.getActionCommand().equals(OrderManager.GET_TOTAL)) {
            double total = getOrderTotal(objOrderManager.getVisitor().getIterator());
            JOptionPane.showMessageDialog(objOrderManager, "Total: " + total);
        }
        if (e.getActionCommand().equals(OrderManager.DELETE)) {
            int selectedIndex = objOrderManager.getOrderList().getSelectedIndex();
            if (selectedIndex != -1) {
                OrderIterator iterator = objOrderManager.getVisitor().getIterator();
                iterator.reset();
                int currentIndex = 0;
                while (iterator.hasNext()) {
                    iterator.next();
                    if (currentIndex == selectedIndex) {
                        iterator.remove();
                        break;
                    }
                    currentIndex++;
                }

                objOrderManager.refreshOrderList();
            }
        }
        if(e.getSource() == objOrderManager.getOrderTypeCtrl()) {
            objOrderManager.updateUI();
        }
        if(e.getSource() == objOrderManager.getOrderList()) {
            objOrderManager.updateOrderTypeComboFromSelection();
        }
    }

    private Order createOrder(String orderType, double orderAmount, double tax, double SH) {
        if (orderType.equals(OrderManager.CA_ORDER)) {
            return new CaliforniaOrder(orderAmount, tax);
        } else if (orderType.equals(OrderManager.NON_CA_ORDER)) {
            return new NonCaliforniaOrder(orderAmount);
        } else if (orderType.equals(OrderManager.OVERSEAS_ORDER)) {
            return new OverseasOrder(orderAmount, SH);
        } else if (orderType.equals(OrderManager.CAN_ORDER)) {
            return new CanadianOrder(orderAmount);
        }
        return null;
    }

    private double getOrderTotal(OrderIterator orders) {
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
            } else if (o instanceof CanadianOrder) {
                CanadianOrder ca = (CanadianOrder) o;
                total += ca.getOrderAmount();
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
        } else if (str.equals(OrderManager.CAN_ORDER)) {
            builder = new CanadianOrderUIBuilder();
        }
        return builder;
    }
}