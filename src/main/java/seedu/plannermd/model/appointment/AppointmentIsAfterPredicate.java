package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s has a starting time after the start dateTime given.
 */
public class AppointmentIsAfterPredicate implements Predicate<Appointment> {

    private final LocalDateTime filterStartDateTime;

    /**
     * Creates an AppointmentIsAfterPredicate with a LocalDate object. The predicate is not time sensitive.
     * All appointments on that particular {@code filterStartDate} will return true when tested on the predicate.
     */
    public AppointmentIsAfterPredicate(LocalDate filterStartDate) {
        requireNonNull(filterStartDate);
        this.filterStartDateTime = filterStartDate.atStartOfDay();
    }

    /**
     * Creates an AppointmentIsAfterPredicate with a LocalDateTime object. The predicate
     * is time sensitive based on the time specified in {@code filteredStartDateTime}.
     */
    public AppointmentIsAfterPredicate(LocalDateTime filterStartDateTime) {
        requireNonNull(filterStartDateTime);
        this.filterStartDateTime = filterStartDateTime;
    }

    @Override
    public boolean test(Appointment appointment) {
        LocalDateTime apptStartTime = appointment.getAppointmentStartTime();
        return !apptStartTime.isBefore(filterStartDateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentIsAfterPredicate // instanceof handles nulls
                && filterStartDateTime.equals(((AppointmentIsAfterPredicate) other).filterStartDateTime));
    }

}
