public class Word {
    String word_target;
    String word_explain;
    public Word(){

    }
    public Word(String word_target, String word_explain){
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    @Override
    public String toString() {
        return word_target; //To change body of generated methods, choose Tools | Templates.
    }
}