package com.estafet.learning;

import java.util.ArrayList;
import java.util.List;

public class TradeOrder extends Orders {
/*    protected static final List <String> TRADEORDER_LIST_WITH_ARTICLES;

    static {
        TRADEORDER_LIST_WITH_ARTICLES = null;
    }*/

    TradeOrder() {
        super(10000, "Details about the client field", null, 2.00);
        System.out.println("I am a TradeOrder() constructor.");
    }
}
