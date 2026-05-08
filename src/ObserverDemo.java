public class ObserverDemo {

    public static void main(String[] args) {

        Hospital hospital =
                new Hospital("MediTrack Central");

        DoctorNotifier doctorNotifier =
                new DoctorNotifier("Dr. Lim");

        BillingNotifier billingNotifier =
                new BillingNotifier();

        AuditNotifier auditNotifier =
                new AuditNotifier();

        WardNurseNotifier wardNurseNotifier =
                new WardNurseNotifier("Ward A");

        UrgentOnlyDoctorNotifier urgentDoctorNotifier =
                new UrgentOnlyDoctorNotifier("Lim");

        hospital.addObserver(doctorNotifier);
        hospital.addObserver(billingNotifier);
        hospital.addObserver(auditNotifier);
        hospital.addObserver(wardNurseNotifier);
        hospital.addObserver(urgentDoctorNotifier);

        hospital.admitPatient(
                "Maria Santos",
                80
        );

        System.out.println();

        hospital.removeObserver(
                wardNurseNotifier
        );

        hospital.admitPatient(
                "Ahmad Razali",
                25
        );
    }
}