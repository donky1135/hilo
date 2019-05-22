
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
public class main
        {
            public static Scanner k = new Scanner(System.in);
            public static void main(String args[]) throws IOException {
                System.out.println("Put in your name");
                String name = k.next();
                scoreStore(game(), name);
            }
            public static int game(){
                Random r = new Random();
                int game = r.nextInt(100);
                System.out.println("guess my number between 1 & 100, you win by having the least tries");
                int guess = k.nextInt();
                int guesses = 0;
                while(guess != game){
                    System.out.println((guess > game)?("A little high"):("A little low"));
                    guess = k.nextInt();
                    guesses++;
                }
                System.out.println("You got it done in " + guesses + " tries");
                return guesses;
            }
            public static void scoreStore (int Sc, String name)throws IOException{

                File file = new File("/tmp/data.ser");

                TreeMap<Integer,String> map = new TreeMap<Integer, String>();

                ObjectMapper mapin = new ObjectMapper();

                ObjectWriter writin = mapin.writer(new DefaultPrettyPrinter());

                TypeReference tr = new TypeReference<TreeMap<Integer, String>>() {};

                if(!file.exists()){

                    map.put(Sc, name);

                    YouScore(map);

                    writin.writeValue(file, map);

                }

                else{
                    map = mapin.readValue(file, tr);

                    map.put(Sc, name);

                    YouScore(map);

                    writin.writeValue(file, map);

                }
            }

            public static void YouScore(TreeMap<Integer, String> map){

                Set<Integer> keys= map.keySet();

                int[] topg = new int[keys.size()];

                int index = 0;

                for (Integer el: keys) topg[index++] = el;

                System.out.println("Top " + ((topg.length < 4)?(topg.length):(5)) + " players");

                for (int x = 0; x <= ((topg.length-1 < 4)?(topg.length-1):(4)); x++){

                    System.out.println(map.get(topg[x]) + " : " + topg[x]);

                }
            }
        }
