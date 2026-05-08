import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HospitalTest {

    @Test
    void removeObserver_removedObserverNotCalled() {

        Hospital hospital =
                new Hospital("MediTrack");

        TestObserver observer =
                new TestObserver();

        hospital.addObserver(observer);

        hospital.removeObserver(observer);

        hospital.admitPatient(
                "Maria",
                80
        );

        assertFalse(observer.wasCalled);
    }

    static class TestObserver
            implements AdmissionObserver {

        boolean wasCalled = false;

        @Override
        public void onPatientAdmitted(
                String patientName,
                int riskScore) {

            wasCalled = true;
        }
    }
}