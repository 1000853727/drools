package org.drools.common;

import org.drools.Cheese;
import org.drools.base.evaluators.Operator;
import org.drools.rule.VariableConstraint;

public class DefaultBetaConstraintsTest extends BaseBetaConstraintsTest {
    
    public void testNoIndexConstraints() {
        VariableConstraint constraint0 = ( VariableConstraint ) getConstraint( "cheeseType0", Operator.NOT_EQUAL, "type", Cheese.class );        
        VariableConstraint[] constraints = new VariableConstraint[] { constraint0 };        
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );
        
        VariableConstraint constraint1 = ( VariableConstraint ) getConstraint( "cheeseType1", Operator.NOT_EQUAL, "type", Cheese.class );        
        constraints = new VariableConstraint[] { constraint0, constraint1 };        
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );        
        
        VariableConstraint constraint2 = ( VariableConstraint ) getConstraint( "cheeseType2", Operator.NOT_EQUAL, "type", Cheese.class );        
        constraints = new VariableConstraint[] { constraint0, constraint1, constraint2 };        
        checkBetaConstraints( constraints, DefaultBetaConstraints.class ); 
        
        VariableConstraint constraint3 = ( VariableConstraint ) getConstraint( "cheeseType3", Operator.NOT_EQUAL, "type", Cheese.class );        
        constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3 };
        checkBetaConstraints( constraints, DefaultBetaConstraints.class ); 
        
        VariableConstraint constraint4 = ( VariableConstraint ) getConstraint( "cheeseType4", Operator.NOT_EQUAL, "type", Cheese.class );        
        constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3, constraint4 }; 
        checkBetaConstraints( constraints, DefaultBetaConstraints.class ); 
        
        VariableConstraint constraint5 = ( VariableConstraint ) getConstraint( "cheeseType5", Operator.NOT_EQUAL, "type", Cheese.class );        
        constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3,constraint5 };   
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );     
        
        VariableConstraint constraint6 = ( VariableConstraint ) getConstraint( "cheeseType6", Operator.NOT_EQUAL, "type", Cheese.class );        
        constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3, constraint4, constraint5, constraint6 };   
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );             
    }    
    
    public void testIndexedConstraint() {
        VariableConstraint constraint0 = ( VariableConstraint ) getConstraint( "cheeseType0", Operator.EQUAL, "type", Cheese.class );        
        VariableConstraint[] constraints = new VariableConstraint[] { constraint0 };        
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );
        
        VariableConstraint constraint1 = ( VariableConstraint ) getConstraint( "cheeseType1", Operator.EQUAL, "type", Cheese.class );        
        constraints = new VariableConstraint[] { constraint0, constraint1 };        
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );        
        
        VariableConstraint constraint2 = ( VariableConstraint ) getConstraint( "cheeseType2", Operator.EQUAL, "type", Cheese.class );        
        constraints = new VariableConstraint[] { constraint0, constraint1, constraint2 };        
        checkBetaConstraints( constraints, DefaultBetaConstraints.class ); 
        
        VariableConstraint constraint3 = ( VariableConstraint ) getConstraint( "cheeseType3", Operator.EQUAL, "type", Cheese.class );        
        constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3 };
        checkBetaConstraints( constraints, DefaultBetaConstraints.class ); 
        
        VariableConstraint constraint4 = ( VariableConstraint ) getConstraint( "cheeseType4", Operator.EQUAL, "type", Cheese.class );        
        constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3, constraint4 }; 
        checkBetaConstraints( constraints, DefaultBetaConstraints.class ); 
        
        VariableConstraint constraint5 = ( VariableConstraint ) getConstraint( "cheeseType5", Operator.EQUAL, "type", Cheese.class );        
        constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3, constraint4, constraint5 };   
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );     
        
        VariableConstraint constraint6 = ( VariableConstraint ) getConstraint( "cheeseType6", Operator.EQUAL, "type", Cheese.class );        
        constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3, constraint4, constraint5, constraint6 };   
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );          
    }        
    
    
    public void testSingleIndex() {
        VariableConstraint constraint0 = ( VariableConstraint ) getConstraint( "cheeseType1", Operator.EQUAL, "type", Cheese.class );
        VariableConstraint constraint1 = ( VariableConstraint ) getConstraint( "cheeseType2", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint2 = ( VariableConstraint ) getConstraint( "cheeseType3", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint3 = ( VariableConstraint ) getConstraint( "cheeseType4", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint4 = ( VariableConstraint ) getConstraint( "cheeseType5", Operator.NOT_EQUAL, "type", Cheese.class );
        
        VariableConstraint[] constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3, constraint4 };
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );    
    }   
    
    public void testSingleIndexNotFirst() {
        VariableConstraint constraint0 = ( VariableConstraint ) getConstraint( "cheeseType1", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint1 = ( VariableConstraint ) getConstraint( "cheeseType2", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint2 = ( VariableConstraint ) getConstraint( "cheeseType3", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint3 = ( VariableConstraint ) getConstraint( "cheeseType4", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint4 = ( VariableConstraint ) getConstraint( "cheeseType5", Operator.EQUAL, "type", Cheese.class );
        
        VariableConstraint[] constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3, constraint4 };
        
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );         
    }    
    
    public void testDoubleIndex() {
        VariableConstraint constraint0 = ( VariableConstraint ) getConstraint( "cheeseType1", Operator.EQUAL, "type", Cheese.class );
        VariableConstraint constraint1 = ( VariableConstraint ) getConstraint( "cheeseType2", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint2 = ( VariableConstraint ) getConstraint( "cheeseType3", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint3 = ( VariableConstraint ) getConstraint( "cheeseType4", Operator.EQUAL, "type", Cheese.class );
        VariableConstraint constraint4 = ( VariableConstraint ) getConstraint( "cheeseType5", Operator.NOT_EQUAL, "type", Cheese.class );
        
        VariableConstraint[] constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3, constraint4 };
        
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );       
    }   
    
    public void testDoubleIndexNotFirst() {
        VariableConstraint constraint0 = ( VariableConstraint ) getConstraint( "cheeseType1", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint1 = ( VariableConstraint ) getConstraint( "cheeseType2", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint2 = ( VariableConstraint ) getConstraint( "cheeseType3", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint3 = ( VariableConstraint ) getConstraint( "cheeseType4", Operator.EQUAL, "type", Cheese.class );
        VariableConstraint constraint4 = ( VariableConstraint ) getConstraint( "cheeseType5", Operator.EQUAL, "type", Cheese.class );
        
        VariableConstraint[] constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3, constraint4 };
        
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );       
    }       
    
    
    public void testTripleIndex() {
        VariableConstraint constraint0 = ( VariableConstraint ) getConstraint( "cheeseType1", Operator.EQUAL, "type", Cheese.class );
        VariableConstraint constraint1 = ( VariableConstraint ) getConstraint( "cheeseType2", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint2 = ( VariableConstraint ) getConstraint( "cheeseType3", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint3 = ( VariableConstraint ) getConstraint( "cheeseType4", Operator.EQUAL, "type", Cheese.class );
        VariableConstraint constraint4 = ( VariableConstraint ) getConstraint( "cheeseType5", Operator.EQUAL, "type", Cheese.class );
        
        VariableConstraint[] constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3, constraint4 };
        
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );               
    }      
    
    public void testTripleIndexNotFirst() {
        VariableConstraint constraint0 = ( VariableConstraint ) getConstraint( "cheeseType1", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint1 = ( VariableConstraint ) getConstraint( "cheeseType2", Operator.EQUAL, "type", Cheese.class );
        VariableConstraint constraint2 = ( VariableConstraint ) getConstraint( "cheeseType3", Operator.NOT_EQUAL, "type", Cheese.class );
        VariableConstraint constraint3 = ( VariableConstraint ) getConstraint( "cheeseType4", Operator.EQUAL, "type", Cheese.class );
        VariableConstraint constraint4 = ( VariableConstraint ) getConstraint( "cheeseType5", Operator.EQUAL, "type", Cheese.class );
        
        VariableConstraint[] constraints = new VariableConstraint[] { constraint0, constraint1, constraint2, constraint3, constraint4 };
        
        checkBetaConstraints( constraints, DefaultBetaConstraints.class );               
    }     

}
