package org.drools.lang.descr;

/*
 * Copyright 2005 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.Serializable;

/**
 * This is the super type for all pattern AST nodes.
 */
public class BaseDescr
    implements
    Serializable {

    private static final long serialVersionUID = 400L;
    private int               startCharacter   = -1;
    private int               endCharacter     = -1;
    private int               line             = -1;
    private int               column           = -1;
    private int               endLine          = -1;
    private int               endColumn        = -1;
    private String            text             = "";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLocation(final int line,
                            final int column) {
        this.line = line;
        this.column = column;
    }

    public void setEndLocation(final int line,
                               final int column) {
        this.endLine = line;
        this.endColumn = column;
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    public int getEndLine() {
        return this.endLine;
    }

    public int getEndColumn() {
        return this.endColumn;
    }

    /**
     * @return the endCharacter
     */
    public int getEndCharacter() {
        return this.endCharacter;
    }

    /**
     * @param endCharacter the endCharacter to set
     */
    public void setEndCharacter(final int endCharacter) {
        this.endCharacter = endCharacter;
    }

    /**
     * @return the startCharacter
     */
    public int getStartCharacter() {
        return this.startCharacter;
    }

    /**
     * @param startCharacter the startCharacter to set
     */
    public void setStartCharacter(final int startCharacter) {
        this.startCharacter = startCharacter;
    }
}