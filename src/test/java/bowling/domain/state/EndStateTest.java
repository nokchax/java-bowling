package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.exception.UnReachableStateException;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("종료 스테이트에 대한 테스트")
class EndStateTest {
    private static final EndState END_STATE = new EndState() {
        @Override
        public List<Integer> getDownPins() {
            return null;
        }

        @Override
        protected Score add(Score prevScore) {
            return null;
        }
    };

    @Test
    @DisplayName("종료 스테이트에서는 핀을 쓰러뜨리려 할 경우 예외가 발생한다")
    void downPins() {
        assertThatExceptionOfType(UnReachableStateException.class)
                .isThrownBy(() -> END_STATE.downPins(Pin.of(10)));
    }

    @Test
    @DisplayName("종료 스테이트는 isEnd() 호출시 true를 반환한다.")
    void isEnd() {
        assertThat(END_STATE.isEnd()).isTrue();
    }
}
