package datastruct.stack.calculate;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.locks.LockSupport;

public class Calculator {
    private Stack<Double> numStack;
    private Stack<String> symbolStack;

    public Calculator(Stack<Double> numStack, Stack<String> symbolStack) {
        this.numStack = numStack;
        this.symbolStack = symbolStack;
    }

    public static class Expression {
        private String expr;

        public Expression(String expr) {
            this.expr = expr;
        }

        public String getExpr() {
            return expr;
        }

        public void setExpr(String expr) {
            this.expr = expr;
        }

    }

    //1+4*5-2
    public double calculate(String expression) {
        Expression expre = new Expression(expression);
        List<String> list = new ArrayList<>();
        String[] split = expre.getExpr().split("[0-9]+");
        for (String s1 : split) {
            if (!"".equals(s1)) {
                list.add(stringSplit(s1, expre));
                list.add(s1);
            }
        }
        list.add(expre.getExpr());
        //System.out.println(Arrays.toString(split));
        for (String s : list) {
            System.out.println(s);
            if (s.matches("[0-9]+")) {
                numStack.push(Double.valueOf(s));
                continue;
            }
            if (symbolStack.isEmpty()) {
                symbolStack.push(s);
                continue;
            }
            //小于等于
            if (priority(s)<=priority(symbolStack.peek())) {
                double val = comput(symbolStack.pop(), numStack.pop(), numStack.pop());
                numStack.push(val);
                symbolStack.push(s);
                continue;
            }
            symbolStack.push(s);
        }
        int size = symbolStack.size();
        for (int i = 0; i < size; i++) {
            double val = comput(symbolStack.pop(), numStack.pop(), numStack.pop());
            numStack.push(val);
        }
        return numStack.pop();
    }

    public static void main(String[] args) {
//        Calculator calculator = new Calculator(new Stack<Double>(), new Stack<String>());
//        System.out.println(calculator.calculate("2+11/5+11/5"));


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

    //比上一个小，或者等于都需要先计算
    private int priority(String symbol) {
        if ("*".equals(symbol) || "/".equals(symbol)) {
            return 1;
        } else if ("+".equals(symbol) || "-".equals(symbol)) {
            return 0;
        } else {
            throw new UnsupportedOperationException("暂不支持操作");
        }
    }


    private String stringSplit(String key, Expression expr) {
        String expre = expr.getExpr();
        switch (key) {
            case "+":
                expr.setExpr(expre.substring(expre.indexOf("+") + 1));
                return expre.substring(0, expre.indexOf("+"));
            case "-":
                expr.setExpr(expre.substring(expre.indexOf("-") + 1));
                return expre.substring(0, expre.indexOf("-"));
            case "*":
                expr.setExpr(expre.substring(expre.indexOf("*") + 1));
                return expre.substring(0, expre.indexOf("*"));
            case "/":
                expr.setExpr(expre.substring(expre.indexOf("/") + 1));
                return expre.substring(0, expre.indexOf("/"));
            default:
                throw new UnsupportedOperationException("暂不支持操作" + key);
        }
    }

}
