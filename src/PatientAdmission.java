public class PatientAdmission {

    public int calculateRiskScore(double temperatureC, int age) {

        if (temperatureC < 30.0 || temperatureC > 45.0) {
            throw new IllegalArgumentException(
                    "Temperature out of valid range: " + temperatureC
            );
        }

        int score = 0;

        if (temperatureC >= 39.5) {
            score += 50;
        } else if (temperatureC >= 37.5) {
            score += 25;
        }

        if (age >= 65) {
            score += 30;
        } else if (age >= 50) {
            score += 15;
        }

        return score;
    }

    public String getAdmissionPriority(int riskScore) {

        if (riskScore >= 70) {
            return "URGENT";
        }

        if (riskScore >= 30) {
            return "MODERATE";
        }

        return "ROUTINE";
    }

    public boolean isValidPatientName(String name) {

        return name != null
                && !name.trim().isEmpty()
                && name.length() <= 100;
    }

    public boolean isEligibleForPriorityCare(
            int age,
            boolean hasChronicCondition) {

        return age >= 65 || hasChronicCondition;
    }

    public String formatPatientReport(
            String name,
            int riskScore,
            String priority) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Invalid patient name"
            );
        }

        if (riskScore < 0) {
            throw new IllegalArgumentException(
                    "Risk score cannot be negative"
            );
        }

        if (!priority.equals("URGENT")
                && !priority.equals("MODERATE")
                && !priority.equals("ROUTINE")) {

            throw new IllegalArgumentException(
                    "Unknown priority"
            );
        }

        return "Patient: " + name
                + " | Risk Score: " + riskScore
                + " | Priority: " + priority;
    }
}