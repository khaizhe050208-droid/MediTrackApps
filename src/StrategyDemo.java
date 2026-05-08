public class StrategyDemo {
    public static void main(String[] args) {
        double temp = 38.7;
        int age  = 62;

        PatientAdmissionV2 standard = new PatientAdmissionV2(new StandardRiskStrategy());
        PatientAdmissionV2 conservative = new PatientAdmissionV2(new ConservativeRiskStrategy());

        System.out.println("Standard risk:     " + standard.calculateRiskScore(temp, age));
        System.out.println("Conservative risk: " + conservative.calculateRiskScore(temp, age));

        // Swap strategy at runtime
        standard.setStrategy(new ConservativeRiskStrategy());
        System.out.println("After swap:        " + standard.calculateRiskScore(temp, age));
    }
}
