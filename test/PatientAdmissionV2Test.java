import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatientAdmissionV2Test {

    @Test
    void sameInput_differentStrategies_returnDifferentScores() {

        PatientAdmissionV2 standardAdmission =
                new PatientAdmissionV2(
                        new StandardRiskStrategy()
                );

        PatientAdmissionV2 conservativeAdmission =
                new PatientAdmissionV2(
                        new ConservativeRiskStrategy()
                );

        int standardScore =
                standardAdmission.calculateRiskScore(
                        39.0,
                        70
                );

        int conservativeScore =
                conservativeAdmission.calculateRiskScore(
                        39.0,
                        70
                );

        assertNotEquals(
                standardScore,
                conservativeScore
        );
    }

    @Test
    void setStrategy_newStrategyIsUsed() {

        PatientAdmissionV2 admission =
                new PatientAdmissionV2(
                        new StandardRiskStrategy()
                );

        int oldScore =
                admission.calculateRiskScore(
                        39.0,
                        70
                );

        admission.setStrategy(
                new ConservativeRiskStrategy()
        );

        int newScore =
                admission.calculateRiskScore(
                        39.0,
                        70
                );

        assertNotEquals(
                oldScore,
                newScore
        );
    }

    @Test
    void pediatricRiskStrategy_usedWithoutModifyingAdmissionV2() {

        PatientAdmissionV2 admission =
                new PatientAdmissionV2(
                        new PediatricRiskStrategy()
                );

        int score =
                admission.calculateRiskScore(
                        38.5,
                        10
                );

        assertEquals(
                60,
                score
        );
    }
}