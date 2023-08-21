import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DeliveryManager {
    public ArrayList<Delivery> deliveries = new ArrayList<>();

    public void loadFile(String city, String path) {
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i]; // year,month,day,title,count
            String[] parts = line.split(",");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            String title = parts[3];
            int count = Integer.parseInt(parts[4]);

            Delivery delivery = new Delivery(title, count, year, month, day, city);
            deliveries.add(delivery);
        }
    }

    public String topCity(int month) {
        HashMap<String, Integer> freqs = new HashMap<>();
        for (Delivery delivery : deliveries) {
            if (delivery.month != month) {
                continue;
            }
            freqs.put(delivery.city, freqs.getOrDefault(delivery.city, 0) + delivery.count);
        }
        String cityMax = null;
        for (String city : freqs.keySet()) {
            if (cityMax == null) {
                cityMax = city;
                continue;
            }
            if (freqs.get(cityMax) < freqs.get(city)) {
                cityMax = city;
            }
        }
        return cityMax;
    }

    public String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом.");
            return null;
        }
    }
}
