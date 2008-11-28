package org.drools.examples;

import java.util.Random;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.audit.WorkingMemoryFileLogger;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.KnowledgeType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class NumberGuessExample {

    public static final void main(String[] args) throws Exception {
        final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newClassPathResource( "NumberGuess.drl",
                                                                    ShoppingExample.class ),
                              KnowledgeType.DRL );
        kbuilder.add( ResourceFactory.newClassPathResource( "NumberGuess.rf",
                                                                    ShoppingExample.class ),
                              KnowledgeType.DRF );

        final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );

        final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        final WorkingMemoryFileLogger logger = new WorkingMemoryFileLogger( ksession );
        logger.setFileName( "log/numberguess" );

        ksession.insert( new GameRules( 100,
                                        5 ) );
        ksession.insert( new RandomNumber() );
        ksession.insert( new Game() );

        ksession.startProcess( "Number Guess" );
        ksession.fireAllRules();

        logger.writeToDisk();

        ksession.dispose();
    }

    public static class RandomNumber {
        private int randomNumber;

        public RandomNumber() {
            this.randomNumber = new Random().nextInt( 100 );
        }

        public int getValue() {
            return this.randomNumber;
        }
    }

    public static class Guess {
        private int value;

        public Guess(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public String toString() {
            return "Guess " + this.value;
        }
    }

    public static class GameRules {
        private int maxRange;
        private int allowedGuesses;

        public GameRules(int maxRange,
                         int allowedGuesses) {
            this.maxRange = maxRange;
            this.allowedGuesses = allowedGuesses;
        }

        public int getAllowedGuesses() {
            return allowedGuesses;
        }

        public int getMaxRange() {
            return maxRange;
        }

    }

    public static class Game {
        private int biggest;
        private int smallest;
        private int guessCount;

        public Game() {
            this.guessCount = 0;
            this.biggest = 0;
            this.smallest = 100;
        }

        public void incrementGuessCount() {
            guessCount++;
        }

        public int getBiggest() {
            return this.biggest;
        }

        public int getSmallest() {
            return this.smallest;
        }

        public int getGuessCount() {
            return this.guessCount;
        }

        public void setGuessCount(int guessCount) {
            this.guessCount = guessCount;
        }

        public void setBiggest(int biggest) {
            this.biggest = biggest;
        }

        public void setSmallest(int smallest) {
            this.smallest = smallest;
        }
    }
}
