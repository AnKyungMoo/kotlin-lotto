package calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource

class CalculatorTest {
    private lateinit var calculator: Calculator

    @BeforeEach
    fun setUp() {
        calculator = Calculator()
    }

    @ParameterizedTest
    @NullAndEmptySource
    fun `빈 문자열 또는 null을 입력할 경우 0을 반환해야 한다`(expression: String?) {
        assertThat(calculator.add(expression)).isEqualTo(0)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "11", "111", "1234", "0", "012"])
    fun `숫자 하나를 문자열로 입력할 경우 해당 숫자를 반환한다`(expression: String?) {
        assertThat(calculator.add(expression)).isEqualTo(expression?.toInt())
    }

    @ParameterizedTest
    @ValueSource(strings = ["1,2"])
    fun `숫자 두개를 , 구분자로 입력할 경우 두 숫자의 합을 반환한다`(expression: String?) {
        assertThat(calculator.add(expression)).isEqualTo(3)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1,2:3"])
    fun `구분자를 , 이외에 콜론을 사용할 수 있다`(expression: String?) {
        assertThat(calculator.add(expression)).isEqualTo(6)
    }
}
