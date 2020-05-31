package bowling.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("마지막 프레임에서의 종료 상태 테스트")
class LastEndTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(() -> LastEnd.init()).doesNotThrowAnyException();
    }
}