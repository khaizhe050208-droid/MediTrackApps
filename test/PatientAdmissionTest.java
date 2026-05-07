import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class PatientAdmissionTest {

    private PatientAdmission admission;

    @BeforeEach
    void setUp() {
        admission = new PatientAdmission();
    }

    @Test
    void calculateRiskScore_highTempElderlyPatient_returnsHighScore() {
        int score = admission.calculateRiskScore(40.0, 70);
        assertEquals(80, score);
    }

    @Test
    void calculateRiskScore_normalTempYoungPatient_returnsZero() {
        int score = admission.calculateRiskScore(36.5, 25);
        assertEquals(0, score);
    }

    @Test
    void calculateRiskScore_elevatedTempMiddleAge_returnsMediumScore() {
        int score = admission.calculateRiskScore(38.0, 55);
        assertEquals(40, score);
    }

    @Test
    void getAdmissionPriority_scoreAbove70_returnsUrgent() {
        assertEquals("URGENT", admission.getAdmissionPriority(80));
    }

    @Test
    void getAdmissionPriority_scoreBetween30And70_returnsModerate() {
        assertEquals("MODERATE", admission.getAdmissionPriority(40));
    }

    @Test
    void getAdmissionPriority_scoreBelow30_returnsRoutine() {
        assertEquals("ROUTINE", admission.getAdmissionPriority(10));
    }

    @Test
    void isValidPatientName_validName_returnsTrue() {
        assertTrue(admission.isValidPatientName("Maria Santos"));
    }

    @Test
    void isValidPatientName_nullName_returnsFalse() {
        assertFalse(admission.isValidPatientName(null));
    }

    @Test
    void isValidPatientName_emptyString_returnsFalse() {
        assertFalse(admission.isValidPatientName(""));
    }

    @Test
    void calculateRiskScore_temperatureExactly395_returns50() {
        int score = admission.calculateRiskScore(39.5, 30);
        assertEquals(50, score);
    }

    @Test
    void calculateRiskScore_ageExactly65_adds30Points() {
        int score = admission.calculateRiskScore(36.5, 65);
        assertEquals(30, score);
    }

    @Test
    void isValidPatientName_nameExactly100Characters_returnsTrue() {
        String name = "A".repeat(100);
        assertTrue(admission.isValidPatientName(name));
    }

    @Test
    void isValidPatientName_name101Characters_returnsFalse() {
        String name = "A".repeat(101);
        assertFalse(admission.isValidPatientName(name));
    }

    @Test
    void isEligibleForPriorityCare_ageAbove65_returnsTrue() {
        assertTrue(admission.isEligibleForPriorityCare(70, false));
    }

    @Test
    void isEligibleForPriorityCare_hasChronicCondition_returnsTrue() {
        assertTrue(admission.isEligibleForPriorityCare(30, true));
    }

    @Test
    void isEligibleForPriorityCare_ageBelow65AndNoCondition_returnsFalse() {
        assertFalse(admission.isEligibleForPriorityCare(30, false));
    }

    @Test
    void isEligibleForPriorityCare_ageExactly65_returnsTrue() {
        assertTrue(admission.isEligibleForPriorityCare(65, false));
    }

    @Test
    void calculateRiskScore_temperatureBelow30_throwsIllegalArgument() {
        assertThrows(
                IllegalArgumentException.class,
                () -> admission.calculateRiskScore(20.0, 40)
        );
    }

    @Test
    void calculateRiskScore_temperatureAbove45_throwsIllegalArgument() {
        assertThrows(
                IllegalArgumentException.class,
                () -> admission.calculateRiskScore(46.0, 40)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "40.0, 70, 80",
            "38.0, 55, 40",
            "36.5, 25, 0",
            "39.5, 30, 50"
    })
    void calculateRiskScore_variousInputs_returnsCorrectScore(
            double temp,
            int age,
            int expectedScore) {

        assertEquals(
                expectedScore,
                admission.calculateRiskScore(temp, age)
        );
    }

    @Test
    void formatPatientReport_validInput_returnsFormattedString() {
        String result = admission.formatPatientReport(
                "Maria",
                80,
                "URGENT"
        );

        assertEquals(
                "Patient: Maria | Risk Score: 80 | Priority: URGENT",
                result
        );
    }

    @Test
    void formatPatientReport_nullName_throwsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> admission.formatPatientReport(
                        null,
                        80,
                        "URGENT"
                )
        );
    }

    @Test
    void formatPatientReport_negativeRiskScore_throwsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> admission.formatPatientReport(
                        "Maria",
                        -1,
                        "URGENT"
                )
        );
    }

    @Test
    void formatPatientReport_unknownPriority_throwsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> admission.formatPatientReport(
                        "Maria",
                        80,
                        "HIGH"
                )
        );
    }
}