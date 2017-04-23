package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Marczyk Grzegorz
 * @version 1.2.3
 */
class DataValidatorTest {

    @Nested
    class DataValidatorTest_isInputDomainCorrect_FAIL {

        @Test
        void isInputDomainCorrect_fail_noDomains() {
            assertFalse(DataValidator.isInputDomainCorrect("www http pl no domains"));
        }

        @Test
        void isInputDomainCorrect_fail_twoDomains() {
            assertFalse(DataValidator.isInputDomainCorrect("www.wikipedia.org www.github.com"));
        }

        @Test
        void isInputDomainCorrect_fail_httpFormat() {
            assertFalse(DataValidator.isInputDomainCorrect("https://github.com/gmarczyk"));
        }

        @Test
        void isInputDomainCorrect_fail_domainEndingSlash() {
            assertFalse(DataValidator.isInputDomainCorrect("www.wikipedia.org/"));
        }

        @Test
        void isInputDomainCorrect_fail_suffixTooShort() {
            assertFalse(DataValidator.isInputDomainCorrect("www.wikipedia.o"));
        }

        @Test
        void isInputDomainCorrect_fail_suffixTooLong() {
            assertFalse(DataValidator.isInputDomainCorrect("www.wikipedia.abcdefg"));
        }

        @Test
        void isInputDomainCorrect_fail_domainInsideString() {
            assertFalse(DataValidator.isInputDomainCorrect("something www.wikipedia.org else"));
            assertFalse(DataValidator.isInputDomainCorrect("something www.wikipedia.org"));
            assertFalse(DataValidator.isInputDomainCorrect("www.wikipedia.org else"));
            assertFalse(DataValidator.isInputDomainCorrect("   www.wikipedia.org"));
            assertFalse(DataValidator.isInputDomainCorrect("www.wikipedia.org   "));
        }
    }

    @Nested
    class DataValidatorTest_isInputDomainCorrect_PASS {

        @Test
        void isInputDomainCorrect_pass_noWWW() {
            assertTrue(DataValidator.isInputDomainCorrect("wikipedia.org"));
        }

        @Test
        void isInputDomainCorrect_pass_withWWW() {
            assertTrue(DataValidator.isInputDomainCorrect("www.wikipedia.org"));
        }

        @Test
        void isInputDomainCorrect_pass_noWWW_domainWithSubdomains() {
            assertTrue(DataValidator.isInputDomainCorrect("pl.wiki-pe.dia.org"));
        }

        @Test
        void isInputDomainCorrect_pass_withWWW_domainWithSubdomains() {
            assertTrue(DataValidator.isInputDomainCorrect("www.pl.wikipedia.org"));
        }
    }

    @Nested
    class DataValidatorTest_isInputDomainCorrect_BOUNDARY {

        @Test
        void isInputDomainCorrect_boundary_suffixUpperLimit() {
            assertTrue(DataValidator.isInputDomainCorrect("www.wikipedia.abcdef"));
        }

        @Test
        void isInputDomainCorrect_boundary_suffixLowerLimit() {
            assertTrue(DataValidator.isInputDomainCorrect("www.wikipedia.ab"));
        }
    }

    @Test
    void isKeyWordCorrect_fail_empty() {
        assertFalse(DataValidator.isKeyWordCorrect(""));
    }

    @Test
    void isKeyWordCorrect_fail_onlySpacesInside() {
        assertFalse(DataValidator.isKeyWordCorrect("   "));
    }

    @Test
    void isKeyWordCorrect_pass_anyString() {
        assertTrue(DataValidator.isKeyWordCorrect("  1@-+=~A BCD %2G&lt www.google.pl |& https://github.com/gmarczyk anything"));
    }

}