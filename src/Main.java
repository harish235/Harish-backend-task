import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.*;
import java.io.FileWriter;


public class Main {
    // Main driver method
    public static void main(String[] args)
            throws FileNotFoundException
    {

        String path = "/Users/harish/IdeaProjects/Employee/employees.txt";
        int count = 0;
        InputStream is = new FileInputStream(path);
        HashMap<String, String> dev
                = new HashMap<String, String>();
        HashMap<String, String> qa
                = new HashMap<String, String>();
        HashMap<String, String> manager
                = new HashMap<String, String>();
        try (Scanner sc = new Scanner(
                is, StandardCharsets.UTF_8.name())) {

            while (sc.hasNextLine()) {
                count++;
                if (count <= 1) {

                    sc.nextLine ();
                    continue;
                }
                String curr_line = sc.nextLine();
                System.out.println(curr_line);
                String[] entities = curr_line.split("\\s*,\\s*");
//                for(String name : entities){
//                    System.out.println(name);
//                }
                String id = entities[0];
                id = id.replaceAll("\\s", "");
                String full_name = entities[1]+ " "+entities[2];
                System.out.println(full_name);
                String des = entities[5];
                des = des.substring(0, des.length() - 1);


                if(des.equals("DEV")){
                    dev.put(id, full_name);
                }
                else if(des.equals("MANAGER")){
                    manager.put(id, full_name);
                }
                else if(des.equals("QA")){
                    qa.put(id, full_name);
                }
            }
            File devFile = new File("/Users/harish/IdeaProjects/Employee/dev.txt");
            File qaFile = new File("/Users/harish/IdeaProjects/Employee/qa.txt");
            File managerFile = new File("/Users/harish/IdeaProjects/Employee/manager.txt");

            BufferedWriter bDev = null;
            BufferedWriter bQa = null;
            BufferedWriter bManager = null;

            try {

                // create new BufferedWriter for the output file
                bDev = new BufferedWriter(new FileWriter(devFile));
                bQa = new BufferedWriter(new FileWriter(qaFile));
                bManager = new BufferedWriter(new FileWriter(managerFile));

                // iterate map entries
                for (Map.Entry<String, String> entry :
                        dev.entrySet()) {

                    // put key and value separated by a colon
                    bDev.write(entry.getKey() + "\t:\t"
                            + entry.getValue());

                    // new line
                    bDev.newLine();
                }

                bDev.flush();
                for (Map.Entry<String, String> entry :
                        manager.entrySet()) {

                    // put key and value separated by a colon
                    bManager.write(entry.getKey() + "\t:\t"
                            + entry.getValue());

                    // new line
                    bManager.newLine();
                }

                bManager.flush();

                for (Map.Entry<String, String> entry :
                        qa.entrySet()) {

                    // put key and value separated by a colon
                    bQa.write(entry.getKey() + "\t:\t"
                            + entry.getValue());

                    // new line
                    bQa.newLine();
                }

                bQa.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {

                try {

                    // always close the writer
                    bDev.close();
                }
                catch (Exception e) {
                }
            }


        }
    }
}

