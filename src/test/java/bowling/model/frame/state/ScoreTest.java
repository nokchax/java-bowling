package bowling.model.frame.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Pins.DOWN_ALL;
import static bowling.model.frame.state.Score.*;
import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @DisplayName("Default 점수")
    @Test
    void getScore_default() {
        // when
        Score result = Score.of(ZERO_OF_COUNT, DEFAULT_SCORE);

        // then
        assertThat(result.isCompleted()).isTrue();
        assertThat(result.getScore()).isEqualTo(DEFAULT.getScore());
    }

    @DisplayName("계산이 남았을 경우 점수 계산을 할 수 없다")
    @Test
    void getScore_remainBonusCount_fail() {
        // given
        int count = ZERO_OF_COUNT + 1;

        // when
        Score result = Score.of(count, DEFAULT_SCORE);

        // then
        assertThat(result.isCompleted()).isFalse();
    }

    @DisplayName("스트라이크 일 경우 2번의 보너스 점수를 얻는다")
    @Test
    void getScore_strike_thanTwoBonus() {
        // given && when
        Score result = ofStrike()
                .calculate(DOWN_ALL)
                .calculate(DOWN_ALL);

        // then
        assertThat(result.isCompleted()).isTrue();
        assertThat(result.getScore()).isEqualTo(30);
    }

    @DisplayName("스페어 일 경우 1번의 보너스 점수를 얻는다")
    @Test
    void getScore_spare_thanOneBonus() {
        // given && when
        Score result = ofSpare()
                .calculate(DOWN_ALL);

        // then
        assertThat(result.isCompleted()).isTrue();
        assertThat(result.getScore()).isEqualTo(20);
    }

    private static Score ofStrike() {
        return Score.of(2, DOWN_ALL.count());
    }

    private static Score ofSpare() {
        return Score.of(1, DOWN_ALL.count());
    }
}