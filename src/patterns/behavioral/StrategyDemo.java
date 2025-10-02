package patterns.behavioral;


// Strategy pattern demo: grading strategies
interface GradingStrategy { double grade(double raw); }
class PassFailStrategy implements GradingStrategy{ public double grade(double raw){ return raw >= 50 ? 1.0 : 0.0; } }
class PercentageStrategy implements GradingStrategy{ public double grade(double raw){ return raw; } }


public class StrategyDemo {
public static void demo(){
GradingStrategy strat = new PercentageStrategy();
System.out.println("Score (percent): " + strat.grade(87.5));
strat = new PassFailStrategy();
System.out.println("Pass/Fail: " + strat.grade(87.5));
}
public static void main(String[] args){ demo(); }
}