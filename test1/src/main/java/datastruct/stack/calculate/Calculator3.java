package datastruct.stack.calculate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Calculator3 {
    List<String> numList = new ArrayList<>();
    Stack<String> symbolStack = new Stack();
    Stack<String> numStack = new Stack();
    public static void main(String[] args) {
        //System.out.println("1.10".matches("(\\d+)|(\\d+.\\d+)"));
        System.out.println(".".matches("(\\d+)|(\\.)"));
        Calculator3 calculator3 = new Calculator3();
        //1+((2+3)*4)-5 ==  "1 2 3 + 4 * + 5 -"   result:16
        String middleExpr = "1.1+((((2+3)))*4)-5";
        List<String> list = calculator3.middleExprssionToList(middleExpr);
        System.out.println(list);
        String suffixExpression = calculator3.middleTranSuffix(list);
        calculator3.suffixComput(suffixExpression);

    }

    private String middleTranSuffix(List<String> list){
        for (String s : list) {
            //数值操作
            if(s.matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9]) ")){
                numList.add(s);
                continue;
            }
            //符号操作
            if(symbolStack.isEmpty()){
                symbolStack.push(s);
                continue;
            }
            if(isOperateSymbol(s) && isOperateSymbol(symbolStack.peek()) && symbolPriority(s)<=symbolPriority(symbolStack.peek())){
                numList.add(symbolStack.pop());
                symbolStack.push(s);
                continue;
            }
            if (s.equals(")")){
                while (true){
                    String element = symbolStack.pop();
                    if(element.equals("(")){
                        break;
                    }
                    numList.add(element);
                }
                continue;
            }
            symbolStack.push(s);
        }
        while (!symbolStack.isEmpty()){
            String element = symbolStack.pop();
            numList.add(element);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <numList.size(); i++) {
            sb.append(numList.get(i)).append(" ");
        }
        return sb.toString().trim();
    }

    private boolean isOperateSymbol(String symbol){
        if(symbol.equals("(") || symbol.equals(")")){
            return false;
        }
        return true;
    }

    private int symbolPriority(String symbol){
        if(symbol.equals("+") || symbol.equals("-")){
            return 1;
        }else if(symbol.equals("*") || symbol.equals("/")){
            return 2;
        }else {
            throw new UnsupportedOperationException("不支持该运算符"+symbol);
        }
    }

    //中缀表达式转为list
    private List<String> middleExprssionToList(String str){
        List<String> list = new ArrayList<>();
        int size = str.length();
        int index=0;
        String s = null;
        StringBuilder sb = new StringBuilder();
        while (true){
            if(index==size){
                break;
            }
            s  = str.substring(index,index+1);
            index++;
            //判断是否为符号
            if(!s.matches("(\\d+)|(\\.)")){
                list.add(s);
                continue;
            }
            //查看下一位是不是数字
            if(index!=size && str.substring(index,index+1).matches("(\\d+)|(\\.)")){
                sb.append(s);
                continue;
            }
            sb.append(s);
            list.add(sb.toString());
            sb = new StringBuilder();
        }
        return list;
    }

    //后缀表达式计算,遇到运算符就弹出2个元素做运算，遇到数字不管多少都直接入栈
    private void suffixComput(String suffixExpr){
        //(3+4)*5-6 == "3 4 + 5 * 6 -"    result:29
        //String suffixExpr = "3 4 + 5 * 6 -";
        //1+((2+3)*4)-5 ==  "1 2 3 + 4 * + 5 -"   result:16
        //String suffixExpr = "1 2 3 + 4 * + 5 -";
        //(30+4)*6-6 == "30 4 + 5 * 6 -" result:164
        //String suffixExpr = "30 4 + 5 * 6 -";
        //4*5-8+60+8/2 == "4 5 * 8 - 60 + 8 / 2 +" result:76
        //String suffixExpr = "4 5 * 8 - 60 + 8 2 / +";
        List<String> asList = Arrays.asList(suffixExpr.split(" "));
        System.out.println(asList);
        for (String s : asList) {
            if(s.matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9]) ")){
                numStack.push(s);
                continue;
            }
            double res = comput(s, Double.parseDouble(numStack.pop()), Double.parseDouble(numStack.pop()));
            numStack.push(""+res);
        }
        System.out.println(numStack.pop());
    }

    //计算
    private double comput(String symbol, double num1, double num2) {
        switch (symbol) {
            case "+":
                return new BigDecimal(num1).add(new BigDecimal(num2)).doubleValue();
            case "-":
                return new BigDecimal(num2).subtract(new BigDecimal(num1)).doubleValue();
            case "*":
                return new BigDecimal(num1).multiply(new BigDecimal(num2)).doubleValue();
            case "/":
                return new BigDecimal(num2).divide(new BigDecimal(num1)).doubleValue();
            default:
                throw new UnsupportedOperationException("暂不支持操作");
        }
    }




}
