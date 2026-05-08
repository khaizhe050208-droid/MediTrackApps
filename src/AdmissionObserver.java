public interface AdmissionObserver {
    void onPatientAdmitted(String patientName, int riskScore);
}

