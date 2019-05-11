package org.prime.test;

import org.junit.Assert;
import org.junit.Test;
import org.prime.PrimeUtil;

/**
 * @author chenjianrong-lhq 2019年03月09日 16:18:30
 * @Description:
 * @ClassName: PrimeUtilTest
 */
public class PrimeUtilTest {

    @Test
    public void testPrimeUtil() {
        int[] expected = {};

        Assert.assertArrayEquals(expected, PrimeUtil.getPrimes(2));
        Assert.assertArrayEquals(expected, PrimeUtil.getPrimes(0));
        Assert.assertArrayEquals(expected, PrimeUtil.getPrimes(-1));
    }

    @Test
    public void testPrimeUtil2() {

        Assert.assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13, 17}, PrimeUtil.getPrimes(19));
        Assert.assertArrayEquals(new int[]{2, 3}, PrimeUtil.getPrimes(5));
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7}, PrimeUtil.getPrimes(8));
    }

    /**
     * @param []
     * @return void
     * @Title: testPrimeUtil3
     * @Description:逆向验证法
     * @author chenjianrong-lhq 2019-03-10 11:15
     */
    @Test
    public void testPrimeUtil3() {
        for (int i = 2; i < 500; i++) {
            verifyPrimeList(PrimeUtil.getPrimes(i));
        }
    }

    private void verifyPrimeList(int[] primes) {
        for (int i = 2; i < primes.length; i++) {
            verifyPrime(primes[i]);
        }
    }

    private void verifyPrime(int prime) {
        for (int i = 2; i < prime/2+1; i++) {
            Assert.assertTrue(prime % i != 0);
        }
    }
}
