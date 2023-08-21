import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class SaleManager {
    public ArrayList<Sale> sales = new ArrayList<>();

    public SaleManager(String path) {
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i]; // title,count,price,city,received
            String[] parts = line.split(",");
            String title = parts[0];
            int count = Integer.parseInt(parts[1]);
            int price = Integer.parseInt(parts[2]);
            String city = parts[3];
            boolean isReceived = Boolean.parseBoolean(parts[4]);

            Sale sale = new Sale(title, count, price, city, isReceived);
            sales.add(sale);
        }
    }

    public String getTopProduct() {
        HashMap<String, Integer> freqs = new HashMap<>();
        for (Sale sale : sales) {
            freqs.put(sale.title, freqs.getOrDefault(sale.title, 0) + sale.count);
        }
        String maxTitle = null;
        for (String title : freqs.keySet()) {
            if (maxTitle == null) {
                maxTitle = title;
                continue;
            }
            if (freqs.get(maxTitle) < freqs.get(title)) {
                maxTitle = title;
            }
        }
        return maxTitle;
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
