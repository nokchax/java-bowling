package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("스페어 상태 테스트")
class SpareTest {

    @Test
    @DisplayName("스페어는 하나의 핀 객체를 가지고 초기화한다")
    void init() {
        assertThatCode(() -> Spare.init(Pin.of(5))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("상태 가져오기")
    void getState() {
        State spareState = Spare.init(Pin.of(5))
                .getState()
                .get(0);

        assertThat(spareState).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("넘어진 핀 갯수 가져오기")
    void getDownPins() {
        State spareState = Spare.init(Pin.of(5));

        assertThat(spareState.getDownPins()).containsExactly(5);
    }

    @Test
    @DisplayName("스페어 상태는 점수를 구하기 위해 추가적인 정보가 더 필요하다")
    void getScore() {
        Spare spare = Spare.init(Pin.of(5));

        assertThat(spare.calculateScore().isCalculable()).isFalse();
    }

    @Test
    @DisplayName("left 가 1인 경우 첫번째 쓰러뜨린 핀의 개수 만큼만 더하고 리턴한다")
    void addScore() {
        Score score = Score.ofSpare();
        Spare spare = Spare.init(Pin.of(5));

        assertThat(spare.addScore(score)).isEqualTo(Score.of(15, 0));
    }

    @Test
    @DisplayName("left 가 2인 경우 첫번째, 두번째 쓰러뜨린 핀의 개수를 모두 더하고 리턴한다")
    void addScoreHavingLeft2() {
        Score score = Score.ofStrike();
        Spare spare = Spare.init(Pin.of(5));

        assertThat(spare.addScore(score)).isEqualTo(Score.of(20, 0));
    }
}
