package ch.zhaw.iwi.devops.fizzbuzz;

public class FizzBuzzConverter {

    public String convert(int i) {
        if (i % 3 == 0 && i % 5 == 0 && i % 7 == 0) {
            return "FizzBuzzBang";
        } else if (i % 3 == 0 && i % 5 == 0) {
            return "FizzBang";
        } else if (i % 5 == 0 && i % 7 == 0) {
            return "BuzzBang";
        } else if (i % 3 == 0 && i % 7 == 0) {
            return "FizzBuzz";
        } else if (i % 3 == 0) {
            return "Fizz";
        } else if (i % 7 == 0) {
            return "Buzz";
        } else if (i % 5 == 0) {
            return "Bang";
        }
        return String.valueOf(i);
    }

}
