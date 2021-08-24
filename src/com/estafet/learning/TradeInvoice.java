package com.estafet.learning;

import java.lang.reflect.Field;
import java.util.*;
import java.io.*;

public class TradeInvoice extends Invoice {
    public Random rand = new Random();
    public Map<String, Double> articlesToPass = new LinkedHashMap<String, Double>();


    //public static Map<String, Double> articlesDefaultMap = new LinkedHashMap<String, Double>();
    public static Map<String, Double> articlesDefaultMapMutable = Map.of("NZXT H510", 74.99, "AMD Ryzen 5 5700G", 369.99, "Asus ROG Strix B550-E Gaming",
                209.07, "Nvidia GeForce RTX 3060 Ti", 2899.99, "Corsair Vengeance LPX 16GB (2x8GB) DDR4-3200", 77.99,
                "Addlink S70 512GB NVMe SSD", 84.99, "WD Black 1TB", 69.99, "Corsair TX650M 650W", 129.99);
    public static Map<String, Double> articlesDefaultMap = new LinkedHashMap<>(articlesDefaultMapMutable);

    static
    {
/*
    articlesDefaultMap = Map.of("NZXT H510", 74.99, "AMD Ryzen 5 5700G", 369.99, "Asus ROG Strix B550-E Gaming",
            209.07, "Nvidia GeForce RTX 3060 Ti", 2899.99, "Corsair Vengeance LPX 16GB (2x8GB) DDR4-3200", 77.99,
            "Addlink S70 512GB NVMe SSD", 84.99, "WD Black 1TB", 69.99, "Corsair TX650M 650W", 129.99);
*/
    }

    // cannot reference here because first the superconstructor will be called. Cannot pass null for articles. Pass the list from the main program!
    TradeInvoice() {
        //super(19555, "Invoice details goes here.", null, 3.14);
        //System.out.println("I am a TradeInvoice() constructor.");
    }


    public void generateRandomTradeInvoiceData()
    {
        double passTotalAmountBeforeVAT = 0;

        int invoiceNumber = rand.nextInt(27000 - 19000) + 19000;
        setInvoiceNumber(invoiceNumber);
        String clientDetailsToPass = UUID.randomUUID().toString();
        setClientDetails(clientDetailsToPass);

        // not preciese, because if we have overlapping in the rand number generator we are gonna have 1 item less in the basket.
        int numberOfItemsIntoTheBasket = rand.nextInt(8 - 3) + 3;

        ArrayList<Integer> listOfItems = new ArrayList<>();
        for (int i = 0; i <= numberOfItemsIntoTheBasket; i++)
        {
            int randItemFromTheList = rand.nextInt(8 - 1) + 1;
            if (!(listOfItems.contains(randItemFromTheList)))
            {
                listOfItems.add(randItemFromTheList);
                Object currentItem = articlesDefaultMap.keySet().toArray()[randItemFromTheList];
                double currentItemPrice = articlesDefaultMap.get((articlesDefaultMap.keySet().toArray())[randItemFromTheList]);
                articlesToPass.put((String) currentItem, currentItemPrice);
                //System.out.println(currentItem + " " + currentItemPrice);
                passTotalAmountBeforeVAT += currentItemPrice;
            }
            else
            {
                --numberOfItemsIntoTheBasket;
            }
        }

        setTotalAmountBeforeVAT(passTotalAmountBeforeVAT);
        setArticles(articlesToPass);

/*        System.out.println("totalAmountToPass: " + passTotalAmountBeforeVAT);
        for (String i : articlesToPass.keySet()) {
            System.out.println("key: " + i + " value: " + articlesToPass.get(i));
        }*/
    }


    // VAT is calculated on the discounted price of the product. Is it better the retrieve the prices (with get function) inside of the method or to pass them as arguments?
    public void cutThemSomeSlack(double additionalDiscountPercent) throws DiscountNotApplicableException {
        //System.out.printf("Additional discount percent is: %s percents\n\n", additionalDiscountPercent);

        double passDiscountedAmount = 0;
        double passTotalAmountBeforeVATWithDiscount = getTotalAmountBeforeVAT();

        if (passTotalAmountBeforeVATWithDiscount < 1000 ) {
            throw new DiscountNotApplicableException("Total amount not reached for the particular discount. Minimum amount is 1000. Current amount: " + passTotalAmountBeforeVATWithDiscount);
        }

        double randAdditionalDiscountPercent = additionalDiscountPercent;
        int randDiscountPercent = (rand.nextInt(5 - 1) + 1)*10;

        randDiscountPercent += randAdditionalDiscountPercent;

        passDiscountedAmount = (passTotalAmountBeforeVATWithDiscount*randDiscountPercent)/100;

        passTotalAmountBeforeVATWithDiscount = (passTotalAmountBeforeVATWithDiscount - ((passTotalAmountBeforeVATWithDiscount*randDiscountPercent)/100));
        setDiscountedAmount(passDiscountedAmount);
        setTotalAmountBeforeVATWithDiscount(passTotalAmountBeforeVATWithDiscount);
    }


    public void calculateInvoiceWithVAT() {
        double passTotalAmountAfterVAT = getTotalAmountBeforeVATWithDiscount();
        passTotalAmountAfterVAT += (passTotalAmountAfterVAT*20)/100;
        setTotalAmountAfterVAT(passTotalAmountAfterVAT);
    }

/*
    public static void  printObjectPropertiesList(List<Invoice> ordersObjects) {
        for (Orders item : ordersObjects) {
            System.out.println("Item: " + item.toString());
        }
    }
*/

    public void printObjectProperties() {
        System.out.println("Item: " + this.toString());
    }

    public void saveInvoiceToFile() throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(
                    new FileWriter("C:\\TrainingPlanProjects\\Sprint_04\\InvoicePropertiesOutput.txt"));
                    bw.write("\n" + String.valueOf(this.articlesToPass) + ", ");
                    bw.write("\n" + this.getInvoiceNumber() + ", ");
                    bw.write("\n" + this.getClientDetails() + ", ");
                    bw.write("\n" + this.getTotalAmountBeforeVAT() + ", ");
                    bw.write("\n" + this.getTotalAmountBeforeVATWithDiscount() + ", ");
                    bw.write("\n" + this.getDiscountedAmount() + ", ");
                    bw.write("\n" + this.getDiscountedAmount() + ", ");
                    bw.write("\n" + this.getTotalAmountAfterVAT() + ", ");
        } catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            bw.close();
        }
    }

    // Entry point
    public void ExecuteActions ()
        {
            generateRandomTradeInvoiceData();
            try {
                cutThemSomeSlack(additionalDiscount());
            } catch (DiscountNotApplicableException e) {
                e.printStackTrace();
            }
            calculateInvoiceWithVAT();
            printObjectProperties();
            try {
                saveInvoiceToFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
