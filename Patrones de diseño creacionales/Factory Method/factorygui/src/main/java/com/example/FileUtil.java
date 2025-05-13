package com.example;

import java.io.*;
import java.util.*;

public class FileUtil {

  DataOutputStream dos;

  /*
       Método utilitario para escribir un texto dado en un archivo
  */
  public boolean writeToFile(String fileName, String dataLine,
      boolean isAppendMode, boolean isNewLine) {
    if (isNewLine) {
      dataLine = "\n" + dataLine;
    }

    try {
      File outFile = new File(fileName);
      if (isAppendMode) {
        dos = new DataOutputStream(
                new FileOutputStream(fileName, true));
      } else {
        dos = new DataOutputStream(
                new FileOutputStream(outFile));
      }

      dos.writeBytes(dataLine);
      dos.close();
    } catch (FileNotFoundException ex) {
      return (false);
    }
    catch (IOException ex) {
      return (false);
    }
    return (true);

  }

  /*
        Lee datos de un archivo dado
   */
  public String readFromFile(String fileName) {
    String DataLine = "";
    try {
      File inFile = new File(fileName);
      BufferedReader br = new BufferedReader(
                            new InputStreamReader(
                              new FileInputStream(inFile)));

      DataLine = br.readLine();
      br.close();
    } catch (FileNotFoundException ex) {
      return (null);
    }
    catch (IOException ex) {
      return (null);
    }
    return (DataLine);

  }

  // Método para verificar si un archivo existe
  public boolean isFileExists(String fileName) {
    File file = new File(fileName);
    return file.exists();
  }

  // Método para eliminar un archivo
  public boolean deleteFile(String fileName) {
    File file = new File(fileName);
    return file.delete();
  }

  /*
        Lee datos de un archivo dado en un Vector
   */
  public Vector fileToVector(String fileName) {
    Vector v = new Vector();
    String inputLine;
    try {
      File inFile = new File(fileName);
      BufferedReader br = new BufferedReader(
                            new InputStreamReader(
                              new FileInputStream(inFile)));

      while ((inputLine = br.readLine()) != null) {
        v.addElement(inputLine.trim());
      }
      br.close();
    } // Try
    catch (FileNotFoundException ex) {
      //
    }
    catch (IOException ex) {
      //
    }
    return (v);
  }

  /*
        Escribe datos de un vector de entrada en un archivo dado
   */
  public void vectorToFile(Vector v, String fileName) {
    for (int i = 0; i < v.size(); i++) {
      writeToFile(fileName, (String) v.elementAt(i), true,
                  true);
    }
  }

  /*
        Copia filas únicas de un archivo fuente a un archivo de destino
   */
  public void copyUniqueElements(String sourceFile,
      String resultFile) {
    Vector v = fileToVector(sourceFile);
    v = MiscUtil.removeDuplicates(v);
    vectorToFile(v, resultFile);
  }

} // fin de la clase FileUtil