package org.drools.compiler;

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

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.drools.RuleBase;
import org.drools.base.ClassFieldAccessor;
import org.drools.base.ClassFieldAccessorCache;
import org.drools.base.ClassFieldReader;
import org.drools.base.ClassFieldWriter;
import org.drools.base.ClassTypeResolver;
import org.drools.base.TypeResolver;
import org.drools.commons.jci.problems.CompilationProblem;
import org.drools.factmodel.ClassBuilder;
import org.drools.factmodel.ClassDefinition;
import org.drools.factmodel.FieldDefinition;
import org.drools.facttemplates.FactTemplate;
import org.drools.facttemplates.FactTemplateImpl;
import org.drools.facttemplates.FieldTemplate;
import org.drools.facttemplates.FieldTemplateImpl;
import org.drools.lang.descr.AttributeDescr;
import org.drools.lang.descr.BaseDescr;
import org.drools.lang.descr.FactTemplateDescr;
import org.drools.lang.descr.FieldTemplateDescr;
import org.drools.lang.descr.FunctionDescr;
import org.drools.lang.descr.FunctionImportDescr;
import org.drools.lang.descr.GlobalDescr;
import org.drools.lang.descr.ImportDescr;
import org.drools.lang.descr.PackageDescr;
import org.drools.lang.descr.QueryDescr;
import org.drools.lang.descr.RuleDescr;
import org.drools.lang.descr.TypeDeclarationDescr;
import org.drools.lang.descr.TypeFieldDescr;
import org.drools.process.core.Process;
import org.drools.reteoo.ReteooRuleBase;
import org.drools.rule.ImportDeclaration;
import org.drools.rule.Package;
import org.drools.rule.Rule;
import org.drools.rule.TypeDeclaration;
import org.drools.rule.builder.RuleBuildContext;
import org.drools.rule.builder.RuleBuilder;
import org.drools.spi.InternalReadAccessor;
import org.drools.xml.XmlPackageReader;
import org.drools.xml.XmlProcessReader;
import org.xml.sax.SAXException;

/**
 * This is the main compiler class for parsing and compiling rules and
 * assembling or merging them into a binary Package instance. This can be done
 * by merging into existing binary packages, or totally from source.
 * 
 * If you are using the Java dialect the JavaDialectConfiguration will attempt
 * to validate that the specified compiler is in the classpath, using
 * ClassLoader.loasClass(String). If you intented to just Janino sa the compiler
 * you must either overload the compiler property before instantiating this
 * class or the PackageBuilder, or make sure Eclipse is in the classpath, as
 * Eclipse is the default.
 */
public class PackageBuilder {

    //private DialectRegistry              dialectRegistry;

    private Map<String, PackageRegistry> pkgRegistryMap;

    private List                         results;

    private PackageBuilderConfiguration  configuration;

    private ClassFieldAccessorCache      classFieldExtractorCache;

    public static final RuleBuilder      ruleBuilder = new RuleBuilder();

    /**
     * Optional RuleBase for incremental live building
     */
    private ReteooRuleBase               ruleBase;

    /**
     * Current default package
     */
    private String                       defaultNamespace;

    /**
     * default dialect
     */
    private final String                 defaultDialect;

    /**
     * Use this when package is starting from scratch.
     */
    public PackageBuilder() {
        this( (RuleBase) null,
              null );
    }

    /**
     * This will allow you to merge rules into this pre existing package.
     */

    public PackageBuilder(final Package pkg) {
        this( pkg,
              null );
    }

    public PackageBuilder(final RuleBase ruleBase) {
        this( ruleBase,
              null );
    }

    /**
     * Pass a specific configuration for the PackageBuilder
     * 
     * PackageBuilderConfiguration is not thread safe and it also contains
     * state. Once it is created and used in one or more PackageBuilders it
     * should be considered immutable. Do not modify its properties while it is
     * being used by a PackageBuilder.
     * 
     * @param configuration
     */
    public PackageBuilder(final PackageBuilderConfiguration configuration) {
        this( (RuleBase) null,
              configuration );
    }

    //    /**
    //     * This allows you to pass in a pre existing package, and a configuration
    //     * (for instance to set the classloader).
    //     * 
    //     * @param pkg
    //     *            A pre existing package (can be null if none exists)
    //     * @param configuration
    //     *            Optional configuration for this builder.
    //     */
    //    public PackageBuilder(final Package pkg,
    //                          PackageBuilderConfiguration configuration) {
    //        if ( configuration == null ) {
    //            configuration = new PackageBuilderConfiguration();
    //        }
    //
    //        this.configuration = configuration;
    //        this.results = new ArrayList();
    //        this.pkg = pkg;
    //        this.classFieldExtractorCache = ClassFieldAccessorCache.getInstance();
    //
    //        if ( this.pkg != null ) {
    //            ClassLoader cl = this.pkg.getDialectRuntimeRegistry().getClassLoader();
    //            this.typeResolver = new ClassTypeResolver( new HashSet<String>( this.pkg.getImports().keySet() ),
    //                                                       cl );
    //            // make an automatic import for the current package
    //            this.typeResolver.addImport( this.pkg.getName() + ".*" );
    //        } else {
    //            // this.typeResolver = new ClassTypeResolver( new HashSet<String>(),
    //            // this.configuration.getClassLoader() );
    //        }
    //
    //        this.dialectRegistry = this.configuration.buildDialectRegistry();
    //
    //        this.dialect = this.dialectRegistry.getDialect( this.configuration.getDefaultDialect() );
    //
    //        if ( this.pkg != null ) {
    //            this.dialectRegistry.initAll( this );
    //        }
    //
    //    }

    public PackageBuilder(Package pkg,
                          PackageBuilderConfiguration configuration) {
        if ( configuration == null ) {
            configuration = new PackageBuilderConfiguration();
        }
        this.configuration = configuration;

        this.defaultNamespace = pkg.getName();
        this.defaultDialect = this.configuration.getDefaultDialect();

        this.pkgRegistryMap = new HashMap<String, PackageRegistry>();
        this.results = new ArrayList();
        this.classFieldExtractorCache = ClassFieldAccessorCache.getInstance();

        PackageRegistry pkgRegistry = new PackageRegistry( this,
                                                           pkg );
        pkgRegistry.setDialect( this.defaultDialect );
        this.pkgRegistryMap.put( pkg.getName(),
                                 pkgRegistry );
    }

    public PackageBuilder(RuleBase ruleBase,
                          PackageBuilderConfiguration configuration) {
        if ( configuration == null ) {
            configuration = new PackageBuilderConfiguration();
        }
        this.configuration = configuration;

        // FIXME, we need to get drools to support "default" namespace.
        //this.defaultNamespace = pkg.getName();        
        this.defaultDialect = this.configuration.getDefaultDialect();

        this.pkgRegistryMap = new HashMap<String, PackageRegistry>();
        this.results = new ArrayList();
        this.classFieldExtractorCache = ClassFieldAccessorCache.getInstance();

        this.ruleBase = (ReteooRuleBase) ruleBase;
    }

    /**
     * Load a rule package from DRL source.
     * 
     * @param reader
     * @throws DroolsParserException
     * @throws IOException
     */
    public void addPackageFromDrl(final Reader reader) throws DroolsParserException,
                                                      IOException {
        final DrlParser parser = new DrlParser();
        final PackageDescr pkg = parser.parse( reader );
        this.results.addAll( parser.getErrors() );
        addPackage( pkg );
    }

    /**
     * Load a rule package from XML source.
     * 
     * @param reader
     * @throws DroolsParserException
     * @throws IOException
     */
    public void addPackageFromXml(final Reader reader) throws DroolsParserException,
                                                      IOException {
        final XmlPackageReader xmlReader = new XmlPackageReader( this.configuration.getSemanticModules() );

        try {
            xmlReader.read( reader );
        } catch ( final SAXException e ) {
            throw new DroolsParserException( e.toString(),
                                             e.getCause() );
        }

        addPackage( xmlReader.getPackageDescr() );
    }

    /**
     * Load a rule package from DRL source using the supplied DSL configuration.
     * 
     * @param source
     *            The source of the rules.
     * @param dsl
     *            The source of the domain specific language configuration.
     * @throws DroolsParserException
     * @throws IOException
     */
    public void addPackageFromDrl(final Reader source,
                                  final Reader dsl) throws DroolsParserException,
                                                   IOException {
        final DrlParser parser = new DrlParser();
        final PackageDescr pkg = parser.parse( source,
                                               dsl );
        this.results.addAll( parser.getErrors() );
        addPackage( pkg );
    }

    /**
     * Add a ruleflow (.rfm) asset to this package.
     */
    public void addRuleFlow(Reader processSource) {
        ProcessBuilder processBuilder = new ProcessBuilder( this );

        try {
            processBuilder.addProcessFromFile( processSource );
            this.results.addAll( processBuilder.getErrors() );
        } catch ( Exception e ) {
            if ( e instanceof RuntimeException ) {
                throw (RuntimeException) e;
            }
            this.results.add( new RuleFlowLoadError( "Unable to load the rule flow.",
                                                     e ) );
        }

        this.results = getResults( this.results );
    }

    public void addProcessFromXml(Reader reader) {
        ProcessBuilder processBuilder = new ProcessBuilder( this );
        XmlProcessReader xmlReader = new XmlProcessReader( this.configuration.getSemanticModules() );
        try {
            Process process = xmlReader.read( reader );
            processBuilder.buildProcess( process );
            this.results.addAll( processBuilder.getErrors() );
        } catch ( Exception e ) {
            if ( e instanceof RuntimeException ) {
                throw (RuntimeException) e;
            }
            this.results.add( new RuleFlowLoadError( "Unable to load the rule flow.",
                                                     e ) );
        }

        this.results = getResults( this.results );
    }

    private void addSemanticModules() {
        // this.configuration.getSemanticModules();
    }

    /**
     * This adds a package from a Descr/AST This will also trigger a compile, if
     * there are any generated classes to compile of course.
     */
    public void addPackage(final PackageDescr packageDescr) {
        //validatePackageName( packageDescr );
        validateUniqueRuleNames( packageDescr );

        String dialectName = this.defaultDialect;
        // see if this packageDescr overrides the current default dialect
        for ( Iterator it = packageDescr.getAttributes().iterator(); it.hasNext(); ) {
            AttributeDescr value = (AttributeDescr) it.next();
            if ( "dialect".equals( value.getName() ) ) {
                dialectName = value.getValue();
                break;
            }
        }

        if ( !isEmpty( packageDescr.getNamespace() ) ) {
            // use the default namespace
            this.defaultNamespace = packageDescr.getNamespace();
        } else {
            // packagedescr defines a new default namespace
            packageDescr.setNamespace( this.defaultNamespace );
        }

        PackageRegistry pkgRegistry = this.pkgRegistryMap.get( packageDescr.getNamespace() );
        if ( pkgRegistry == null ) {
            // initialise the package and namespace if it hasn't been used before
            pkgRegistry = newPackage( packageDescr );
        } else {
            // merge into existing package
            mergePackage( packageDescr );
        }

        // set the default dialect for this package
        pkgRegistry.setDialect( dialectName );

        // only try to compile if there are no parse errors
        if ( !hasErrors() ) {
            for ( final Iterator it = packageDescr.getFactTemplates().iterator(); it.hasNext(); ) {
                addFactTemplate( (FactTemplateDescr) it.next() );
            }

            if ( !packageDescr.getFunctions().isEmpty() ) {

                for ( final Iterator it = packageDescr.getFunctions().iterator(); it.hasNext(); ) {
                    FunctionDescr functionDescr = (FunctionDescr) it.next();
                    if ( isEmpty( functionDescr.getNamespace() ) ) {
                        // make sure namespace is set on components
                        functionDescr.setNamespace( this.defaultNamespace );
                    }
                    if ( isEmpty( functionDescr.getDialect() ) ) {
                        // make sure namespace is set on components
                        functionDescr.setDialect( pkgRegistry.getDialect() );
                    }
                    preCompileAddFunction( functionDescr );
                }

                // iterate and compile
                for ( final Iterator it = packageDescr.getFunctions().iterator(); it.hasNext(); ) {
                    // inherit the dialect from the package
                    FunctionDescr functionDescr = (FunctionDescr) it.next();
                    addFunction( functionDescr );
                }

                // We need to compile all the functions now, so scripting
                // languages like mvel can find them
                compileAll();

                for ( final Iterator it = packageDescr.getFunctions().iterator(); it.hasNext(); ) {
                    FunctionDescr functionDescr = (FunctionDescr) it.next();
                    postCompileAddFunction( functionDescr );
                }
            }

            // iterate and compile
            for ( final Iterator it = packageDescr.getRules().iterator(); it.hasNext(); ) {
                RuleDescr ruleDescr = (RuleDescr) it.next();
                if ( isEmpty( ruleDescr.getNamespace() ) ) {
                    // make sure namespace is set on components
                    ruleDescr.setNamespace( this.defaultNamespace );
                }
                if ( isEmpty( ruleDescr.getDialect() ) ) {
                    ruleDescr.setDialect( pkgRegistry.getDialect() );
                }
                addRule( ruleDescr );
            }
        }

        compileAll();
        reloadAll();

        // some of the rules and functions may have been redefined     
        this.results = getResults( this.results );

        // iterate and compile
        if ( this.ruleBase != null ) {
            for ( final Iterator it = packageDescr.getRules().iterator(); it.hasNext(); ) {
                RuleDescr ruleDescr = (RuleDescr) it.next();
                pkgRegistry = this.pkgRegistryMap.get( ruleDescr.getNamespace() );
                this.ruleBase.addRule( pkgRegistry.getPackage(),
                                       pkgRegistry.getPackage().getRule( ruleDescr.getName() ) );
            }
        }
    }

    public boolean isEmpty(String string) {
        return (string == null || string.trim().length() == 0);
    }

    private void compileAll() {
        for ( PackageRegistry pkgRegistry : this.pkgRegistryMap.values() ) {
            pkgRegistry.compileAll();
        }
    }

    private void reloadAll() {
        for ( PackageRegistry pkgRegistry : this.pkgRegistryMap.values() ) {
            pkgRegistry.getDialectRuntimeRegistry().reloadDirty();
        }
    }

    private List getResults(List results) {
        for ( PackageRegistry pkgRegistry : this.pkgRegistryMap.values() ) {
            results = pkgRegistry.getDialectCompiletimeRegistry().addResults( results );
        }
        return results;
    }

    //
    //    private void validatePackageName(final PackageDescr packageDescr) {
    //        if ( (this.pkg == null || this.pkg.getName() == null || this.pkg.getName().equals( "" )) && (packageDescr.getName() == null || "".equals( packageDescr.getName() )) ) {
    //            throw new MissingPackageNameException( "Missing package name for rule package." );
    //        }
    //        if ( this.pkg != null && packageDescr.getName() != null && !"".equals( packageDescr.getName() ) && !this.pkg.getName().equals( packageDescr.getName() ) ) {
    //            throw new PackageMergeException( "Can't merge packages with different names. This package: " + this.pkg.getName() + " - New package: " + packageDescr.getName() );
    //        }
    //        return;
    //    }

    private void validateUniqueRuleNames(final PackageDescr packageDescr) {
        final Set names = new HashSet();
        String namespace = packageDescr.getNamespace();
        for ( final Iterator iter = packageDescr.getRules().iterator(); iter.hasNext(); ) {
            final RuleDescr rule = (RuleDescr) iter.next();
            final String name = rule.getName();
            if ( names.contains( name ) ) {
                this.results.add( new ParserError( "Duplicate rule name: " + name,
                                                   rule.getLine(),
                                                   rule.getColumn() ) );
            }
            names.add( name );
        }
    }

    private PackageRegistry newPackage(final PackageDescr packageDescr) {
        Package pkg;
        if ( this.ruleBase != null && this.ruleBase.getPackage( packageDescr.getName() ) != null ) {
            // there is a rulebase and it already defines this package so use it.
            pkg = this.ruleBase.getPackage( packageDescr.getName() );
        } else {
            // define a new package
            pkg = new Package( packageDescr.getName(),
                               this.configuration.getClassLoader() );

            // if there is a rulebase then add the package.
            if ( this.ruleBase != null ) {
                this.ruleBase.addPackage( pkg );
                pkg = this.ruleBase.getPackage( packageDescr.getName() );
            }
        }

        PackageRegistry pkgRegistry = new PackageRegistry( this,
                                                           pkg );
        
        // add default import for this namespace
        pkgRegistry.addImport( packageDescr.getNamespace() + ".*" );

        this.pkgRegistryMap.put( packageDescr.getName(),
                                 pkgRegistry );

        mergePackage( packageDescr );

        return pkgRegistry;
    }

    private void mergePackage(final PackageDescr packageDescr) {
        PackageRegistry pkgRegistry;
        if ( isEmpty( packageDescr.getName() ) ) {
            pkgRegistry = this.pkgRegistryMap.get( this.defaultNamespace );
        } else {
            pkgRegistry = this.pkgRegistryMap.get( packageDescr.getNamespace() );
        }

        final List imports = packageDescr.getImports();
        for ( final Iterator it = imports.iterator(); it.hasNext(); ) {
            ImportDescr importEntry = (ImportDescr) it.next();
            pkgRegistry.addImport( importEntry.getTarget() );
        }

        processTypeDeclarations( packageDescr );

        for ( final Iterator it = packageDescr.getFunctionImports().iterator(); it.hasNext(); ) {
            String importEntry = ((FunctionImportDescr) it.next()).getTarget();
            pkgRegistry.addStaticImport( importEntry );
            pkgRegistry.getPackage().addStaticImport( importEntry );
        }

        final List globals = packageDescr.getGlobals();
        for ( final Iterator it = globals.iterator(); it.hasNext(); ) {
            final GlobalDescr global = (GlobalDescr) it.next();
            final String identifier = global.getIdentifier();
            final String className = global.getType();

            Class clazz;
            try {
                clazz = pkgRegistry.getTypeResolver().resolveType( className );
                pkgRegistry.getPackage().addGlobal( identifier,
                                                    clazz );
            } catch ( final ClassNotFoundException e ) {
                this.results.add( new GlobalError( identifier,
                                                   global.getLine() ) );
            }
        }

    }

    /**
     * @param packageDescr
     */
    private void processTypeDeclarations(final PackageDescr packageDescr) {
        PackageRegistry defaultRegistry;
        if ( isEmpty( packageDescr.getName() ) ) {
            defaultRegistry = this.pkgRegistryMap.get( this.defaultNamespace );
        } else {
            defaultRegistry = this.pkgRegistryMap.get( packageDescr.getNamespace() );
        }

        PackageRegistry pkgRegistry = null;
        for ( TypeDeclarationDescr typeDescr : packageDescr.getTypeDeclarations() ) {
            // make sure namespace is set on components
            if ( isEmpty( typeDescr.getNamespace() ) ) {
                // use the default namespace 
                typeDescr.setNamespace( defaultRegistry.getPackage().getName() );
                pkgRegistry = defaultRegistry;
            } else {
                // use the namespace specified by the type declaration
                pkgRegistry = this.pkgRegistryMap.get( typeDescr.getNamespace() );
            }

            TypeDeclaration type = new TypeDeclaration( typeDescr.getTypeName() );

            // is it a regular fact or an event?
            String role = typeDescr.getMetaAttribute( TypeDeclaration.Role.ID );
            if ( role != null ) {
                type.setRole( TypeDeclaration.Role.parseRole( role ) );
            }

            // is it a POJO or a template?
            String templateName = typeDescr.getMetaAttribute( TypeDeclaration.ATTR_TEMPLATE );
            if ( templateName != null ) {
                type.setFormat( TypeDeclaration.Format.TEMPLATE );
                FactTemplate template = pkgRegistry.getPackage().getFactTemplate( templateName );
                if ( template != null ) {
                    type.setTypeTemplate( template );
                } else {
                    this.results.add( new TypeDeclarationError( "Template not found '" + template + "' for type '" + type.getTypeName() + "'",
                                                                typeDescr.getLine() ) );
                    continue;
                }
            } else {
                String className = typeDescr.getMetaAttribute( TypeDeclaration.ATTR_CLASS );
                if ( className == null ) {
                    className = type.getTypeName();
                }
                type.setFormat( TypeDeclaration.Format.POJO );
                Class clazz;
                try {
                    if ( typeDescr.getFields().size() > 0 ) {
                        // generate the bean if its needed
                        generateDeclaredBean( typeDescr,
                                              type,
                                              pkgRegistry );
                    }
                    clazz = pkgRegistry.getTypeResolver().resolveType( className );
                    type.setTypeClass( clazz );
                    if ( type.getTypeClassDef() != null ) {
                        try {
                            buildFieldAccessors( type,
                                                 pkgRegistry );
                        } catch ( Exception e ) {
                            this.results.add( new TypeDeclarationError( "Error creating field accessors for '" + className + "' for type '" + type.getTypeName() + "'",
                                                                        typeDescr.getLine() ) );
                            continue;
                        }
                    }
                } catch ( final ClassNotFoundException e ) {

                    this.results.add( new TypeDeclarationError( "Class not found '" + className + "' for type '" + type.getTypeName() + "'",
                                                                typeDescr.getLine() ) );
                    continue;
                }
            }

            String timestamp = typeDescr.getMetaAttribute( TypeDeclaration.ATTR_TIMESTAMP );
            if ( timestamp != null ) {
                type.setTimestampAttribute( timestamp );
            }
            String duration = typeDescr.getMetaAttribute( TypeDeclaration.ATTR_DURATION );
            if ( duration != null ) {
                type.setDurationAttribute( duration );
                InternalReadAccessor extractor = ClassFieldAccessorCache.getInstance().getReader( type.getTypeClass(),
                                                                                                  duration,
                                                                                                  this.configuration.getClassLoader() );
                type.setDurationExtractor( extractor );
            }

            pkgRegistry.getPackage().addTypeDeclaration( type );
        }
    }

    /**
     * 
     * @param registry 
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws IntrospectionException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException
     */
    private final void buildFieldAccessors(final TypeDeclaration type,
                                           PackageRegistry registry) throws SecurityException,
                                                                    IllegalArgumentException,
                                                                    InstantiationException,
                                                                    IllegalAccessException,
                                                                    IOException,
                                                                    IntrospectionException,
                                                                    ClassNotFoundException,
                                                                    NoSuchMethodException,
                                                                    InvocationTargetException,
                                                                    NoSuchFieldException {
        ClassFieldAccessorCache cache = ClassFieldAccessorCache.getInstance();
        ClassDefinition cd = type.getTypeClassDef();

        for ( FieldDefinition attrDef : cd.getFieldsDefinitions() ) {
            ClassFieldReader reader = cache.getReader( cd.getDefinedClass(),
                                                       attrDef.getName(),
                                                       registry.getPackage().getDialectRuntimeRegistry().getClassLoader() );
            ClassFieldWriter writer = cache.getWriter( cd.getDefinedClass(),
                                                       attrDef.getName(),
                                                       registry.getPackage().getDialectRuntimeRegistry().getClassLoader() );
            ClassFieldAccessor accessor = new ClassFieldAccessor( reader,
                                                                  writer );
            attrDef.setFieldAccessor( accessor );
        }
    }

    /**
     * Generates a bean, and adds it to the composite class loader that
     * everything is using.
     * 
     */
    private void generateDeclaredBean(TypeDeclarationDescr typeDescr,
                                      TypeDeclaration type,
                                      PackageRegistry pkgRegistry) {
        // need to fix classloader?
        ClassBuilder cb = new ClassBuilder();
        String fullName = typeDescr.getNamespace() + "." + typeDescr.getTypeName();
        ClassDefinition def = new ClassDefinition( fullName );
        Map<String, TypeFieldDescr> flds = typeDescr.getFields();
        try {
            for ( TypeFieldDescr field : flds.values() ) {
                String fullFieldType = pkgRegistry.getTypeResolver().resolveType( field.getPattern().getObjectType() ).getName();
                def.addField( new FieldDefinition( field.getFieldName(),
                                                   fullFieldType ) );
            }

            byte[] d = cb.buildClass( def );
            pkgRegistry.getPackage().getPackageScopeClassLoader().addClass( fullName,
                                                                            d );
            type.setTypeClassDef( def );
        } catch ( Exception e ) {
            e.printStackTrace();
            this.results.add( new TypeDeclarationError( "Unable to create a class for declared type " + fullName + ": " + e.getMessage() + ";",
                                                        typeDescr.getLine() ) );
        }
    }

    private void addFunction(final FunctionDescr functionDescr) {
        PackageRegistry pkgRegistry = this.pkgRegistryMap.get( functionDescr.getNamespace() );
        Dialect dialect = pkgRegistry.getDialectCompiletimeRegistry().getDialect( functionDescr.getDialect() );
        dialect.addFunction( functionDescr,
                             pkgRegistry.getTypeResolver() );
    }

    private void preCompileAddFunction(final FunctionDescr functionDescr) {
        PackageRegistry pkgRegistry = this.pkgRegistryMap.get( functionDescr.getNamespace() );
        Dialect dialect = pkgRegistry.getDialectCompiletimeRegistry().getDialect( functionDescr.getDialect() );
        dialect.preCompileAddFunction( functionDescr,
                                       pkgRegistry.getTypeResolver() );
    }

    private void postCompileAddFunction(final FunctionDescr functionDescr) {
        PackageRegistry pkgRegistry = this.pkgRegistryMap.get( functionDescr.getNamespace() );
        Dialect dialect = pkgRegistry.getDialectCompiletimeRegistry().getDialect( functionDescr.getDialect() );
        dialect.postCompileAddFunction( functionDescr,
                                        pkgRegistry.getTypeResolver() );
    }

    private void addFactTemplate(final FactTemplateDescr factTemplateDescr) {
        final List fields = new ArrayList();
        int index = 0;
        PackageRegistry pkgRegistry = this.pkgRegistryMap.get( this.defaultNamespace );
        for ( final Iterator it = factTemplateDescr.getFields().iterator(); it.hasNext(); ) {
            final FieldTemplateDescr fieldTemplateDescr = (FieldTemplateDescr) it.next();
            FieldTemplate fieldTemplate = null;
            try {
                fieldTemplate = new FieldTemplateImpl( fieldTemplateDescr.getName(),
                                                       index++,
                                                       pkgRegistry.getTypeResolver().resolveType( fieldTemplateDescr.getClassType() ) );
            } catch ( final ClassNotFoundException e ) {
                this.results.add( new FieldTemplateError( pkgRegistry.getPackage(),
                                                          fieldTemplateDescr,
                                                          null,
                                                          "Unable to resolve Class '" + fieldTemplateDescr.getClassType() + "'" ) );
            }
            fields.add( fieldTemplate );
        }

        final FactTemplate factTemplate = new FactTemplateImpl( pkgRegistry.getPackage(),
                                                                factTemplateDescr.getName(),
                                                                (FieldTemplate[]) fields.toArray( new FieldTemplate[fields.size()] ) );
    }

    private void addRule(final RuleDescr ruleDescr) {
        // this.dialect.init( ruleDescr );

        if ( ruleDescr instanceof QueryDescr ) {
            // ruleDescr.getLhs().insertDescr( 0, baseDescr );
        }

        PackageRegistry pkgRegistry = this.pkgRegistryMap.get( ruleDescr.getNamespace() );

        DialectCompiletimeRegistry ctr = pkgRegistry.getDialectCompiletimeRegistry();
        RuleBuildContext context = new RuleBuildContext( this.configuration,
                                                         ruleDescr,
                                                         ctr,
                                                         pkgRegistry.getPackage(),
                                                         ctr.getDialect( pkgRegistry.getDialect() ) );
        this.ruleBuilder.build( context );

        this.results.addAll( context.getErrors() );

        context.getDialect().addRule( context );

        if ( this.ruleBase != null ) {
            if ( pkgRegistry.getPackage().getRule( ruleDescr.getName() ) != null ) {
                this.ruleBase.removeRule( pkgRegistry.getPackage(),
                                          pkgRegistry.getPackage().getRule( ruleDescr.getName() ) );
            }
        }

        pkgRegistry.getPackage().addRule( context.getRule() );
    }

    /**
     * @return The compiled package. The package may contain errors, which you
     *         can report on by calling getErrors or printErrors. If you try to
     *         add an invalid package (or rule) to a RuleBase, you will get a
     *         runtime exception.
     * 
     * Compiled packages are serializable.
     */
    public Package getPackage() {
        PackageRegistry pkgRegistry = this.pkgRegistryMap.get( this.defaultNamespace );
        Package pkg = null;
        if ( pkgRegistry != null ) {
            pkg = pkgRegistry.getPackage();
            pkgRegistry.getPackage().getDialectRuntimeRegistry().reloadDirty();
        }
        if ( hasErrors() && pkg != null ) {
            pkg.setError( getErrors().toString() );
        }
        return pkg;
    }

    public Package[] getPackages() {
        Package[] pkgs = new Package[this.pkgRegistryMap.size()];
        int i = 0;
        String errors = null;
        if (!getErrors().isEmpty()) {
        	errors = getErrors().toString();
        }
        for ( PackageRegistry pkgRegistry : this.pkgRegistryMap.values() ) {
            Package pkg = pkgRegistry.getPackage();
            pkg.getDialectRuntimeRegistry().reloadDirty();
            if (errors != null) {
            	pkg.setError( errors );
            }
            pkgs[i++] = pkg;
        }

        return pkgs;
    }

    /**
     * Return the PackageBuilderConfiguration for this PackageBuilder session
     * 
     * @return The PackageBuilderConfiguration
     */
    public PackageBuilderConfiguration getPackageBuilderConfiguration() {
        return this.configuration;
    }

    public PackageRegistry getPackageRegistry(String name) {
        return this.pkgRegistryMap.get( name );
    }

    public Map<String, PackageRegistry> getPackageRegistry() {
        return this.pkgRegistryMap;
    }

    /**
     * Return the ClassFieldExtractorCache, this should only be used internally,
     * and is subject to change
     * 
     * @return the ClsasFieldExtractorCache
     */
    public ClassFieldAccessorCache getClassFieldExtractorCache() {
        return this.classFieldExtractorCache;
    }

    /**
     * This will return true if there were errors in the package building and
     * compiling phase
     */
    public boolean hasErrors() {
        return this.results.size() > 0;
    }

    /**
     * @return A list of Error objects that resulted from building and compiling
     *         the package.
     */
    public PackageBuilderErrors getErrors() {
        return new PackageBuilderErrors( (DroolsError[]) this.results.toArray( new DroolsError[this.results.size()] ) );
    }

    /**
     * Reset the error list. This is useful when incrementally building
     * packages. Care should be used when building this, if you clear this when
     * there were errors on items that a rule depends on (eg functions), then
     * you will get spurious errors which will not be that helpful.
     */
    protected void resetErrors() {
        this.results.clear();
    }

    public String getDefaultNamespace() {
        return this.defaultNamespace;
    }
    
    public String getDefaultDialect() {
        return this.defaultDialect;
    }
    public static class MissingPackageNameException extends IllegalArgumentException {
        private static final long serialVersionUID = 400L;

        public MissingPackageNameException(final String message) {
            super( message );
        }

    }

    public static class PackageMergeException extends IllegalArgumentException {
        private static final long serialVersionUID = 400L;

        public PackageMergeException(final String message) {
            super( message );
        }

    }

    /**
     * This is the super of the error handlers. Each error handler knows how to
     * report a compile error of its type, should it happen. This is needed, as
     * the compiling is done as one hit at the end, and we need to be able to
     * work out what rule/ast element caused the error.
     * 
     * An error handler it created for each class task that is queued to be
     * compiled. This doesn't mean an error has occurred, it just means it *may*
     * occur in the future and we need to be able to map it back to the AST
     * element that originally spawned the code to be compiled.
     */
    public abstract static class ErrorHandler {
        private final List errors  = new ArrayList();

        protected String   message;

        private boolean    inError = false;

        /** This needes to be checked if there is infact an error */
        public boolean isInError() {
            return this.inError;
        }

        public void addError(final CompilationProblem err) {
            this.errors.add( err );
            this.inError = true;
        }

        /**
         * 
         * @return A DroolsError object populated as appropriate, should the
         *         unthinkable happen and this need to be reported.
         */
        public abstract DroolsError getError();

        /**
         * We must use an error of JCI problem objects. If there are no
         * problems, null is returned. These errors are placed in the
         * DroolsError instances. Its not 1 to 1 with reported errors.
         */
        protected CompilationProblem[] collectCompilerProblems() {
            if ( this.errors.size() == 0 ) {
                return null;
            } else {
                final CompilationProblem[] list = new CompilationProblem[this.errors.size()];
                this.errors.toArray( list );
                return list;
            }
        }
    }

    public static class RuleErrorHandler extends ErrorHandler {

        private BaseDescr descr;

        private Rule      rule;

        public RuleErrorHandler(final BaseDescr ruleDescr,
                                final Rule rule,
                                final String message) {
            this.descr = ruleDescr;
            this.rule = rule;
            this.message = message;
        }

        public DroolsError getError() {
            return new RuleBuildError( this.rule,
                                       this.descr,
                                       collectCompilerProblems(),
                                       this.message );
        }

    }

    public static class ProcessErrorHandler extends ErrorHandler {

        private BaseDescr descr;

        private Process   process;

        public ProcessErrorHandler(final BaseDescr ruleDescr,
                                   final Process process,
                                   final String message) {
            this.descr = ruleDescr;
            this.process = process;
            this.message = message;
        }

        public DroolsError getError() {
            return new ProcessBuildError( this.process,
                                          this.descr,
                                          collectCompilerProblems(),
                                          this.message );
        }

    }

    /**
     * There isn't much point in reporting invoker errors, as they are no help.
     */
    public static class RuleInvokerErrorHandler extends RuleErrorHandler {

        public RuleInvokerErrorHandler(final BaseDescr ruleDescr,
                                       final Rule rule,
                                       final String message) {
            super( ruleDescr,
                   rule,
                   message );
        }
    }

    public static class ProcessInvokerErrorHandler extends ProcessErrorHandler {

        public ProcessInvokerErrorHandler(final BaseDescr processDescr,
                                          final Process process,
                                          final String message) {
            super( processDescr,
                   process,
                   message );
        }
    }

    public static class FunctionErrorHandler extends ErrorHandler {

        private FunctionDescr descr;

        public FunctionErrorHandler(final FunctionDescr functionDescr,
                                    final String message) {
            this.descr = functionDescr;
            this.message = message;
        }

        public DroolsError getError() {
            return new FunctionError( this.descr,
                                      collectCompilerProblems(),
                                      this.message );
        }

    }

    private String ucFirst(final String name) {
        return name.toUpperCase().charAt( 0 ) + name.substring( 1 );
    }
}