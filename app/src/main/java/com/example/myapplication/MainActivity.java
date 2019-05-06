package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private  TextView quantityTextView;
    private TextView quantityPrice;
    private int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   }

    public void increment(View view){
        if(quantity == 100){
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    public void decrement(View view){
        if(quantity == 0){
            return ;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);

    }
    public void submitOrder(View view){
//        display(quantity * 5);
        int price = calculatePrice();
        String priceMessage = creatOrderSummary(price);
        displayMessage(priceMessage);
    }
    private void display(int number){
        quantityPrice = (TextView) findViewById(R.id.price_text);
        quantityPrice.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayQuantity(int numberOfCoffe){
        quantityTextView = (TextView) findViewById(R.id.quantity_textView);
        quantityTextView.setText("" + numberOfCoffe);
    }

    /**
    * Calculates the price of the order
//     * @param quantity is the number of cups of coffee
//     * @param pricePerCup is price of one cup
     */
    private int calculatePrice(){
        int price = quantity * 5;
        return price;
    }
    private void displayMessage(String message){
        TextView textView = (TextView) findViewById(R.id.price_text);
        textView.setText(message);
    }

//    The createOrderSummary Method
    private String creatOrderSummary(int price){

        String name = "Ebeh Elisha";
        String message = "Name: " + name ;
        message = message +  "\n Quantity: " + quantity;
        message = message + "\n Total: $" + price;
        message = message + "\n Thank You";
        return message;

    }


}
