package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TextView quantityTextView;
    private TextView quantityPrice;
    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 0) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);

    }

    public void submitOrder(View view) {
        EditText editText = (EditText) findViewById(R.id.name);
        String displayName = editText.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCreamCheckBox = (CheckBox) findViewById(R.id.chocolateCream);
        boolean hasChocolateCream = chocolateCreamCheckBox.isChecked();

//        display(quantity * 5);
        int price = calculatePrice(hasWhippedCream, hasChocolateCream);
        String priceMessage = creatOrderSummary(price, hasWhippedCream, hasChocolateCream, displayName);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java App Order" + displayName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
        }
        displayMessage(priceMessage);

    }

    /**
     * display method
     *
     * @param number
     */
    private void display(int number) {
        quantityPrice = (TextView) findViewById(R.id.order_summary_text);
        quantityPrice.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     *
     * @param numberOfCoffe
     */
    private void displayQuantity(int numberOfCoffe) {
        quantityTextView = (TextView) findViewById(R.id.quantity_textView);
        quantityTextView.setText("" + numberOfCoffe);
    }

    /**
     * Calculates the price of the order
     *     * @param addWhippedCream is the number of cups of coffee
     *      * @param addChocolate is price of one cup
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;
        if(addWhippedCream == true){
            basePrice = basePrice + 1;
        }
        if(addChocolate == true){
            basePrice = basePrice + 2;
        }

        int price = quantity * basePrice;
        return price;
    }

    /**
     * @param message
     */
    private void displayMessage(String message) {
        TextView textView = (TextView) findViewById(R.id.order_summary_text);
        textView.setText(message);
    }

    /**
     *  Create Order Summary method
     * @param price
     * @param addWhippedCream
     * @param addChocolate
     * @param name
     * @return
     */
    private String creatOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name) {

        String message = "Name: " + name;
        message += "\n Add Whipped Cream? " + addWhippedCream;
        message += "\n Add Chocolate " + addChocolate;
        message = message + "\n Quantity: " + quantity;
        message = message + "\n Total: $" + price;
        message = message + "\n Thank You";
        return message;

    }


}
