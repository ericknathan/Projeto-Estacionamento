package dao;

import model.Movement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class MovementDao {

    private Movement movement;
    private static final String FILE_PATH = "/home/erick/Estudos/Senai/Semestre 1/workspaces/intellij/ProjetoEstacionamento/src/resources/movements.ds1";

    public MovementDao() {}
    public MovementDao(Movement movement) {
        this.movement = movement;
    }

    public void registerMovement() {

        Path path = Paths.get(FILE_PATH);

        try {
            BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.WRITE, StandardOpenOption.APPEND);

            writer.newLine();
            writer.write(movement.toStringEntrance());
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void registerMovementExit(Movement movement) {
        Path path = Paths.get(FILE_PATH);

        try {
            BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.WRITE, StandardOpenOption.APPEND);

            writer.newLine();
            writer.write(movement.toStringExit());
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Movement> listMovements() {

        Path path = Paths.get(FILE_PATH);

        try {
            BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));

            String line = reader.readLine();
            ArrayList<Movement> movements = new ArrayList<>();

            while (line != null) {
                String[] movementVector = line.split(";");
                Movement movement = new Movement();

                ZonedDateTime entranceResult = ZonedDateTime.parse(movementVector[3], DateTimeFormatter.ISO_DATE_TIME);
                LocalDateTime entranceDate = entranceResult.toLocalDateTime();

                movement.setID(movementVector[0]);
                movement.setLicensePlate(movementVector[1]);
                movement.setModel(movementVector[2]);
                movement.setEntranceDate(entranceDate);

                movements.add(movement);
                line = reader.readLine();
            }

            reader.close();
            return movements;
        } catch (IOException ex) {
            System.out.println("Não foi possível encontrar o arquivo específicado!");
            ex.printStackTrace();
            return null;
        }
    }

    public Movement searchMovement(String licensePlate) {

        Path path = Paths.get(FILE_PATH);

        try {
            BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));

            String line = reader.readLine();
            movement = new Movement();

            while (line != null) {
                String[] movementVector = line.split(";");

                if(movementVector[1].equals(licensePlate.toUpperCase().replaceAll("-", "").replaceAll(" ", ""))) {
                    ZonedDateTime entranceResult = ZonedDateTime.parse(movementVector[3], DateTimeFormatter.ISO_DATE_TIME);
                    LocalDateTime entranceDate = entranceResult.toLocalDateTime();
                    LocalDateTime exitDate = null;

                    movement.setID(movementVector[0]);
                    movement.setLicensePlate(movementVector[1]);
                    movement.setModel(movementVector[2]);
                    movement.setEntranceDate(entranceDate);
                    //ZonedDateTime exitResult = ZonedDateTime.parse(movementVector[4], DateTimeFormatter.ISO_DATE_TIME);
                    //movement.setExitDate(exitResult.toLocalDateTime());
                    //movement.setTime(movementVector.length >= 6 ? movementVector[7] : "00:00");
                    //movement.setPrice(movementVector.length >= 7 ? Double.parseDouble(movementVector[8]) : 0.0);
                    break;
                }

                line = reader.readLine();
            }

            reader.close();
            return movement;
        } catch (IOException ex) {
            System.out.println("Não foi possível encontrar o arquivo específicado!");
            ex.printStackTrace();
            return null;
        }
    }
}