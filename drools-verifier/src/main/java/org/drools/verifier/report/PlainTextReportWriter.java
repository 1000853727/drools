package org.drools.verifier.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.drools.verifier.data.VerifierReport;
import org.drools.verifier.report.components.Severity;
import org.drools.verifier.report.components.VerifierMessageBase;

/**
 * 
 * @author rikkola
 *
 */
public class PlainTextReportWriter
    implements
    VerifierReportWriter {

    public void writeReport(OutputStream out,
                            VerifierReport result) throws IOException {

        StringBuffer str = new StringBuffer();

        for ( Severity severity : Severity.values() ) {
            Collection<VerifierMessageBase> messages = result.getBySeverity( severity );

            str.append( "************* " );
            str.append( severity.getTuple() );
            str.append( " " );

            str.append( messages.size() );
            str.append( " ******************\n" );
            for ( VerifierMessageBase message : messages ) {
                str.append( message );
                str.append( "\n" );
            }
            str.append( "\n" );
        }

        out.write( str.toString().getBytes() );
    }

}
