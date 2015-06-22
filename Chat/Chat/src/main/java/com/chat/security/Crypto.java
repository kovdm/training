package com.chat.security;

public class Cryptography {

	/**
	 * This is implementation of Password-Based Key Derivation Function 2 
	 * (PBKDF 2) on java. PBKDF2 applies a pseudorandom function, such as a 
	 * cryptographic hash, cipher, or HMAC to the input password or passphrase 
	 * along with a salt value and repeats the process many times
	 * to produce a derived key, which can then be used as a cryptographic key
	 * in subsequent operations. The added computational work makes password 
	 * cracking much more difficult, and is known as key stretching.
	 * When the standard was written in 2000, the recommended minimum number 
	 * of iterations was 1000, but the parameter is intended to be increased
	 * over time as CPU speeds increase. Having a salt added to the password 
	 * reduces the ability to use precomputed hashes (rainbow tables) for attacks,
	 * and means that multiple passwords have to be tested individually, 
	 * not all at once. The standard recommends a salt length of at least 64 bits
	 *
	 * General view:
	 * DK = PBKDF2(PRF, Password, Salt, c, dkLen), where
	 * PRF - pseudorandom function with output hLen
	 * Password - master password
	 * Salt - cryptographic salt
	 * c - number of iterations
	 * dkLen - lenght of a derived key
	 * 
	 * Implementation:
	 * PBKDF(String password, int dkLen)
	 * Assuming that PRF - SHA2, Salt
	 */
	 public String PBKDF2(
 
}
