package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.plannermd.commons.util.StringUtil;

public class AppointmentContainsPatientPredicate implements Predicate<Appointment> {

    private final List<String> keywords;

    public AppointmentContainsPatientPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(appointment.getPatient().getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsPatientPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentContainsPatientPredicate) other).keywords)); // state check
    }
}