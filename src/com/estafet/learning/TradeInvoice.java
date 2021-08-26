package com.estafet.learning;

import java.util.*;
import java.io.*;
import static com.estafet.learning.Globals.*;

public class TradeInvoice extends Invoice {
    public Random rand = new Random();
    public Map<String, Double> articlesToPass = new LinkedHashMap<String, Double>();

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
                Object currentItem = ARTICLES_DEFAULT_MAP.keySet().toArray()[randItemFromTheList];
                double currentItemPrice = ARTICLES_DEFAULT_MAP.get((ARTICLES_DEFAULT_MAP.keySet().toArray())[randItemFromTheList]);
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
    public void cutThemSomeSlack(double additionalDiscountPercent) throws DiscountNotApplicableException, ShippingNotSupported {
        //System.out.printf("Additional discount percent is: %s percents\n\n", additionalDiscountPercent);

        double passDiscountedAmount = 0;
        double passTotalAmountBeforeVATWithDiscount = getTotalAmountBeforeVAT();

        if (passTotalAmountBeforeVATWithDiscount < 500 ) {
            throw new DiscountNotApplicableException("Total amount not reached for the particular discount. Minimum amount is 1000. Current amount: " + passTotalAmountBeforeVATWithDiscount);
        }

        if (passTotalAmountBeforeVATWithDiscount < 1500 ) {
            throw new ShippingNotSupported("Shipping is not supported for the following amount: " + passTotalAmountBeforeVATWithDiscount);
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

/*    public static void  printObjectPropertiesList(List<Invoice> ordersObjects) {
        for (Invoice item : ordersObjects) {
            System.out.println("Item: " + item.toString());
        }
    }*/

    public void printObjectProperties() {
        System.out.println("Item: " + this.toString());
    }

    public void saveInvoiceObjectToFile() throws IOException {
        // BufferedWriter bw = null;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\TrainingPlanProjects\\Sprint_04\\InvoicePropertiesOutput.txt"))) {

                    bw.write("\n" + String.valueOf(this.articlesToPass) + ", ");
                    bw.write("\n" + this.getInvoiceNumber() + ", ");
                    bw.write("\n" + this.getClientDetails() + ", ");
                    bw.write("\n" + this.getTotalAmountBeforeVAT() + ", ");
                    bw.write("\n" + this.getTotalAmountBeforeVATWithDiscount() + ", ");
                    bw.write("\n" + this.getDiscountedAmount() + ", ");
                    bw.write("\n" + this.getDiscountedAmount() + ", ");
                    bw.write("\n" + this.getTotalAmountAfterVAT() + ", ");
        } catch(Exception e) {
            System.out.println("Directory or file not found.");
            e.printStackTrace();
        }
        finally {
            System.out.println("Finally still exist in try with resources block.");
/*            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();*/
            }
    }

    // Entry point
    public void ExecuteActions ()
        {
            generateRandomTradeInvoiceData();
            try {
                cutThemSomeSlack(additionalDiscount());
            } catch (DiscountNotApplicableException | ShippingNotSupported e) {
                e.printStackTrace();
            }
            calculateInvoiceWithVAT();
            //tradeInvoiceObjectsList.add(this);
            printObjectProperties();
            try {
                saveInvoiceObjectToFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
