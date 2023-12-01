import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class HW6 {


    static class Notebook {
        HashMap<String, Integer> features;

        public Notebook(HashMap<String, Integer> features) {
            this.features = features;
        }

        public boolean match(HashMap<String, Integer> criteria)  {
            for (Entry<String, Integer> it : criteria.entrySet()) {
                String key = it.getKey();
                int val = it.getValue();
                if (!matchSingleFeature(key, val)) {
                    return false;
                }
            }
            return true;
        }

        public boolean matchSingleFeature(String key, int val) {
            Integer feat = this.features.getOrDefault(key, null);
            if (feat == null) {
                return false;
            }
            if (feat < val) {
                return false;
            }
            return true;
        }
        public String toString() {
            return features.toString();
        }
    }
    
    static Notebook newNotebook(int memory, int hdd, int display, int color, int os) {
        HashMap<String, Integer> f = new HashMap<String, Integer>();
        f.put("memory", memory);
        f.put("hdd", hdd);
        f.put("display", display);
        f.put("color", color);
        f.put("os", os);
        return new HW6.Notebook(f);
    }

    static List<String> features() {
        return Arrays.asList(new String[] {"memory", "hdd", "display", "color", "os"});
    }

    static ArrayList<Notebook> notebooks() {
        ArrayList<Notebook> n = new ArrayList<Notebook>();
        for (Integer mem: Arrays.asList(1024, 2048, 4096))  {
            for (Integer hdd: Arrays.asList(10, 15, 20, 25, 30)) {
                for (Integer display: Arrays.asList(14, 15, 17)) {
                    for (Integer color: Arrays.asList(0, 1, 2, 3)) {
                        for (Integer os: Arrays.asList(0, 1)) {
                            n.add(newNotebook(mem, hdd, display, color, os));
                        }
                    }
                }
            }
        }

        return n;
    }
    public static void main(String[] args) {
        ArrayList<Notebook> notebooks = notebooks();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> criteria = new HashMap<>();
        List<String> features = features();
        boolean end = false;
        try {
            while (!end) {
                System.out.println(String.format("Current criteria is: %s", criteria));
                System.out.println(String.format("Press 'a' to add new criteria or 'f' to run search: ", criteria));
                String line = input.readLine();
                switch (line) {
                    case "a":
                        System.out.println(String.format("Select feature %s:", features));
                        String feat = input.readLine();
                        System.out.println("Minimum value:");
                        Integer value = Integer.parseInt(input.readLine());
                        criteria.put(feat, value);
                        break;
                    case "f":
                        System.out.println("Matched notebooks:");
                        for (Notebook n: notebooks) {
                            if (n.match(criteria)) {
                                System.out.println(n);
                            }
                        }
                        end = true;
                        break;
                    default:
                        System.out.println(String.format("Unsupported command: %s, try again", line));
                        break;
                }
                
            }
        }
        catch (Exception e) {
            System.out.println(String.format("exception: %s", e));
        }

    }
}


