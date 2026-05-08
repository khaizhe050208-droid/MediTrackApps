public class CombinedPatternDemo {

    public static void main(String[] args) {

        Hospital hospital =
                new Hospital(
                        "MediTrack Central",
                        new ConservativeRiskStrategy()
                );

        hospital.addObserver(
                new DoctorNotifier("Dr. Lim")
        );

        hospital.addObserver(
                new BillingNotifier()
        );

        hospital.addObserver(
                new AuditNotifier()
        );

        hospital.admitPatient(
                "Maria Santos",
                39.0,
                70
        );
    }
}