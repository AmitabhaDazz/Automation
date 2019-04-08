/*
 * package Recon.ReconAutomation.raw;
 * 
 * import java.io.BufferedReader; import java.io.File; import
 * java.io.FileInputStream; import java.io.FileNotFoundException; import
 * java.io.FileReader; import java.io.IOException;
 * 
 * public class RawFileProcessing {
 * 
 * public static void main(String[] args) { BufferedReader reader = null; try {
 * reader = new BufferedReader(new FileReader("F:\\Raw\\new.xlsx")); String line
 * = null; while ((line = reader.readLine()) != null) { String[] words =
 * line.split(",");
 * System.out.println("[SubscriptionId= "\" + words[0] + "\", Discoverer = \"" +
 * words[1] + "\", Country = \"" + words[2] + "\"]"); } } catch
 * (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) {
 * e.printStackTrace(); } finally { if (reader != null) { try { reader.close();
 * } catch (IOException e) { e.printStackTrace(); } } } } }
 */