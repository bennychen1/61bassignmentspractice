public class Palindrome {

    /** Creates and returns a Deque from the characters of WORD. */
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> letters = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            letters.addLast(word.charAt(i));
        }
        return letters;
    }

    /** Returns TRUE if WORD is a palindrome, FALSE otherwise */
    public boolean isPalindrome(String word) {
        /*Deque<Character> regWord = wordToDeque(word);

        while (regWord.size() > 1) {
            if (regWord.removeFirst() != regWord.removeLast()) {
                return false;
            }
        }
        return true; */

        return isPalinDromeHelper(wordToDeque(word));

    }

    /** Helper method for isPalindrome. Returns TRUE is the characters in WORDDEQUE
     * are from a palindrome. */
    private boolean isPalinDromeHelper(Deque<Character> wordDeque) {
        if (wordDeque.size() <= 1) {
            return true;
        }

        return wordDeque.removeFirst() == wordDeque.removeLast()
                && isPalinDromeHelper(wordDeque);
    }

    /** Returns true if WORD is a palindrome according to the definition of equal characters
     * provided by character comparator CC. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeCCHelper(wordToDeque(word), cc);
    }

    /** Helper function for isPalindrome with a character comparator. */
    private boolean isPalindromeCCHelper(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() <= 1) {
            return true;
        }

        return cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast()) &&
                isPalindromeCCHelper(wordDeque, cc);
    }
}
