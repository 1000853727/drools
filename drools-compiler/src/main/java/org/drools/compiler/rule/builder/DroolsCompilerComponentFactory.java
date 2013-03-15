package org.drools.compiler.rule.builder;


import org.drools.core.base.FieldDataFactory;
import org.drools.core.base.FieldFactory;
import org.drools.compiler.lang.MVELDumper;
import org.drools.compiler.lang.ExpressionRewriter;

public class DroolsCompilerComponentFactory {

    private ConstraintBuilderFactory constraintBuilderFactory = new DefaultConstraintBuilderFactory();

    public ConstraintBuilderFactory getConstraintBuilderFactoryService() {
        return constraintBuilderFactory;
    }

    public void setConstraintBuilderFactoryProvider( ConstraintBuilderFactory provider ) {
        constraintBuilderFactory = provider;
    }

    public void setDefaultConstraintBuilderFactoryProvider() {
        constraintBuilderFactory = new DefaultConstraintBuilderFactory();
    }




    private ExpressionRewriter expressionProcessor = new MVELDumper();

    public ExpressionRewriter getExpressionProcessor() {
        return expressionProcessor;
    }

    public void setExpressionProcessor( ExpressionRewriter provider ) {
        expressionProcessor = provider;
    }

    public void setDefaultExpressionProcessor() {
        expressionProcessor = new MVELDumper();
    }



    private FieldDataFactory fieldFactory = FieldFactory.getInstance();

    public FieldDataFactory getFieldFactory() {
        return fieldFactory;
    }

    public void setFieldDataFactory( FieldDataFactory provider ) {
        fieldFactory = provider;
    }

    public void setDefaultFieldDataFactory() {
        fieldFactory = FieldFactory.getInstance();
    }


}
