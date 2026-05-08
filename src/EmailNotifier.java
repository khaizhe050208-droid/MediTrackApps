public class EmailNotifier implements Notifier {

    @Override
    public void send(String report, String recipient) {

        System.out.println(
                "Sending EMAIL to "
                        + recipient
                        + ": "
                        + report
        );
    }
}