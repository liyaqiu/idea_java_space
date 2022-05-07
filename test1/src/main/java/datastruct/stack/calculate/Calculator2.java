package datastruct.stack.calculate;

import datastruct.stack.ArrayStack;
import datastruct.stack.SingleLinkedStack;

import java.math.BigDecimal;

public class Calculator2 {
    private ArrayStack numStack = new ArrayStack(100);
    private SingleLinkedStack symbolStack = new SingleLinkedStack(100);

    public static void main(String[] args) {
        Calculator2 calcula = new Calculator2();
        Double res = calcula.calculate("1.0000+11/(2+2)+3");
        System.out.println(res);


    }


    private Double calculate(String expression) {
        int index = 0;
        int ch = 0;
        StringBuilder numUnion = new StringBuilder();
        while (true) {
            if (index == expression.length()) {
                break;
            }
            ch = expression.substring(index, index + 1).charAt(0);
            //System.out.println((char) ch);
            index++;
            //判断是否为数值
            if (!isSymbol(ch)) {
                numUnion.append((char)ch);
                //查看下一个是否为符号，如果说符号则可以放入栈中
                if(index == expression.length() || isSymbol(expression.substring(index, index + 1).charAt(0))){
                    numStack.push(Double.parseDouble(numUnion.toString()));
                    numUnion = new StringBuilder();

                }
                continue;
            }
            //符号
            if (symbolStack.isEmpty()) {
                symbolStack.push(ch);
                continue;
            }
            //判断第二符号，如果小于等于第一个优先级，则需要计算   10+10/2*2
            if (priority(ch) <= priority(symbolStack.peek())) {
                double vaule = comput(symbolStack.pop(), numStack.pop(), numStack.pop());
                numStack.push(vaule);
                symbolStack.push(ch);
                continue;
            }
            symbolStack.push(ch);
        }
        while (true){
            if(symbolStack.isEmpty()){
                break;
            }
            double result = comput(symbolStack.pop(), numStack.pop(), numStack.pop());
            numStack.push(result);
        }
        return numStack.peek();
    }

    //计算
    private double comput(int symbol, double num1, double num2) {
        switch (symbol) {
            case '+':
                return new BigDecimal(num1).add(new BigDecimal(num2)).doubleValue();
            case '-':
                return new BigDecimal(num2).subtract(new BigDecimal(num1)).doubleValue();
            case '*':
                return new BigDecimal(num1).multiply(new BigDecimal(num2)).doubleValue();
            case '/':
                return new BigDecimal(num2).divide(new BigDecimal(num1)).doubleValue();
            default:
                throw new UnsupportedOperationException("暂不支持操作");
        }
    }

    private int priority(int ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        } else {
            throw new UnsupportedOperationException("暂时不支持其他!");
        }
    }

    private boolean isSymbol(int ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            return true;
        }
        return false;
    }

}
