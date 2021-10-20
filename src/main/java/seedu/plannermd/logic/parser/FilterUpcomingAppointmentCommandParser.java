package seedu.plannermd.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;

import java.util.Arrays;
import java.util.List;

import seedu.plannermd.logic.commands.apptcommand.AppointmentFilters;
import seedu.plannermd.logic.commands.apptcommand.FilterAppointmentCommand;
import seedu.plannermd.logic.commands.apptcommand.FilterUpcomingAppointmentCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.appointment.AppointmentContainsDoctorPredicate;
import seedu.plannermd.model.appointment.AppointmentContainsPatientPredicate;

/**
 * Parses input arguments and creates a new FilterUpcomingAppointmentCommand object.
 */
public class FilterUpcomingAppointmentCommandParser implements
        Parser<FilterUpcomingAppointmentCommand> {

    public static final String NO_ARGUMENTS_MESSAGE = "No arguments provided.\n"
            + FilterAppointmentCommand.MESSAGE_USAGE;
    private static final String UNUSED_PREAMBLE = "0 ";

    /**
     * Parses the given {@code String} of arguments in the context of the FilterUpcomingAppointmentCommand
     * and returns a FilterUpcomingAppointmentCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format
     */
    public FilterUpcomingAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(UNUSED_PREAMBLE + args,
                PREFIX_DOCTOR, PREFIX_PATIENT);

        AppointmentFilters filters = AppointmentFilters.upcomingAppointmentsFilter();

        if (!argumentMultimap.getPreamble().equals("0")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterUpcomingAppointmentCommand.MESSAGE_USAGE));
        }
        if (argumentMultimap.getValue(PREFIX_DOCTOR).isPresent()) {
            String doctorKeywords = argumentMultimap.getValue(PREFIX_DOCTOR).get();
            filters.setHasDoctor(new AppointmentContainsDoctorPredicate(stringToList(doctorKeywords)));
        }
        if (argumentMultimap.getValue(PREFIX_PATIENT).isPresent()) {
            String patientKeywords = argumentMultimap.getValue(PREFIX_PATIENT).get();
            filters.setHasPatient(new AppointmentContainsPatientPredicate(stringToList(patientKeywords)));
        }
        return new FilterUpcomingAppointmentCommand(filters);
    }

    private List<String> stringToList(String string) throws ParseException {
        requireNonNull(string);
        if (string.trim().isEmpty()) {
            throw new ParseException(NO_ARGUMENTS_MESSAGE);
        }
        String[] nameKeywords = string.trim().split("\\s+");
        return Arrays.asList(nameKeywords);
    }
}
