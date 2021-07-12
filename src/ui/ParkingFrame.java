package ui;

import dao.MovementDao;
import model.Movement;
import util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class ParkingFrame {

    private static final String FILE_PATH = "/home/erick/Estudos/Senai/Semestre 1/workspaces/intellij/ProjetoEstacionamento/src/resources/prices.ds1";

    JTextField licensePlateField;
    JTextField modelField;

    Color greenColor = new Color(98, 171, 55);
    Color blackColor = new Color(3, 25, 30);
    Color blueColor = new Color(79, 134, 198);
    Color redColor = new Color(191, 33, 30);

    Font fontTitle = new Font("Roboto", Font.BOLD, 18);
    Font fontSubtitle = new Font("Roboto", Font.BOLD, 12);
    Font fontBigger = new Font("Roboto", Font.BOLD, 12);

    JTextField vehicleModelField;
    JTextField entranceDateField;
    JTextField entranceHourField;
    JTextField exitDateField;
    JTextField exitHourField;
    JTextField timeField;
    JTextField priceField;

    public void createScreen() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Estaciona Bem");
        frame.setSize(700, 585);
        frame.setLayout(null);

        JLabel entranceLabel = new JLabel();
        entranceLabel.setText("ENTRADA");
        entranceLabel.setBounds(40, 40, 100, 20);
        entranceLabel.setForeground(greenColor);
        entranceLabel.setFont(fontTitle);

        JLabel entranceLicensePlateLabel = new JLabel();
        entranceLicensePlateLabel.setText("PLACA:");
        entranceLicensePlateLabel.setBounds(40, 70, 60, 14);
        entranceLicensePlateLabel.setForeground(blackColor);
        entranceLicensePlateLabel.setFont(fontSubtitle);

        licensePlateField = new JTextField();
        licensePlateField.setBounds(40, 90, 240, 30);

        JLabel modelLabel = new JLabel();
        modelLabel.setText("MODELO:");
        modelLabel.setBounds(290, 70, 60, 14);
        modelLabel.setForeground(blackColor);
        modelLabel.setFont(fontSubtitle);

        modelField = new JTextField();
        modelField.setBounds(290, 90, 240, 30);

        JButton entranceButton = new JButton();

        entranceButton.setText("ENTRAR");
        entranceButton.setBounds(550, 90, 110, 30);
        entranceButton.setBackground(blueColor);
        entranceButton.setForeground(Color.WHITE);
        entranceButton.setFont(fontSubtitle);

        /* --------------------------------------------------------------------------------- */

        DefaultTableModel vehiclesTableModel = new DefaultTableModel();
        vehiclesTableModel.addColumn("CÓDIGO");
        vehiclesTableModel.addColumn("PLACA");
        vehiclesTableModel.addColumn("MODELO");
        vehiclesTableModel.addColumn("DATA ENTRADA");

        MovementDao dao = new MovementDao();
        ArrayList<Movement> movements = dao.listMovements();

        for(Movement c : movements) {
            ZonedDateTime entranceResult = ZonedDateTime.parse(c.getEntranceDate() + "+03:00", DateTimeFormatter.ISO_DATE_TIME);
            LocalDateTime entranceDate = entranceResult.toLocalDateTime();

            String[] clientConstructor = {c.getID(), c.getLicensePlate(), c.getModel(), Util.formatDate(entranceDate)};
            vehiclesTableModel.addRow(clientConstructor);
        }

        JTable vehiclesTable = new JTable(vehiclesTableModel);
        vehiclesTable.getTableHeader().setReorderingAllowed(false);
        vehiclesTable.setEnabled(false);

        JScrollPane vehiclesTableScroll = new JScrollPane(vehiclesTable);
        vehiclesTableScroll.setBounds(40, 130, 620, 150);

        /* --------------------------------------------------------------------------------- */

        JLabel exitLabel = new JLabel();
        exitLabel.setText("SAÍDA");
        exitLabel.setBounds(40, 290, 100, 20);
        exitLabel.setForeground(greenColor);
        exitLabel.setFont(fontTitle);

        JLabel exitLicensePlateLabel = new JLabel();
        exitLicensePlateLabel.setText("PLACA:");
        exitLicensePlateLabel.setBounds(40, 320, 60, 14);
        exitLicensePlateLabel.setForeground(blackColor);
        exitLicensePlateLabel.setFont(fontSubtitle);

        JTextField exitLicensePlateField = new JTextField();
        exitLicensePlateField.setBounds(40, 340, 115, 30);

        JButton searchButton = new JButton();
        searchButton.setText("BUSCAR");
        searchButton.setBounds(170, 340, 110, 30);
        searchButton.setBackground(blueColor);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(fontSubtitle);

        JLabel status = new JLabel();
        status.setText("Placa não encontrada");
        status.setBounds(300, 340, 200, 30);
        status.setForeground(redColor);
        status.setFont(fontSubtitle);
        status.setVisible(false);

        JLabel vehicleModelLabel = new JLabel();
        vehicleModelLabel.setText("MODELO:");
        vehicleModelLabel.setBounds(40, 380, 60, 14);
        vehicleModelLabel.setForeground(blackColor);
        vehicleModelLabel.setFont(fontSubtitle);

        vehicleModelField = new JTextField();
        vehicleModelField.setBounds(40, 400, 110, 30);
        vehicleModelField.setEditable(false);

        JLabel entranceDateLabel = new JLabel();
        entranceDateLabel.setText("DATA ENTRADA:");
        entranceDateLabel.setBounds(155, 380, 100, 14);
        entranceDateLabel.setForeground(blackColor);
        entranceDateLabel.setFont(fontSubtitle);

        entranceDateField = new JTextField();
        entranceDateField.setBounds(155, 400, 110, 30);
        entranceDateField.setEditable(false);

        JLabel entranceHourLabel = new JLabel();
        entranceHourLabel.setText("HORA ENTRADA:");
        entranceHourLabel.setBounds(270, 380, 100, 14);
        entranceHourLabel.setForeground(blackColor);
        entranceHourLabel.setFont(fontSubtitle);

        entranceHourField = new JTextField();
        entranceHourField.setBounds(270, 400, 110, 30);
        entranceHourField.setEditable(false);

        JLabel exitDateLabel = new JLabel();
        exitDateLabel.setText("DATA SAÍDA:");
        exitDateLabel.setBounds(385, 380, 100, 14);
        exitDateLabel.setForeground(blackColor);
        exitDateLabel.setFont(fontSubtitle);

        exitDateField = new JTextField();
        exitDateField.setBounds(385, 400, 110, 30);
        exitDateField.setEditable(false);

        JLabel exitHourLabel = new JLabel();
        exitHourLabel.setText("HORA SAÍDA:");
        exitHourLabel.setBounds(500, 380, 100, 14);
        exitHourLabel.setForeground(blackColor);
        exitHourLabel.setFont(fontSubtitle);

        exitHourField = new JTextField();
        exitHourField.setBounds(500, 400, 110, 30);
        exitHourField.setEditable(false);

        JLabel timeLabel = new JLabel();
        timeLabel.setText("TEMPO:");
        timeLabel.setBounds(615, 380, 60, 14);
        timeLabel.setForeground(blackColor);
        timeLabel.setFont(fontSubtitle);

        timeField = new JTextField();
        timeField.setBounds(615, 400, 45, 30);
        timeField.setEditable(false);

        JLabel priceLabel = new JLabel();
        priceLabel.setText("VALOR A PAGAR:");
        priceLabel.setBounds(40, 470, 100, 14);
        priceLabel.setForeground(blackColor);
        priceLabel.setFont(fontSubtitle);

        priceField = new JTextField();
        priceField.setBounds(150, 452, 170, 50);
        priceField.setEditable(false);

        JButton sendButton = new JButton();
        sendButton.setText("EFETUAR SAÍDA");
        sendButton.setBounds(350, 452, 150, 50);
        sendButton.setBackground(blueColor);
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(fontSubtitle);

        JButton closeButton = new JButton();
        closeButton.setText("FECHAR SISTEMA");
        closeButton.setBounds(510, 452, 150, 50);
        closeButton.setBackground(redColor);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(fontSubtitle);

        frame.getContentPane().add(entranceLabel);
        frame.getContentPane().add(entranceLicensePlateLabel);
        frame.getContentPane().add(licensePlateField);
        frame.getContentPane().add(modelLabel);
        frame.getContentPane().add(modelField);
        frame.getContentPane().add(entranceButton);
        frame.getContentPane().add(vehiclesTableScroll);
        frame.getContentPane().add(exitLabel);
        frame.getContentPane().add(exitLicensePlateLabel);
        frame.getContentPane().add(exitLicensePlateField);
        frame.getContentPane().add(searchButton);
        frame.getContentPane().add(status);
        frame.getContentPane().add(vehicleModelLabel);
        frame.getContentPane().add(vehicleModelField);
        frame.getContentPane().add(entranceDateLabel);
        frame.getContentPane().add(entranceDateField);
        frame.getContentPane().add(entranceHourLabel);
        frame.getContentPane().add(entranceHourField);
        frame.getContentPane().add(exitDateLabel);
        frame.getContentPane().add(exitDateField);
        frame.getContentPane().add(exitHourLabel);
        frame.getContentPane().add(exitHourField);
        frame.getContentPane().add(timeLabel);
        frame.getContentPane().add(timeField);
        frame.getContentPane().add(priceLabel);
        frame.getContentPane().add(priceField);
        frame.getContentPane().add(sendButton);
        frame.getContentPane().add(closeButton);

        frame.setVisible(true);

        entranceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(validateForm() == true) {
                    Movement movement = new Movement();
                    movement.setID(Util.generateID());
                    movement.setLicensePlate(licensePlateField.getText());
                    movement.setModel(modelField.getText());
                    movement.setEntranceDate(LocalDateTime.now());

                    MovementDao dao = new MovementDao(movement);
                    dao.registerMovement();

                    JOptionPane.showMessageDialog(null, "O veículo " + movement.getLicensePlate() + "\nfoi cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    clearForm();
                    vehiclesTableModel.addRow(new String[]{movement.getID(), movement.getLicensePlate(), movement.getModel(), Util.formatDate(ZonedDateTime.parse(movement.getEntranceDate() + "+03:00", DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime())});
                } else {
                    JOptionPane.showMessageDialog(null, "Você deve preencher todos os campos.", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(exitLicensePlateField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Você deve inserir a placa para a busca.", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    MovementDao dao = new MovementDao();
                    try {
                        String[] movementVector = dao.searchMovement(exitLicensePlateField.getText()).toStringEntrance().split(";");

                        String entranceHour = Util.formatHour(ZonedDateTime.parse(movementVector[3], DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime());
                        String exitHour = Util.formatHour(LocalDateTime.now());

                        status.setVisible(false);
                        vehicleModelField.setText(movementVector[2]);
                        entranceDateField.setText(Util.formatDate(ZonedDateTime.parse(movementVector[3], DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime()));
                        entranceHourField.setText(entranceHour);
                        exitDateField.setText(Util.formatDate(LocalDateTime.now()));
                        exitHourField.setText(exitHour);

                        int entranceMinutes = Integer.parseInt(entranceHour.split(":")[0]) * 60 + Integer.parseInt(entranceHour.split(":")[1]);
                        int exitMinutes = Integer.parseInt(exitHour.split(":")[0]) * 60 + Integer.parseInt(exitHour.split(":")[1]);
                        int totalMinutes = exitMinutes - entranceMinutes;
                        int minutes = totalMinutes % 60;
                        int hours = (totalMinutes - minutes) / 60;
                        String time = totalMinutes > 60 ?  hours + "h" + minutes + "m" : totalMinutes + "m";
                        timeField.setText(time);

                        Path path = Paths.get(FILE_PATH);
                        BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
                        String line = reader.readLine();
                        int firstHourPrice = Integer.parseInt(line.split(":")[1]);
                        line = reader.readLine();
                        int otherHoursPrice = Integer.parseInt(line.split(":")[1]);
                        line = reader.readLine();
                        int toleranceMinutes = Integer.parseInt(line.split(":")[1]);
                        int price = hours == 1 && minutes <= toleranceMinutes || hours == 0 ? firstHourPrice : firstHourPrice + (hours - 1) * otherHoursPrice + (minutes > toleranceMinutes ? otherHoursPrice : 0);
                        priceField.setText("R$" + String.valueOf(price));
                    } catch(Exception e) {
                        e.printStackTrace();
                        status.setVisible(true);
                    }
                }
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MovementDao dao = new MovementDao();
                Movement movement = dao.searchMovement(exitLicensePlateField.getText());
                movement.setExitDate(LocalDateTime.now());
                movement.setPrice(Double.parseDouble(priceField.getText().replace("R$", "")));
                movement.setTime(timeField.getText());
                dao.registerMovementExit(movement);
                JOptionPane.showMessageDialog(null, "A saída do veículo " + movement.getLicensePlate() + "\nfoi efetuada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                frame.setVisible(false);
            }
        });
    }

    private void clearForm() {
        licensePlateField.setText("");
        modelField.setText("");

        vehicleModelField.setText("");
        entranceDateField.setText("");
        entranceHourField.setText("");
        exitDateField.setText("");
        exitHourField.setText("");
        timeField.setText("");
        priceField.setText("");

        licensePlateField.requestFocus();
    }

    private boolean validateForm() {
        boolean valid = true;

        if(licensePlateField.getText().trim().length() == 0 || modelField.getText().trim().length() == 0) {
            valid = false;
        }

        return valid;
    }
}
