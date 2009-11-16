package org.drools.examples.pacman;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.ConsequenceException;

public class Main {
    StatefulKnowledgeSession ksession = null;
    PacMan                   pacMan;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.initKsession();
        main.buildGrid();
        main.initGui();
        main.run();
    }

    public void run() {
        try {
            // run forever
            this.ksession.fireUntilHalt();
        } catch ( ConsequenceException e ) {
            throw e;
        }
    }

    public void initKsession() {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newClassPathResource( "base.drl",
                                                            getClass() ),
                      ResourceType.DRL );        
        kbuilder.add( ResourceFactory.newClassPathResource( "key-handlers.drl",
                                                            getClass() ),
                      ResourceType.DRL );
        kbuilder.add( ResourceFactory.newClassPathResource( "pacman.drl",
                                                            getClass() ),
                      ResourceType.DRL );
        kbuilder.add( ResourceFactory.newClassPathResource( "monster.drl",
                                                            getClass() ),
                      ResourceType.DRL );        

        if ( kbuilder.hasErrors() ) {
            System.out.println( kbuilder.getErrors() );
        }

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );

        this.ksession = kbase.newStatefulKnowledgeSession();

        this.pacMan = new PacMan();
        this.pacMan.setSpeed( 5 );
        this.ksession.insert( this.pacMan );

        Monster monster = new Monster();
        monster.setSpeed( 3 );
        this.ksession.insert( monster );

        this.ksession.insert( new Score() );

        KnowledgeRuntimeLoggerFactory.newThreadedFileLogger( this.ksession,
                                                             "pacman.log",
                                                             3000 );

        Location pacLocation = new Location( this.pacMan,
                                             1,
                                             5 );

        Location monLocation = new Location( monster,
                                             10,
                                             5 );

        this.ksession.insert( pacLocation );
        this.ksession.insert( monLocation );

        Tick tick = new Tick( 0 );
        this.ksession.insert( tick );
    }

    public void buildGrid() throws Exception {

        BufferedReader reader = new BufferedReader( ResourceFactory.newClassPathResource( "grid1.dat",
                                                                                          Main.class ).getReader() );

        String line = null;
        List<String> lines = new ArrayList<String>();
        while ( (line = reader.readLine()) != null ) {
            lines.add( line );
        }

        for ( int row = lines.size() - 1; row >= 0; row-- ) {
            line = lines.get( row );
            int whiteCellCount = 0;
            for ( int col = 0; col < line.length(); col++ ) {
                char c = line.charAt( col );
                
                Cell cell = new Cell( lines.size() - row - 1,
                                      col - whiteCellCount ); // use white spaces for layout, so need to correct
                CellContents contents = null;
                switch ( c ) {
                    case '*' : {
                        contents = new CellContents( cell,
                                                     CellType.WALL );
                        break;
                    }
                    case '.' : {
                        contents = new CellContents( cell,
                                                     CellType.FOOD );
                        break;
                    }
                    case '#' : {
                        contents = new CellContents( cell,
                                                     CellType.POWER_PILL );
                        break;
                    }                    
                    case '_' : {
                        contents = new CellContents( cell,
                                                     CellType.EMPTY );
                        break;
                    }
                    case ' ' : {
                        // ignore, just for spacing
                        whiteCellCount++;
                        break;
                    }
                    default : {
                        throw new IllegalArgumentException( "'" + c + "' is an invalid cell type" );
                    }
                }
                if ( contents != null ) {
                    System.out.println( cell + " : " + contents );
                    ksession.insert( cell );
                    ksession.insert( contents );
                }
            }
        }
    }

    public void initGui() {
        PacmanGui.init( this.ksession );
    }

}
