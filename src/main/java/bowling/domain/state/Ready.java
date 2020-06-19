package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public class Ready extends State {

    private Ready() {}

    public static Ready instance() {
        return LazyHolder.READY;
    }

    @Override
    protected State processDownPins(final Pin downPins) {
        if (downPins.isAllDown()) {
            return Strike.instance();
        }

        return Running.init(downPins);
    }

    @Override
    public List<Integer> getDownPins() {
        return Collections.emptyList();
    }

    @Override
    protected Score add(Score prevScore) {
        return prevScore;
    }

    private static class LazyHolder {
        private static final Ready READY = new Ready();

        private LazyHolder() {}
    }
}
