class Accounting{
    public double valueOfSupply;
    public double vatRate;
    public double expenseRate;

    public double getDividend1(){
        return getIncome() * 0.5;    }
    public double getDividend2(){
        return getIncome() * 0.3;
    }
    public double getDividend3(){
        return getIncome() * 0.2;
    }
    public double getIncome(){
        return valueOfSupply - getExpense();
    }
    public double getExpense(){
        return valueOfSupply * expenseRate;
    }
    public double getTotal(){
        return valueOfSupply + getVAT();
    }
    public double getVAT(){
        return valueOfSupply * vatRate;
    }
    public void print(){
        System.out.println("Value of Supply : " + valueOfSupply);
        System.out.println("VAT : " + getVAT());
        System.out.println("Total : " + getTotal());
        System.out.println("Expense : " + getExpense());
        System.out.println("Income : " + getIncome());
        System.out.println("Dividend1 : " + getDividend1());
        System.out.println("Dividend2 : " + getDividend2());
        System.out.println("Dividend3 : " + getDividend3());
    }
}

public class AccountingClassApp {

    public static void main(String[] args) {

        /*
        **다음과 같이Accounting의 값을 계속 바꾸는건 비효율적이다.**

        Accounting.valueOfSupply = 10000;
        Accounting.vatRate = 0.1;
        Accounting.expenseRate = 0.3;

        Accounting.print();

        Accounting.valueOfSupply = 20000;
        Accounting.vatRate = 0.05;
        Accounting.expenseRate = 0.2;

        Accounting.print();


        **다음 방법은 어떤가? 역시 비효율적이다.**

        Accounting1.valueOfSupply = 10000;
        Accounting1.vatRate = 0.1;
        Accounting1.expenseRate = 0.3;

        Accounting1.print();

        Accounting2.valueOfSupply = 20000;
        Accounting2.vatRate = 0.05;
        Accounting2.expenseRate = 0.2;

        * Accounting2.print();

        */
        Accounting a1 = new Accounting();
        a1.valueOfSupply = 10000;
        a1.vatRate = 0.1;
        a1.expenseRate = 0.3;

        a1.print();

        Accounting a2 = new Accounting();
        a2.valueOfSupply = 20000;
        a2.vatRate = 0.5;
        a2.expenseRate = 0.2;

        a2.print();
    }
}
