package bowling.view.state;

import bowling.dto.StateDtos;

import java.util.stream.Collectors;

public class StatesStringConverter {

    private static final String EMPTY = "";

    public static String convert(final StateDtos stateDtos) {
        return stateDtos.getStateDtos()
                .stream()
                .map(StateFormat::convert)
                .collect(Collectors.joining(EMPTY));
    }
}
