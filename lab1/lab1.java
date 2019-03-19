package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        String languages[]={"C", "C++", "C#", "Go", "JavaScript", "Perl", "PHP", "Python", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        System.out.format("n = %d\n",n);
        n=n*3;
        String binary="10101";
        int number_from_binary=Integer.parseInt(binary,2);
        System.out.format("n = %d\n",n);
        n=n+number_from_binary;
        String hexa="FF";
        int number_from_hexa=Integer.parseInt(binary,16);
        n=n+number_from_hexa;
        n*=6;
        int sum_of_digits;
        do {
            sum_of_digits=0;
            while (n > 0) {
                sum_of_digits += n % 10;
                n = n / 10;
            }
            n=sum_of_digits;
        }
        while(sum_of_digits>9);
        int result=sum_of_digits;
        System.out.format("n = %d\n",sum_of_digits);
        System.out.print("Willy-nilly, this semester I will learn " + languages[result]);
    }
}
