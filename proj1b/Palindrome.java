public class Palindrome {


    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> letters = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            letters.addLast(word.charAt(i));
        }
        return letters;
    }

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

    private boolean isPalinDromeHelper(Deque<Character> wordDeque) {
        if (wordDeque.size() <= 1) {
            return true;
        }

        return wordDeque.removeFirst() == wordDeque.removeLast()
                && isPalinDromeHelper(wordDeque);
    }
}
