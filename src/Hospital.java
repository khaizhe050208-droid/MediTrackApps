
import java.util.ArrayList;
import java.util.List;

public class Hospital implements AdmissionSubject {

    private List<AdmissionObserver> observers =
            new ArrayList<>();

    private String hospitalName;

    private RiskCalculationStrategy strategy;

    public Hospital(
            String hospitalName,
            RiskCalculationStrategy strategy) {

        this.hospitalName = hospitalName;
        this.strategy = strategy;
    }

    @Override
    public void addObserver(
            AdmissionObserver observer) {

        observers.add(observer);
    }

    @Override
    public void removeObserver(
            AdmissionObserver observer) {

        observers.remove(observer);
    }

    @Override
    public void notifyObservers(
            String patientName,
            int riskScore) {

        for (AdmissionObserver observer : observers) {

            observer.onPatientAdmitted(
                    patientName,
                    riskScore
            );
        }
    }

    public void admitPatient(
            String patientName,
            double temperatureC,
            int age) {

        int riskScore =
                strategy.calculateRisk(
                        temperatureC,
                        age
                );

        System.out.println(
                "[" + hospitalName + "] Admitting: "
                        + patientName
        );

        notifyObservers(
                patientName,
                riskScore
        );
    }
}

