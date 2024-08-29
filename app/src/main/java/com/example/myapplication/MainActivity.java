package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
   private Double resultLeft;
   private Double resultRight;
   private String resultString;
   private String toReplace;
   private String replace;
   private Pattern patternVerificareExistentaCaracterelorSpeciale = Pattern.compile("([x\\*/\\+\\-])");
   private Pattern patternVerificareNumarDecimalNegativ = Pattern.compile("(^ -?\\d+(\\.)\\d+$)");

   private Pattern pattern4 = Pattern.compile("(^ -?\\d+|(\\.)|\\d+$)");

   private String math;
   private TextView Button;
   private StringBuilder currentInput;
   private char lastChar = ' ';

    private boolean containsDecimal(String text) {
        return text.contains(".");
    }

    private boolean endsWithZero(double number) {
        return number % 1 == 0;
    }


    private boolean containsSpecialCharacters(String text, Pattern pattern) {
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

   private boolean isHyphenNumber(String str, Pattern pattern4, Pattern pattern3) {
        if (str == null || str.isEmpty()) {
            return false;
        } else {
            Matcher matcher3 = pattern3.matcher(str);
            Matcher matcher4 = pattern4.matcher(str);
            return matcher3.matches() || matcher4.matches();
        }
    }


    private boolean hasMoreThanTwoDecimals(String numberStr) {
        int indexOfDecimal = numberStr.indexOf('.');
        if (indexOfDecimal == -1) {
            return false;
        }
        String decimalPart = numberStr.substring(indexOfDecimal + 1);
        return decimalPart.length() > 2;
    }

    private String removeTrailingZero(double number) {
        if (number == (int) number) {
            return Integer.toString((int) number);
        } else {
            return Double.toString(number);
        }
    }

    private void replaceText(String replace, TextView Button, Matcher m, Pattern x, String toReplace, Pattern pattern) {
        if (containsDecimal(replace)) {
            Button.setText(replace);
            m = x.matcher(toReplace);
            math = replace;
            m = pattern.matcher(math);
        } else {
            Button.setText(replace);
            m = x.matcher(toReplace);
            math = replace;
            m = pattern.matcher(math);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentInput = new StringBuilder();

        Button buttonPunct = findViewById(R.id.button1);
        Button button0 = findViewById(R.id.button0);
        Button button3 = findViewById(R.id.button3);
        Button buttonDot = findViewById(R.id.buttonDot);
        Button button2 = findViewById(R.id.button2);
        Button buttonEgal = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonImpartire = findViewById(R.id.buttonImpartire);
        Button buttonAdunare = findViewById(R.id.buttonAdunare);
        Button buttonDEL = findViewById(R.id.buttonDEL);
        Button buttonEqual = findViewById(R.id.buttonEqual);
        Button buttonX = findViewById(R.id.buttonX);
        Button buttonMinus = findViewById(R.id.buttonMinus);

        Button = findViewById(R.id.TextView);

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            String buttonText;

            @Override
            public void onClick(View v) {
                int id = v.getId();
                    if (id == R.id.buttonX || id == R.id.buttonMinus || id == R.id.buttonDot || id == R.id.button0 || id == R.id.button1 || id == R.id.button2 || id == R.id.button3 || id == R.id.button4 || id == R.id.button5 || id == R.id.button6 || id == R.id.button7 || id == R.id.button8 || id == R.id.button9 || id == R.id.buttonAdunare || id == R.id.buttonImpartire || id == R.id.buttonDEL) {
                        Button clickedButton = (Button) v;
                        buttonText = clickedButton.getText().toString();
                        appendDigit(buttonText.charAt(0));
                    }
                    if (id == R.id.buttonEqual) {
                        functieCalcul("(^\\ -*\\d{1,10}\\.{0,1}\\d{0,10})|([x\\*/\\+\\-])|(\\d{1,10}\\.{0,1}\\d{0,10})");
                    }

                    if (id == R.id.buttonDEL) {
                        Button.setText(" ");
                    }



            }
        };

        buttonPunct.setOnClickListener(buttonClickListener);
        buttonDot.setOnClickListener(buttonClickListener);
        button0.setOnClickListener(buttonClickListener);
        button2.setOnClickListener(buttonClickListener);
        button3.setOnClickListener(buttonClickListener);
        button4.setOnClickListener(buttonClickListener);
        button5.setOnClickListener(buttonClickListener);
        button6.setOnClickListener(buttonClickListener);
        button7.setOnClickListener(buttonClickListener);
        button8.setOnClickListener(buttonClickListener);
        button9.setOnClickListener(buttonClickListener);
        buttonImpartire.setOnClickListener(buttonClickListener);
        buttonAdunare.setOnClickListener(buttonClickListener);
        buttonDEL.setOnClickListener(buttonClickListener);
        buttonX.setOnClickListener(buttonClickListener);
        buttonMinus.setOnClickListener(buttonClickListener);
        buttonEqual.setOnClickListener(buttonClickListener);

    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '.';
    }

    private boolean isOperatorStart(char c) {
        return c == '+' || c == '*' || c == '/' || c == '.';
    }



    private void appendDigit(char digit) {
            if (isOperator(digit) && isOperator(lastChar)) {
                return;
            }

        if (Button.getText().toString().equals(" ") && digit == '0') {
            return;
        }

        if (Button.getText().toString().equals(" ") && isOperatorStart(digit)){
            return;
        }

            if (Button == null) {
                currentInput.append(digit);
                Button.setText(currentInput.toString());
            } else {
                Button.append(Character.toString(digit));
            }
            lastChar = digit;
        }



    public void functieCalcul(String regex) {
        math = Button.getText().toString();
        Pattern x = Pattern.compile(regex);

        while (containsSpecialCharacters(Button.getText().toString(), patternVerificareExistentaCaracterelorSpeciale) && isHyphenNumber(Button.getText().toString(), patternVerificareNumarDecimalNegativ, pattern4) == false ) {
            Matcher m = x.matcher(math);
            List<String> splitsList = new ArrayList<>();


            while (m.find()) {
                splitsList.add(m.group());
            }

            boolean calculated = false;
            for (int i = 0; i < splitsList.size(); i++) {
                double finalResult = 0;
                if ("*".equals(splitsList.get(i)) || "/".equals(splitsList.get(i))) {
                    calculated = true;
                    if (splitsList.get(i).equals("*")) {
                        resultLeft = Double.parseDouble(splitsList.get(i - 1));
                        resultRight = Double.parseDouble(splitsList.get(i + 1));
                        finalResult += resultLeft * resultRight;
                        if (endsWithZero(resultLeft) && !endsWithZero(resultRight)) {
                            int intResultLeft = (int) (double) resultLeft;
                            toReplace = intResultLeft + "*" + resultRight;
                        } else if (endsWithZero(resultRight) && !endsWithZero(resultLeft)) {
                            int intResultRight = (int) (double) resultRight;
                            toReplace = resultLeft + "*" + intResultRight;
                        } else if (endsWithZero(resultRight) && endsWithZero(resultLeft)) {
                            int intResultRight = (int) (double) resultRight;
                            int intResultLeft = (int) (double) resultLeft;
                            toReplace = intResultLeft + "*" + intResultRight;
                        }else if (!endsWithZero(resultRight) && !endsWithZero(resultLeft)) {
                            toReplace = resultLeft + "*" + resultRight;
                        }
                        resultString = removeTrailingZero(finalResult);
                        if (hasMoreThanTwoDecimals(resultString)) {
                            double number = Double.parseDouble(resultString);
                            DecimalFormat df = new DecimalFormat("#.00");
                            String formattedNumber = df.format(number);
                            replace = Button.getText().toString().replace(toReplace, formattedNumber);
                            replaceText(formattedNumber, Button, m, x, toReplace, patternVerificareExistentaCaracterelorSpeciale);
                        } else {
                             replace = Button.getText().toString().replace(toReplace, resultString);
                            replaceText(replace, Button, m, x, toReplace, patternVerificareExistentaCaracterelorSpeciale);
                        }
                    } else if (splitsList.get(i).equals("/")) {
                        resultLeft = Double.parseDouble(splitsList.get(i - 1));
                        resultRight = Double.parseDouble(splitsList.get(i + 1));
                        finalResult += resultLeft / resultRight;
                        if (endsWithZero(resultLeft) && !endsWithZero(resultRight)) {
                            int intResultLeft = (int) (double) resultLeft;
                            toReplace = intResultLeft + "/" + resultRight;
                        } else if (endsWithZero(resultRight) && !endsWithZero(resultLeft)) {
                            int intResultRight = (int) (double) resultRight;
                            toReplace = resultLeft + "/" + intResultRight;
                        } else if (endsWithZero(resultRight) && endsWithZero(resultLeft)) {
                            int intResultRight = (int) (double) resultRight;
                            int intResultLeft = (int) (double) resultLeft;
                            toReplace = intResultLeft + "/" + intResultRight;
                        }else if (!endsWithZero(resultRight) && !endsWithZero(resultLeft)) {
                            toReplace = resultLeft + "/" + resultRight;
                        }
                        resultString = removeTrailingZero(finalResult);
                        if (hasMoreThanTwoDecimals(resultString)) {
                            double number = Double.parseDouble(resultString);
                            DecimalFormat df = new DecimalFormat("#.00");
                            String formattedNumber = df.format(number);
                            replace = Button.getText().toString().replace(toReplace, formattedNumber);
                            replaceText(formattedNumber, Button, m, x, toReplace, patternVerificareExistentaCaracterelorSpeciale);
                        } else {
                            replace = Button.getText().toString().replace(toReplace, resultString);
                            replaceText(replace, Button, m, x, toReplace, patternVerificareExistentaCaracterelorSpeciale);
                        }

                    }
                    break;
                }
            }
            if (calculated) {
                continue;
            }
            for (int i = 0; i < splitsList.size(); i++) {
                double finalResult = 0;
                String resultDouble;
                if ("+".equals(splitsList.get(i)) || "-".equals(splitsList.get(i))) {
                    calculated = true;
                    if (splitsList.get(i).equals("+")) {
                        resultLeft = Double.parseDouble(splitsList.get(i - 1));
                        resultRight = Double.parseDouble(splitsList.get(i + 1));
                        finalResult += resultLeft + resultRight;
                        if (endsWithZero(resultLeft) && !endsWithZero(resultRight)) {
                            int intResultLeft = (int) (double) resultLeft;
                            toReplace = intResultLeft + "+" + resultRight;
                        } else if (endsWithZero(resultRight) && !endsWithZero(resultLeft)) {
                            int intResultRight = (int) (double) resultRight;
                            toReplace = resultLeft + "+" + intResultRight;
                        } else if (endsWithZero(resultRight) && endsWithZero(resultLeft)) {
                            int intResultRight = (int) (double) resultRight;
                            int intResultLeft = (int) (double) resultLeft;
                            toReplace = intResultLeft + "+" + intResultRight;
                        }else if (!endsWithZero(resultRight) && !endsWithZero(resultLeft)) {
                            toReplace = resultLeft + "+" + resultRight;
                        }
                        resultString = removeTrailingZero(finalResult);
                        if (hasMoreThanTwoDecimals(resultString)) {
                            double number = Double.parseDouble(resultString);
                            DecimalFormat df = new DecimalFormat("#.00");
                            String formattedNumber = df.format(number);
                            replace = Button.getText().toString().replace(toReplace, formattedNumber);
                            replaceText(formattedNumber, Button, m, x, toReplace, patternVerificareExistentaCaracterelorSpeciale);
                        } else {
                            replace = Button.getText().toString().replace(toReplace, resultString);
                            replaceText(replace, Button, m, x, toReplace, patternVerificareExistentaCaracterelorSpeciale);
                        }
                    } else if (splitsList.get(i).equals("-")) {
                        resultLeft = Double.parseDouble(splitsList.get(i - 1));
                        resultRight = Double.parseDouble(splitsList.get(i + 1));
                        finalResult += resultLeft - resultRight;
                        if (endsWithZero(resultLeft) && !endsWithZero(resultRight)) {
                            int intResultLeft = (int) (double) resultLeft;
                            toReplace = intResultLeft + "-" + resultRight;
                        } else if (endsWithZero(resultRight) && !endsWithZero(resultLeft)) {
                            int intResultRight = (int) (double) resultRight;
                            toReplace = resultLeft + "-" + intResultRight;
                        } else if (endsWithZero(resultRight) && endsWithZero(resultLeft)) {
                            int intResultRight = (int) (double) resultRight;
                            int intResultLeft = (int) (double) resultLeft;
                            toReplace = intResultLeft + "-" + intResultRight;
                        }else if (!endsWithZero(resultRight) && !endsWithZero(resultLeft)) {
                            toReplace = resultLeft + "-" + resultRight;
                        }
                        resultString = removeTrailingZero(finalResult);
                        if (hasMoreThanTwoDecimals(resultString)) {
                            double number = Double.parseDouble(resultString);
                            DecimalFormat df = new DecimalFormat("#.00");
                            String formattedNumber = df.format(number);
                            replace = Button.getText().toString().replace(toReplace, formattedNumber);
                            replaceText(formattedNumber, Button, m, x, toReplace, patternVerificareExistentaCaracterelorSpeciale);
                        } else {
                            replace = Button.getText().toString().replace(toReplace, resultString);
                            replaceText(replace, Button, m, x, toReplace, patternVerificareExistentaCaracterelorSpeciale);
                        }

                    }
                    break;
                }
            }
            if (calculated) {
                continue;
            }
        }
    }
}


