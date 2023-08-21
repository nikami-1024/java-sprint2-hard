import java.util.HashMap;

public class Checker {

    public SaleManager saleManager;
    public DeliveryManager deliveryManager;

    public Checker(SaleManager saleManager, DeliveryManager deliveryManager) {
        this.saleManager = saleManager;
        this.deliveryManager = deliveryManager;
    }

    public boolean check() {
        // todo

        boolean check = true;

        // city -> title -> count
        HashMap<String, HashMap<String, Integer>> deliveredBySales = new HashMap<>();
        for (Sale sale : saleManager.sales) {
            if (!sale.isReceived) {
                continue;
            }
            if (!deliveredBySales.containsKey(sale.city)) {
                deliveredBySales.put(sale.city, new HashMap<>());
            }
            HashMap<String, Integer> titleToCount = deliveredBySales.get(sale.city);
            titleToCount.put(sale.title, titleToCount.getOrDefault(sale.title, 0) +
                    sale.count);
        }

        HashMap<String, HashMap<String, Integer>> deliveredByDelivery = new HashMap<>();
        for (Delivery delivery : deliveryManager.deliveries) {
            if (!deliveredByDelivery.containsKey(delivery.city)) {
                deliveredByDelivery.put(delivery.city, new HashMap<>());
            }
            HashMap<String, Integer> titleToCount = deliveredByDelivery.get(delivery.city);
            titleToCount.put(delivery.title, titleToCount.getOrDefault(delivery.title, 0) +
                    delivery.count);
        }

        for (String city : deliveredBySales.keySet()) {
            HashMap<String, Integer> titleToCountBySales = deliveredBySales.get(city);
            HashMap<String, Integer> titleToCountByDelivery = deliveredByDelivery.get(city);

            if (titleToCountByDelivery == null) {
                System.out.println("Город " + city + " есть в отчёте о продажах," +
                        " но нет в отчётах о доставке.");
                check = false;
                continue;
            }

            for (String title : titleToCountBySales.keySet()) {
                int countBySales = titleToCountBySales.get(title);
                int countByDelivery = titleToCountByDelivery.getOrDefault(title, 0);
                if (countBySales != countByDelivery) {
                    System.out.println("В городе " + city + " товар " + title +
                            " был продан по отчёту о продажах " + "в количестве " +
                            countBySales + " штук, а по отчёту о доставках в количестве " +
                            countByDelivery + " штук.");
                    check = false;
                }
            }
        }

        return check;
    }
}
